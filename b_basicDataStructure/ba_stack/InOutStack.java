package b_basicDataStructure.ba_stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author Morgan
 * @create 2022-11-04-19:55
 */
public class InOutStack {

    // ACWing 129
    public void solveInAndOutStack(int n) {
        // backTrace
        backTrace(n, 1);

        // sort
        Collections.sort(res, (a, b) -> {
            for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
                if (!a.get(i).equals(b.get(i))) return a.get(i) - b.get(i);
            }
            return -1;
        });

        // out put
        for (int i = 0; i < Math.min(20, res.size()); i++) {
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < res.get(i).size(); j++) {
                sb.append(res.get(i).get(j));
            }

            System.out.println(sb.toString());
        }
    }

    static List<List<Integer>> res = new ArrayList<>();
    static List<Integer> path = new ArrayList();
    static Stack<Integer> stack = new Stack<>();

    // 回溯 但是到20次就停止 选取字典序最前面的20个
    // 如何保证呢？ 贪心
    static void backTrace(int n, int idx) {
        if (res.size() >= 20) return;
        if (path.size() + stack.size() == n) {
            List<Integer> tempArr = new ArrayList<>(path);
            Stack<Integer> s = new Stack<>();
            s.addAll(stack);

            while (!s.isEmpty()) tempArr.add(s.pop());
            res.add(new ArrayList<>(tempArr));
            return;
        }

        for (int i = idx; i <= n; i++) {
            // option1 pop 优先选择这个分支 让字典序最小的先拿到
            if (!stack.isEmpty()) {
                int pop = stack.pop();
                path.add(pop);

                backTrace(n, i);

                path.remove(path.size() - 1);
                stack.add(pop);
            }

            // option2 add
            stack.add(i);
            backTrace(n, i + 1);
            stack.pop();
        }

    }
}
