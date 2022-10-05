package a_baseAlogorithm.ab_recursive;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Morgan
 * @create 2022-10-05-22:18
 */
public class recursive_enumeration_n {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        backTrace(1, n);
        for (String s : res) {
            System.out.println(s);
        }
    }

    static StringBuffer path = new StringBuffer();
    static List<String> res = new ArrayList<>();

    static void backTrace(int pos, int n) {
        res.add(path.toString());

        for (int i = pos; i <= n; i++) {
            StringBuffer origin = new StringBuffer(path.toString());
            path.append(i).append(" ");
            backTrace(i + 1, n);
            path = origin;
        }
    }
}
