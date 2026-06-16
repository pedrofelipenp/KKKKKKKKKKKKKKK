import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length != 5) {
            System.out.println("Use: java Main <num_producers> <max_items_per_producer> <producing_time> <num_consumers> <consuming_time>");
            return;
        }

        int numProducers = Integer.parseInt(args[0]);
        int maxItemsPerProducer = Integer.parseInt(args[1]);
        int producingTime = Integer.parseInt(args[2]);
        int numConsumers = Integer.parseInt(args[3]);
        int consumingTime = Integer.parseInt(args[4]);

        int totalItems = numProducers * maxItemsPerProducer;

        Buffer buffer = new Buffer(totalItems);

        List<Thread> producers = new ArrayList<>();
        List<Thread> consumers = new ArrayList<>();

        for (int i = 1; i <= numProducers; i++) {
            Producer producer = new Producer(i, buffer, maxItemsPerProducer, producingTime);
            Thread thread = new Thread(producer);
            producers.add(thread);
            thread.start();
        }

        for (int i = 1; i <= numConsumers; i++) {
            Thread pairThread = new Thread(new PairConsumer(i, buffer, consumingTime));
            Thread oddThread = new Thread(new OddConsumer(i, buffer, consumingTime));

            consumers.add(pairThread);
            consumers.add(oddThread);

            pairThread.start();
            oddThread.start();
        }

        try {
            for (Thread producer : producers) {
                producer.join();
            }

            while (!buffer.allConsumed()) {
                Thread.sleep(10);
            }

            for (Thread consumer : consumers) {
                consumer.interrupt();
            }

            for (Thread consumer : consumers) {
                consumer.join();
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Finished.");
    }
}