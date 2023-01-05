package b_basicDataStructure.bb_que;

import CodeForce.Main;

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
        // 头部大 尾部小的单调栈
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int n = nums.length;
        // 初始化前k个
        for (int i = 0; i < k; i++) {
            while (!stack.isEmpty() && nums[stack.peekLast()] < nums[i]) {
                stack.pollLast();
            }
            stack.addLast(i);
        }
        int[] res = new int[n - k + 1];
        for (int i = k; i < n; i++) {
            res[i - k] = nums[stack.peekFirst()];
            while (!stack.isEmpty() && nums[stack.peekLast()] < nums[i]) {
                stack.pollLast();
            }
            stack.addLast(i);
            // 距离大于k的就舍弃
            while (!stack.isEmpty() && i - stack.peekFirst() + 1 > k) {
                stack.pollFirst();
            }
        }
        res[n - k] = nums[stack.peekFirst()];
        return res;
    }
}
