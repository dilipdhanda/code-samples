package com.a.data_structs.adv;

/*
Classes can be defined inside a public class (as nested classes) or outside a public class but within the same file.
In Java, you can define only one public class per file.
Other classes in the same file must be package-private (without an access modifier).

INSIDE - Classes defined INSIDE a public class are called NESTED classes.
Nested classes can be static or non-static (inner classes).
- STATIC Nested class does not have a reference (more efficient memory usage) to an instance of the outer class.
 - in Java, static nested classes are not called inner classes.
- NON-STATIC Nested Class (aka INNER Class) has a reference to an instance of the outer class.
INNER class is a non-static nested class. It has access to the instance members of the outer class.

OUTSIDE - Class Defined OUTSIDE a Public Class but in the Same File must be package-private (without an access modifier).

protected keyword in Java is an access modifier that provides a level of visibility between public and
default (package-private).
- Within the Same Package: any class within the same package, similar to package-private (default) access.
- Subclasses (Inheritance): can be accessed by subclasses, even if they are in different packages.
 */
interface Greeting {
    void greet();
}

class PackagePrivateClass {
    public void display() {
        System.out.println("Inside package-private class");
    }
}

public class _InnerClass {

    public static void main(String[] args) {
        _InnerClass mainClass = new _InnerClass(); // main _InnerClass class constructor access is fine in main()
        Inn inC = mainClass.createInn();
        // inC = new Inn(); // inner objects, classes or methods cannot be referenced in static context

        _InnerClass.createAnonymousInnerClass();

        PackagePrivateClass packagePrivateClass = new PackagePrivateClass();
        packagePrivateClass.display();

        // Static Nested class can be directly accessed in mina()
        TreeNode_Static_Nested root = new TreeNode_Static_Nested(2);
        root.left = new TreeNode_Static_Nested(1);
        root.right = new TreeNode_Static_Nested(3);
    }

    static class TreeNode_Static_Nested {
        int val;
        TreeNode_Static_Nested left;
        TreeNode_Static_Nested right;

        TreeNode_Static_Nested(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    public static void createAnonymousInnerClass() {
        // Anonymous inner class implementing the Greeting interface
        Greeting greeting = new Greeting() {
            @Override
            public void greet() {
                System.out.println("Hello, world!");
            }
        };
        // Using the anonymous inner class
        greeting.greet();
    }

    Inn createInn(){
        return new Inn(); // need to create in static method, cannot in main()
    }
    public class Inn {
        void f(){
            System.out.println("Inner class Inn ");
        }
    }

    public final class InnerClassFinal {
        // Final Inner class code
    }
    private class InnerClassPrivate {
        // Private Inner class code
    }
}
