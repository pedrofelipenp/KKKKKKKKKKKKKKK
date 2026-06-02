int leitores = 0;

Semaphore mutex = new Semaphore(1);
Semaphore salaVazia = new Semaphore(1);
Semaphore fila = new Semaphore(1);

String safe_lookup(String config_key) {
    fila.wait();
    fila.signal();

    mutex.wait();

    leitores++;

    if (leitores == 1) {
        salaVazia.wait();
    }

    mutex.signal();

    String resposta = lookup(config_key);

    mutex.wait();

    leitores--;

    if (leitores == 0) {
        salaVazia.signal();
    }

    mutex.signal();

    return resposta;
}

void safe_update(String config_key, String new_value) {
    fila.wait();

    salaVazia.wait();

    update(config_key, new_value);

    salaVazia.signal();

    fila.signal();
}