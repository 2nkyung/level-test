package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalcTest {

	Calc c = new Calc();
	int num1 = 3;
	int num2 = 5;
	
	@Test
	public void divisionTest() {
		assertEquals(num1/num2, c.division(num1, num2));
	}
}
