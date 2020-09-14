package Course;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public String[] solution(String[] orders, int[] course) {
        int startIndex = 0;
        Set<String> cours = new HashSet<>();
        Set<String>[] order = new HashSet[orders.length];

        String[] sortedOred = new String[orders.length];
       /* for (int i = 0; i < orders.length; i++) {
            char[] temp = orders[i].toCharArray();
            Arrays.sort(temp);
            sortedOred[i] = Arrays.toString(temp);
        }*/
        //Arrays.sort(sortedOred);
        for (int i = 0; i < orders.length; i++) {
            char[] temp = orders[i].toCharArray();
            for (int j = 0; j < temp.length; j++) {
                cours.add(String.valueOf(temp[j]));

            }
        }
        for (int i = 0; i < course.length; i++) {
            for (int j = 0; j < sortedOred.length; j++) {
                if(sortedOred[j].length() >= course[i]){ //코스보다 작은것 제외
                    String temp = sortedOred[j].substring(startIndex, startIndex+course[i]); //셈플1
                    for (int k = 0; k < sortedOred.length; k++) {
                        if(j != k){
                            if(sortedOred[k].length() >= course[i]){


                            }
                        }
                    }

                }
            }
            /*for (String order : sortedOred){
                if(order.length() >= course[i]){
                    String temp = order.substring(startIndex, startIndex+course[i]);
                }
            }*/
        }
        for(String t : sortedOred){
            System.out.println(t);
        }
        String[] answer = {};
        return answer;
    }
}
class MainApp{
    public static void main (String[]a){
        Solution s = new Solution();
        String[] t = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] c = {2, 3, 4};
        s.solution(t, c);
    }
}