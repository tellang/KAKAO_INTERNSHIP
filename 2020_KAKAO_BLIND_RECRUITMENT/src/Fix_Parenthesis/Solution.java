package Fix_Parenthesis;

import java.util.*;

public class Solution {
    public String solution(String p) {
        return Splitter(p);
    }

    public Paren Cut(String p) {
        int leftParenNum, rightParenNum, i;
        leftParenNum = 0;
        rightParenNum = 0;
        i = 0;
        char[] parenthesis = p.toCharArray();
        boolean isCorrectParen = false;
        Stack<Character> parenStack = new Stack<>();
        Paren paren;
        if (!p.isEmpty()) {
            while (true) {
                if (parenthesis[i] == '(') {
                    parenStack.push('(');
                    leftParenNum++;
                } else {
                    rightParenNum++;
                    if (!parenStack.isEmpty()) {
                        parenStack.pop();
                        if (parenStack.isEmpty()&&(leftParenNum == rightParenNum)) {
                            isCorrectParen = true;
                            break;
                        }
                    }
                }
                if (leftParenNum == rightParenNum) {
                    break;
                }
                i++;
            }
            if (i + 1 >= p.length()) {
                paren = new Paren(p, "", isCorrectParen);
            } else {
                paren = new Paren(p.substring(0, i + 1), p.substring(i + 1), isCorrectParen);
            }
        } else {
            paren = new Paren();
        }
        return paren;
    }

    public String Splitter(String s) {
        Paren paren;
        if (s.isEmpty()) {
            return "";
        } else {
            paren = Cut(s);
            if (paren.isCorrect) {
                return paren.u + Splitter(paren.v);
            } else {
                String temp = "(";
                temp += Splitter(paren.v);
                temp += ")";
                return temp + CutNReverse(paren.u);
            }
        }
    }

    public String CutNReverse(String s) {
        return Reverse(s.substring(1, s.length() - 1));
    }

    public String Reverse(String s) {
        String temp = s;
        if(s.length()>0){
            temp = temp.replace("(", "a");
            temp = temp.replace(")", "(");
            temp = temp.replace("a", ")");
        }
        return temp;
    }
}

class Paren {
    String u, v;
    boolean isCorrect = false;

    public Paren(String u, String v, boolean isCorrect) {
        this.u = u;
        this.v = v;
        this.isCorrect = isCorrect;
    }

    public Paren() {
        this.u = "";
        this.v = "";
    }
}

class MainApp {
    public static void main(String[] args) {
        String s = ")()(";
        Solution o = new Solution();
        System.out.println(o.solution(s));
    }
}
