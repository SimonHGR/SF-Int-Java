package finalanswer;

import java.util.stream.IntStream;

public class AddUp {
  public static void main(String[] args) {
    var res = IntStream.iterate(1, x -> x + 1)
        .limit(10)
        .reduce(0, (a, b) -> a + b);
//        .forEach(s -> System.out.println(s));
    System.out.println("sum is " + res);
  }
}
