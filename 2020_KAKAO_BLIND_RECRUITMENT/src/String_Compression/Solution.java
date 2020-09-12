package String_Compression;

import java.util.Stack;

public class Solution {
    public int solution(String s) {
        int stringLen = s.length(), present, shortestLength = stringLen;
        String sub;
        StringBuilder answer;
        Stack<String> stack = new Stack<>();
        for (int compressSize = 1; compressSize <= stringLen / 2; compressSize++) {
            answer = new StringBuilder();
            present = 0;
            while (present < stringLen) {
                if (stringLen - present < compressSize) {
                    if (stack.size() != 1) {
                        answer.append(stack.size());
                    }
                    sub = s.substring(present);
                    answer.append(sub);
                    break;
                } else if (stringLen - present == compressSize) {
                    sub = s.substring(present);
                } else {
                    sub = s.substring(present, present + compressSize);
                }
                if (!stack.isEmpty()) {
                    if (!stack.peek().equals(sub)) {
                        if (stack.size() != 1) {
                            answer.append(stack.size());
                        }
                        stack.clear();
                    } else {
                        present += compressSize;
                        stack.push(sub);
                        if (present >= stringLen) {
                            answer.append(stack.size());
                        }
                        continue;
                    }
                }
                stack.push(sub);
                answer.append(stack.peek());
                present += compressSize;
            }
            stack.clear();
            if (answer.length() < shortestLength) {
                shortestLength = answer.length();
                //System.out.println(answer+": "+answer.length()+" compressSize: "+compressSize);
            }
        }
        return shortestLength;
    }
}

class MainApp {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "aaccaaccccccaaccaaccccccccc";
        System.out.println(s.solution(str));
    }
}