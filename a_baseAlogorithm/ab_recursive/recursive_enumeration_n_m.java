package a_baseAlogorithm.ab_recursive;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Morgan
 * @create 2022-10-06-15:34
 */

// https://lcpredictor.herokuapp.com/
public class recursive_enumeration_n_m {
    // 顺序回溯 控制组合长度为m
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(), m = scan.nextInt();
        backTrace(n, m, 1);
        for (List<Integer> l : res) {
            StringBuffer sb = new StringBuffer();
            for (int i : l) sb.append(i).append(" ");
            System.out.println(sb.toString());
        }

    }

    static List<Integer> path = new ArrayList<>();
    static List<List<Integer>> res = new ArrayList<>();

    static void backTrace(int n, int m, int pos) {
        if (path.size() == m) {
            res.add(new ArrayList<>(path));
            return;
        }

        if ((n - pos + 1) + path.size() < m) {
            return; // 剪枝 如果剩余的元素都添加都无法填满m 那么stop
        }

        for (int i = pos; i <= n; i++) {
            path.add(i);
            backTrace(n, m, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
