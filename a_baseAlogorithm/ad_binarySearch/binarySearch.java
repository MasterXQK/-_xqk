package a_baseAlogorithm.ad_binarySearch;

/**
 * @author Morgan
 * @create 2022-10-10-14:03
 */
public class binarySearch {

    // 1.二分查找 找最左侧的 >= target的最小值.如 arr = {0, 1, 1, 1, 2} binarySearchLeft(a, 1) = 1
    // 2.如果要用此方法判断target是否存在于数组，可以用 arr[binarySearchLeft(arr, target)] == target 来判断 为true则存在
    static int binarySearchLeft(int[] arr, int target) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int mid = (l + r) >> 1; // 检查会不会死循环
            if (arr[mid] < target) l = mid + 1; // 必不可能为答案
            else if (arr[mid] == target) r = mid; // 符合要求 有可能为答案 缩小范围
            else if (arr[mid] > target) r = mid; // 符合要求 有可能为答案 缩小范围
        }
        return l;
    }

    // 二分查找 找最右侧的 <= target范围内的的最大值. 如 arr = {0, 1, 1, 1, 2} binarySearch(arr, 1) = 3
    // 2.如果要用此方法判断target是否存在于数组，可以用 arr[binarySearchLeft(arr, target)] == target 来判断 为true则存在
    static int binarySearchRight(int[] arr, int target) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int mid = (l + r + 1) >> 1; // 检查会不会死循环
            if (arr[mid] < target) l = mid; // 可能为答案（如没有target的情况
            else if (arr[mid] == target) l = mid; // 符合要求 有可能为答案 缩小范围
            else if (arr[mid] > target) r = mid - 1; // 必不可能为答案
        }
        return l;
    }

}
