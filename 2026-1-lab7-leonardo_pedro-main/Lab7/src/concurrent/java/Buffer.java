import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.function.Predicate;

class Buffer {
    private static final int CAPACITY = 100;

    private final List<Integer> data = new ArrayList<>();

    private final Semaphore mutex = new Semaphore(1);
    private final Semaphore empty = new Semaphore(CAPACITY);
    private final Semaphore full = new Semaphore(0);

    private int consumedCount = 0;
    private final int totalItems;

    public Buffer(int totalItems) {
        this.totalItems = totalItems;
    }

    public void put(int value) throws InterruptedException {
        empty.acquire();

        mutex.acquire();
        data.add(value);
        System.out.println("Inserted: " + value + " | Buffer size: " + data.size());
        mutex.release();

        full.release();
    }

    public int removeIf(Predicate<Integer> condition) throws InterruptedException {
        while (true) {
            full.acquire();

            mutex.acquire();

            for (int i = 0; i < data.size(); i++) {
                int value = data.get(i);

                if (condition.test(value)) {
                    data.remove(i);
                    consumedCount++;

                    System.out.println("Removed: " + value + " | Buffer size: " + data.size());

                    mutex.release();
                    empty.release();

                    return value;
                }
            }

            mutex.release();

            // Não achou item compatível: devolve a permissão,
            // pois nenhum item foi removido.
            full.release();

            Thread.sleep(1);
        }
    }

    public boolean allConsumed() {
        boolean result;

        try {
            mutex.acquire();
            result = consumedCount >= totalItems;
            mutex.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            result = true;
        }

        return result;
    }
}