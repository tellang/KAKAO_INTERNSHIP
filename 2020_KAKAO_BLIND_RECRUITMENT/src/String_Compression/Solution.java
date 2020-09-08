package String_Compression;

import java.util.Stack;

public class Solution {
    public int solution(String s) {
        int stringLen = s.length(), present, shortestLength = Integer.MAX_VALUE;
        String sub;
        StringBuilder answer;
        Stack<String> stack = new Stack<>();
        for (int compressSize = 1; compressSize <= stringLen / 2; compressSize++) {
            answer = new StringBuilder();
            present = 0;
            while (present <= stringLen - compressSize) {
                if(present - (compressSize + stringLen) > compressSize){
                    sub = s.substring(present);
                }
                else {
                    sub = s.substring(present, present + compressSize);
                }
                if (stack.isEmpty()) {
                    stack.push(sub);
                } else {
                    if (!stack.peek().equals(sub)) {
                        if (stack.size() == 1) {
                            answer.append(stack.pop());
                        } else {
                            answer.append(stack.size()).append(stack.pop());
                            stack.clear();
                        }
                    }
                    stack.push(sub);
                    if(present == stringLen - compressSize){
                        if (stack.size() == 1) {
                            answer.append(stack.pop());
                        } else {
                            answer.append(stack.size()).append(stack.pop());
                            stack.clear();
                        }
                    }
                }
                present+=compressSize;

            }
            if(answer.length() < shortestLength){
                shortestLength = answer.length();
            }
        }
        return shortestLength;
    }
}
class MainApp{
    public static void main(String[]args){
        Solution s = new Solution();
        String str = "ababcdcdababcdcd";
        System.out.println(s.solution(str));
    }
}