package Ban;
import java.util.*;

public class Solution {
    public int solution(String[] user_id, String[] banned_id) {
        Set<String> isFirstName = new HashSet<>();
        Queue<SuspectNList> suspectQue = new LinkedList<>();
        List<Integer> starIndexQue = new ArrayList<>();
        List<Integer>[] banSuspect = new ArrayList[banned_id.length];
        Set<Set<Integer>> answerSet = new HashSet<>();
        int banIdLength, starIndex, presentIndex;
        char[] userId;
        for (int i = 0; i < banned_id.length; i++){
            banSuspect[i] = new ArrayList<>();
            String bannedId = banned_id[i];
            banIdLength = bannedId.length();

            starIndex = bannedId.indexOf('*'); //Find * char index
            while (!(starIndex == -1)) {
                starIndexQue.add(starIndex);
                starIndex = bannedId.indexOf('*', starIndex + 1);
            }

            for (int j = 0; j < user_id.length; j++) {
                String user = user_id[j];
                if (user.length() == banIdLength) { //Search for same length
                    userId = user.toCharArray();
                    for (int index : starIndexQue) {
                        userId[index] = '*';
                    }
                    String userStarId = new String(userId);
                    if (userStarId.equals(bannedId)) {
                        if (isFirstName.add(user)) {
                            banSuspect[i].add(j);
                        }
                    }
                }
            }
            isFirstName.clear();
            starIndexQue.clear();
        }

        for (int i = 0; i < banSuspect[0].size(); i++) {
            suspectQue.add(new SuspectNList(0, banSuspect[0].get(i)));
            while (!suspectQue.isEmpty()){
                SuspectNList node = suspectQue.poll();
                presentIndex = node.index;
                if(presentIndex + 1 < banned_id.length) {
                    for (Integer index : banSuspect[presentIndex + 1]) {
                        if(!node.suspectSet.contains(index))
                            suspectQue.add(new SuspectNList(presentIndex + 1, index, node.suspectSet));
                    }
                }
                else {
                    if(node.suspectSet.size() == banned_id.length){
                        Set<Integer> tempSet = new TreeSet<>(node.suspectSet);
                        answerSet.add(tempSet);
                    }
                }
            }
        }

        return answerSet.size();
    }
}
class SuspectNList{
    Set<Integer> suspectSet = new HashSet<>();
    int index;
    SuspectNList(int index, int suspectIndex){
        this.index = index;
        this.suspectSet.add(suspectIndex);
    }
    SuspectNList(int index, int suspectIndex, Set<Integer> lastSet){
        this.index = index;
        this.suspectSet.addAll(lastSet);
        this.suspectSet.add(suspectIndex);
    }
}
class MainApp{
    public static void main(String[] args){
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"*rodo", "*rodo", "******"};
        Solution s = new Solution();
        System.out.println(s.solution(user_id, banned_id));
    }
}