package com.c0.intellib;

class Greeting {
  void sayHello() {
    System.out.println("Hello from Greeting class");
  }
}
public class AnonymousClass {
  public static void main(String[] args) {
    Runnable r = new Runnable() {
      @Override
      public void run() {
        System.out.println("Running Runnable in an anonymous class!");
      }
    };
    new Thread(r).start(); // you didn’t need to write a separate class MyRunnable implements Runnable

    // Example 2 — Anonymous Class from a Concrete Class
    Greeting g = new Greeting() {
      @Override
      void sayHello() {
        System.out.println("Hello from Anonymous class");
      }
    };
    g.sayHello();
  }
}
