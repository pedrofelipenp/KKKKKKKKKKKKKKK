public class PVTemplate {

    private NodePV root;
    private int size;

    public int alturaPreta() {
        if (isEmpty()) {
            return 0;
        }

        NodePV current = root;
        int altura = 0;

        while (current != null) {
            if (!current.vermelho) {
                altura++;
            }
            current = current.left;
        }

        int alturaAtual = 0;
        NodePV node = root;

        while (node != null) {
            if (!node.vermelho) {
                alturaAtual++;
            }

            if (node.left != null) {
                node = node.left;
            } else if (node.right != null) {
                node = node.right;
            } else {
                NodePV parent = node.parent;

                while (parent != null && node == parent.right) {
                    node = parent;
                    parent = parent.parent;
                }

                node = parent;
            }

            if (alturaAtual > altura) {
                altura = alturaAtual;
            }
        }

        return altura;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

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
           
