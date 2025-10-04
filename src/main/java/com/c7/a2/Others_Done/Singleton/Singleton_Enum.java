package com.c7.a2.Others_Done.Singleton;

/*
enum is one of the simplest and most effective ways to implement a Singleton in Java.
This approach is thread-safe, protects against serialization and reflection attacks,
and provides a concise syntax.
 */
public enum Singleton_Enum {
    INSTANCE;
    // Add any other methods you need
    public void doSomething() {
        System.out.println("Singleton with ENUM");
    }

    public static void main(String[] args) {
        Singleton_Enum singletonInstance = Singleton_Enum.INSTANCE;
        singletonInstance.doSomething();
    }
}

