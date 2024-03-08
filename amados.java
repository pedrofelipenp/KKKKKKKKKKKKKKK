public class MoveImpostor {

    public void moveImpostor(int[] v) {
        int impostorIndex = findImpostor(v);
        if (impostorIndex != -1) {
            moveElementToPlace(v, impostorIndex);
        }
    }

    private int findImpostor(int[] v) {
        for (int i = 1; i < v.length; i++) {
            if (v[i] < v[i - 1]) {
                return i;
            }
        }
        return -1;
    }

    private void moveElementToPlace(int[] v, int impostorIndex) {
        int impostor = v[impostorIndex];
        int i = impostorIndex;

        while (i > 0 && impostor < v[i - 1]) {
            v[i] = v[i - 1];
            i--;
        }

        v[i] = impostor;
    }
}
// a
public class MergeSort {

    public void sort(int[] a, int ini, int fim) {
        if (ini < fim) {
            int meio = (fim + ini) / 2;
            sort(a, ini, meio);
            sort(a, meio + 1, fim);
            merge(a, ini, meio, fim);
        }
    }

    private void merge(int[] a, int ini, int meio, int fim) {
        int[] helper = new int[a.length];
        for (int i = ini; i <= fim; i++) {
            helper[i] = a[i];
        }

        int i = ini;
        int j = meio + 1;
        int k = ini;

        while (i <= meio && j <= fim) {
            if (helper[i] <= helper[j]) {
                a[k++] = helper[i++];
            } else {
                a[k++] = helper[j++];
            }
        }

        while (i <= meio) {
            a[k++] = helper[i++];
        }
    }
}
//a 
public class Duplicados {

    public boolean contemDuplicados(int[] v) {
        for (int i = 0; i < v.length - 1; i++) {
            for (int j = i + 1; j < v.length; j++) {
                if (v[i] == v[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
