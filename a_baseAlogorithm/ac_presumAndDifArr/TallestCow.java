package a_baseAlogorithm.ac_presumAndDifArr;

import java.util.*;

/**
 * @author Morgan
 * @create 2022-10-10-12:26
 */
public class TallestCow {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt(), P = scan.nextInt(), H = scan.nextInt(), M = scan.nextInt();
        int[] difArr = new int[N];

        // 去重
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < M; i++) {
            int A = scan.nextInt() - 1, B = scan.nextInt() - 1;
            int tempA = A, tempB = B;
            A = Math.min(tempA, tempB); B = Math.max(tempA, tempB);
            String key = new StringBuffer().append(A).append("_").append(B).toString();
            if (!set.add(key)) continue;
            difArr[A + 1]--;
            difArr[B]++;
        }

        int[] arr = new int[N];
        arr[P - 1] = H;
        // arr[P - 1] = H 从这点根据 difArr[i] = arr[i] - arr[i - 1] 往两边推 得到Arr
        for (int i = P; i < N; i++) arr[i] = arr[i - 1] + difArr[i];
        for (int i = P - 2; i >= 0; i--) arr[i] = arr[i + 1] - difArr[i + 1];

        // output
        for (int i = 0; i < N; i++) System.out.println(arr[i]);
    }
}
