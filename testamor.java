public class PVTemplate {

    private NodePV root;
    private int size;

   public int alturaPreta() {
    return alturaPreta(root);
}

private int alturaPreta(Node node) {
    if (node == null) {
        return 1;
    }

    int leftBlackHeight = alturaPreta(node.left);
    int rightBlackHeight = alturaPreta(node.right);

    if (!node.vermelho) {
        return Math.max(leftBlackHeight, rightBlackHeight) + 1;
    } else { 
        return Math.max(leftBlackHeight, rightBlackHeight);
    }
}

    public boolean isEmpty() {
        return this.root == null;
    }
    
    /**
     * Implementação iterativa da adição de um elemento em uma árvore binária de pequisa.
     * @param element o valor a ser adicionado na árvore.
     */
    public void add(int element, boolean vermelho) {
        this.size += 1;
        if (isEmpty())
            this.root = new NodePV(element, vermelho);
        else {
            
            NodePV aux = this.root;
            
            while (aux != null) {
                
                if (element < aux.value) {
                    if (aux.left == null) { 
                        NodePV newNode = new NodePV(element, vermelho);
                        aux.left = newNode;
                        newNode.parent = aux;
                        return;
                    }
                    
                    aux = aux.left;
                } else {
                    if (aux.right == null) { 
                        NodePV newNode = new NodePV(element, vermelho);
                        aux.right = newNode;
                        newNode.parent = aux;
                        return;
                    }
                    
                    aux = aux.right;
                }
            }
        }
        
    }
    
    
}


class NodePV {
    
    int value;
    NodePV left;
    NodePV right;
    NodePV parent;
    boolean vermelho;
    
    NodePV(int v, boolean vermelho) {
        this.value = v;
        this.vermelho = vermelho;
    }

    public NodePV getLeft() {
        return this.left;
    }

    public NodePV getRight() {
        return this.right;
    }

    public NodePV getParent() {
        return this.parent;
    }

    public int getValue() {
        return this.value;
    }


    public boolean hasOnlyLeftChild() {
        return (this.left != null && this.right == null);
    }
    
    public boolean hasOnlyRightChild() {
        return (this.left == null && this.right != null);
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }
    
}
