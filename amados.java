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
//merge
public class MergeSort {

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int[] helper = new int[arr.length];
        mergeSort(arr, helper, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int[] helper, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(arr, helper, low, mid);
            mergeSort(arr, helper, mid + 1, high);
            merge(arr, helper, low, mid, high);
        }
    }

    private static void merge(int[] arr, int[] helper, int low, int mid, int high) {
        for (int i = low; i <= high; i++) {
            helper[i] = arr[i];
        }

        int i = low;
        int j = mid + 1;
        int k = low;

        while (i <= mid && j <= high) {
            if (helper[i] <= helper[j]) {
                arr[k++] = helper[i++];
            } else {
                arr[k++] = helper[j++];
            }
        }

        while (i <= mid) {
            arr[k++] = helper[i++];
        }
    }
}
