class PairConsumer extends Consumer {
    public PairConsumer(int id, Buffer buffer, int sleepTime) {
        super(id, buffer, sleepTime);
    }

    @Override
    protected boolean canConsume(int item) {
        return item % 2 == 0;
    }

    @Override
    protected String getType() {
        return "Pair";
    }
}