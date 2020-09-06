package Stepping_Stone;

public class Solution {
    public int solution(int[] stones, int k) {
        int answer, max, leftIndex, rightIndex;
        answer = 0;leftIndex = 0;rightIndex = 0;max = 0;

        //max = stones[0];
        for (; rightIndex < k - 1; rightIndex++) {
            if(max < stones[rightIndex]){
                max = stones[rightIndex];
            }
        }
        if(max < stones[rightIndex]){
            max = stones[rightIndex];
        }
        answer = max;
        while (rightIndex < stones.length - 1){
            if(stones[leftIndex] == max){
                if(stones[rightIndex + 1] >= max){
                    max = stones[++rightIndex];
                    leftIndex++;
                }
                else{//re search
                    rightIndex++;
                    leftIndex++;
                    max = stones[leftIndex];
                    for (int i = leftIndex + 1 ; i < rightIndex + 1; i++) {
                        if(stones[i] > max){
                            max = stones[i];
                        }
                    }
                    if(max < answer){
                        answer = max;
                    }
                }
            }
            else{
                if(stones[rightIndex + 1] > max){
                    max = stones[++rightIndex];
                    leftIndex++;
                }
                else{
                    rightIndex++;
                    leftIndex++;
                }
            }
        }
        return answer;
    }
}
class MainApp{
    public static void main(String[] args){
        Solution s =new Solution();
        int[] stones = {2, 20, 2, 21, 2, 31, 1, 41, 2, 10};
        System.out.println(s.solution(stones, 2));
    }
}
