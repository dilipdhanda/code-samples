package com.c0.data_structs;

public class _Character {
  public static void main(String[] args) {
    solution(59);
  }
  static int solution(int n) {
    String s = Integer.toString(n);
    int sum = 0;
    for (Character c : s.toCharArray()){
      sum += Character.getNumericValue(c);
    }
    System.out.println(sum);
    return sum;
  }
}
