class Broker {
    int N;
    Request[] buffer;

    int inicio = 0;
    int fim = 0;

    Semaphore mutex = new Semaphore(1);
    Semaphore vagas;
    Semaphore itens;

    Broker(int n) {
        N = n;
        buffer = new Request[N];

        vagas = new Semaphore(N);
        itens = new Semaphore(0);
    }

    void submitRequest(Request r) {
        vagas.wait();

        mutex.wait();

        buffer[fim] = r;
        fim = (fim + 1) % N;

        mutex.signal();

        itens.signal();
    }

    Request getWork() {
        itens.wait();

        mutex.wait();

        Request r = buffer[inicio];
        inicio = (inicio + 1) % N;

        mutex.signal();

        vagas.signal();

        return r;
    }
}

class Worker {
    Broker broker;

    Worker(Broker b) {
        broker = b;
    }

    void run() {
        while (true) {
            Request req = broker.getWork();

            exec(req);
        }
    }

    void exec(Request req) {
        // função já existente
    }
}