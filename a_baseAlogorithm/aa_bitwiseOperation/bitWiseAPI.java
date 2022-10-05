package a_baseAlogorithm.aa_bitwiseOperation;

import com.sun.org.apache.bcel.internal.generic.POP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Morgan
 * @create 2022-10-05-17:36
 */
public class bitWiseAPI {

    // 判断两个数正负号是否相同
    static boolean isSeemSign(int a, int b) {
        // 异或 相同为0 不同为1， 符号位若同则0 不同为1（负数）
        return (a ^ b) > 0;
    }

    // 获得一个二进制数的某一位
    static int getBit(int a, int b) {
        // 获得二进制数a的第b位上的数
        return a >> b & 1;
    }

    // 设置二进制数的某一位为1
    static int setBit1(int a, int b) {
        // 用或运算 (1 << b)的b位为1 所以a与他执行或运算 第b个位置的数就被设置为1
        return a | (1 << b);
    }

    // 设置二进制数的某一位为0
    static int setBit0(int a, int b) {
        // 用与运算 ~(1 << b) 取反以后 只有第b位置为0 其余为1 在与运算 则能设置b位为0
        return a & ~(1 << b);
    }

    // 设置二进制的某一位取反
    static int setBitReverse(int a, int b) {
        return a ^ (1 << b);
    }

    // 汉明权重 -> 求出二进制中的所有1的个数 （并且可以定位1的位置）
    static void popCount(int n) {
        int curN = n;
        HashMap<Integer, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < 31; i++) idxMap.put(1 << i, i);

        List<Integer> idxList = new ArrayList<>();

//        while (curN > 0) {
//            idxList.add(idxMap.get(curN & -curN));
//            curN -= curN & -curN; // 使用lowBit的方式
//        }

        curN = n;
        int idx = 0;
        for (; curN > 0; curN = curN >> 1) {
            if ((curN & 1) == 1) idxList.add(idx);
            idx++;
        } // 用循环的方式

        System.out.printf("二进制%d中所有1的位置 = %s \n", n, idxList);
        System.out.printf("二进制%d中所有1的个数 = %s \n", n, idxList.size());
    }

    public static void main(String[] args) {
        int a = 32 - 1;
        String sa = Integer.toString(a, 2);
        System.out.println(sa);

        a = setBit0(a, 2);
        sa = Integer.toString(a, 2);
        System.out.println(sa);

        int bit = getBit(a, 2);
        System.out.println(bit);

        a = setBit1(a, 2);
        sa = Integer.toString(a, 2);
        System.out.println(sa);


        int x = Integer.MAX_VALUE;
        int y = Integer.MIN_VALUE;
        System.out.println(isSeemSign(x, y));

        popCount(32 - 1);
    }


}
