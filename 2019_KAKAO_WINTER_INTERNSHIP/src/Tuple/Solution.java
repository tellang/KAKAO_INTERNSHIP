package Tuple;

import java.util.*;
public class Solution {
    public int[] solution(String s) {
        char[] tupleSubset = s.toCharArray();
        int leftIndex, rightIndex, num, tupleIndex, termLenght;
        int[][] integerTuple = new int[s.length()+1][];
        List<Integer> list = new ArrayList<>();
        leftIndex = 2;rightIndex = 3;tupleIndex = 0;termLenght = 0;num = 0;
        while (rightIndex < tupleSubset.length){
            while (!(tupleSubset[leftIndex] >= '0' && tupleSubset[leftIndex] <= '9')){
                leftIndex++;
                num = 0;
            }
            list.add(leftIndex - 1);
            while (tupleSubset[rightIndex] != '}'){
                if(tupleSubset[rightIndex] == ',') {
                    num++;
                    list.add(rightIndex);
                }
                rightIndex++;
            }
            list.add(rightIndex);
            num++;
            if(termLenght < num) {
                termLenght = num;
            }
            integerTuple[num] = new int[num];
            for (int i = 0; i < num; i++) {
                if(num > 1){
                    integerTuple[num][i] = Integer.parseInt(s.substring(list.get(i) + 1, list.get(i+1)));
                }
                else {
                    integerTuple[num][i] = Integer.parseInt(s.substring(leftIndex, rightIndex));
                }
            }
            leftIndex = rightIndex + 3;
            rightIndex = leftIndex + 1;
            num = 0;
            list.clear();
        }
        integerTuple[0] = new int[termLenght];
        for (int i = 1; i < termLenght + 1; i++) {
            for(int tuple: integerTuple[i]){
                if(!list.contains(tuple)){
                    integerTuple[0][tupleIndex] = tuple;
                    list.add(tuple);
                    tupleIndex++;
                }
            }
        }
        return integerTuple[0];
    }
}
class Perfect_Solution {
    public int[] solution(String s) {
        Set<String> set = new HashSet<>();
        String[] arr = s.replaceAll("[{]", " ")
                .replaceAll("[}]", " ").trim().split(" , ");
        //System.out.println(Arrays.toString(arr));
        Arrays.sort(arr, Comparator.comparingInt(String::length));
        //System.out.println(Arrays.toString(arr));
        int[] answer = new int[arr.length];
        int idx = 0;
        for(String s1 : arr) {
            for(String s2 : s1.split(",")) {
                if(set.add(s2))
                    answer[idx++] = Integer.parseInt(s2);
            }
        }
        return answer;
    }
}
