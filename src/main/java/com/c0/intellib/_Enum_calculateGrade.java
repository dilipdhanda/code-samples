package com.c0.intellib;

public enum _Enum_calculateGrade {
  one, two, three;

  public String getGrade() {
    String grade = new String();
    switch (this) {
      case one:
        grade = "one";
        break;
      case two:
        grade = "two";
        break;
      case three:
        grade = "three";
        break;
    }
    return grade;
  }

  public static void main(String[] args) {
    System.out.println(three.getGrade()); // i answered correct: three
  }
}

