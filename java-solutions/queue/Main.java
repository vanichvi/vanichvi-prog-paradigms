package queue;

public class Main {
    public static void main(String[] args) {
        ArrayQueueModule queue = new ArrayQueueModule();
        queue.enqueue(1);
        queue.enqueue(1);
        queue.enqueue(true);
        System.out.println(queue.count(1));

    }
}
