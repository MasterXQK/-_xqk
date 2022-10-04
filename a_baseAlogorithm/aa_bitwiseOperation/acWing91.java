package a_baseAlogorithm.aa_bitwiseOperation;

import java.util.Arrays;

/**
 * @author Morgan
 * @create 2022-10-04-15:47
 */
public class acWing91 {
    // https://www.acwing.com/problem/content/submission/93/
    static int hamilton(int n, int[][] weight) {
        int[][] f = new int[1 << n][n];
        for (int[] sf : f) Arrays.fill(sf, Integer.MAX_VALUE / 2);
        f[1][0] = 0;
        for (int i = 1; i < 1 << n; i++) {
            for (int j = 0; j < n; j++) if ((i >> j & 1) == 1) {
                for (int k = 0; k < n; k++) if (((i ^ (1 << j)) >> k & 1) == 1) {
                    f[i][j] = Math.min(f[i][j], f[i ^ (1 << j)][k] + weight[k][j]);
                }
            }
        }
        return f[(1 << n) - 1][n - 1];
    }
}
