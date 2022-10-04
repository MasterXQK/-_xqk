package a_baseAlogorithm.aa_bitwiseOperation;
import java.util.*;

/**
 * @author Morgan
 * @create 2022-10-01-19:15
 */

// http://bailian.openjudge.cn/practice/1995/
public class poj1995 {
//    import java.util.*;
    class Main {
        public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);
            int t = scan.nextInt();
            while (t-- > 0) {
                int M = scan.nextInt();
                int n = scan.nextInt();
                int sum = 0;
                for (int i = 0; i < n; i++) {
                    int a = scan.nextInt(), b = scan.nextInt();
                    sum = (sum + fastPow(a, b, M)) % M;
                }
                System.out.println(sum);
            }
        }
        // 快速幂 (a ^ b) % m
        static int fastPow(int a, int b, int m) {
            int ans = 1 % m;
            // 通过 &1与>>遍历b的二进制每一位
            for (; b > 0; b >>= 1) {
                if ((b & 1) == 1) ans = (int) ((long) ans * (long) a % m);
                a = (int) ((long) a * (long) a % m);
            }
            return ans;
        }
    }

}
