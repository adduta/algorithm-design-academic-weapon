package divideandconquer;

public class MergeSort {
    /**
     * Takes an array and sorts it in an ascending order. Note that the method is void, so it should
     * sort the input, rather than return a sorted copy.
     *
     * @param arr - the array that needs to be sorted.
     */
    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    void sort(int arr[], int l, int r) {
        if (l >= r) return;

        int m = (l + r) / 2;
        sort(arr, l, m);
        sort(arr, m + 1, r);
        merge(arr, l, m, r);
    }

    void merge(int arr[], int l, int m, int r) {
        int leftLength = m - l + 1;
        int rightLength = r - m;

        int[] left = new int[leftLength];
        int[] right = new int[rightLength];

        for (int i = 0; i < leftLength; i++) {
            left[i] = arr[l + i];
        }

        for (int i = 0; i < rightLength; i++) {
            right[i] = arr[m + 1 + i];
        }

        // Merge the temporary arrays.
        int i = 0, j = 0;
        int k = l;
        while (i < leftLength && j < rightLength) {
            if (left[i] < right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < leftLength) {
            arr[k++] = left[i++];
        }

        while (j < rightLength) {
            arr[k++] = right[j++];
        }
    }
}
