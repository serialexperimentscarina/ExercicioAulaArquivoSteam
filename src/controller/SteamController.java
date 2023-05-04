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
			FileWriter abrirArq = new FileWriter(arquivo);
			PrintWriter escreverArq = new PrintWriter(abrirArq);
			
			for (String linha: conteudo) {
				String[] vetLinha = linha.split(",");
				escreverArq.write(vetLinha[0] + " ; " + vetLinha[1] + System.getProperty("line.separator"));
			}
			
			escreverArq.close();
			abrirArq.close();
			
		}  else {
			throw new IOException("Caminho inválido!");
		}
		
	}
	
	private String[] pegaMedia(String ano, String mes, double media) throws Exception {
		URL url = SteamController.class.getResource("SteamCharts.csv");
		File csv = new File(url.getFile());
		StringBuffer conteudo = new StringBuffer();
		
		System.out.println(url.getPath());
		System.out.println(url.getFile());
		if(csv.exists() && csv.isFile()) {
			FileInputStream abreFluxo = new FileInputStream(csv);
			InputStreamReader leFluxo = new InputStreamReader(abreFluxo);
			BufferedReader buffer = new BufferedReader(leFluxo);
			String linha = buffer.readLine();
			
			while (linha != null) {
				String[] vetLinha = linha.split(",");
				if (ano.equals(vetLinha[1]) && mes.equals(vetLinha[2]) && media <= (Double.parseDouble(vetLinha[3]))) {
					conteudo.append(vetLinha[0] + "," + vetLinha[3] + System.getProperty("line.separator"));
				}
				linha = buffer.readLine();
			}
			buffer.close();
			leFluxo.close();
			abreFluxo.close();
		}
		if (conteudo.length() == 0) {
			JOptionPane.showMessageDialog(null, "Busca não retornou resultados");
			return new String[]{};
		}
		return conteudo.toString().split(System.getProperty("line.separator"));
		
	}
	
}
