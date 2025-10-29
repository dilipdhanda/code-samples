package com.c0.intellib;

import java.util.ArrayList;
import java.util.List;

class Person {
  private String name, gender;
  private int age;
}

class Employee extends Person {
  public Employee(String linda, int i, String female) {
    super();
  }
}

class Contractor {
  public Contractor(String maya, int i, String female) {
  }
} // Line 10

public class Inheritance_and_Sorting {
  private static List<Person> persons;

  public static void main(String args[]) {
    persons = new ArrayList<Person>();
    persons.add(new Employee("Linda", 30, "Female"));  // Line 19
    // Compilation fails at line 20 and 21 - I chose correct option in text
    // as Contractor does not extend Person
//    persons.add(new Contractor("Maya", 21, "Female")); // Line 20
//    Collections.sort(persons);                         // Line 21
  }
}

