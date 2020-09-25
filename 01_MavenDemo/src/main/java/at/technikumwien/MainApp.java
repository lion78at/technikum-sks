package at.technikumwien;

import java.util.Arrays;

public class MainApp {
    public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Usage: MainApp [number1] [number2] ... [numberN]");
		}
		else {
			int[] numbers = Arrays.stream(args)
				.mapToInt(Integer::parseInt)
				.toArray();
			
			Statistics statistics = new Statistics(numbers);

			System.out.printf(
				"Anzahl: %d, Summe: %d, Durchschnitt: %.2f%n",
				statistics.cnt(), statistics.sum(), statistics.avg()
			);
		}
    }
}