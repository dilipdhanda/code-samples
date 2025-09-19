package a.java_data_structs.adv;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class _Comparator_Comparable {

    static class Movie implements Comparable<Movie> // 'Movie' that implements Comparable
    {
        public int compareTo(Movie m) // COMPARE
        {
            return m.year - this.year; // 0 equal, +ve means bigger
        }
        private double rating; private String name; private int year;
        public Movie(String nm, double rt, int yr)
        { this.name = nm; this.rating = rt; this.year = yr; }
        public double getRating() { return rating; }
        public String getName()   {  return name; }
        public int getYear()      {  return year;  }
    }


    static class RatingCompare implements Comparator<Movie>  // COMPARATOR - Class to compare Movies by ratings
    {
        public int compare(Movie m1, Movie m2)
        {
            if (m1.getRating() < m2.getRating()) return -1;
            if (m1.getRating() > m2.getRating()) return 1;
            else return 0;
        }
    }

    static class NameCompare implements Comparator<Movie>     // COMPARATOR - Class to compare Movies by name
    {
        public int compare(Movie m1, Movie m2)
        {
            return m1.getName().compareTo(m2.getName());
        }
    }

    public static void main(String[] args)
    {
        ArrayList<Movie> list = new ArrayList<Movie>();
        list.add(new Movie("Force Awakens", 8.3, 2015));
        list.add(new Movie("Star Wars", 8.7, 1977));
        list.add(new Movie("Empire Strikes Back", 8.8, 1980));
        list.add(new Movie("Return of the Jedi", 8.4, 1983));

        System.out.println("\nSorted by year - using internal Comparable");
        Collections.sort(list); // natural Comparable.compareTo()
        printList(list);

        System.out.println("\nSorted by name - using external Comparator");
        NameCompare nameCompare = new NameCompare(); // utility class Comparator.compare()
        Collections.sort(list, nameCompare);
        printList(list);

        System.out.println("\nSorted by rating - using external Comparator");
        RatingCompare ratingCompare = new RatingCompare(); // utility class Comparator.compare()
        Collections.sort(list, ratingCompare);
        printList(list);
    }
    static void printList(ArrayList<Movie> list){
        for (Movie movie: list) System.out.println(movie.getRating() + " " + movie.getName() + " " + movie.getYear());
    }
}
