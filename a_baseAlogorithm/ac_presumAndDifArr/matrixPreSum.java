package a_baseAlogorithm.ac_presumAndDifArr;

/**
 * @author Morgan
 * @create 2022-10-10-10:33
 */
import java.util.*;

public class matrixPreSum {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[][] arr = new int[5001][5001];
        int N = scan.nextInt(), R = scan.nextInt();
        for (int i = 0; i < N; i++) arr[scan.nextInt()][scan.nextInt()] += scan.nextInt();

        int ans = solve(arr, R);
        System.out.println(ans);
    }

    static int solve(int[][] arr, int R) {
        int m = arr.length, n = arr[0].length;
        // dp指代matrix前缀和 dp[x][y] 指代 arr[x - 1][y - 1](包含)到arr[0][0](包含)的一个矩阵的和
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + arr[i - 1][j - 1];
            }
        }
        if (R >= Math.max(m, n)) return dp[m][n];
        int maxArea = 0;
        for (int i = R; i <= m; i++) {
            for (int j = R; j <= n; j++) {
                // 计算小正方形面积 需要画图 由两块preSum矩形加减与matrix[i][j]拼接而成
                maxArea = Math.max(maxArea,
                        dp[i][j] - dp[i - R][j] - dp[i][j - R] + dp[i - R][j - R]);
            }
        }
        return maxArea;
    }
}
