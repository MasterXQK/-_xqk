package a_baseAlogorithm.ae_sort;

import java.util.PriorityQueue;

/**
 * @author Morgan
 * @create 2022-10-12-20:20
 */
public class MaintainMedium {
    // 动态维护中位数

    class MaintainMediumPoint {
        // 大数堆 小根堆 poll出最小的
        PriorityQueue<Integer> bigP = new PriorityQueue<>((a, b) -> a - b);

        // 小数堆 大根堆 poll出这堆最大的
        PriorityQueue<Integer> smallP = new PriorityQueue<>((a, b) -> b - a);

        // 添加元素
        void add(int x) {
            // 优先加入到大数里去
            if (bigP.size() <= smallP.size()) bigP.add(x);
            else smallP.add(x);
        }

        // 获得中位数
        double getMedium() {
            // 调整两个队列保持 大数堆最小值 >= 小数堆最大值
            while (!bigP.isEmpty() && !smallP.isEmpty() && smallP.peek() > bigP.peek()) {
                smallP.add(bigP.poll());
                bigP.add(smallP.poll());
            }

            int size = bigP.size() + smallP.size();
            if (size % 2 == 1) {
                // 奇数
                return bigP.peek();
            } else {
                // 偶数
                return (double) bigP.peek() / 2 + (double) smallP.peek() / 2;
            }
        }
    }
}
