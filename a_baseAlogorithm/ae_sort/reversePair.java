package a_baseAlogorithm.ae_sort;

/**
 * @author Morgan
 * @create 2022-10-16-18:02
 */

public class reversePair {
    // 逆序对问题 -> 归并排序、线段树
    // https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/

    // 如果用线段树 需要处理好数的上届和下届 以及int和long溢出问题
    public static void main(String[] args) {
        // 归并排序求数组逆序对
        Solution mergeSolution = new Solution();
        // 线段树求逆序对
        Solution1 segmentSolution = new Solution1();
        // TODO 树状数组
    }


    // 归并排序求数组逆序对 （也可以改为求区间逆序对）
    static class Solution {
        int[] temp;
        public int reversePairs(int[] nums) {
            temp = new int[nums.length];
            return mergeSortV1(nums, 0, nums.length - 1);
        }

        int mergeSortV1(int[] nums, int l, int r) {
            if (l >= r) return 0;

            int mid = (l + r) >> 1;
            int ans1 = mergeSortV1(nums, l, mid);
            int ans2 = mergeSortV1(nums, mid + 1, r);

            int ans3 = 0;
            for (int i = mid + 1; i <= r; i++) {

                if (nums[mid] <= nums[i]) continue;
                int binary = binarySearch(nums,l, mid, nums[i]);
                ans3 += (mid - binary + 1);
            }
            merge(nums, l, mid, r);
            return ans1 + ans2 + ans3;
        }

        void merge(int[] nums, int l, int mid, int r) {
            for (int i = l; i <= r; i++) temp[i] = nums[i];
            int i1 = l, i2 = mid + 1, i = l;
            while (i1 <= mid && i2 <= r) {
                if (temp[i1] <= temp[i2]) nums[i++] = temp[i1++];
                else nums[i++] = temp[i2++];
            }
            while (i1 <= mid) nums[i++] = temp[i1++];
            while (i2 <= r) nums[i++] = temp[i2++];
        }

        // [l, r]范围内（递增），找出 > target的最小的数
        int binarySearch(int[] nums, int l, int r, int target) {
            if (l > r) return 0;
            while (l < r) {
                int mid = (l + r) >> 1;
                if (nums[mid] > target) r = mid;
                else if (nums[mid] == target) l = mid + 1;
                else if (nums[mid] < target) l = mid + 1;
            }
            return nums[l] > target ? l : -1;
        }
    }

    // 线段树求逆序对
    static class Solution1 {
        long max = Integer.MIN_VALUE;
        long min = Integer.MAX_VALUE;

        public int reversePairs(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                max = (long) Math.max(max, nums[i]);
                min = (long) Math.min(min, nums[i]);
            }

            SegmentTree s = new SegmentTree();
            int ans = 0;
            for (int n : nums) {
                ans += n == max ? 0 : s.query(s.root, min, max, n + 1, max);
                s.update(s.root, min, max, n, n, 1);
            }
            return ans;
        }

        class SegmentTree {
            class Node {
                int val, add;
                Node left, right;
                Node() {}
            }


            int N = (int) 1e9;
            Node root = new Node();

            // [l, r]区间有值的个数
            int query(Node node, long start, long end, long l, long r) {
                if (l <= start && end <= r) {
                    return node.val;
                }
                long mid = (start + end) >> 1;
                int ans = 0;
                pushDown(node);
                // start l mid r end
                if (l <= mid) ans += query(node.left, start, mid, l, r);
                if (r > mid) ans += query(node.right, mid + 1, end, l, r);
                return ans;
            }

            void pushDown(Node node) {
                if (node.left == null) node.left = new Node();
                if (node.right == null) node.right = new Node();
                if (node.add == 0) return;

                node.left.val += node.add;
                node.right.val += node.add;
                node.left.add += node.add;
                node.right.add += node.add;

                node.add = 0;
            }

            void update(Node node, long start, long end, long l, long r, int updVal) {
                if (l <= start && end <= r) {
                    node.val += updVal;
                    node.add += updVal;
                    return;
                }
                long mid = (start + end) >> 1;
                pushDown(node);
                // start l mid r end
                if (l <= mid) update(node.left, start, mid, l, r, updVal);
                if (r > mid) update(node.right, mid + 1, end, l, r, updVal);
                pushUp(node);
            }

            void pushUp(Node node) { node.val = node.left.val + node.right.val; }
        }
    }



}
