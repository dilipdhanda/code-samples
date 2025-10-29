package com.c0.intellib;
import java.util.*;

public class equals_hashCode_Override {
  // Make it static so we can use it from static main
  // Static inner class: Allows use from main() without needing an outer instance.
  static final class Employee {
    private final int empID;
    private final int depID;
    Employee(int empID, int depID) {
      this.empID = empID;
      this.depID = depID;
    }
    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof Employee) )
        return false;
      if (obj == this)
        return true;
      return this.empID == ((Employee)obj).empID
          && this.depID == ((Employee)obj).depID;
    }
    @Override
    public int hashCode() {
      return Objects.hash(empID, depID);
    }
  }

  public static void main(String[] args) {
    Map<Employee, String> m = new HashMap<>();
    m.put(new Employee(1010, 0), "TSR");

    // This lookup now works because equals/hashCode are consistent
    System.out.println(m.get(new Employee(1010, 0))); // prints: TSR
  }
}

