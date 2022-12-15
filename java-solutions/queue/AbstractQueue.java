package queue;

import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractQueue implements Queue {
    protected abstract void enqueueImpl(Object element);

    protected abstract Object elementImpl();

    protected abstract Object dequeueImpl();

    protected abstract AbstractQueue createImpl();

    public AbstractQueue filter(Predicate p) {
        if (p != null) {
            AbstractQueue result = createImpl();

            int size = size();
            Object e;

            while (size > 0) {
                e = dequeue();
                if (p.test(e)) {
                    result.enqueue(e);
                }
                enqueue(e);
                size--;
            }

            return result;
        } else {
            throw new RuntimeException();
        }
    }

    public AbstractQueue map(Function f) {
        if (f != null) {
            AbstractQueue result = createImpl();

            int size = size();
            Object e;

            while (size > 0) {
                e = dequeue();
                result.enqueue(f.apply(e));
                enqueue(e);
                size--;
            }

            return result;
        } else {
            throw new RuntimeException();
        }
    }

    public void enqueue(Object element) {
        if(element!=null) {
            enqueueImpl(element);
        }else{
            throw new RuntimeException();
        }
    }

    public Object element() {
        if(size()>0) {
            return elementImpl();
        }else{
            throw new RuntimeException();
        }
    }

    public Object dequeue() {
        if(size()>0) {
            return dequeueImpl();
        }else{
            throw new RuntimeException();
        }
    }

    public void clear() {
        while (!isEmpty()) {
            dequeue();
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public abstract int size();
}
