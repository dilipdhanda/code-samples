package com.c0.intellib;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
Anonymous inner class can extend exactly one class or implement exactly one interface.
An anonymous inner class is a class without a name that you define and instantiate in one go.
It’s often used to provide a quick implementation of an interface or to override methods of a class.
 */
class Greeting {
  void sayHello() {
    System.out.println("Hello from Greeting class");
  }
}
public class _Anonymous_Class {
  public static void main(String[] args) {
    Runnable r = new Runnable() { // Anonymous class
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

    // Anonymous Class from an Interface
    ActionListener listener = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("Button clicked!");
      }
    };

    new Thread(() -> System.out.println("Running via lambda!")).start();

    // 2nd line is error. * operator has converted b * 50 into int, which cannot be converted to byte without casting
    // byte b = 50; b = b * 50;
  }
}
