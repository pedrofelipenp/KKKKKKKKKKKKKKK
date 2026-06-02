String rota;
String[] urls;

float menorPreco;
int melhorProvedor = -1;

int quantidadeRespostas = 0;
int metade;

Semaphore mutex = new Semaphore(1);
Semaphore podeResponder = new Semaphore(0);

void consulta(int i) {
    float preco = price(urls[i], rota);

    mutex.wait();

    if (melhorProvedor == -1 || preco < menorPreco) {
        menorPreco = preco;
        melhorProvedor = i;
    }

    quantidadeRespostas++;

    if (quantidadeRespostas == metade) {
        podeResponder.signal();
    }

    mutex.signal();
}

int main(String args[]) {
    rota = args[0];

    int n = args.length - 1;
    urls = new String[n];

    for (int i = 0; i < n; i++) {
        urls[i] = args[i + 1];
    }

    metade = (n + 1) / 2;

    for (int i = 0; i < n; i++) {
        create_thread(consulta, i);
    }

    podeResponder.wait();

    return melhorProvedor;
}