package Ficha2;

public class Ex6 {

	public static void main(String[] args) {
		
		int hours = 2;
		int minutes = 20;
		int seconds = 40;
		int hoursInSeconds = 0;
		
		hoursInSeconds = hours * 3600 + minutes * 60 + seconds;
		
		System.out.println("2hh:20mm:40ss = " + hoursInSeconds + "s");
		
		//Alternative
		System.out.printf("%02dh%02dm%02ds = %d segundos \n", hours, minutes, seconds, hoursInSeconds);
		
	}

}
