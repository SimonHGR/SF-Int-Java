package streamstuff;

import java.util.List;

public class SecondLab {
  public static void main(String[] args) {
    List<Student> lis = List.of(
        Student.of("Fred", 3.2, "Math", "Physics"),
        Student.of("Jim", 2.2, "Journalism"),
        Student.of("Alice", 3.7, "English Language", "English Literature", "History"),
        Student.of("Susan", 3.6, "English Literature", "Journalism", "GeoPolitics"),
        Student.of("Bill", 0),
        Student.of("Sheila", 3.8, "Math", "Physics", "AstroPhysics", "Quantum Mechanics"),
        Student.of("Bob", 2.8, "Math", "Statistics", "AstroPhysics", "Quantum Mechanics")
    );

    // build and print a table of students grouped by grade
    // want this done in 5 minutes, or I need to help!

    // print a table of grades, and the number of students with that grade

    // print a table of grades, and the names, comma separated, of students with that grade

    // group students by "arts" or "sciences", and calculate the average grade of
    // that category of student


    // optional: Build/print a "concordance" (table of word frequency)
    // for the book "Pride and Prejudice"
    // opt2: list the 200 most frequent words in descending order of frequency.
    // 1) Look at the API for java.nio.file.Files -- access contents of a text file
    //    as Stream<String>
    // 2) Look at Pattern -- you will need to divide the lines up into words
    //    pattern "\\W+" is recommended -- look for the way to split into a Stream!!
    // 3) remember that collect/groupingBy builds a Map, and Map is not streamable
    //    directly. Use .entrySet().stream()...
    // API docs (bookmark this!) https://docs.oracle.com/en/java/javase/21/docs/api/index.html

  }
}
