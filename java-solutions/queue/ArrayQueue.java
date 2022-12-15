package queue;

import java.util.Objects;

public class ArrayQueue extends AbstractQueue {
    private Object[] values = new Object[32];
    private int H = 0;
    private int T = H;

    protected ArrayQueue createImpl() {
        return new ArrayQueue();
    }

    public void enqueueImpl(Object element) {
        ensureCapacity();
        values[T] = element;
        T = (T < values.length - 1 ? T + 1 : 0);
    }

    public Object elementImpl() {
        return values[H];
    }

    public Object dequeueImpl() {
        if (H < values.length - 1) {
            return values[H++];
        } else {
            H = 0;
            return values[values.length - 1];
        }
    }

    public int size() {
        return ((T < H) ? (values.length - (H - T)) : (T - H));
    }

    public void clear() {
        H = 0;
        T = 0;
    }

    //pre:
    //post: elements.length == 2 * elements'.length
    //      elements array contains all the same values as before in straight order
    //      head == 0
    //      tail == head + size, where size is the number of elements in the queue
    private void ensureCapacity() {
        int size = size();
        if (size < values.length - 1) {
            return;
        }

        Object[] newElements = new Object[2 * values.length];
        System.arraycopy(copyToArray(), 0, newElements, 0, size);
        values = newElements;
        H = 0;
        T = H + size;
    }

    //pre:
    //post: result contains all the elements in straight order
    private Object[] copyToArray() {
        Object[] result = new Object[size()];

        if (T < H) {
            System.arraycopy(values, H, result, 0, values.length - H);
            System.arraycopy(values, 0, result, values.length - H, T);
        } else {
            System.arraycopy(values, H, result, 0, T - H);
        }
        return result;
    }

    //pre: element != null
    //post: elements[head] == element
    //      head == head' - 1 or head == elements.length
    //      tail != head
    public void push(Object element) {
        if (element != null) {

            ensureCapacity();
            if (H == 0) {
                H = values.length;
            }
            --H;
            values[H] = element;
        } else {
            throw new RuntimeException();
        }
    }

    //pre: queue is not empty
    //post: result == elements[tail - 1] or result == elements[elements.length - 1]
    public Object peek() {
        if (T != H) {

            if (T > 0) {
                return values[T - 1];
            } else {
                return values[values.length - 1];
            }
        } else {
            throw new RuntimeException();
        }
    }

    //pre: queue is not empty
    //post: result == elements[tail' - 1] or result == elements[elements.length - 1]
    public Object remove() {
        if (T != H) {
            T = (T > 0) ? (T - 1) : (values.length - 1);
            return values[T];
        } else {
            throw new RuntimeException();
        }
    }

    public int count(Object elem) {
        Objects.requireNonNull(elem);
        int counter = 0;
        for (Object value : values) {
            if (value != null && value.equals(elem)) {
                counter++;
            }
        }
        return counter;
    }
}
