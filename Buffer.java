import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Buffer {

    private final BlockingQueue<Integer> queue;

    public Buffer(int capacity) {
        this.queue = new ArrayBlockingQueue<>(capacity);
    }

    public void put(int value) throws InterruptedException {
        queue.put(value);
        System.out.println("Inserted: " + value + " | Buffer size: " + queue.size());
    }

    public int remove() throws InterruptedException {
        int value = queue.take();
        System.out.println("Removed: " + value + " | Buffer size: " + queue.size());
        return value;
    }
}
