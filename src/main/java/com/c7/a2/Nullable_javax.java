package com.c7.a2;

import javax.annotation.Nullable;
import javax.annotation.Nonnull;

public class Nullable_javax {
    @Nullable // no runtime checks tho, there will be NullPointerException is we call fns on nulls
    private String middleName;

    @Nonnull
    public String getFirstName() {
        return "John";
    }

    @Nullable
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(@Nullable String middleName) {
        this.middleName = middleName;
    }

    public static void main(String[] args) {
        System.out.println((new Nullable_javax().getMiddleName())); // null
        System.out.println((new Nullable_javax().getMiddleName().length())); // NullPointerException
    }
}


