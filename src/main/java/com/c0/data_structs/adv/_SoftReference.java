package com.c0.data_structs.adv;

import java.lang.ref.SoftReference;

public class _SoftReference {
    public static void main(String[] args) {
        // Creating a strong reference to an object
        String strongReference = new String("Hello, SoftReference!");

        // Creating a soft reference to the same object
        SoftReference<String> softReference = new SoftReference<>(strongReference);

        // Print the value of the soft reference
        System.out.println("Soft reference: " + softReference.get());

        // Clear the strong reference
        strongReference = null;

        // Suggest garbage collection
        System.gc();

        // Check if the soft reference still holds the object
        if (softReference.get() != null) {
            System.out.println("Soft reference after GC: " + softReference.get());
        } else {
            System.out.println("Soft reference has been cleared.");
        }
    }
}

