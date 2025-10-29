package com.c0.intellib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {
  public static void main(String[] args) {

  }

  void tests(){

    List<Icecream> icecreams = Arrays.asList(
      new Icecream("strawberry"),
      new Icecream("butterscotch"),
      new Icecream("chocolate")
    );
    icecreams.stream()
      .filter(ice -> ice.getFlavor().equals("butterscotch"))
      .forEach(System.out::println);
    // Answer was this, I chose wrong answer
    Icecream iceFlavor = icecreams.stream()
      .filter(x -> "butterscotch".equals(x.getFlavor()))
      .findAny()// this will not print, so you will need System.out.println
      .orElse(null);
    System.out.println("Found ice cream: " + iceFlavor);

    List<Integer> listCards= Arrays.asList(1,2,4,5,6);
    for(Integer card : listCards) {
      int xCard = card * card;
      System.out.println(xCard);
    }
    listCards.stream()
      .map(card -> card * card)
      .forEach(System.out::println); // Best option
    // my answer below was wrong, it wont compile .stream() method does not take any arguments
    // listCards.stream ((xCard) -> xCard*xCard). forEach(System.out::println);

    List profile = new ArrayList(); //Line1
    // ✅ Compiles, but ⚠️ not type-safe — the compiler won’t prevent adding mixed object types.
    // Better use List<String> profile = new ArrayList<>();
    profile.add("Manager");
    profile.add("Executive");
    profile.add ("Engineer");
    profile.forEach(System.out::println);

    Die d = new Die (10);
    System.out.println("Die has " + d.numFaces + " " + d.faceValue); // my answer was correct
  }

  static class Icecream {
    private String flavor;
    public Icecream(String flavor) {
      this.flavor = flavor;
    }
    public String getFlavor() {
      return flavor;
    }
    @Override
    public String toString() {
      return flavor;
    }
  }

  static class Die {
    int numFaces; int faceValue; public Die( ) {
      numFaces = 6;
      faceValue = 1;
    }
    public Die(int faces) {
      if (faces < 4)
        numFaces = 6;
      else
        numFaces = faces;
      faceValue = 1;
    }
  }

}
