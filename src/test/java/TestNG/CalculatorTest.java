package TestNG;

import a1.Calculator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CalculatorTest {

  Calculator calc = new Calculator();

  @Test
  public void testAdd() {
    Assert.assertEquals(calc.add(2, 3), 5);
  }

  @Test
  public void testSubtract() {
    Assert.assertEquals(calc.subtract(5, 3), 2);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testDivideByZero() {
    calc.divide(10, 0);
  }
}
