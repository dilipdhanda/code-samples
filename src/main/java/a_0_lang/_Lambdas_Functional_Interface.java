package a_0_lang;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Supplier;
/*

Flattening:
{
    "a":{
        "b": {
            "c" :"data"
        },
        "d" : "0"
    }
}

Output:
{
    "a.b.c" : "data",
    "a.d":0
}

str = "a".b.b.data.
left traversal, for each node, add to str, until you get a leaf and print 1st flatten entry, also remove he last entry "data"
go up after leaf - recursive or for loop

 */
public class _Lambdas_Functional_Interface {
    public static void main(String[] args) {
        ArrayList<String> students = new ArrayList<>();
        students.add("Sally");
        students.add("Polly");
        students.add("Molly");
        students.add("Tony");

        // Consumer // regular, only input, no output
        Consumer<String> printItem = n -> System.out.println(n);
        students.forEach(printItem); students.forEach(System.out::println);

        // Function // has both input and output
        Function<Integer, Integer> doubly = n -> n * n;
        System.out.println(doubly.apply(8));

        // Predicate // returns true or false - takes input, has an output that can be later checked for boolean return
        IntPredicate isDivByThree = n -> n % 3 == 0;
        System.out.println(isDivByThree.test(3));

        // Supplier // no input, only output
        Supplier<Double> randomNumUnder100 = () -> Math.random() * 100;
        System.out.println(randomNumUnder100.get());
    }
}
