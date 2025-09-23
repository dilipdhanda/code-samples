package com.others.a2.Others_Done.Singleton;

// final keyword is used to prevent the class from being subclassed. This ensures the integrity of the
// Singleton pattern, as subclassing could potentially create multiple instances.
public final class Singleton_Double_Check { // with Double_Checked_locking
    // Volatile keyword ensures that multiple threads handle the uniqueInstance variable correctly.
    private static volatile Singleton_Double_Check uniqueInstance;
    // Private constructor prevents instantiation from other classes
    private Singleton_Double_Check() {
    }
    // Public method to provide access to the instance
    public static Singleton_Double_Check getInstance() {
        if (uniqueInstance == null) { // First check (no locking)
            synchronized (Singleton_Double_Check.class) { // Could synchronize on a local variable also
                if (uniqueInstance == null) { // Second check (with locking)
                    uniqueInstance = new Singleton_Double_Check();
                    System.out.println("Created uniqueInstance with Singleton_Double_Checked_locking");
                }
            }
        } else {
            System.out.println("uniqueInstance already created");
        }
        return uniqueInstance;
    }
    public static void main(String[] args) {
        Singleton_Double_Check.getInstance();
        Singleton_Double_Check.getInstance();
    }

    // To further ensure cloning prevention, override the clone method from Object class
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning of this Singleton instance is not allowed");
    }
}
