package a_baseAlogorithm.ae_sort;

import com.sun.deploy.util.ParameterUtil;
import com.sun.scenario.effect.Merge;

/**
 * @author Morgan
 * @create 2022-10-12-16:55
 */
public class mergeSort {

    // copy arr
    static int[] c;

    static void MergeSort(int[] arr) {
        c = new int[arr.length];
        MergeSort(arr, 0, arr.length - 1);
    }
    static void MergeSort(int[] arr, int l, int r) {
        if (l >= r) return;

        int mid = (l + r) >> 1;
        MergeSort(arr, l, mid);
        MergeSort(arr, mid + 1, r);

        merge(arr, l, mid, r);
    }

    static void merge(int[] arr, int l, int mid, int r) {
        // copy arr into c
        for (int i = l; i <= r; i++) c[i] = arr[i];

        // arr1 [l, mid]
        // arr2 [mid + 1, r]
        int idx1 = l, idx2 = mid + 1, idx = l;
        while (idx1 <= mid && idx2 <= r) {
            if (c[idx1] <= c[idx2]) arr[idx++] = c[idx1++];
            else arr[idx++] = c[idx2++];
        }
        while (idx1 <= mid) arr[idx++] = c[idx1++];
        while (idx2 <= r) arr[idx++] = c[idx2++];
    }

}
