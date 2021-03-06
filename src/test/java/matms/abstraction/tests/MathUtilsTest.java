package matms.abstraction.tests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.junit.jupiter.api.Test;

import matms.abstraction.MathUtils;

public class MathUtilsTest {

	@Test
	public void factCalculatorTest() {
		assertTrue(MathUtils.getInstance().factCalculator(BigInteger.ZERO) == BigInteger.ONE);
		assertTrue(MathUtils.getInstance().factCalculator(BigInteger.ONE) == BigInteger.ONE);
		assertTrue(MathUtils.getInstance().factCalculator(BigInteger.valueOf(5)).compareTo(BigInteger.valueOf(120)) == 0);
		assertTrue(MathUtils.getInstance().factCalculator(BigInteger.valueOf(99)).compareTo(new BigInteger("933262154439441526816992388562667004907159682643816214685929638952175999932299156089414639761565182862536979208272237582511852109168640000000000000000000000")) == 0);
	}
	
	@Test
	public void factCalculatorNegativeTest() {
		assertThrows(IllegalArgumentException.class, () -> MathUtils.getInstance().factCalculator(BigInteger.valueOf(-1)));
	}
	
	@Test
	public void binomialCoefficientTest() {
		assertTrue(MathUtils.getInstance().binomialCoefficient(BigInteger.valueOf(50), BigInteger.ZERO).compareTo(BigInteger.ONE) == 0);
		assertTrue(MathUtils.getInstance().binomialCoefficient(BigInteger.valueOf(100), BigInteger.valueOf(100)).compareTo(BigInteger.ONE) == 0);
		assertTrue(MathUtils.getInstance().binomialCoefficient(BigInteger.valueOf(20), BigInteger.valueOf(5)).compareTo(MathUtils.getInstance().binomialCoefficient(BigInteger.valueOf(20), BigInteger.valueOf(15))) == 0);
		assertTrue(BigInteger.valueOf(10).multiply(MathUtils.getInstance().binomialCoefficient(BigInteger.valueOf(15), BigInteger.valueOf(10))).compareTo(BigInteger.valueOf(15).multiply(MathUtils.getInstance().binomialCoefficient(BigInteger.valueOf(14), BigInteger.valueOf(9)))) == 0);
	}
	
	@Test
	public void binomialCoefficientNegativeTest() {
		assertThrows(IllegalArgumentException.class, () -> MathUtils.getInstance().binomialCoefficient(BigInteger.valueOf(-1), BigInteger.valueOf(0)));
		assertThrows(IllegalArgumentException.class, () -> MathUtils.getInstance().binomialCoefficient(BigInteger.valueOf(0), BigInteger.valueOf(-1)));
	}
	
	@Test
	public void binomialCoefficientSmallerAndGreaterThanTest() {
		assertThrows(IllegalArgumentException.class, () -> MathUtils.getInstance().binomialCoefficient(BigInteger.valueOf(2), BigInteger.valueOf(10)));
	}
	
	@Test
	public void customLogTest() {
		DecimalFormat df = new DecimalFormat("#.####");
		df.setRoundingMode(RoundingMode.CEILING);
		
		assertTrue(MathUtils.getInstance().customLog(2, 128) == MathUtils.getInstance().customLog(2, 4) + MathUtils.getInstance().customLog(2, 32));
		assertTrue(MathUtils.getInstance().customLog(2, 2) == MathUtils.getInstance().customLog(2, 8) - MathUtils.getInstance().customLog(2, 4));
		assertTrue(MathUtils.getInstance().customLog(5, 390625) == 4 * MathUtils.getInstance().customLog(5, 25));
		assertTrue(df.format(MathUtils.getInstance().customLog(10, 8)).equals(df.format(MathUtils.getInstance().customLog(2, 8) / MathUtils.getInstance().customLog(2, 10))));
	}
}
