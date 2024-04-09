public class MoveImpostor {

    public void moveImpostor(int[] v) {
        int impostorIndex = findImpostorIndex(v);
        if (impostorIndex != -1) {
            swap(v, impostorIndex);
        }
    }

    private int findImpostorIndex(int[] v) {
        for (int i = 0; i < v.length - 1; i++) {
            if (v[i] > v[i + 1]) {
                return i + 1;
            }
        }
        return -1;
    }

    private void swap(int[] v, int impostorIndex) {
        int temp = v[impostorIndex];
        v[impostorIndex] = v[impostorIndex - 1];
        v[impostorIndex - 1] = temp;
    }
}
  ...
