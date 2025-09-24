package com.curr.data_structs.adv;

public class _Cloneable implements Cloneable {
    private String name;
    private int age;

    public _Cloneable(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); // Perform a shallow copy
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }

    public static void main(String[] args) {
        try {
            _Cloneable original = new _Cloneable("John Doe", 30);
            _Cloneable clone = (_Cloneable) original.clone();

            System.out.println("Original: " + original);
            System.out.println("Clone: " + clone);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}

