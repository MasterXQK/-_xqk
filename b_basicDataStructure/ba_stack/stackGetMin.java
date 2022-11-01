package b_basicDataStructure.ba_stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author Morgan
 * @create 2022-11-01-15:37
 */
public class stackGetMin {

    public static void main(String[] args) {
        MinStack m = new MinStack();

        m.push(0);
        m.push(1);
        m.push(0);
        System.out.println(m.getMin());
        m.pop();
        System.out.println(m.getMin());
        /*
        ["MinStack","push","push","push","getMin","pop","getMin"]
        [[],[0],[1],[0],[],[],[]]

         */
    }
}



// O(1) push
// O(1) pop peek
// O(1) getMin()
// 如果我们记录单个最小值 那么当最小值在栈顶被pop则我们不知道下一个最小值是谁
// 因此我们用一个线性结构来记录历史上的每个最小值
// 这样pop后我们往前找那个版本即可获得当时最小值

/*
class MinStack {

    Stack<Integer> stack;
    HashMap<Integer, Integer> versionMap;

    public MinStack() {
        stack = new Stack<>();
        versionMap = new HashMap<>();
        versionMap.put(0, -1); // size == 0, min = -1
    }

    public void push(int val) {
        int curMin = getMin();
        curMin = Math.min(curMin, val);
        stack.add(val);
        versionMap.put(stack.size(), curMin);
    }

    public void pop() {
        versionMap.remove(stack.size());
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        if (stack.isEmpty()) return Integer.MAX_VALUE;
        return versionMap.getOrDefault(stack.size(), -1);
    }

}
*/

// 双栈
// s 模拟 pop peek push
// minStack记录当前栈顶元素的最小值
// 如
//     s    : 9 2 1 5 3 0 2
// minStack : 9 2 1 1 1 0 0
class MinStack {

    Stack<Integer> s = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    public MinStack() {
        s = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        int min = Math.min(val, minStack.isEmpty() ? Integer.MAX_VALUE : minStack.peek());
        minStack.add(min);
        s.add(val);
    }

    public void pop() {
        s.pop();
        minStack.pop();
    }

    public int top() {
        return s.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

