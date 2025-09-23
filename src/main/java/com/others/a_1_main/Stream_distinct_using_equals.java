package com.others.a_1_main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
// import com.google.common.collect.Lists; // use new ArrayList() for now

public class Stream_distinct_using_equals {

    public static void main(String[] args) {

        List<Book> bl = new ArrayList();
        bl.add(new Book("Sangwan"));
        bl.add(new Book("Dhanda"));
        bl.add(new Book("Dhanda"));
        bl.add(new Book("Sangwan"));
        System.out.println(bl);
        System.out.println(getAllAuthorsAlphabeticallyStream1(bl));

    }

    public static class Book {
        public Book(String an) { a = new Author(an); }
        Author a;
        Author getAuthor() { return a;}
        public String toString() { return a.toString(); }
        public int hashCode() { return a.hashCode(); }
        public boolean equals(Object obj) { return this.a.getSurname().equals(((Book)obj).a.surname); }
    }

    public static class Author {
        Author(String an) { surname = an; }
        String surname = "Dhanda";
        String getSurname() { return surname; }
        public String toString() { return surname; }
        public int hashCode() { return surname.hashCode(); }
        public boolean equals(Object obj) { return this.surname.equals(((Author)obj).surname); }
    }

    public static List<Author> getAllAuthorsAlphabetically(List<Book> books) {
        List<Author> authors = new ArrayList<>();
        for (Book book : books) {
            Author author = book.getAuthor();
            if (!authors.contains(author)) {
                authors.add(author);
            }
        }
        Collections.sort(authors, new Comparator<Author>() {
            public int compare(Author o1, Author o2) {
                return o1.getSurname().compareTo(o2.getSurname());
            }
        });
        return authors;
    }

    public static List<Author> getAllAuthorsAlphabeticallyStream1(List<Book> books) {
        return books.stream()
                .map(book -> book.getAuthor())
                .distinct()
                .sorted((o1, o2) -> o1.getSurname().compareTo(o2.getSurname()))
                .collect(Collectors.toList());
    }

    public static List<Author> getAllAuthorsAlphabeticallyStream2(List<Book> books) {
        return books.stream()
                .map(Book::getAuthor)
                .distinct()
                .sorted(Comparator.comparing(Author::getSurname))
                .collect(Collectors.toList());
    }

}
