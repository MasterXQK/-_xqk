package b_basicDataStructure.ba_stack;

import java.util.Stack;

/**
 * @author Morgan
 * @create 2022-11-04-20:08
 */

public class Calculator {

    public static void main(String[] args) {
        Calculator c = new Calculator();
        int calculate = c.calculate("(1+(4+5+2)-3)+(6+8)");
        System.out.println(calculate);
    }
    // 四则运算
    // https://leetcode.cn/problems/basic-calculator/
    // (1+(4+5+2)-3)+(6+8)
    static int calculate(String s) {
        // 处理中括号 大括号
        s = s.replace("[", "(").replace("]", ")");
        s = s.replace("{", "(").replace("}", ")");
        return cal(s);
    }
    // 目的是让 乘除法优先于加减法
    static void fillInStack(Stack<Integer> stack, int num, char ops) {
        if (ops == '+') stack.add(num);
        else if (ops == '-') stack.add(-num);
        else if (ops == '*') stack.add(stack.pop() * num);
        else stack.add(stack.pop() / num);
    }

    static int cal(String s) {
        int ans = 0;
        char ops = '+';
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') continue;

            if (Character.isDigit(c)) {
                // 这样做是因为大数可能会占用多个char字符
                int j;
                for (j = i + 1; j < s.length() && Character.isDigit(s.charAt(j)); j++);
                int num = Integer.parseInt(s.substring(i, j));
                i = j - 1;
                fillInStack(stack, num, ops);
            } else if (c == '(') {
                int left = 1;
                int j;
                // 这里注意括号重叠括号的处理，这里for条件退出的时候还是会++ 注意边界处理
                for (j = i + 1; j < s.length() && left > 0; j++) {
                    if (s.charAt(j) == '(') left++;
                    else if (s.charAt(j) == ')') left--;
                }
                // 递归计算括号
                int num = cal(s.substring(i + 1, j - 1));
                i = j - 1;

                fillInStack(stack, num, ops);
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                ops = c;
            }
        }
        while (!stack.isEmpty()) ans += stack.pop();
        return ans;
    }

}
