package streamstuff;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Example {
  public static void main(String[] args) {
    IntStream is = Stream.of("Fred", "Jim", "Sheila")
        .filter(s -> s.length() < 6)
        .map(s -> s.toUpperCase())
        .peek(s -> System.out.println("peek: " + s))
        .flatMapToInt(s -> s.chars());

    List<Integer> intermediateResults = is.boxed().toList();

    intermediateResults.stream().forEach(s -> System.out.println((char)(s.intValue())));
    intermediateResults.stream().forEach(s -> System.out.println("value is " + s.intValue()));
//        is.forEach(s -> System.out.println((char)s))
    ;
//        is.forEach(s -> System.out.println("again: " + s));

    System.out.println(is);

//    var ss = IntStream.iterate(0, x -> x + 1)
    var ss = IntStream.of(0,1,2,3,4,5,6,7,8,9)
//        .limit(10)
//        .filter(s -> true)
//        .flatMap(s -> IntStream.of(s))
        .map(s -> s)
        .peek(x -> System.out.println("x is " + x))
//        .forEach(x -> System.out.println(x))
        .count()
    ;
    System.out.println(ss);

    boolean b = Stream.of("Math", "Physics", "Chemistry")
        .peek(x -> System.out.println(x))
        .anyMatch(x -> x.length() > 5);

    System.out.println(b);
  }
}
