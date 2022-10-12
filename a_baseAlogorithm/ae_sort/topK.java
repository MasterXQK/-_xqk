package a_baseAlogorithm.ae_sort;

/**
 * @author Morgan
 * @create 2022-10-12-20:35
 */
public class topK {

    static int getTopK(int[] arr, int k) {
        // 第1大的数 就是sort(arr) -> arr[arr.length - k]
        int targetIdx = arr.length - k;

        return quickSort1(arr, 0, arr.length - 1, targetIdx);
    }

    // 核心思路就是快速排序 然后利用快速排序的base base点交换后 左侧小于base 右侧大于base
    // 这样依次二分寻找特定的区间即可
    // 时间复杂度 n + n / 2 + n / 4 + n / 8 + ... = O(N)
     static int quickSort1(int[] arr, int l, int r, int targetIdx) {
        if (l > r) return -1;

        int start = l, end = r, base = arr[l];

        while (l < r) {
            while (l < r && arr[r] >= base) r--;
            while (l < r && arr[l] <= base) l++;
            swap(arr, l, r);
        }
        swap(arr, start, l);

        if (l == targetIdx) return arr[l];
        else if (l < targetIdx) return quickSort1(arr, l + 1, end, targetIdx);
        else return quickSort1(arr, start, l - 1, targetIdx);
     }

     static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
     }
}
