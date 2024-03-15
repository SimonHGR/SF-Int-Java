package streamstuff;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SecondLab {
  public static String getLetterGrade(double gpa) {
    if (gpa > 3.5) return "A";
    if (gpa > 3) return "B";
    if (gpa > 2.5) return "C";
    if (gpa > 2) return "D";
    return "E";
  }

  public static boolean isQualified(Stream<String> classStream, Set<String> qualifying) {
    return classStream.anyMatch(c -> qualifying.contains(c));
  }

  public static void main(String[] args) {
    Set<String> sciences = Set.of("Math", "Physics", "Statistics", "AstroPhysics", "Quantum Mechanics");

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
    var map = lis.stream()
        .collect(Collectors.groupingBy(s -> getLetterGrade(s.getGpa())));
//    map.entrySet().stream()
//        .forEach(e -> System.out.println(e.getKey() + " : " + e.getValue()));
    map.forEach((k, v) -> System.out.println(k + " : " + v));

    // print a table of grades, and the number of students with that grade
    lis.stream()
        .collect(Collectors.groupingBy(s -> getLetterGrade(s.getGpa()), Collectors.counting()))
        .entrySet().stream()
        .forEach(e -> System.out.println("There are " + e.getValue() + " students with grade " + e.getKey()));

    // print a table of grades, and the names, comma separated, of students with that grade
    lis.stream()
        .collect(Collectors.groupingBy(s -> getLetterGrade(s.getGpa()),
            Collectors.mapping(s -> s.getName(),
                Collectors.joining(", ", "Students are ", ""))))
        .entrySet().stream()
        .forEach(e -> System.out.println(e.getKey() + " : " + e.getValue()));

    // group students by "arts" or "sciences", and calculate the average grade of
    // that category of student

//    lis.stream()
//        .collect(Collectors.partitioningBy(s -> isQualified(s.getCourses().stream(), sciences)))
//        .entrySet().stream()
//        .forEach(e -> System.out.println(e));

//    lis.stream()
//        .collect(Collectors.groupingBy(s -> isQualified(s.getCourses().stream(), sciences) ? "Science" : "Arts",
//            Collectors.mapping(s -> s.getGpa(),
////                Collectors.averagingDouble((Double x) -> { return x; } ))))
//                Collectors.averagingDouble(x -> x))))
//        .entrySet().stream()
//        .forEach(e -> System.out.println(e));

    lis.stream()
        .collect(Collectors.groupingBy(s -> isQualified(s.getCourses().stream(), sciences) ? "Science" : "Arts",
            Collectors.averagingDouble((Student s) -> s.getGpa())))
        .entrySet().stream()
        .forEach(e -> System.out.println(e));

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

    Pattern WORD_BOUNDARY = Pattern.compile("\\W+");

    Comparator<Map.Entry<String, Long>> byValue = Map.Entry.comparingByValue();
    Comparator<Map.Entry<String, Long>> byDescendingValue = byValue.reversed();

    try (Stream<String> input = Files.lines(Path.of("PrideAndPrejudice.txt"))) {
      input
//          .map(s -> s.toLowerCase())
          .map(String::toLowerCase)
//          .flatMap(s -> WORD_BOUNDARY.splitAsStream(s))
          .flatMap(WORD_BOUNDARY::splitAsStream)
          .filter(s -> !s.isEmpty())
          .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
          .entrySet().stream()
//          .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
//          .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
          .sorted(byDescendingValue)
          .limit(200)
          .map(e -> String.format("%20s : %5d", e.getKey(), e.getValue()))
          .forEach(System.out::println);
    } catch (IOException fnfe) {
      System.out.println("oops, not found!");
    }
  }
}
