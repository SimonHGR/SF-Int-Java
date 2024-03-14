package superiterable;

import java.util.List;

public class TrySuperIterable {
  public static void main(String[] args) {
    SuperIterable<String> sis = new SuperIterable<>(
        List.of("Alice", "Bob", "Charlie", "Denise", "Elana")
    );

    for (String s : sis) {
      System.out.println("> " + s);
    }

    SuperIterable<String> longStrings = sis.filter(s -> s.length() > 3);

    System.out.println("Long Strings...");
    for (String s : sis) {
      System.out.println("> " + s);
    }


    // create a method in SuperIterable that allows changing the VALUE of
    // the elements and creating a new S.I. with those element in it
    SuperIterable<String> upperCaseStrings = sis.change(s -> s.toUpperCase());
  }
}
