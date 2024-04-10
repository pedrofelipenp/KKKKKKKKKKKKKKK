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
