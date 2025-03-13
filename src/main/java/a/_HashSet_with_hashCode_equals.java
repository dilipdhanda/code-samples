package a;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class _HashSet_with_hashCode_equals {
    public static class Person{
        private String name;
        private int age;
        Person(String name, int age){
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            if (age != person.age) return false;
            // nice way to check null and avoid if block
            return name != null ? name.equals(person.name) : person.name == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + age;
            return result;
        }
    }

    public static void main(String[] args) {

        /*
        How objects are added to HashSet? First .hashCode() is called to locate the bucket. If bucket exists, the
            .equals() is called to check if any of the existing objects that are same as this object.

        HashCode Contract: .equals() objects must return same .hashCode(). Not equal objects may or may not (preferred
         for fewer collisions) return the same hashCode. hashCode must be consistent and same everytime.

        Overriding the hashCode() method in Java is crucial when you intend to use your custom objects as keys in a
        hash-based collection like HashSet, HashMap, or HashTable. When you override equals(), you should also
        override hashCode() to maintain the contract that equal objects must have equal hash codes.
        Points to Consider:
            Always override hashCode() when you override equals().
            Use the same set of fields in both hashCode() and equals() methods.
            Use a constant multiplier while combining hash codes; a prime number is often a good choice.
         */

        HashSet<Person> s = new HashSet();
        s.add(new Person("Dilip", 45));
        s.add(new Person("Dilip", 45));
        s.add(new Person("Dilip", 50));

        Set<String> set = new HashSet<>(); // HashSet of strings
        set.add("apple");
        set.add("banana");
        set.add("cherry");
        set.add("apple"); // Duplicate, will be ignored
        boolean containsBanana = set.contains("banana");
        set.remove("cherry");
        int size = set.size();

        set.stream().forEach(element -> { System.out.println(element); }); // Iterate Streams

        Iterator<String> iterator = set.iterator(); // iterator
        while (iterator.hasNext()) { String element = iterator.next(); }

        for (String element : set) { System.out.println(element); } // for
    }
}
