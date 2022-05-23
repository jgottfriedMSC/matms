package matms.abstraction;

public class MathUtils {

	public static int factCalculator(int n) {
		int fact = 1;
		int i = 1;
		while (i <= n) {
			fact = fact * i;
			i++;
		}
		return fact;
	}
	
	public static double customLog(int base, int logNumber) {
		return Math.log(logNumber) / Math.log(base);
	}
	
}
