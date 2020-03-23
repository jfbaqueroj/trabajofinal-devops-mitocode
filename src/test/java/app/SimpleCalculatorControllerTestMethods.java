package app;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.mitocode.calculator.SimpleCalculatorController;

public class SimpleCalculatorControllerTestMethods {
	
	SimpleCalculatorController controller;
	@Before
	public void setUp(){
		controller = new SimpleCalculatorController();
	}
//Sum
	@Test
	public void test_01_AddPositivos() {
		String result = "2 + 3 = 5";
		assertEquals(result, controller.add(2, 3));
	}
	
	@Test
	public void test_02_AddPositivoYNegativo() {
		String result = "-1 + 2 = 1";
		assertEquals(result, controller.add(-1, 2));
	}
	
	@Test
	public void test_03_AddConCero() {
		String result = "0 + 10 = 10";
		assertEquals(result, controller.add(0, 10)); 
	}
//Sub
	@Test
	public void test_04_SubPositivos() {
		String result = "2 - 3 = -1";
		assertEquals(result, controller.sub(2, 3));
	}
	
	@Test
	public void test_05_SubPositivoYNegativo() {
		String result = "-1 - 2 = -3";
		assertEquals(result, controller.sub(-1, 2));
	}
	
	@Test
	public void test_06_SubConCero() {
		String result = "10 - 0 = 10";
		assertEquals(result, controller.sub(10, 0)); 
	}
//Mul
	@Test
	public void test_07_MulPositivos() {
		String result = "3 x 3 = 9";
		assertEquals(result, controller.mul(3, 3));
	}
	
	@Test
	public void test_08_MulPositivoYNegativo() {
		String result = "-1 x 5 = -5";
		assertEquals(result, controller.mul(-1, 5));
	}
	
	@Test
	public void test_09_MulConCero() {
		String result = "10 x 0 = 0";
		assertEquals(result, controller.mul(10, 0));

	}
//Div
	@Test
	public void test_10_DivPositivos() {
		String result = "9 / 3 = 3";
		assertEquals(result, controller.div(9, 3));
	}
	
	@Test
	public void test_11_DivPositivoYNegativo() {
		String result = "-8 / 4 = -2";
		assertEquals(result, controller.div(-8, 4));
	}
	
	@Test
	public void test_12_DivConCero() {
		String result = "0 / 10 = 0";
		assertEquals(result, controller.div(0, 10));

	}
}