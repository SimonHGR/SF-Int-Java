package superiterable;

import java.util.List;

public class TrySuperIterable {
  public static void main(String[] args) {
    SuperIterable<String> sis = new SuperIterable<>(
        List.of("Alice", "Bob", "Charlie", "Denise", "Elana")
    );

//    for (String s : sis) {
//      System.out.println("> " + s);
//    }

    sis.forEvery(s -> System.out.println("> " + s));

    SuperIterable<String> longStrings = sis.filter(s -> s.length() > 3);

    System.out.println("Long Strings...");
//    for (String s : sis) {
//      System.out.println("> " + s);
//    }
    sis.forEvery(s -> System.out.println("LONG: " + s));

    // create a method in SuperIterable that allows changing the VALUE of
    // the elements and creating a new S.I. with those element in it
//    SuperIterable<String> upperCaseStrings = sis.change(s -> s.toUpperCase());
//    SuperIterable<String> moreUpperCaseStrings = sis.map(s -> s.toUpperCase());
    sis
        .filter(s -> s.length() < 7)
        .map(s -> s.toUpperCase())
//        .forEvery(s -> System.out.println("From map " + s));
        .forEach(s -> System.out.println("From map " + s));
  }
}
