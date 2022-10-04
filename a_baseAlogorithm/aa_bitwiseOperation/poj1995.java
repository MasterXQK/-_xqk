package a_baseAlogorithm.aa_bitwiseOperation;
import java.util.*;

/**
 * @author Morgan
 * @create 2022-10-01-19:15
 */

// http://bailian.openjudge.cn/practice/1995/
public class poj1995 {
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
