package Calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static java.lang.StrictMath.abs;

public class Calculator {
    public static void main(String[] args) {
        String exp = "10*100*10000000*1000-1000";
        Calculator c = new Calculator();
        System.out.println("urTest: " + c.solution(exp));
        System.out.println(stack(1, 2, 3, exp));
    }

    public static long stack(int mulL, int plsL, int minL, String exp) {
        Map<String, Integer> oprL = new HashMap<>();
        Stack<String> opr = new Stack<>();
        Stack<Long> num = new Stack<>();
        int exOprIndex = -1;

        oprL.put("*", mulL);
        oprL.put("-", minL);
        oprL.put("+", plsL);

        for (int i = 0; i < exp.length(); i++) {
            if (exp.charAt(i) == '*' || exp.charAt(i) == '+'
                    || exp.charAt(i) == '-') {
                num.push(Long.parseLong(exp.substring(exOprIndex + 1, i)));
                if (opr.empty())
                    opr.push(exp.substring(i, i + 1));
                else if (oprL.get(opr.peek()) < oprL.get(exp.substring(i, i + 1)))
                    opr.push(exp.substring(i, i + 1));
                else {
                    while (!opr.empty()) {
                        if (oprL.get(opr.peek()) < oprL.get(exp.substring(i, i + 1)))
                            break;
                        calculate(opr, num);
                    }
                    opr.push(exp.substring(i, i + 1));
                }
                exOprIndex = i;
            }

        }
        num.push(Long.parseLong(exp.substring(exOprIndex + 1)));

        while (!opr.empty()) {
            calculate(opr, num);
        }
        return abs(num.pop());
    }

    public static void calculate(Stack<String> opr, Stack<Long> num) {
        String operator = opr.pop();
        long left, right;
        right = num.pop();
        left = num.pop();
        if (operator.equals("+"))
            num.push(left + right);
        else if (operator.equals("*"))
            num.push(left * right);
        else
            num.push(left - right);
    }

    public long solution(String exp) {
        long answer = 0;
        int[][] lot = new int[][]{
                {1, 2, 3}, {1, 3, 2}, {2, 1, 3}, {2, 3, 2}, {3, 1, 2}, {3, 2, 1}
        };
        for (int[] Rotate : lot) {
            if (stack(Rotate[0], Rotate[1], Rotate[2], exp) > answer)
                answer = stack(Rotate[0], Rotate[1], Rotate[2], exp);
        }

        return answer;
    }
}