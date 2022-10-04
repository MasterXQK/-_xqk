package a_baseAlogorithm.aa_bitwiseOperation;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Morgan
 * @create 2022-10-04-16:34
 */
public class lowBit {

    public static void main(String[] args) {
        for (int i = 0; i <= 32; i++) map.put(1 << i, i);
    }

    static HashMap<Integer, Integer> map = new HashMap<>();

    static void lowBit(int n) {
        while (n > 0) {
            System.out.println(map.get(n & -n));
//            System.out.println((int) (Math.log(n & -n) / Math.log(2)));
            n -= (n & -n);
        }
    }
}
