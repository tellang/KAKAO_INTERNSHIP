package Zip;

public class Solution {
    public String solution(String new_id) {
        int index;
        String answer;
        answer = new_id.toLowerCase().replaceAll("[^a-z[-_.][0-9]]", "");

        while (answer.startsWith(".")){
            answer = answer.substring(1);
        }
        while (answer.endsWith(".")){
            answer = answer.substring(0, answer.length()-1);
        }
        index = answer.indexOf('.');
        if(!answer.equals("")){
            while (index != -1){
                if(answer.indexOf('.', index + 1) == index + 1){
                    answer = answer.substring(0, index) + answer.substring(index + 1);
                } else{
                    index = answer.indexOf('.', ++index);
                }
            }
        } else{
            answer = "a";
        }

        if(answer.length()>15){
            answer = answer.substring(0, 15);
        }
        while (answer.length()<=2){
            answer+=answer.substring(answer.length()-1);
        }
        while (answer.endsWith(".")){
            answer = answer.substring(0, answer.length()-1);
        }
        return answer;
    }
}
class MainApp{
    public static void main(String[]ar){
        String t = ".asda.s......d";
        Solution s = new Solution();
        System.out.println(s.solution(t));

    }
}