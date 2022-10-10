package a_baseAlogorithm.ac_presumAndDifArr;
import java.util.*;

/**
 * @author Morgan
 * @create 2022-10-10-11:53
 */
public class difArr {
    // 结论题 使arr均相同（差分数组除difArr[0]外全为0）的操作只需要 Math.max(-negSum, posSum)步
    // 因 arr[l, r]区间操作 在差分数组内只需difArr[l]+1, difArr[r]-1,即可
    // 最少操作数下的情况为 difArr[0] 到 difArr[0] + negSum + posSum, 闭区间
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = scan.nextInt();
        int[] difs = new int[n];
        difs[0] = arr[0];

        long negSum = 0, posSum = 0;
        for (int i = 1; i < n; i++) {
            difs[i] = arr[i] - arr[i - 1];
            negSum += difs[i] < 0 ? difs[i] : 0;
            posSum += difs[i] > 0 ? difs[i] : 0;
        }
        long opsCnt = Math.max(-negSum, posSum);
        long arrNum = 1 + Math.abs(posSum + negSum);
        System.out.println(opsCnt + "\n" + arrNum);
    }
}
