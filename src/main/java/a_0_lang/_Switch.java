package a_0_lang;

import java.util.Scanner;

public class _Switch {
    public static void main(String[] args) {

// could not fix compile error,  java: text blocks are not supported in -source 14
//  (use -source 15 or higher to enable text blocks)
//        String textBlock = """
//                            abc
//                            """;
//        System.out.println(textBlock);

        String s = "C";
        // SWITCH Expressions (Java 12) do not need break stmt and also provide safety as default stmt is a must
        // (compile time check). Also allow multiple values in one case stmt
//        char c = switch(s){ // default is a must (compile time check) in switch expression
//            case "A","B" -> '1'; // multiple values in one case stmt
//            case "C" -> {
//                System.out.println("Yielding for C");
//                yield 'Z';  // return value
//            }
//            default -> '3'; // default is a must (compile time check) in switch expression
//        };
//        System.out.println(s);
//
//        switch (s){
//            case "A":
//                System.out.println("1");
//            case "B":
//                System.out.println("2");
//                break;
//            case "C":
//                System.out.println("3");
//        }

        Scanner scanner = new Scanner(System.in);
        s = scanner.next();
        System.out.printf(s);
    }
}
