// Trabalho realizado por:
	// Gonçalo Santos, nº 20231487, LEI, Turma 2
	// Martim Martins, nº 20230327, LEI, Turma 2

package myShoppingList;
import java.util.Scanner;

public class MyShoppingList {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// Variáveis e arrays dados no ínicio
		int tamMax = 100;
		String[] nome = new String[tamMax];
		double[] quanto = new double[tamMax];
		double[] preco = new double[tamMax];
		boolean[] feito = new boolean[tamMax];
		int nItens = 0;

		// Variáveis char switch(case)
		char opcaoPrincipal;
		char editarLista = 0;
		char fazerCompras = 0;
		char fazerContas = 0;
		char listar = 0;
		
		// Variáveis auxiliares

		String novoItem = "";
		double quantidadeItem = 0;
		double precoItem = 0;
		int n = 0;
		int itensComprados = 0;
		int produtoComprado = 0;
		double soma = 0;
		double media = 0;

		do {

			// Apresenta menu
			System.out.println("Bem vindo à nossa lista de compras. O que deseja fazer?");
			System.out.println("(E)ditar lista");

			System.out.println("(F)azer compras");
			System.out.println("(C)ontas");
			System.out.println("(S)air \n");

			System.out.print("Selecione a letra e, f, c, ou s: ");

			// Input para o user escolher qual opção geral quer (editar lista, fazer compras/contas ou sair)
			opcaoPrincipal = input.next().charAt(0);
			input.nextLine();

			// INÍCIO DO SWITCH GERAL 
			switch (opcaoPrincipal) { 

				// CASO EDITAR LISTA
				case 'e': case 'E':

					// Print para o user saber o que tem de escolher neste submenu
					System.out.println("\n(I)nserir item no fim da lista");
					System.out.println("Inserir item na (p)osição n da lista");
					System.out.println("(A)pagar último item inserido na lista");
					System.out.println("Apagar item na posição (n) da lista");
					System.out.println("Apagar itens da posição (m) à n da lista");
					System.out.println("(L)istar todos os itens");
					System.out.println("(V)oltar");

					System.out.print("\nSelecione a letra i, p, a, n, m, l ou v: ");

					// Input para o user, dentro do switch editar lista, qual opção escolher
					editarLista = input.next().charAt(0);
					input.nextLine();

					// SWITCH (EDITAR LISTA)
					switch(editarLista) {

						// INSERIR ITEM NO FIM DA LISTA
						case 'i': case 'I':
							System.out.print("\nA Inserir item no fim da lista...\n");

							// Perguntar ao user qual item pretende adicionar na lista
							System.out.print("Escreva o item que pretende adicionar: ");
							novoItem = input.next();
							input.nextLine();
						
							// Perguntar ao user qual a quantidade respetiva desse item
							System.out.printf("Qual a quantidade de %s que pretende: ", novoItem);
							quantidadeItem = input.nextDouble();
							input.nextLine();
						
							// Perguntar ao user qual o preço desse mesmo item
							System.out.printf("Por favor introduza o preço (0,00€) do produto: ");
							precoItem = input.nextDouble();
							input.nextLine();
						
							System.out.print("\nItem adicionado!\n");
							System.out.println();

							// Verificar se o array está cheio antes de acrescentar um novo item
							// Se o número de itens na lista e menor que o tamanho máximo da lista (100) executar o seguinte código:
							if (nItens < tamMax) {
								// Adiciona o novo item no fim do array, assim como a quantidade e o preço introduzidos e aumenta
								// a variável nItens, pois um item foi adicionado à lista
								nome[nItens] = novoItem;
								quanto[nItens] = quantidadeItem;
								preco[nItens] = precoItem;
								nItens++;
						
							// // Se o número de itens na lista e maior que o tamanho máximo da lista (100) dar uma mensagem ao user
							} else {
								System.out.println("A lista está cheia. Não é possível adicionar mais itens.");
							}
							break;

						// INSERIR ITEM NA POSIÇÃO N DA LISTA
						case 'p': case 'P':
							System.out.println("\nA inserir item na posição n da lista...");

							System.out.print("Escreva em que posição do array pretende adicionar o item: ");
							n = input.nextInt();
							input.nextLine();

							System.out.print("Escreva o nome do item que pretende adicionar: ");
							novoItem = input.next();
							input.nextLine();

							System.out.printf("Qual a quantidade de %s que pretende: ", novoItem);
							quantidadeItem = input.nextDouble();
							input.nextLine();

							System.out.printf("Por favor introduza o preço (0,00€) do produto: ");
							precoItem = input.nextDouble();
							input.nextLine();

							System.out.print("\nItem adicionado!\n");
							
							if (n >= 0 && n <= nItens) {
								// Verificar se o array está cheio antes de acrescentar um novo item
								if (nItens < tamMax) {
									// Adiciona o novo item no fim do array e incrementa nItens
									for (int i = nItens; i > n; i--) {
											nome[i] = nome[i-1];
											quanto[i] = quanto[i-1];
											preco[i] = preco[i-1];
									}
									nome[n] = novoItem;
									quanto[n] = quantidadeItem;
									preco[n] = precoItem;

									nItens++;
								} else {
									System.out.println("A lista está cheia. Não é possível adicionar mais itens.");
								}
							} else {
								System.out.println("\nErro! Posição inválida\n");
							}
							break;
					
						// APAGAR ÚLTIMO ITEM INSERIDO NA LISTA
						case 'a': case 'A':
							System.out.println("\nA apagar último item inserido na lista!");

							// Colocar a string correspondente vazia e decrementar nItens
							if(nItens > 0) {
								nItens--;
								nome[nItens] = "";
								System.out.println("Item apagado com sucesso!\n");
							} else {
								System.out.println("A lista está vazia. Não é possível apagar itens!");
							}
							break;

						// APAGAR ITEM NA POSIÇÃO N DA LISTA
						case 'n': case 'N':
							System.out.println("A Apagar item na posição n da lista\n");

							if(nItens > 0) {
								// Colocar a string correspondente ao input n vazia, colocar a string vazia uma posição acima
								// para não ficar espaços vazios na lista e decrementar nItens
								nItens--;
								System.out.print("Escreva o número do item que pretende eliminar: ");

								n = input.nextInt();
								input.nextLine(); 

								nome[n] = "";

								if (n > 0) {
									nome[n] = nome[n+1];
								}

								System.out.println("Item apagado com sucesso!\n");
							} else {
								System.out.println("A lista está vazia. Não é possível apagar itens!");
							}
							break;

						// APAGAR ITEM DA POSIÇÃO M À N DA LISTA
						case 'm': case 'M':
							System.out.println("A apagar itens da posição m à n da lista...\n");

							if (nItens > 0) {
								System.out.print("\nIndique o primeiro número: ");
								int a = input.nextInt();
								System.out.print("Indique o segundo número: ");
								int b = input.nextInt();

								// Colocar as strings correspondentes vazias de a a b
								if (a >= 0 && a <= b && b < nItens) {
									
									// Shift
									for (int i = a; i + (b - a + 1) < nItens; i++) {
										nome[i] = nome[i + (b - a + 1)];
									}

									// Apagar as posições indicadas pelo utilizador
									for (int i = nItens - (b - a + 1); i < nItens; i++) {
										nome[i] = "";
									}

									// Atualizar a variável nItens
									nItens -= (b - a + 1);
									System.out.println("\nItens apagados com sucesso!\n");
								} else {
									System.out.println("\nNão é possível apagar itens!\n");
								}
							} else {
								System.out.println("A lista está vazia. Não é possível apagar itens!");
							}
							break;

						// LISTAR TODOS OS ITENS
						case 'l': case 'L':
							if (nItens > 0) {
								System.out.println("Listar todos os itens\n");
								System.out.println("\nItens na lista:");

								System.out.printf("\n%12s \t\t%16s \t\t%12s \t\t\t%10s", "Item", "Quantidade", "Preço", "Comprado\n");

								for (int i = 0; i < nItens; i++) {
										System.out.printf("%d: \t%-12s \t\t%8.3f \t\t\t%8.2f \t\t%2s\n", i, nome[i], quanto[i], preco[i], feito[i] ? "x" : ""); 
								}
							} else {
								System.out.println("Lista vazia!");
							}
							System.out.println();
							break;

						case 'v': case 'V':
							System.out.println("Voltar\n");
							break;
						}
						break;
				
				// CASO FAZER COMPRAS
				case 'f': case 'F':
					
				// Print para o user saber o que tem de escolher neste submenu
					System.out.println("\n(M)arcar primeiro item por comprar");
					System.out.println("(D)esmarcar primeiro item comprado");
					System.out.println("Trocar estado por (n)ome");
					System.out.println("Trocar estado por (p)osição");
					System.out.println("(L)istar");
					System.out.println("(V)oltar");

					System.out.print("\nSelecione a letra m, d, n, p, l ou v: ");
					
					// Input que leva aos vários submenus deste switch
					fazerCompras = input.next().charAt(0);
					input.nextLine();

					// SWITCH (FAZER COMPRAS)
					switch(fazerCompras) {

					// MARCAR PRIMEIRO ITEM POR COMPRAR
					case 'm': case 'M':
						System.out.println("\nA Marcar primeiro item por comprar...\n");

						System.out.println("\nItens na lista:");

						System.out.printf("\n%13s", "Comprado\n");

						// Se o utilizador marcar o item como comprado, o feito[i] passa a true e incrementa a variável produtoComprado
						// para seguir para o próximo item da lista
						// Depois de efetuar esta ação, a variável itensComprados é incrementada para indicar que da lista toda
						// o user comprou mais um item da mesma

						if (nItens > 0) {
								for (int i = 0; i < nItens; i++) {
									if (i == itensComprados) {
										feito[i] = true;
										produtoComprado++;
									} else if (i > itensComprados){
										feito[i] = false;
									}
									System.out.printf("%d:\t%-12s\n", i, feito[i] ? "x" : "");
									
								}
								itensComprados++;
								System.out.println();
						}
						break;
					
					// DESMARCAR PRIMEIRO ITEM COMPRADO
					case 'd': case 'D':
						System.out.println("\nA desmarcar primeiro item comprado...\n");

						System.out.println("\nItens na lista:");

						System.out.printf("\n%13s", "Comprado\n");

						// Se o utilizador desmarcar o item comprado, o feito[i] passa a false e decrementa a variável produtoComprado
						// para seguir para o próximo item da lista
						// Depois de efetuar esta ação, a variável itensComprados é decrementada para indicar que da lista toda
						// o user comprou menos um item da mesma
						if (nItens > 0) {
								for (int i = 0; i < nItens; i++) {
									if (i == itensComprados-1) {
										feito[i] = false;
										produtoComprado++;
									} else if (i < itensComprados){
										feito[i] = true	;
									}
									System.out.printf("%d:\t%-12s\n", i, feito[i] ? "x" : "");
									
								}
								itensComprados--;
								System.out.println();
						}
						break;
					
					// TROCAR ESTADO POR NOME
					case 'n': case 'N':
						System.out.println("A trocar estado por nome...\n");
						System.out.print("Introduza o nome para o qual quer mudar o estado do item: ");
						
						novoItem = input.next();
						input.nextLine();
						
						System.out.println("Item trocado com sucesso!\n");
						
						// Fazer um for para comparar de caractere a caractere de nome[i], criar uma variável boolean para indicar
						// se o nome é igual ou não ao que o user introduziu
						// Se for igual passar feito[i] ao estado contrário (se true, false e vice-versa) e incrementar ou 
						// decrementar a variável itensComprados, consoante o estado do feito[i]
						if (nItens > 0) {
							for (int i = 0; i < nItens; i++) {
								boolean nomesIgual = true; 								// .equals	
								
								if (feito[i] && nome[i].length() == novoItem.length()) {
									for (int j = 0; j < nome[i].length(); j++) {
										if (nome[i].charAt(j) != novoItem.charAt(j)) {
											nomesIgual = false;
											break;
										}
									}
									if (nomesIgual) {
										feito[i] = !feito[i];
									}
								} else if (!feito[i] && nome[i].length() == novoItem.length()) {
									for (int j = 0; j < nome[i].length(); j++) {
										if (nome[i].charAt(j) != novoItem.charAt(j)) {
											nomesIgual = false;
											break;
										}
									}
									if (nomesIgual) {
										feito[i] = true;
									}
								}
								System.out.printf("%d:\t%-12s\n", i, feito[i] ? "x" : "");
							}
							System.out.println();
					break;
					}

					// TROCAR ESTADO POR POSIÇÃO
					case 'p': case 'P':
						System.out.println("\nA trocar estado por posição...\n");
						
						System.out.print("Introduza a posição para o qual quer mudar o estado do item: ");
						
						int posicaoNovoItem = input.nextInt();
						input.nextLine();
						
						// o user introduz um número que é a posição da lista, consoante o que o user introduzir, passar o estado 
						// feito[i] ao contrário
						// FALTA FAZER A VARIAVEL ITENS COMPRADOS
						if (nItens > 0) {
							if (posicaoNovoItem >= 0 && posicaoNovoItem < nItens) {
								feito[posicaoNovoItem] = !feito[posicaoNovoItem];
								
								System.out.println("Item trocado com sucesso!\n");
								
								System.out.printf("\n%13s", "Comprado\n");
								
								for (int i = 0; i < nItens; i++) {
									System.out.printf("%d:\t%-12s\n", i, feito[i] ? "x" : "");
								}
								System.out.println();
							} else {
								System.out.println("Posição inválida!\n");
							}	
						}	
						
					break;

					// LISTAR TODOS OS ITENS
					case 'l': case 'L':
						System.out.println("\nListar (t)odos os itens");
						System.out.println("Listar itens (c)omprados");
						System.out.println("Listar itens (p)or comprar");

						System.out.print("\nSelecione a letra t, c ou p: ");

						listar = input.next().charAt(0);
						input.nextLine();

						switch(listar) {
						
						// LISTAR TODOS OS ITENS
						case 't': case 'T':
							System.out.println("\nLista de compras atualizada:\n");
							System.out.printf("\n%12s \t\t%16s \t\t%12s \t\t\t%10s", "Item", "Quantidade", "Preço", "Comprado\n");

							// Listar todos os itens dentro da lista
							for (int i = 0; i < nItens; i++) {
								System.out.printf("%d: \t%-12s \t\t%8.3f \t\t\t%8.2f \t\t%2s\n", i, nome[i], quanto[i], preco[i], feito[i] ? "x" : ""); 
							}
							System.out.println();
							break;
						
						// LISTAR ITENS COMPRADOS
						case 'c': case 'C':
							System.out.println("\nLista de compras atualizada:\n");
							System.out.printf("\n%12s \t\t%16s \t\t%12s \t\t\t%10s", "Item", "Quantidade", "Preço", "Comprado\n");

							// Listar apenas os itens comprados na lista
							for (int i = 0; i < nItens; i++) {
								if (feito[i]) {
									System.out.printf("%d: \t%-12s \t\t%8.3f \t\t\t%8.2f \t\t%2s\n", i, nome[i], quanto[i], preco[i], feito[i] ? "x" : ""); 
								}
							}
							System.out.println();
							break;
							
						// LISTAR ITENS POR COMPRAR
						case 'p': case 'P':
							System.out.println("\nLista de compras atualizada:\n");
							System.out.printf("\n%12s \t\t%16s \t\t%12s \t\t\t%10s", "Item", "Quantidade", "Preço", "Comprado\n");

							// Listar apenas os itens por comprar da lista
							for (int i = 0; i < nItens; i++) {
								if (!feito[i]) {
									System.out.printf("%d: \t%-12s \t\t%8.3f \t\t\t%8.2f \t\t%2s\n", i, nome[i], quanto[i], preco[i], feito[i] ? "x" : ""); 
								}
							}
							System.out.println();

							break;
						}
						break;
					}
 
					case 'v': case 'V':
					break;

				//CASO FAZER CONTAS
				case 'c': case 'C':
					System.out.println("\nQuanto custa a (l)ista?");
					System.out.println("Quanto já (g)astei");
					System.out.println("Quanto custa o que (f)alta comprar?");
					System.out.println("Qual o preço (m)édio por item?\n");

					System.out.print("Selecione a letra l, g, f ou m: ");

					fazerContas = input.next().charAt(0);
					input.nextLine();

					// SWITCH (FAZER COMPRAS)
					switch(fazerContas) {

					// QUANTO CUSTA A LISTA
					case 'l': case 'L': 
						System.out.println("\nQuanto custa a lista?\n");

						if (nItens > 0) {
							soma = 0;
							for (int i = 0; i < nItens; i++) {
								soma += quanto[i] * preco[i];
							}
							System.out.printf("A lista custa %.2f€!\n", soma);
							System.out.println("");
						} else {
							System.out.println("\nA lista custa 0€!");
						}
						break;

					// QUANTO JÁ GASTEI
					case 'g': case 'G':
						System.out.println("\nQuanto já gastei?\n");

						if (nItens > 0) {	
							soma = 0;
							for (int i = 0; i < nItens; i++) {
								if (feito[i]) { 					// if (feito[i] == true)
									soma += quanto[i] * preco[i];
								}
							}
							System.out.printf("Já gastei %.2f€!\n", soma);
							System.out.println("");
						} else {
							System.out.println("\nA lista está vazia! Gastou 0€");
						}
						break;

					// QUANTO CUSTA O QUE FALTA COMPRAR
					case 'f': case 'F':
						System.out.println("\nQuanto custa o que me falta comprar?\n");

						if (nItens > 0) {	
							soma = 0;
							for (int i = 0; i < nItens; i++) {
								if (!feito[i]) { 					
									soma += quanto[i] * preco[i];
								}
							}
							System.out.printf("O que falta comprar custa %.2f€!\n", soma);
							System.out.println("");
						} else {
							System.out.println("\nA lista está vazia, por isso tem tudo comprado!");
						}

						break;

					// QUAL O PREÇO MÉDIO POR ITEM
					case 'm': case 'M':
						System.out.println("\nQual o preço médio por item?\n");

						if (nItens > 0) {	
							soma = 0;
							for (int i = 0; i < nItens; i++) {
								soma += preco[i] * quanto[i];	
								media = soma/nItens;
							}
							System.out.printf("O preço médio por item é %.2f€!\n", media);
							System.out.println("");
						} else {
							System.out.println("\nA lista está vazia! O preço médio por item é 0€");
						}

						break;
					}
					break;

				// CASO SAIR
				case 's': case 'S':
					System.out.println("Obrigado por usar a nossa lista de compras!");
					break;

				default:
					System.out.println("Opção inválida\n");
					break;
			}            
		} while (opcaoPrincipal != 's' && opcaoPrincipal != 'S');
		input.close();
	}
}
