package ca.ghandalf.tutorial.liquibase.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import ca.ghandalf.tutorial.liquibase.utils.Operation;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = { "com.fresche" })
public class OperationTest {

	@Test
	public void getMathSignPlusOperation() {
		Operation actual = Operation.getOperator("+");
		Operation expected = Operation.PLUS;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void getMathSignMinusOperation() {
		Operation actual = Operation.getOperator("-");
		Operation expected = Operation.MINUS;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void getMathSignMultiplyOperation() {
		Operation actual = Operation.getOperator("*");
		Operation expected = Operation.MULTIPLY;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void getMathSignDivideOperation() {
		Operation actual = Operation.getOperator("/");
		Operation expected = Operation.DIVIDE;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void getMathSignNonExistingOperation() {
		try {
			Operation.getOperator("ItDoesntExist");
			Assert.fail();
		} catch (IllegalArgumentException e) {
			// Expected
		}
	}

	@Test
	public void getPlus() {
		Operation actual = Operation.operationType("pluS");
		Operation expected = Operation.PLUS;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void getMinus() {
		Operation actual = Operation.operationType("MinUS");
		Operation expected = Operation.MINUS;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void getMultiply() {
		Operation actual = Operation.operationType("MuLTiPlY");
		Operation expected = Operation.MULTIPLY;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void getDivide() {
		Operation actual = Operation.operationType("DiViDe");
		Operation expected = Operation.DIVIDE;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void getNonExistingOperation() {
		try {
			Operation.operationType("ItDoesntExist");
			Assert.fail();
		} catch (IllegalArgumentException e) {
			// Expected
		}
	}

	@Test
	public void operationPlus() {
		double actual = Operation.PLUS.applyAsDouble(1.0, 2.0);
		double expected = 3.0;
		double delta = 0;
		Assert.assertEquals(expected, actual, delta);
	}

	@Test
	public void operationPlusFailure() {
		try {
			double actual = Operation.PLUS.applyAsDouble(1.0, 20.0);
			double expected = 3.0;
			double delta = 0;
			Assert.assertEquals(expected, actual, delta);
			Assert.fail(
					"Fail! Method was expected to throw an exception because 20.0 + 1.0 = 21.0 not 3.0 has expected.");
		} catch (AssertionError e) {
			// expected
		}
	}

	@Test
	public void operationMinus() {
		double actual = Operation.MINUS.applyAsDouble(1, 2);
		double expected = -1;
		double delta = 0;
		Assert.assertEquals(expected, actual, delta);
	}

	@Test
	public void operationMultiply() {
		double actual = Operation.MULTIPLY.applyAsDouble(1.0, 2.0);
		double expected = 2.0;
		double delta = 0;
		Assert.assertEquals(expected, actual, delta);
	}

	@Test
	public void operationDivide() {
		double actual = Operation.DIVIDE.applyAsDouble(1, 2);
		double expected = .5;
		double delta = 0;
		Assert.assertEquals(expected, actual, delta);
	}

	@Test
	public void whichPlus() {
		String expected = "+";
		String actual = Operation.PLUS.getWhich();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void whichMinus() {
		String expected = "-";
		String actual = Operation.MINUS.getWhich();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void whichMultiply() {
		String expected = "*";
		String actual = Operation.MULTIPLY.getWhich();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void whichDivide() {
		String expected = "/";
		String actual = Operation.DIVIDE.getWhich();
		Assert.assertEquals(expected, actual);
	}

//	@Test
//	public void getAppropriateOperation() {
//		String expected = "+";
//		String actual = Operation.PLUS.mapper("pLuS");
//		Assert.assertEquals(expected, actual);
//
//		expected = "-";
//		actual = Operation.MINUS.mapper("MinUs");
//		Assert.assertEquals(expected, actual);
//
//		expected = "*";
//		actual = Operation.MULTIPLY.mapper("MuLtiPLY");
//		Assert.assertEquals(expected, actual);
//
//		expected = "/";
//		actual = Operation.DIVIDE.mapper("DiVide");
//		Assert.assertEquals(expected, actual);
//
//		expected = "The operation must be one of [plus, minus, multiply, divide]";
//		actual = Operation.DIVIDE.mapper("NotAndOperationAtAll");
//		Assert.assertEquals(expected, actual);
//	}
}
