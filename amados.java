questão 01:
public boolean contains(String v) {
    boolean contains = false;
    
    // Criar uma pilha temporária para preservar a ordem dos elementos
    Pilha tempPilha = new Pilha(this.pilha.length);
    
    // Percorrer a pilha principal até encontrar o elemento desejado ou esvaziar a pilha
    while (!isEmpty()) {
        String elemento = pop();
        if (elemento.equals(v)) {
            contains = true;
        }
        // Armazenar o elemento removido na pilha temporária
        tempPilha.push(elemento);
    }
    
    // Restaurar a pilha principal para o seu estado original
    while (!tempPilha.isEmpty()) {
        push(tempPilha.pop());
    }
    
    return contains;
}

questão 02:
public void sortedAdd(int v) {
    Node newNode = new Node(v);

    if (isEmpty()) {
        addFirst(v);
        return;
    }

    if (v <= head.value) {
        addFirst(v);
        return;
    }

    if (v >= tail.value) {
        addLast(v);
        return;
    }

    Node current = head;
    while (current != null && current.value < v) {
        current = current.next;
    }

    newNode.prev = current.prev;
    newNode.next = current;
    current.prev.next = newNode;
    current.prev = newNode;
    
    size += 1;
}

questão 03:
import java.util.HashSet;

public class StopWords {

    private HashSet<String> texto;

    public StopWords(String[] texto) {
        this.texto = new HashSet<>();
        for (String palavra : texto) {
            this.texto.add(palavra);
        }
    }

    public HashSet<String> removeStopWords(String[] stopWords) {
        HashSet<String> result = new HashSet<>(texto); // Cria uma cópia do conjunto texto

        for (String stopWord : stopWords) {
            result.remove(stopWord); // Remove a stopWord do conjunto result
        }

        return result;
    }
}
