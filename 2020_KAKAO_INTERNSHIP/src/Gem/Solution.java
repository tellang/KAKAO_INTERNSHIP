package Gem;
import java.util.*;

public class Solution{
    public static int totalKinds = 0;
    public static int[] solution(String[] gems) {
        Map<String ,Integer> gemKindNIndex = new HashMap<>();
        Set<String> gemKinds = new HashSet<>();
        Collections.addAll(gemKinds, gems);
        List<String> gemList = new ArrayList<>(gemKinds);
        totalKinds = gemKinds.size();
        int[] answer = new int[] {0, gems.length};
        String left = gems[0];
        int i =0;
        for(; i < gems.length; i++) {// check gemKindNindex's size and
            gemKindNIndex.put(gems[i], i);
            if(gemKindNIndex.containsKey(gems[i]))
            if (gemKindNIndex.size() == totalKinds) {
                answer[1] = gemKindNIndex.get(gems[i]);
                for (String gem: gemList) { //find left index,
                    if(gemKindNIndex.get(left) > gemKindNIndex.get(gem)){
                        left = gem;
                    }
                }
                break;
            }
        }

        for(; i < gems.length; i++){// check gemKindNindex's size and
            if(gems[i].equals(left)){
                gemKindNIndex.replace(gems[i], i);
                for (String gem: gemList) { //find left index
                    if(gemKindNIndex.get(left) > gemKindNIndex.get(gem)){
                        left = gem;
                    }
                }
                if(answer[1] - answer[0] > i - gemKindNIndex.get(left)){
                    answer[0] = gemKindNIndex.get(left);
                    answer[1] = i;
                }
            }
            else {
                gemKindNIndex.replace(gems[i], i);
                if(answer[1] - answer[0] > i - gemKindNIndex.get(left)){
                    answer[0] = gemKindNIndex.get(left);
                    answer[1] = i;
                }
            }
        }
        answer[0]++; answer[1]++;
        return answer;
    }
}
