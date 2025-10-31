package com.c0.aisera_keeper;

import java.util.*;
import java.util.stream.*;

/*
In order to add the stream method (or any others) to the core Collections API, Java needed another new feature,
Default methods (also known as Defender Methods or Virtual Extension methods). This way they could add new methods
to the List interface for example without breaking all the existing implementations (backwards compatibility).
Default methods can be added to any interface. Like the name implies, any class that implements the interface
but does not override the method will get the default implementation.

If your custom class doesn’t implement Iterable<T>, Java doesn’t know how to “iterate” over it.
That means you don’t get the spliterator() method or the for-each loop capability — and definitely not stream().
Then you’d have to call: myList.getData().stream()

Although not strictly related to default methods, the ability to add static methods to interfaces is a
similar change, added in Java 8.
public static<T> Stream<T> of(T... values) {
     return Arrays.stream(values);
 }

 */
public class _Default_extending_Iterable {

  // Define an interface with a default stream() method
  interface MyIterable<T> extends Iterable<T> {
    default Stream<T> stream() {
      // Converts this Iterable into a Stream using its Spliterator
      return StreamSupport.stream(spliterator(), false);
    }
  }
  // Implement the interface in a concrete class
  static class MyList<T> implements MyIterable<T> {
    private final List<T> data;
    public MyList(List<T> data) {
      this.data = data;
    }
    @Override
    public Iterator<T> iterator() {
      return data.iterator();
    }
    @Override
    public Spliterator<T> spliterator() {
      return Spliterators.spliterator(data, 0);
    }
  }

  // Main method to test
  public static void main(String[] args) {
    MyList<String> myList = new MyList<>(Arrays.asList("A", "", "B", "", "C"));
    long emptyCount = myList.stream()
      .filter(String::isEmpty)
      .count();
    System.out.println("Empty string count: " + emptyCount);
  }
}
