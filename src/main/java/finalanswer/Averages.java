package finalanswer;

//class AverageInProgress {
//  private double sum;
//  private long count;
//}

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.concurrent.ThreadLocalRandom;

record AverageInProgress(double sum, long count) {
  public AverageInProgress merge(AverageInProgress this, AverageInProgress other) {
    return new AverageInProgress(this.sum + other.sum, this.count + other.count);
  }

  public Optional<Double> get() {
    if (count != 0) {
      return Optional.of(sum / count);
    } else {
      return Optional.empty();
    }
  }
}

public class Averages {
  public static void main(String[] args) {
    Optional<Double> res = ThreadLocalRandom.current().doubles(0, -1, +1)
//        .parallel()
        .mapToObj(d -> new AverageInProgress(d, 1))
        .reduce(new AverageInProgress(0, 0), (a1, a2) -> a1.merge(a2))
        .get();
//    System.out.println("average is " + res);
    res
        .map(x -> "The mean is " + x)
//        .ifPresent(s -> System.out.println(s));
        .ifPresentOrElse(s -> System.out.println(s), () -> System.out.println("No data in the stream"));
  }
}
