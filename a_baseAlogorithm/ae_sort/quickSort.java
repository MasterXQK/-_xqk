package a_baseAlogorithm.ae_sort;/**
@author Morgan
@create 2022-10-12-16:51

*/public class quickSort {

    static void QuickSort(int[] arr, int l, int r) {
        if (l > r) return;
        int base = arr[l], start = l, end = r;
        while (l < r) {
            while (l < r && arr[r] >= base) r--;
            while (l < r && arr[l] <= base) l++;
            swap(arr, l, r);
        }
        swap(arr, start, l);

        QuickSort(arr, start, l - 1);
        QuickSort(arr, l + 1, end);
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
