package myShoppingList;
import java.util.Scanner;

public class MyShoppingList {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int tamMax = 100;
        String[] nome = new String[tamMax];
        double[] quanto = new double[tamMax];
        double[] preco = new double[tamMax];
        boolean[] feito = new boolean[tamMax];
        int nItens = 0;
        
        char opcaoPrincipal;
        char editarLista = 0;
		char fazerCompras = 0;
		char fazerContas = 0;
        char listar = 0;
        String novoItem;
        int n = 0;
        int m = 0;

        do {
            
        	// Apresenta menu
            System.out.println("Bem vindo à nossa lista de compras. O que deseja fazer?");
            System.out.println("(E)ditar lista");
            System.out.println("(F)azer compras");
            System.out.println("(C)ontas");
            System.out.println("(S)air \n");
        	
            System.out.print("Selecione a letra e, f, c, ou s: ");

            opcaoPrincipal = input.next().charAt(0);
            input.nextLine();

            switch (opcaoPrincipal) { 
                case 'e': case 'E':
                    System.out.println("\n(I)nserir item no fim da lista");
                    System.out.println("Inserir item na (p)osição n da lista");
                    System.out.println("(A)pagar último item inserido na lista");
                    System.out.println("Apagar item na posição (n) da lista");
                    System.out.println("Apagar itens da posição (m) à n da lista");
                    System.out.println("(L)istar todos os itens");
                    System.out.println("(V)oltar");
                    
                    System.out.print("\nSelecione a letra i, p, a, n, m, l ou v: ");
                    
                    editarLista = input.next().charAt(0);
        			input.nextLine();
        			
        			switch(editarLista) {
        			
        				case 'i': case 'I':
        					System.out.print("\nA Inserir item no fim da lista...\n");
        					
        					System.out.print("Escreva o item que pretende adicionar: ");

                            novoItem = input.next();
                            input.nextLine();

                            System.out.print("\nItem adicionado!\n");
                            System.out.println();
    
                            // Verificar se o array está cheio antes de acrescentar um novo item
                            if (nItens < tamMax) {
                                // Adiciona o novo item no fim do array
                                nome[nItens] = novoItem;
                                nItens++;
                            } else {
                                 System.out.println("A lista está cheia. Não é possível adicionar mais itens.");
                            }
        				break;
        				
        				case 'p': case 'P':
        					System.out.println("\nA inserir item na posição n da lista...");

                            System.out.print("Escreva em que posição do array pretende adicionar o item: ");
                            
                            n = input.nextInt();
                            input.nextLine();

                            System.out.print("Escreva o número do item que pretende adicionar: ");

                            novoItem = input.next();
                            input.nextLine();

                            System.out.print("\nItem adicionado!\n");

                                // Verificar se o array está cheio antes de acrescentar um novo item
                                if (nItens < tamMax) {
                                    // Adiciona o novo item no fim do array
                                    if (n < nItens) {
                                        nome[n] = nome[n++];
                                    }
                                    nome[n] = novoItem;
                                    nItens++;
                                } else {
                                    System.out.println("A lista está cheia. Não é possível adicionar mais itens.");
                                }

        					break;
        				
            			case 'a': case 'A':
            				System.out.println("\nA apagar último item inserido na lista!");
            				if(nItens > 0) {
                                nItens--;

                                String itemAEliminar = nome[nItens];
                                nome[nItens] = ""; // ou nome[nItens] = null;
                                
                                System.out.println("Item apagado com sucesso!\n");
                            } else {
                                System.out.println("A lista está vazia. Não é possível apagar itens!");
                            }

        					break;
        				
            			case 'n': case 'N':
            				System.out.println("A Apagar item na posição n da lista...\n");
                            if(nItens > 0) {

                                System.out.print("Escreva o número do item que pretende eliminar: ");

                                n = input.nextInt();
                                input.nextLine(); //perguntar ao stor oq e isto

                                String itemAEliminar = nome[n];
                                nome[n] = ""; // ou nome[nItens] = null;

                                if (n > 0) {
                                	nome[n] = nome[n+1];
                                }

                                nItens--;
                                
                                System.out.println("Item apagado com sucesso!\n");
                            } else {
                                System.out.println("A lista está vazia. Não é possível apagar itens!");
                            }
        					break;
        				
            			case 'm': case 'M':
            				System.out.println("A Apagar itens da posição m à n da lista...\n");
            			    if (nItens > 0) {
            			        System.out.print("Escreva o número do primeiro item que pretende eliminar: ");
            			        m = input.nextInt();
            			        input.nextLine(); // perguntar ao stor oq e isto

            			        System.out.print("Escreva o número do último item até ao qual pretende eliminar: ");
            			        n = input.nextInt();
            			        input.nextLine();

            			        if (m >= 0 && n >= m && n < nItens) {
            			            
            			            for (int i = nItens - 1; i >= n; i--) {
            			                nome[i] = ""; // or nome[i] = null;
            			            }

            			            nItens -= (n - m);

            			            System.out.println("Itens apagados com sucesso!\n");
            			        } else {
            			            System.out.println("Posições inválidas. Não é possível apagar itens!");
            			        }
            			    } else {
            			        System.out.println("A lista está vazia. Não é possível apagar itens!");
            			    }
        					break;
        				
            			case 'l': case 'L':
            				System.out.println("Listar todos os itens\n");
            				// Print the updated list
                            System.out.println("\nItens na lista:");

                        System.out.printf("\n%13s", "Item\n");

                        for (int i = 0; i < nItens; i++) {
                            System.out.printf("%d:\t%-12s\n", i, nome[i]); 
                        }
                        System.out.println();
                        
                        break;
        				
            			case 'v': case 'V':
            				System.out.println("Voltar\n");
        					break;
        			}
        			break;

                case 'f': case 'F':

                    System.out.println("\n(M)arcar primeiro item por comprar");
                    System.out.println("(D)esmarcar primeiro item comprado");
                    System.out.println("Trocar estado por (n)ome");
                    System.out.println("Trocar estado por (p)osição");
                    System.out.println("(L)istar");
                    System.out.println("(V)oltar");

                    System.out.print("\nSelecione a letra m, d, n, p, l ou v: ");

                    fazerCompras = input.next().charAt(0);
        			input.nextLine();
                    
                    switch(fazerCompras) {
                        case 'm': case 'M': 
                            System.out.println("\nMarcar primeiro item por comprar\n");
                            break;

                        case 'd': case 'D':
                            System.out.println("Desmarcar primeiro item comprado\n");
                            break;

                        case 'n': case 'N':
                            System.out.println("Trocar estado por nome\n");
                            break;
                        
                        case 'p': case 'P':
                            System.out.println("Trocar estado por posição\n");
                            break;

                        case 'l': case 'L':
                            System.out.println("\nListar (t)odos os itens");
                            System.out.println("Listar itens (c)omprados");
                            System.out.println("Listar itens (p)or comprar");

                            System.out.print("\nSelecione a letra t, c ou p: ");

                            listar = input.next().charAt(0);
                            input.nextLine();

                            switch(listar) {
                                case 't': case 'T':
                                     // Print the updated list
                                        System.out.println("\nLista de compras atualizada:\n");
                                        System.out.printf("\n%13s \t\t%13s \t%13s \t%13s", "Item", "Quantidade", "Preço", "Comprado\n");
                            
                                    for (int i = 0; i < nItens; i++) {
                                     System.out.printf("%d: \t %-12s\n", i, nome[i]); 
                                    }
                                    System.out.println();
                                    
                                    break;

                                case 'c': case 'C':
                                    System.out.println("Listar itens comprados\n");
                                    break;

                                case 'p': case 'P':
                                    System.out.println("Listar itens por comprar\n");
                                    break;
                            }
                            break;

                        case 'v': case 'V':
                            System.out.println("Voltar\n");
                    }
                    break;

                case 'c': case 'C':
                    System.out.println("\nQuanto custa a (l)ista?");
                    System.out.println("Quanto já (g)astei");
                    System.out.println("Quanto custa o que (f)alta comprar?");
                    System.out.println("Qual o preço (m)édio por item?\n");

                    System.out.print("Selecione a letra l, g, f ou m: ");

                    fazerContas = input.next().charAt(0);
        			input.nextLine();
                    
                    switch(fazerContas) {
                        case 'l': case 'L': 
                            System.out.println("\nQuanto custa a lista\n");
                            break;

                        case 'g': case 'G':
                            System.out.println("\nQuanto já gastei?\n");
                            break;

                        case 'f': case 'F':
                            System.out.println("\nQuanto custa o que me falta comprar?\n");
                            break;

                        case 'm': case 'M':
                            System.out.println("\nQual o preço médio por item?\n");
                            break;
                    }
                    break;
                    
                case 's': case 'S':
                	 System.out.println("Obrigado por usar a nossa lista de compras!");
                	 break;

                default:
                    System.out.println("Opção inválida\n");
                    break;
            }            
        } while (opcaoPrincipal != 's' && opcaoPrincipal != 'S');
    }
}