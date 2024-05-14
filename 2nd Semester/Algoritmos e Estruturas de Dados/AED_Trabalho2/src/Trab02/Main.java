package Trab02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import Trab02.Hash.TabelaHash;

public class Main {

	public static void main(String[] args) {
		
		Hash hash = new Hash();
		TabelaHash tabela = hash.new TabelaHash(30001);

		Scanner scanner = new Scanner(System.in);
		int opcao;
		long inicio, fim, tempo;

		do {
			System.out.println("Menu:");
			System.out.println("1 - Ler Ficheiro");
			System.out.println("2 - Procurar Palavra");
			System.out.println("3 - Inserir Palavra");
			System.out.println("4 - Eliminar Palavra");
			System.out.println("5 - Gravar Ficheiro");
			System.out.println("0 - Sair");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				inicio = System.nanoTime();
				lerFicheiro(tabela);
				fim = System.nanoTime();
				tempo = fim - inicio;
				System.out.println("Tempo de leitura do ficheiro: " + tempo + " nanossegundos");
				break;
			case 2:
				inicio = System.nanoTime();
				System.out.print("Digite a palavra a ser procurada: ");
				String palavraProcurada = scanner.next();
				int resultadoProcura = tabela.procura(palavraProcurada);
				
				if (resultadoProcura != -1)
					System.out.println("Palavra encontrada na tabela: " + resultadoProcura);
				else
					System.out.println("Palavra não encontrada na tabela.");
				
				fim = System.nanoTime();
				tempo = fim - inicio;
				System.out.println("Tempo de procura da palavra: " + tempo + " nanossegundos");
				break;
			case 3:
				inicio = System.nanoTime();
				System.out.print("Digite a palavra a ser inserida: ");
				String palavraInserir = scanner.next();
				boolean inserida = tabela.inserir(palavraInserir);
				
				if (inserida)
					System.out.println("Palavra inserida com sucesso.");
				else
					System.out.println("Palavra já existe na tabela.");
				
				fim = System.nanoTime();
				tempo = fim - inicio;
				System.out.println("Tempo de inserção da palavra: " + tempo + " nanossegundos");
				break;
			case 4:
				inicio = System.nanoTime();
				System.out.print("Digite a palavra a ser eliminada: ");
				String palavraEliminar = scanner.next();
				boolean eliminada = tabela.elimina(tabela.hashing(palavraEliminar));
				
				if (eliminada)
					System.out.println("Palavra eliminada com sucesso.");
				else
					System.out.println("Palavra não encontrada na tabela.");
				
				fim = System.nanoTime();
				tempo = fim - inicio;
				System.out.println("Tempo de eliminação da palavra: " + tempo + " nanossegundos");
				break;
			case 5:
				inicio = System.nanoTime();
				
				/* ADICIONEI DAQUI */

					// LIMPAR O FICHEIRO E DEPOIS FAZER UM CICLO PARA IR ADICIONANDO TODAS AS PALAVRAS DA TABELA HASH PARA O FICHEIRO COM O PUSH() DE CADA UMA DAS PALAVRAS
					
					PrintWriter ficheiro = null;
				
					try {
						ficheiro = new PrintWriter(new FileWriter("palavras.txt", true));
					} catch (Exception e) {
						e.printStackTrace();
						System.exit(0);
					}
				
					gravarParaFicheiro(ficheiro,"\nESTA É A PALAVRA\n");
					ficheiro.close();
				
					fim = System.nanoTime();
					tempo = fim - inicio;
					System.out.println("Tempo de gravação do ficheiro: " + tempo + " nanossegundos\n");
					break;
					
				/* ATÉ AQUI */
				
			case 0:
				System.out.println("Programa encerrado.");
				break;
			default:
				System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
			}
		} while (opcao != 0);

		scanner.close();
	}

	private static void lerFicheiro(TabelaHash tabela) {
		File file = new File("palavras.txt");
		Scanner leitor = null;
		try {
			leitor = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Ficheiro não encontrado");
			e.printStackTrace();
		}
		if (leitor != null) {
			while (leitor.hasNextLine()) {
				String palavra = leitor.nextLine();
				tabela.inserir(palavra);
			}
			leitor.close();
		}
	}
	
	private static void gravarParaFicheiro(PrintWriter pw,String msg) {
		System.out.print(msg);
		pw.print(msg);
		pw.flush();
		}
}
