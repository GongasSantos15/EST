package myShoppingList;
import java.util.Scanner;

public class MyShoppingList2 {

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
                case 'e':
                case 'E':
                    System.out.println("\nA Editar lista:");
                    editarLista = input.next().charAt(0);
        			input.nextLine();
                    // Handle editing logic here
        			
        			switch(editarLista) {
        			
        				case 'i': case 'I':
        					System.out.println("Inserir item no fim da lista\n ");
        				break;
        				
        				case 'p': case 'P':
        					// bloco para novo menu
        					break;
        				
            			case 'a': case 'A':
        					// bloco para novo menu
        					break;
        				
            			case 'n': case 'N':
        					// bloco para novo menu
        					break;
        				
            			case 'd': case 'D':
        					// bloco para novo menu
        					break;
        				
            			case 'l': case 'L':
        					// bloco para novo menu
        					break;
        				
            			case 'v': case 'V':
        					// bloco para novo menu
        					break;
        			}
        			break;

                case 'f':
                case 'F':
                    System.out.println("Fazer Compras");
                    fazerCompras = input.next().charAt(0);
        			input.nextLine();
                    // Handle shopping logic here
                    break;

                case 'c':
                case 'C':
                    System.out.println("Fazer Contas");
                    fazerContas = input.next().charAt(0);
        			input.nextLine();
                    // Handle accounting logic here
                    break;
                    
                case 's': case 'S':
                	 System.out.println("Obrigado por usar a nossa lista de compras!");
                	 break;

                default:
                    System.out.println("Opção inválida\n");
            }

        } while (opcaoPrincipal != 's' && opcaoPrincipal != 'S');
    }
}
