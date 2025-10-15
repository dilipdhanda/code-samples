package com.c0.comparable_comparator;

import java.util.ArrayList;
import java.util.Collections;

class Comparable_Student implements Comparable<Comparable_Student> {
    int rollno; String name; int age;
    
    public int compareTo(Comparable_Student other) {
        // Returns a negative integer, zero, or a positive integer as this object is less
        // than, equal to, or greater than the specified object.
        if (age == other.age)     return 0;
        else if (age > other.age) return 1;
        else                   return -1;
    }
    
    public Comparable_Student(int rollno, String string, int age) {
        this.rollno = rollno; this.name = string; this.age = age;
    }

    public static void main(String args[]) {
        ArrayList<Comparable_Student> al = new ArrayList<Comparable_Student>();
        al.add(new Comparable_Student(101, "Vijay", 23));
        al.add(new Comparable_Student(106, "Ajay", 27));
        al.add(new Comparable_Student(105, "Jai", 21));
        Collections.sort(al);
        for (Comparable_Student st : al) {
            System.out.println(st.rollno + " " + st.name + " " + st.age);
        }
    }
}

