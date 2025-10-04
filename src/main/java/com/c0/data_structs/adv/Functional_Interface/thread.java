package com.c0.data_structs.adv.Functional_Interface;

public class thread {
  public static void main(String[] args) {
    new Thread(new Runnable() {
      public void run() {
        System.out.println("Hello");
      }
    }).start();

  // With lambda
    new Thread(() -> System.out.println("Hello")).start();
  }
}
