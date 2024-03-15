package dontdothis;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.DoubleStream;

public class Example {
  public static void main(String[] args) {
//    long [] count = {0};
//    long count = 0;
    AtomicLong count = new AtomicLong();
    double s = DoubleStream.generate(() -> ThreadLocalRandom.current().nextDouble(-1, +1))
        .limit(1_000_000)
        .parallel()
//        .peek(x -> count[0]++)
//        .peek(x -> count++)
        .peek(x -> count.incrementAndGet())
        .sum();
//    System.out.println("count is " + count[0]);
    System.out.println("count is " + count);

  }
}
