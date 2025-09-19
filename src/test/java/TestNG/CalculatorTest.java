package TestNG;

import a1.Calculator;
import org.testng.Assert;
import org.testng.annotations.*;

public class CalculatorTest {

  Calculator calc;

  @BeforeClass
  public void setUpClass() {
    System.out.println(">>> BeforeClass: Run once before all tests");
    calc = new Calculator();
  }

  @BeforeMethod
  public void setUpMethod() {
    System.out.println("  > BeforeMethod: Run before each test");
  }

  @AfterMethod
  public void tearDownMethod() {
    System.out.println("  < AfterMethod: Run after each test");
  }

  @Test(groups = "basic")
  public void testAdd() {
    Assert.assertEquals(calc.add(2, 3), 5);
  }

  @Test(dataProvider = "subtractData", groups = "basic")
  public void testSubtract(int a, int b, int expected) {
    Assert.assertEquals(calc.subtract(a, b), expected);
  }

  @DataProvider(name = "subtractData")
  public Object[][] subtractData() {
    return new Object[][] {
      {5, 3, 2},
      {10, 7, 3},
      {0, 0, 0}
    };
  }

  @Test(groups = "advanced")
  public void testDivide() {
    Assert.assertEquals(calc.divide(10, 2), 5);
  }
}
