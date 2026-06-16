class OddConsumer extends Consumer {
    public OddConsumer(int id, Buffer buffer, int sleepTime) {
        super(id, buffer, sleepTime);
    }

    @Override
    protected boolean canConsume(int item) {
        return item % 2 != 0;
    }

    @Override
    protected String getType() {
        return "Odd";
    }
}