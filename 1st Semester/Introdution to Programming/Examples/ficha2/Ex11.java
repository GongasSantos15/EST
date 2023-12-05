package Ficha2;

public class Ex11 {

	public static void main(String[] args) {
		double price = 50;
		int discountInPercentage = 50;
		double discount = discountInPercentage * 0.01;
		double finalPrice = price * discount;
		
		String priceLine = "Preço:";
		String discountInPercentageLine = "% Desconto:";
		String finalPriceLine = "Preço Final:";
		String discountLine = "Desconto:";
		
		System.out.printf("%-12s %8.2f€ \n", priceLine, price);
		System.out.printf("%-12s %5d%% \n", discountInPercentageLine, discountInPercentage);
		System.out.printf("%-12s %8.2f€ \n", finalPriceLine, finalPrice);
		System.out.printf("%-12s %8.2f€ \n", discountLine, discount);
	}

}
