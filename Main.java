public class Main {

    public static void main(String[] args) {

        if (args.length != 4) {
            System.out.println("Use: java Main <num_producers> <producing_time> <num_consumers> <consuming_time>");
            return;
        }

        int numProducers = Integer.parseInt(args[0]);
        int producingTime = Integer.parseInt(args[1]);
        int numConsumers = Integer.parseInt(args[2]);
        int consumingTime = Integer.parseInt(args[3]);

        Buffer buffer = new Buffer(10);

        for (int i = 1; i <= numProducers; i++) {
            Thread producerThread = new Thread(new Producer(i, buffer, producingTime));
            producerThread.start();
        }

        for (int i = 1; i <= numConsumers; i++) {
            Thread consumerThread = new Thread(new Consumer(i, buffer, consumingTime));
            consumerThread.start();
        }
    }
}
