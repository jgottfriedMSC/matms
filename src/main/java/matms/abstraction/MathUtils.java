package matms.abstraction;

import java.math.BigInteger;

public class MathUtils {

	public static BigInteger factCalculator(BigInteger n) {
		BigInteger fact = BigInteger.ONE;
		BigInteger i = BigInteger.TWO;
		while (i.intValue() <= n.intValue()) {
			fact = fact.multiply(i);
			i = i.add(BigInteger.ONE);
		}
		return fact;
	}
	
	public static BigInteger binomialCoefficient(BigInteger n, BigInteger k) {
		BigInteger x = factCalculator(n.subtract(k)).multiply(k);
		return factCalculator(n).divide(x);
	}
	
	public static double customLog(int base, int logNumber) {
		return Math.log(logNumber) / Math.log(base);
	}
	
}
