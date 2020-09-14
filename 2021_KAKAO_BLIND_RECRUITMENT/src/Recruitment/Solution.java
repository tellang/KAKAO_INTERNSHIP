package Recruitment;

public class Solution {
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        for (int i = 0; i < info.length; i++) {
            for (int j = 0; j < query.length; j++) {
                if(Find(info[i], query[j])){
                    answer[j]++;
                }
            }
        }
        return answer;
    }
    public boolean Find (String user, String query){
        int lastIndex = 0, presentIndex, userLIndex=0, userPIndex;
        boolean isFind = true;
        String nQuery = query.replace(" and", "");
        for (int i = 0; i < 4; i++) {
            userPIndex = user.indexOf(" ", userLIndex);
            presentIndex = nQuery.indexOf(" ", lastIndex);
            String userCon = user.substring(userLIndex, userPIndex);
            String context = nQuery.substring(lastIndex, presentIndex);
            if(!context.equals("-")){
                if(!userCon.equals(context)){
                    isFind = false;
                    break;
                }
            }
            lastIndex = presentIndex + 1;
            userLIndex = userPIndex + 1;
        }
        if(isFind){
            if(Integer.parseInt(nQuery.substring(lastIndex)) > Integer.parseInt(user.substring(userLIndex))){
                isFind = false;
            }
        }
        return isFind;
    }
}
class MainApp{
    public static void main(String[]ar){
        Solution s = new Solution();
        String[] i = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] q = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
        s.solution(i, q);
    }
}