package a_baseAlogorithm.ab_recursive;

import java.util.*;
/**
 * @author Morgan
 * @create 2022-10-07-21:16
 */
public class 费解的开关 {
    // https://www.acwing.com/problem/content/97/
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        backTrace(5, 0);
        while (t-- > 0) {
            char[][] origin = new char[ 5 ][ 5 ];
            for (int i = 0; i < 5; i++) origin[ i ] = scan.next().toCharArray();

            int cnt = 0;
            boolean ok = true;
            int minOps = Integer.MAX_VALUE;

            for (List<Integer> l : res) {
                int[][] arr = new int[ 5 ][ 5 ];
                for (int i = 0; i < 5; i++) for (int j = 0; j < 5; j++) arr[ i ][ j ] = origin[ i ][ j ] - '0';

                cnt = 0;
                ok = true;

                // 1代表操作这里开灯
                for (int j = 0; j < l.size(); j++) if (l.get(j) == 1) {
                    cnt++;
                    ops(arr, 0, j);
                }

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) if (arr[ i ][ j ] == 0) {
                        // 如果(i, j)为0 则给(i + 1, j)开灯
                        cnt++;
                        ops(arr, i + 1, j);
                    }
                }
                if (cnt > 6) continue; // 操作数大于6 失败

                // 最后一行有灯没开 失败
                for (int j = 0; j < 5; j++) if (arr[ 4 ][ j ] == 0) {
                    ok = false;
                    break;
                }
                if (ok) minOps = Math.min(minOps, cnt);
            }
            System.out.println(minOps == Integer.MAX_VALUE ? -1 : minOps);
        }
    }

    static int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {0, 0}};

    static void ops(int[][] arr, int i, int j) {
        for (int[] d : directions) {
            int x = d[ 0 ] + i, y = d[ 1 ] + j;
            if (x >= 0 && y >= 0 && x < arr.length && y < arr[ 0 ].length) {
                arr[ x ][ y ] ^= 1;
            }
        }
    }

    static List<Integer> path = new ArrayList<>();
    static List<List<Integer>> res = new ArrayList<>();

    static void backTrace(int n, int pos) {
        if (path.size() == n) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (pos >= n) return;

        for (int i = pos; i < n; i++) {
            path.add(0);
            backTrace(n, i + 1);
            path.remove(path.size() - 1);

            path.add(1);
            backTrace(n, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
