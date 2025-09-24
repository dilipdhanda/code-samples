package com.curr.data_structs;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _Streams { // Imperative design to reduce code
    public static void main(String[] args) {
        _Streams s = new _Streams();
//        s.examples();
//        s.map_Employee_to_DTO();
        s.flatMap_Integers();
        s.flatMap_Strings();
    }

    void flatMap_Integers(){
        List<List<Integer>> listOfLists = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9)
        );
        List<Integer> flattenedList = listOfLists.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println(flattenedList); // Output: [1, 2, 3, 4, 5, 6, 7, 8, 9]
    }

    void flatMap_Strings(){
        List<String> words = Arrays.asList("hello", "world");

        List<Character> characters = words.stream()
                .flatMap(word -> word.chars().mapToObj(c -> (char) c))
                .collect(Collectors.toList());

        System.out.println(characters); // Output: [h, e, l, l, o, w, o, r, l, d]
    }

    public void map_Employee_to_DTO() {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice", "HR", 50000),
                new Employee(2, "Bob", "Engineering", 60000),
                new Employee(3, "Charlie", "Sales", 55000)
        );
        List<EmployeeDTO> employeeDTOs = employees.stream()
                .map(emp -> new EmployeeDTO(emp.getId(), emp.getName(), emp.getDepartment()))
                .collect(Collectors.toList());
        employeeDTOs.forEach(dto -> System.out.println(
                "ID: " + dto.getId() + ", Name: " + dto.getName() + ", Department: " + dto.getDepartment()
        ));
    }

    class Employee {
        private int id;
        private String name;
        private String department;
        private double salary;
        public Employee(int id, String name, String department, double salary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }
        public int getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public String getDepartment() {
            return department;
        }
        public double getSalary() {
            return salary;
        }
    }

    class EmployeeDTO {
        private int id;
        private String name;
        private String department;
        public EmployeeDTO(int id, String name, String department) {
            this.id = id;
            this.name = name;
            this.department = department;
        }
        public int getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public String getDepartment() {
            return department;
        }
    }

    void examples(){
        var orderedFoodItems = List.of("hot dog ", "chips"); // VAR Inference variables from Java 10
        var total = 38.23;
        System.out.println(orderedFoodItems.get(0)+total);
        // Create a stream
        Stream<String> shoppingStream = Stream.of("apples", "bananas", "cherries", "coffee");
        // Create a stream from other collection types
        // -------------------------------------------
        // Array
        String[] shoppingArray = new String[]{"apples", "bananas", "cherries", "coffee"};
        Stream<String> shopArrayStream = Arrays.stream(shoppingArray);
        // Lists
        List<String> shoppingList = List.of("apples", "bananas", "cherries", "coffee");
        Stream<String> shoppingListStream = shoppingList.stream();
        // For loop in one line
        shoppingList.stream().forEach(System.out::println);
        shoppingList.parallelStream().forEach(System.out::println);
        // Match
        boolean isOnList = shoppingList.stream().anyMatch(item -> item.contains("apples"));
        // Filter
        Stream<String> itemsInAisle = shoppingList.stream().filter(item -> item.startsWith("c"));
        // Map
        List<Integer> numbersList = List.of(4, 2, 6, 9, 10, 17, 3);
        Stream<Integer> doubledStream = numbersList.stream().map(n -> n*n);
        // Collect
        List<Integer> doubledList = numbersList.stream().map(n -> n * 2).collect(Collectors.toList());
    }
}
