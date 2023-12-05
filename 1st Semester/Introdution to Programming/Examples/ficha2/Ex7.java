package Ficha2;

public class Ex7 {

	public static void main(String[] args) {
		int hoursInSeconds = 19540;
		int secondsInHours = 0;
		int secondsInMinutes = 0;
		int seconds = 0;
		
		secondsInHours = hoursInSeconds / 3600;
		secondsInMinutes = hoursInSeconds % 3600 / 60;
		seconds = hoursInSeconds % 60;
		
		System.out.print(hoursInSeconds + " = " + secondsInHours + "hh:" + secondsInMinutes + "mm:" + seconds + "ss");

	}

}