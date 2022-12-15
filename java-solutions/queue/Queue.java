package queue;

import java.util.function.Function;
import java.util.function.Predicate;

/*
    Model:
        [a_1, a_2, .., a_n]
        n - queue size
    Inv:
        n >= 0
        forall int i in [1, n]: a[i] != null
    Immutable:
        n == n' && forall int i in [1, n]: a[i] == a'[i]
 */
public interface Queue {
    /*
   Pred: f != null
   Post:  (R = queue' : ∀ item ∈ queue : p.test(item) == true) && R.getType() == queue.getType()
       */

    AbstractQueue filter(Predicate p);
    /*
    Pred: f != null
    Post:  (R = queue' : ∀ item ∈ queue : f.apply(item) ∈ queue) && R.getType() == queue.getType()
     */

    AbstractQueue map(Function f);

    /*
        Pred: e != null
        Post: n == n'+1 && a[n] == e && forall int i in [1, n']: a[i] == a'[i]
     */
    void enqueue(Object element);

    /*
        Pred: n > 0
        Post: R == a[1] && Immutable
     */
    Object element();

    /*
        Pred: n > 0
        Post: R == a[1] && n == n'-1 && forall int i in [1, n]: a[i] == a'[i+1]
     */
    Object dequeue();

    /*
        Pred: true
        Post: R == n && Immutable
     */
    int size();

    /*
      Pred: true
      Post: R == (n == 0) && Immutable
    */
    boolean isEmpty();

    /*
       Pred: true
       Post: n == 0
    */
    void clear();
}