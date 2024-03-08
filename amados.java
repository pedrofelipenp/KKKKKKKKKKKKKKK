//oi meu lindo
//aqui que vou te dar um xero
//impostor
public class MoveImpostor {

    public static void moveImpostor(int[] arr) {
        int impostorIndex = findImpostor(arr);
        if (impostorIndex != -1) {
            moveElementToPlace(arr, impostorIndex);
        }
    }

    private static int findImpostor(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return i;
            }
        }
        return -1;
    }

    private static void moveElementToPlace(int[] arr, int impostorIndex) {
        int impostor = arr[impostorIndex];
        int i = impostorIndex;

        while (i > 0 && impostor < arr[i - 1]) {
            arr[i] = arr[i - 1];
            i--;
        }

        arr[i] = impostor;
    }
}

//duplicados
public class DuplicatesChecker {

    public static boolean hasDuplicates(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
