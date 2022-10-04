package a_baseAlogorithm.aa_bitwiseOperation;

/**
 * @author Morgan
 * @create 2022-10-04-10:58
 */


public class acWing90 {
    // https://www.acwing.com/problem/content/92/
    static long bigMul(long a, long b, long M) {
        long ans = 0;
        for (; b > 0; b >>= 1) {
            if ((b & 1) == 1) ans = (ans + a) % M;
            a = 2 * a % M;
        }
        return ans;
    }
}
