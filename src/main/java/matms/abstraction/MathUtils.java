package matms.abstraction;

import java.math.BigInteger;

public class MathUtils {

	public static BigInteger factCalculator(BigInteger n) {
		if (n.intValue() < 0) throw new IllegalArgumentException();
		if (n.intValue() == 0) return BigInteger.ONE;
		BigInteger fact = BigInteger.ONE;
		BigInteger i = BigInteger.TWO;
		while (i.intValue() <= n.intValue()) {
			fact = fact.multiply(i);
			i = i.add(BigInteger.ONE);
		}
		return fact;
	}
	
	public static BigInteger binomialCoefficient(BigInteger n, BigInteger k) {
		if (k.intValue() < 0 || n.intValue() < 0 || k.intValue() > n.intValue()) throw new IllegalArgumentException();
		BigInteger x = factCalculator(k).multiply(factCalculator(n.subtract(k)));
		return factCalculator(n).divide(x);
	}
	
	public static double customLog(int base, int logNumber) {
		return Math.log(logNumber) / Math.log(base);
	}
	
}
