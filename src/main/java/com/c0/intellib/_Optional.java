package com.c0.intellib;

import java.util.Optional;

public class _Optional {
  public static void main(String[] args) {
    Integer width = null;
    Integer height = 8;
    // new Integer (8); deprecated since Java 9 - Integer height = 8 maps to Integer.valueOf(8) and is ass efficient
    Optional Width = Optional.of(width); // will throw null pointer exception as width is null, use ofNullable()
    Optional Height = Optional.ofNullable(height);
    System.out.println("Width of the rectangle is : " + Width. isPresent()) ;
    System.out.println("Height of the rectangle is: " + Height. isPresent());
  }
}
