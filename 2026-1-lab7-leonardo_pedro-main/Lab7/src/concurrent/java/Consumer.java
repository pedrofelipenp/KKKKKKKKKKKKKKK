abstract class Consumer implements Runnable {
    protected final Buffer buffer;
    protected final int sleepTime;
    protected final int id;

    public Consumer(int id, Buffer buffer, int sleepTime) {
        this.id = id;
        this.buffer = buffer;
        this.sleepTime = sleepTime;
    }

    protected abstract boolean canConsume(int item);

    protected abstract String getType();

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                int item = buffer.removeIf(this::canConsume);

                System.out.println(getType() + " Consumer " + id + " consumed item " + item);

                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}