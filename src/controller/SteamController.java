package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

import javax.swing.JOptionPane;

public class SteamController {
	
	public SteamController() {
		super();
	}

	public void imprimeMedia (String ano, String mes, double media) throws Exception {
		String[] conteudo = pegaMedia(ano, mes, media);
		for (String linha: conteudo) {
			String[] vetLinha = linha.split(",");
			System.out.println("( " + vetLinha[0] + " | " + vetLinha[1] + " )");
		}
	}
	
	public void exportaMedia (String ano, String mes, double media, String path, String nome) throws Exception {
		File dir = new File(path);
		
		if (dir.exists() && dir.isDirectory()) {
			String[] conteudo = pegaMedia(ano, mes, media);
			File arquivo = new File(path, (nome + ".csv"));
			
			StringBuffer buffer = new StringBuffer();
			FileWriter fWriter = new FileWriter(arquivo);
			PrintWriter pWriter = new PrintWriter(fWriter);
			
			for (String linha: conteudo) {
				String[] vetLinha = linha.split(",");
				buffer.append(vetLinha[0] + " ; " + vetLinha[1] + System.getProperty("line.separator"));
			}
			
			pWriter.write(buffer.toString());
			pWriter.flush();
			pWriter.close();
			fWriter.close();
			
		}  else {
			throw new IOException("Caminho inválido!");
		}
		
	}
	
	private String[] pegaMedia(String ano, String mes, double media) throws Exception {
		URL url = SteamController.class.getResource("SteamCharts.csv");
		File csv = new File(url.getFile());
		StringBuffer conteudo = new StringBuffer();
		
		if(csv.exists() && csv.isFile()) {
			FileInputStream fInStr = new FileInputStream(csv);
			InputStreamReader InStrReader = new InputStreamReader(fInStr);
			BufferedReader bufferReader = new BufferedReader(InStrReader);
			String linha = bufferReader.readLine();
			
			while (linha != null) {
				String[] vetLinha = linha.split(",");
				if (ano.equals(vetLinha[1]) && mes.equals(vetLinha[2]) && media <= (Double.parseDouble(vetLinha[3]))) {
					conteudo.append(vetLinha[0] + "," + vetLinha[3] + System.getProperty("line.separator"));
				}
				linha = bufferReader.readLine();
			}
			bufferReader.close();
			InStrReader.close();
			fInStr.close();
		}
		if (conteudo.length() == 0) {
			JOptionPane.showMessageDialog(null, "Busca não retornou resultados");
			return new String[]{};
		}
		return conteudo.toString().split(System.getProperty("line.separator"));
		
	}
	
}
