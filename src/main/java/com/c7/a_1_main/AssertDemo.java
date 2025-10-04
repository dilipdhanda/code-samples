package com.c7.a_1_main;

// Assertions are disabled by default in java. You need to manually enable them by adding -ea
// to your command-line arguments when you invoke the java compiler.
public class AssertDemo
{
    public static void main(String[] args)
    {
        int x = -1;
        assert x >= 0: "x < 0";
    }
}
