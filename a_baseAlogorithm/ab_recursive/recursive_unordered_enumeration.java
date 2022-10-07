package a_baseAlogorithm.ab_recursive;

import java.util.*;

/**
 * @author Morgan
 * @create 2022-10-06-15:44
 */
public class recursive_unordered_enumeration {
    // https://www.acwing.com/problem/content/96/
    // 无序递归枚举 排列

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        visit = new boolean[n + 1];
        backTrace(n);
        for (List<Integer> l : res) {
            StringBuffer sb = new StringBuffer();
            for (int i : l) sb.append(i).append(" ");
            System.out.println(sb.toString());
        }
    }

    static List<Integer> path = new ArrayList<>();
    static List<List<Integer>> res = new ArrayList<>();
    static boolean[] visit;

    static void backTrace(int n) {
        if (path.size() == n) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 1; i <= n; i++) if (!visit[i]) {
            visit[i] = true;
            path.add(i);
            backTrace(n);
            path.remove(path.size() - 1);
            visit[i] = false;
        }
    }
}
