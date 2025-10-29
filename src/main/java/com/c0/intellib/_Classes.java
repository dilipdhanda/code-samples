package com.c0.intellib;

class Greeting2_top_level_class_but_not_public {
  void sayHello() {
    System.out.println("Hello from Greeting class");
  }
}

class Game{ }
class Cricket extends Game{ } class Instrument{ }
class Guitar extends Instrument{ }
interface Player<E>{void play(E e); }
interface GamePlayer<E extends Game> extends Player<E>{ }
interface MusicPlayer<E extends Instrument> extends Player{ }
class Bowler implements Player<Guitar> { public void play(Guitar g) { } } // Correct answer
// class MidiPlayer implements MusicPlayer<Instrument>{ public void play (Guitar g){} } // My answer wrong

/*
= TOP LEVEL CLASSES (can be many, but only one public)
Greeting and Demo are two separate top-level classes (both in the same file).
⚠️ But since only one top-level class can be public per file, here only Demo is public.
✅ Greeting here is a regular top-level class, exactly as if you had written it in a
completely separate source file Greeting.java (with class Greeting { ... } inside it).
It just happens to be defined in the same .java file.
 */
public class _Classes {
  public static void main(String[] args) {
    Greeting2_top_level_class_but_not_public g = new Greeting2_top_level_class_but_not_public() {
      @Override
      void sayHello() {
        System.out.println("Hello from Anonymous class created from a top-level class");
      }
    };
    g.sayHello();
  }
}
