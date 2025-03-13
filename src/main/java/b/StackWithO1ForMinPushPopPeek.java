package b;

import java.util.Stack;

//https://www.geeksforgeeks.org/design-a-stack-that-supports-getmin-in-o1-time-and-o1-extra-space/

public class StackWithO1ForMinPushPopPeek {

    // A user defined stack that supports getMin() in addition to push() and pop()
    static class MyStack
    {
        Stack<Integer> stack;
        Integer minEle; // stores the minimum element in the stack
        MyStack() { stack = new Stack<Integer>(); }

        void getMin() {         // Prints minimum element of MyStack
            if (stack.isEmpty()) System.out.println("Stack is empty");
            else                 System.out.println("Minimum Element in the " +" stack is: " + minEle);
        }

        void peek()         // prints top element of MyStack
        {
            if (stack.isEmpty()) { System.out.println("Stack is empty "); return; }

            Integer top = stack.peek(); // Top element.

            System.out.print("Top Most Element is: ");

            // If t < minEle means minEle stores value of t.
            if (top < minEle) System.out.println(minEle);
            else              System.out.println(top);
        }

        // Removes the top element from MyStack
        void pop()
        {
            if (stack.isEmpty()) { System.out.println("Stack is empty"); return; }

            Integer top = stack.pop();
            Integer ret = top;

            // Minimum will change as the minimum element of the stack is being removed.
            if (top < minEle)
            {
                ret = minEle;
                minEle = 2*minEle - top;
            }

            System.out.println("POP ed : "+ret+" , Min : "+minEle);
        }

        // Insert new number into MyStack
        void push(Integer x)
        {
            str += x + " , ";
            Integer internalPushed = x;

            if (stack.isEmpty()) { minEle = x; }

            // If new number is less than original minEle
            if (x < minEle)
            {
                internalPushed = 2*x - minEle; // IMP - over writes value from push call
                System.out.println("Pushed : " + (2*x - minEle));
                minEle = x;
            }
            else {
                internalPushed = x;
            }

            stack.push(internalPushed);
            System.out.println("Pushed : "+x+" internal pushed : "+internalPushed);
        }

        private static String str = "=== PUSH ed  Items : ";
        void print(){
            Integer[] ar = new Integer[100]; // by default all array elements are null
            stack.copyInto(ar);
            System.out.println(str);
            System.out.print("=== Internal Stack : ");
            for (Integer i : ar) { if (i == null) break; System.out.print(i); System.out.print(" , "); }
            System.out.print(" = Min : "+minEle+"\n");
        }
    }



    public static void main(String[] args)
    {
        MyStack myStack = new MyStack();

        myStack.push(9);
        myStack.push(6);
        myStack.push(6);
        myStack.push(6);
        myStack.push(9);
        myStack.push(4);
        myStack.push(4);  myStack.print();
        myStack.pop();    myStack.getMin(); myStack.print();
        myStack.pop();    myStack.print();
        myStack.pop();    myStack.print();
        myStack.pop();    myStack.print();
        myStack.pop();    myStack.print();
        myStack.pop();    myStack.print();
    }
}

