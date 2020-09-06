package Crane;

import java.util.*;

public class Solution {
    public static int answer;

    public int solution(int[][] board, int[] moves) {
        Stack<Integer>[] stacks = new Stack[board.length];
        Stack<Integer> popStack = new Stack<>();
        answer = 0;
        int presentStack;
        for (int i = 0; i < board.length; i++) {
            stacks[i] = new Stack<>();
            for (int j = board.length - 1; j > -1; j--) {
                if (board[j][i] != 0)
                    stacks[i].push(board[j][i]);
            }
        }

        for (int move : moves) {
            if (!stacks[move - 1].empty()) {
                presentStack = stacks[move - 1].pop();
                if (popStack.empty()) {
                    popStack.push(presentStack);
                } else if (presentStack == popStack.peek()) {
                    answer += 2;
                    popStack.pop();
                } else {
                    popStack.push(presentStack);
                }
            }
        }
        return answer;
    }
}
