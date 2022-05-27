package matms.abstraction.tests;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import matms.abstraction.MathUtils;

public class MathUtilsSpec {

	@Test
	public void factCalculatorTest() {
		assertTrue(MathUtils.factCalculator(BigInteger.ZERO) == BigInteger.ONE);
		assertTrue(MathUtils.factCalculator(BigInteger.ONE) == BigInteger.ONE);
		assertTrue(MathUtils.factCalculator(BigInteger.valueOf(5)).compareTo(BigInteger.valueOf(120)) == 0);
		assertTrue(MathUtils.factCalculator(BigInteger.valueOf(99)).compareTo(new BigInteger("933262154439441526816992388562667004907159682643816214685929638952175999932299156089414639761565182862536979208272237582511852109168640000000000000000000000")) == 0);
	}
	
}
