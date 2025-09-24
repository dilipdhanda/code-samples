package com.curr.data_structs.adv;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class _Stack_Deque {
    /*
    ArrayDeque is a resizable-array implementation of the Deque interface
    Stack great when you want to reverse things. Also, good to keep state, like a runtime stack of fn calls
    Stack class lets you remove element from anywhere in between.
    Stack is Synchronized while ArrayDeque is not
    */
    public static void main(String[] args) {
        stack_Stack();
        stack_as_ArrayDeque();

    }
    public static void stack_as_ArrayDeque() {
        Deque<String> stack = new ArrayDeque<>();
        stack.push("First request");
        stack.push("Second request");
        stack.push("Third request");
        System.out.println(stack);
        System.out.println(stack.peek());
        stack.pop();
        System.out.println(stack);
        stack.poll(); // same as pop() - // pop() throws exception on empty stack, while poll() return null
        System.out.println(stack);
    }
    public static void stack_Stack() {
        Stack<String> deckOfCards = new Stack();

        String card1 = "Jack : Diamonds";
        String card2 = "8 : Hearts";
        String card3 = "3 : Clubs";

        deckOfCards.push(card1);
        deckOfCards.push(card2);
        deckOfCards.push(card3);

        System.out.println(deckOfCards);

        // See what's on top w/o removal
        String top = deckOfCards.peek();
        System.out.println("Top: " + top);

        // See size
        System.out.println("Size of Stack: " + deckOfCards.size());
        System.out.println();

        // Remove
        while (!deckOfCards.isEmpty()) {
            String removedItem = deckOfCards.pop();
            System.out.println("Removed " + removedItem);
        }
    }
}
