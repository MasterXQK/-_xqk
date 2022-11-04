package b_basicDataStructure.ba_stack;

import java.util.Stack;

/**
 * @author Morgan
 * @create 2022-11-04-20:02
 */



public class ReversePolish {

    // 求解逆波兰表达式
    // https://leetcode.cn/problems/evaluate-reverse-polish-notation/
    public int evalRPN(String[] tokens) {
        Stack<Integer> numS = new Stack<>();
        int res = 0;
        for (String s : tokens) {
            if (s.equals("-")) {
                int b = numS.pop();
                int a = numS.pop();
                numS.add(a - b);
            } else if (s.equals("+")) {
                int b = numS.pop();
                int a = numS.pop();
                numS.add(a + b);
            } else if (s.equals("*")) {
                int b = numS.pop();
                int a = numS.pop();
                numS.add(a * b);
            } else if (s.equals("/")) {
                int b = numS.pop();
                int a = numS.pop();
                numS.add(a / b);
            } else {
                numS.add(Integer.parseInt(s));
            }
        }
        while (!numS.isEmpty()) res += numS.pop();
        return res;
    }
}
