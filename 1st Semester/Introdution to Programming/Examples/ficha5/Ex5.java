package ficha5;

import java.util.Scanner;

public class Ex5 {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
	   // Array preços
        int price[] = {20, 30, 40, 70};
        
        // Array zonas
        String zones[] = {"1ª Plateia", "2ª Plateia", "Balcão", "Camarote"};
        
        // Array lugares disponíveis
        int availableTickets[] = {200, 100, 100 ,10};
        
        int location;
        
        do {
            for (int i = 0; i < zones.length; i++) {
                System.out.printf("%d- %s \t(%d€)\t- %3d lugares disponíveis\n", i+1 ,zones[i], price[i], availableTickets[i]);    
            }
            System.out.println("0: Sair");
        
        do {
            System.out.println("\nQual a zona para a qual quer comprar o bilhete? \n1 (1ª Plateia); 2 (2ª Plateia); 3 (Balcão); 4 (Camarote)");
            location = input.nextInt();
        } while (location < 0 || location > 4);
        
        if (location != 0) {
            System.out.println("\nQuantos bilhetes pretende comprar? ");
            int quantity = input.nextInt();
            
            if (quantity > availableTickets[location - 1]) {
                System.out.printf("\nBilhetes Esgotados\n");
            } else {
                int cost = quantity * price[location - 1];
                availableTickets[location - 1] -= quantity;
                System.out.println("\nCusto dos bilhetes:" + cost + "€\n");
            }
        }
        else {
            System.out.println("Obrigado por nos visitar!");
        }
            
    } while (location != 0);
    
    input.close();
	}

}
