package b_basicDataStructure.bb_que;

import CodeForce.Main;
import javafx.util.Pair;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author Morgan
 * @create 2023-01-05-22:11
 */
public class maxSubArrSum {
    // 最大子序和
    // https://www.acwing.com/problem/content/description/137/
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int n = scan.nextInt();
        int m = scan.nextInt();
        long[] sum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + scan.nextInt();
        }

        long max = sum[1]; // 即第一位数
        //peek为最小值
        PriorityQueue<Long> pri = new PriorityQueue<>((a, b) -> a > b ? 1 : -1);
        // 枚举右侧
        for (int r = 1; r < n; r++) {
            // 其实就是在 sum[r - m, r - 1] 里找一个最小值 我们用优先队列实现
            if (pri.size() < m) {
                pri.add(sum[r - 1]);
            } else {
                pri.add(sum[r - 1]);
                pri.remove(sum[r - m - 1]);
            }

            max = Math.max(max, sum[r] - pri.peek());
        }
        out.println(max);
        out.flush();
    }

    // 滑动窗口最大值
    // https://leetcode.cn/problems/sliding-window-maximum/
    public int[] maxSlidingWindow(int[] nums, int k) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int n = nums.length;
        int[] res = new int[n - k + 1];
        for (int i = 0; i < k; i++) {
            while (!stack.isEmpty() && nums[stack.peekLast()] <= nums[i]) {
                stack.pollLast();
            }
            stack.addLast(i);
        }

        for (int i = k; i < n; i++) {
            res[i - k] = nums[stack.peekFirst()];
            while (!stack.isEmpty() && i - stack.peekFirst() >= k) {
                stack.pollFirst();
            }
            while (!stack.isEmpty() && nums[stack.peekLast()] <= nums[i]) {
                stack.pollLast();
            }
            stack.addLast(i);
        }
        res[n - k] = nums[stack.peekFirst()];
        return res;

    }


    // 862. 和至少为 K 的最短子数组
    // 前缀和 + 单调队列
    // https://leetcode.cn/problems/shortest-subarray-with-sum-at-least-k/description/
    // https://leetcode.cn/problems/shortest-subarray-with-sum-at-least-k/solutions/1925036/liang-zhang-tu-miao-dong-dan-diao-dui-li-9fvh/
    public int shortestSubarray(int[] nums, int k) {
        // sum[left, right) == sum[right] - sum[left]
        long curSum = 0L;
        int n = nums.length;
        int minLen = Integer.MAX_VALUE;
        ArrayDeque<Pair<Integer, Long>> stack = new ArrayDeque<>();
        stack.add(new Pair(-1, 0L));
        for (int i = 0; i < n; i++) {
            curSum += nums[i];
            while (!stack.isEmpty() && curSum - stack.peekFirst().getValue() >= k) {
                minLen = Math.min(minLen, i - stack.pollFirst().getKey());
            }
            while (!stack.isEmpty() && stack.peekLast().getValue() >= curSum) {
                stack.pollLast();
            }
            stack.add(new Pair(i, curSum));
        }
        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }

    // 918. 环形子数组的最大和
    // https://leetcode.cn/problems/maximum-sum-circular-subarray/description/
    public int maxSubarraySumCircular(int[] nums) {
        // 前缀和 + 单调队列
        int n = nums.length;
        int[] arr = new int[n * 2];
        for (int i = 0; i < n; i++) {
            arr[i] = nums[i];
            arr[i + n] = nums[i];
        }

        long curSum = 0L;
        long max = nums[0];
        ArrayDeque<Pair<Integer, Long>> stack = new ArrayDeque<>();
        stack.add(new Pair(-1, 0L));
        for (int i = 0; i < 2 * n; i++) {
            curSum += arr[i];
            while (!stack.isEmpty() && i - stack.peekFirst().getKey() > n) {
                stack.pollFirst();
            }

            max = Math.max(max, curSum - stack.peekFirst().getValue());
            // 这里容易把栈清空， 因此max在这之前赋值
            while (!stack.isEmpty() && curSum <= stack.peekLast().getValue()) {
                stack.pollLast();
            }
            stack.add(new Pair(i, curSum));
        }
        return (int) max;
    }
}
