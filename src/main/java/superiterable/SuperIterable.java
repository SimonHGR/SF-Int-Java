package superiterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E> {
  private Iterable<E> self;

  public SuperIterable(Iterable<E> self) {
    this.self = self;
  }

  public SuperIterable<E> filter(Predicate<E> crit) {
//  public SuperIterable<E> filter(SuperIterable<E> this, Predicate<E> crit) {
    List<E> res = new ArrayList<>();
    for (E s : this.self) {
      if (crit.test(s)) {
        res.add(s);
      }
    }
//    return new SuperIterable<E>(res);
    return new SuperIterable<>(res);
  }

  // declare "change" -- return SuperIterable<E>
  // the result will be a S.I. containing the results of calling the
  // argument behavior on each of the original contents.
  // formal parameter type will be UnaryOperator<E>

  @Override
  public Iterator<E> iterator() {
    return self.iterator();
  }
}
