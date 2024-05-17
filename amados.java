QUESTÃO BST

public class BST {

    private Nodo raiz;
    private int tamanho;

    public boolean estaVazia() {
        return this.raiz == null;
    }

    public void adicionar(int elemento) {
        this.tamanho += 1;
        if (estaVazia())
            this.raiz = new Nodo(elemento);
        else {
            Nodo aux = this.raiz;
            while (aux != null) {
                if (elemento < aux.valor) {
                    if (aux.esquerda == null) {
                        Nodo novoNodo = new Nodo(elemento);
                        aux.esquerda = novoNodo;
                        novoNodo.pai = aux;
                        return;
                    }
                    aux = aux.esquerda;
                } else {
                    if (aux.direita == null) {
                        Nodo novoNodo = new Nodo(elemento);
                        aux.direita = novoNodo;
                        novoNodo.pai = aux;
                        return;
                    }
                    aux = aux.direita;
                }
            }
        }
    }

    public boolean equals(BST outra) {
        return equalsHelper(this.raiz, outra.raiz);
    }

    private boolean equalsHelper(Nodo esteNodo, Nodo outroNodo) {
        if (esteNodo == null && outroNodo == null) {
            return true;
        }
        if (esteNodo == null || outroNodo == null) {
            return false;
        }
        return (esteNodo.valor == outroNodo.valor)
            && equalsHelper(esteNodo.esquerda, outroNodo.esquer


QUESTÃO LINKED LIST

public void sortedAdd(int v) {
    NodeLL newNode = new NodeLL(v);

    if (isEmpty()) {
        addFirst(v);
        return;
    }

    if (v < head.value) {
        addFirst(v);
        return;
    }

    if (v > tail.value) {
        addLast(v);
        return;
    }

    NodeLL current = head;

    while (current != null && current.value < v) {
        current = current.next;
    }

    NodeLL prevNode = current.prev;
    NodeLL nextNode = current;

    prevNode.next = newNode;
    newNode.prev = prevNode;
    newNode.next = nextNode;
    nextNode.prev = newNode;

    size++;
}


QUESTÃO BUSCA BINÁRIA TEM 2 TIPOS, USEM OQ DER CERTO

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuscaBinaria {

    public int buscaBinaria(int[] v, int k) {
        return buscaBinariaRecursiva(v, k, 0, v.length - 1);
    }

    private int buscaBinariaRecursiva(int[] v, int k, int ini, int fim) {
        if (ini > fim) {
            return -1; // Elemento não encontrado
        }

        int meio = ini + (fim - ini) / 2;

        if (v[meio] == k) {
            return meio; // Elemento encontrado
        } else if (v[meio] > k) {
            return buscaBinariaRecursiva(v, k, ini, meio - 1); // Buscar na metade esquerda
        } else {
            return buscaBinariaRecursiva(v, k, meio - 1, fim); // Buscar na metade direita
        }
    }

    public static void main(String[] args) {
        BuscaBinaria busca = new BuscaBinaria();
        busca.testBuscaBinaria();
    }

    public void testBuscaBinaria() {
        BuscaBinaria busca = new BuscaBinaria();
        int[] v = new int[]{100};
        assertEquals(-1, busca.buscaBinaria(v, 1));
        assertEquals(0, busca.buscaBinaria(v, 100));

        v = new int[]{10, 20};
        assertEquals(-1, busca.buscaBinaria(v, 100));
        assertEquals(0, busca.buscaBinaria(v, 10));
        assertEquals(1, busca.buscaBinaria(v, 20));

        v = new int[]{10, 20, 30};
        assertEquals(-1, busca.buscaBinaria(v, 100));
        assertEquals(0, busca.buscaBinaria(v, 10));
        assertEquals(1, busca.buscaBinaria(v, 20));
        assertEquals(2, busca.buscaBinaria(v, 30));

        v = new int[]{10, 20, 30, 40};
        assertEquals(-1, busca.buscaBinaria(v, 100));
        assertEquals(-1, busca.buscaBinaria(v, 15));
        assertEquals(-1, busca.buscaBinaria(v, 9));
        assertEquals(-1, busca.buscaBinaria(v, 35));
        assertEquals(0, busca.buscaBinaria(v, 10));
        assertEquals(1, busca.buscaBinaria(v, 20));
        assertEquals(2, busca.buscaBinaria(v, 30));
        assertEquals(3, busca.buscaBinaria(v, 40));
    }
}



E OU OUTRO




public class BuscaBinaria {

    public int buscaBinaria(int[] v, int k) {
        return buscaBinariaRecursiva(v, k, 0, v.length - 1);
    }

    private int buscaBinariaRecursiva(int[] v, int k, int ini, int fim) {
        if (ini > fim) {
            return -1; // Elemento não encontrado
        }

        int meio = ini + (fim - ini) / 2;

        if (v[meio] == k) {
            return meio; // Elemento encontrado
        } else if (v[meio] > k) {
            return buscaBinariaRecursiva(v, k, ini, meio - 1); // Buscar na metade esquerda
        } else {
            return buscaBinariaRecursiva(v, k, meio + 1, fim); // Buscar na metade direita
        }
    }

    public static void main(String[] args) {
        BuscaBinaria busca = new BuscaBinaria();
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int chave = 7;
        int resultado = busca.buscaBinaria(array, chave);
        System.out.println("Índice do elemento " + chave + ": " + resultado);
    }
}

