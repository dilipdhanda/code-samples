package com.c0.intellib;

/*
In Java, an interface can include:  Classes, Interfaces, Enums, Records (since Java 16)
- All of these are considered nested types.

Yes — interfaces can have inner (nested) classes.
- They’re always public static, and they’re often used for grouping constants, helper classes,
or factory methods inside the interface definition.
Why static?
- An interface doesn’t have instance fields, so its nested types can’t depend on an instance of the interface.
- Therefore, all nested types are automatically static and don’t need an enclosing instance.

protected int j; not allowed
- All fields in an interface are implicitly public static final, i.e. constants.
- You cannot declare an instance field (protected int j;) or any non-constant value inside an interface.

Common use cases
- Grouping related helper classes - HttpConstants
- Providing default implementations or factories - Shape

Interfaces describe contracts, not implementations
- A class defines how something works (implementation + state).
- An interface defines what something can do (method signatures + type).
Interfaces are still “stateless”
- Even though they can contain: constants (public static final) static methods, default methods (with bodies)
  - and nested types (classes/enums/records/interfaces)
- They cannot hold instance state (no instance variables, no constructors).
- So an interface can’t represent an actual object on its own — only behavior.

= Why interfaces are still essential?
Multiple inheritance of behavior: Java doesn’t support multiple class inheritance
 — but interfaces solve this safely:
- Class can extend only one class but multiple interfaces
Plug-and-play polymorphism: Interfaces make your code loosely coupled:
  void startServer(ServerStartInterface server) {
      server.startServer();
  }
- The caller doesn’t care which implementation — only that it satisfies the contract.
- You can pass in a lambda, mock, or any future implementation.
Functional interfaces and lambdas - Interfaces made modern functional programming possible in Java:
  @FunctionalInterface
  interface Task { void run(); }

  Task t = () -> System.out.println("Running task...");
  t.run();
- This works because interfaces can have exactly one abstract method — enabling concise lambda syntax.
- Even if a functional interface has other methods, as long as they are: default methods, static methods
  or overrides of Object methods (toString, equals, hashCode) …it’s still a functional interface.
- Regular interfaces can have any number of abstract methods
API design
- Interfaces are key for frameworks and libraries (e.g., Spring, JPA, JDBC):
- List, Set, Map are interfaces.
- ArrayList, HashSet, HashMap are classes implementing them.
- This abstraction lets developers swap implementations freely:
  List<String> items = new ArrayList<>();
  // Later
  items = new LinkedList<>();
- No other code changes — because the contract (interface List) remains the same.

= Built-in functional interfaces (in java.util.function) - Java 8+ provides many:
Interface	    Abstract Method	   Example
Runnable	    void run()	       new Thread(() -> System.out.println("run")).start();
Supplier<T>	  T get()	           Supplier<String> s = () -> "Hi";
Consumer<T>	  void accept(T t)	 Consumer<String> c = System.out::println;
Function<T,R>	R apply(T t)	     Function<Integer,String> f = x -> "Result: " + x;
Predicate<T>	boolean test(T t)	 Predicate<Integer> p = x -> x > 10;

When a class extends a superclass or implements multiple interfaces that declare the
same method signature — possibly with default implementations.
- Class always wins over any interface default method
- If multiple interfaces provide the same default method, the implementing class
   must override it to resolve the conflict explicitly.
- if multiple extend classes (in parent hierarchy) have same method signature, then closes parent class wins
 */

public class _Interface {

  interface HttpConstants {
    int OK = 200;
    int NOT_FOUND = 404;

    class Headers {
      public static final String CONTENT_TYPE = "Content-Type";
    }
  }

  interface Shape {
    double area();

    class Factory {
      public static Shape circle(double r) {
        return () -> Math.PI * r * r;
      }
    }
  }

  public interface InterfaceWithInnerClass {
    class InnerClass {
      public void show() {
        System.out.println("Inside interface’s inner class");
      }
    }
      /* …it actually behaves as if you had written:
  public interface Example {
    public static class InnerClass {
        public void show() {
            System.out.println("Inside interface’s inner class");
        }
      }
    }
   */
  }

  interface Example {
    static int i = 0;
    /*
    protected int j; not allowed
    All fields in an interface are implicitly public static final, i.e. constants.
    You cannot declare an instance field (protected int j;) or any non-constant value inside an interface.
     */
    // protected int j; // I answered wrong, I said interface cannot have a inner class
    // ✅ Fix: remove the modifier and give it a value if you need a constant like below
    int j = 10; // implicitly public static final - this fix was not in text

    void someMethod();
    class InnerCLass {
      InnerCLass() {
        System.out.println("This is an inner class inside an interface");
      }
    }
  }

  @FunctionalInterface
  interface Task { void run(); }

  public static void main(String[] args) throws InterruptedException {
    Task t = () -> System.out.println("Running task...");
    t.run();
//    runInterfaceServer();
  }

  static void runInterfaceServer() throws InterruptedException {
    // Implement the functional method with a lambda
    ServerStartInterface s = () -> {
      System.out.println("Server started! Thread: " + Thread.currentThread().getName());
      // do startup work here...
    };
    // Run it on a new thread
    Thread t = new Thread(s::startServer); // method reference to the functional method
    t.start();
    // Wait for the thread to finish (optional)
    t.join();
  }

  // A Functional Interface in Java (introduced in Java 8) is an interface that contains exactly one abstract method.
// Methods that are overrides of Object methods (like equals, hashCode, toString)
// do not count toward the “single abstract method” restriction.
// I chose Correct Answer: The implementation of the functional interface is valid even
// if two methods from the Object class are overridden.
  @FunctionalInterface
  interface ServerStartInterface {
    public void startServer();
    @Override
    public String toString();
    @Override
    public boolean equals(Object obj);
  }

}
