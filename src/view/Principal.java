package view;

import javax.swing.JOptionPane;

import controller.SteamController;

public class Principal {

	public static void main(String[] args) {

		SteamController steam = new SteamController();
		String opc, ano, mes, caminho, nome;
		double media;
		
		try {
			do {
				opc = JOptionPane.showInputDialog("Menu : \n1 - Imprimir média de jogadores; \n2 - Exportar média de jogadores; \n3 - Encerrar");
				if (opc == null) {
					JOptionPane.showMessageDialog(null, "Encerrando.");
					System.exit(0);;
				} 
				switch (opc) {
					case "1":
						ano = JOptionPane.showInputDialog("Digite o ano: ");
						mes = JOptionPane.showInputDialog("Digite o mês: ");
						try {
							media = Double.parseDouble(JOptionPane.showInputDialog("Digite a média: "));
						} catch (NumberFormatException e) {
							media = 0;
						}
						steam.imprimeMedia(ano, mes, media);
						break;
					case "2":
						ano = JOptionPane.showInputDialog("Digite o ano: ");
						mes = JOptionPane.showInputDialog("Digite o mês: ");
						try {
							media = Double.parseDouble(JOptionPane.showInputDialog("Digite a média: "));
						} catch (NumberFormatException e) {
							media = 0;
						}
						caminho = JOptionPane.showInputDialog("Digite o caminho do arquivo: ");
						nome = JOptionPane.showInputDialog("Digite o nome do arquivo: ");
						steam.exportaMedia(ano, mes, media, caminho, nome);
						break;
					case "3":
						JOptionPane.showMessageDialog(null, "Encerrando execução.");
						break;
					default: 
						JOptionPane.showMessageDialog(null, "Op��o inv�lida!");
						break;
					}
			} while (!opc.equals("3"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro, encerrando execução.");
			e.printStackTrace();
		}
		
	}

}
