buffer[N]

int in = 0
int out = 0
int count = 0

Lock lock = new Lock()
Condition cond = new Condition()

procedure produzir(item)

    lock.lock()

    while (count == N) {
        cond.wait()
    }

    buffer[in] = item
    in = (in + 1) mod N
    count = count + 1

    cond.notifyAll()

    lock.unlock()

    
procedure consumir()

    lock.lock()

    while (count == 0) {
        cond.wait()
    }

    item = buffer[out]
    out = (out + 1) mod N
    count = count - 1

    cond.notifyAll()

    lock.unlock()

    return item
