package ficha4;

public class Ex2 {

	public static void main(String[] args) {
		
		System.out.println("Aqui está a tabela ASCII!");
		
		for (int i=0; i < 256; i++) {
			System.out.printf("\nDecimal: %03d -> Char: %c" , i, i);
		}

	}

}
