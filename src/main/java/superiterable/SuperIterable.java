package superiterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

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
  public SuperIterable<E> change(UnaryOperator<E> op) {
    List<E> res = new ArrayList<>();
    for (E s : this.self) {
      res.add(op.apply(s));
    }
    return new SuperIterable<>(res);
  }

  // a "bucket o'stuff" that allows transformations on each individual stuff-item
  // and produces a new "bucket o'stuff" of the same type using a "map" operation
  // like this, is called "Functor"
  public <F> SuperIterable<F> map(Function<E, F> op) {
    List<F> res = new ArrayList<>();
    for (E s : this.self) {
      F theF = op.apply(s);
      res.add(theF);
    }
    return new SuperIterable<>(res);
  }

  // flatMap can "do" filter, and map...
  // a "bucket o'stuff" that allows transformations in this form
  // is called "Monad"
  public <F> SuperIterable<F> flatMap(Function<E, SuperIterable<F>> op) {
    List<F> res = new ArrayList<>();
    for (E s : this.self) {
      SuperIterable<F> theFs = op.apply(s);
      for (F f : theFs) {
        res.add(f);
      }
    }
    return new SuperIterable<>(res);
  }

  public void forEvery(Consumer<E> op) {
    for (E e : this.self) {
      op.accept(e);
    }
  }

  @Override
  public Iterator<E> iterator() {
    return self.iterator();
  }
}
