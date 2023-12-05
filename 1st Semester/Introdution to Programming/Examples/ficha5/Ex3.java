package ficha5;

public class Ex3 {

	public static void main(String[] args) {

		double grades[] = {12, 15.5, 14, 13.5, 8, 9, 7.5, 16, 18, 19, 20, 16.7, 11.6, 13.4, 14.6, 7.8, 9.5, 6, 17.3, 15};
		
		double max=grades[0];
		double min=grades[0];
		
		int approved = 0;
		int reproved = 0;

		for(int i=0; i < grades.length; i++) {
				if (grades[i] > max)  {
					max = grades[i];
				}
				
				if (grades[i] < min) {
					min = grades[i];
				}
				
				if (grades[i] < 9.5) {
					reproved++;
				} else {
					approved++;
				}
			
		}

		System.out.println("A melhor nota do array é: " + max);
		System.out.println("A pior nota do array é: " + min);
		System.out.println("O número de reprovados do array é: " + reproved);
		System.out.println("O número de aprovados do array é: " + approved);

	}

}
