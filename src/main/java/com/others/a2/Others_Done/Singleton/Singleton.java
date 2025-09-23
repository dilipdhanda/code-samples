package com.others.a2.Others_Done.Singleton;

public class Singleton {
    // Private constructor to prevent instantiation
    private Singleton() {
        System.out.println("Singleton with HOLDER inner class");
    }
    // Static inner class responsible for holding the Singleton instance
    private static class Holder {
        private static final Singleton INSTANCE = new Singleton();
    }
    // Public method to provide access to the instance
    public static Singleton getInstance() {
        return Holder.INSTANCE;
    }
    public static void main(String[] args) {
        Singleton singletonInstance = Singleton.getInstance();
        // Use the singletonInstance as needed
    }
}

