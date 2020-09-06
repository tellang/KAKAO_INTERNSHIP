package Cave;
import java.util.*;

public class Solution {
    public static Map<Integer, Integer> orderNode, pathNode;
    public static int elderOrder0, elderOrder1;
    public boolean solution(int n, int[][] path, int[][] order) {
        Queue<Integer> que = new LinkedList<>(); // present node number
        orderNode = new HashMap<>(); //descent, parent
        pathNode = new HashMap<>(); //descent, parent
        boolean[][] adjList = new boolean[n][n];
        int parentNode = 0;

        for (int[] orderSet : order) {
            orderNode.put(orderSet[1], orderSet[0]);
        }
        for (int[] pathSet : path){
            adjList[pathSet[0]][pathSet[1]] = true;
            adjList[pathSet[1]][pathSet[0]] = true;
        }
        que.add(0);
        while (!que.isEmpty()){
            parentNode = que.poll();
            for (int i = 1; i < adjList.length; i++) { // find descent node at parentNode line, need to reduce cycle time
                if(adjList[parentNode][i]){// add all descent node number
                    if(!pathNode.containsKey(i)){
                        que.add(i);
                        pathNode.put(i, parentNode); // save to hashtable
                    }
                }
            }
            if(pathNode.size()+1 == n){ //ecept
                break;
            }
        }

        for (int i = 0; i < order.length; i++) { //더 정갈한 반복으로 바꿔야함
            for (int j = 0; j < order.length; j++) {
                if (i != j) {
                    if (IsCrossConnected(order[i], order[j])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean IsCrossConnected(int[] firstOrder, int[] secondOrder) {

        elderOrder0 = 0; elderOrder1 = 0;
        return IsDirectConnected(firstOrder[0], secondOrder[1], elderOrder0) && IsDirectConnected(firstOrder[1], secondOrder[0], elderOrder1)
                && elderOrder0 != elderOrder1;
    }

    public boolean IsDirectConnected(int firstNode, int secondNode, int elderOrder) {
        boolean normal, reverse;
        normal = false;
        reverse = false;
        if (DirectConnection(firstNode, secondNode)){
            normal = true;
            elderOrder = 0;
        }
        if (DirectConnection(secondNode, firstNode)) {
            reverse = true;
            elderOrder = 1;
        }
        return reverse || normal;
    }

    public boolean DirectConnection(int firstNode, int secondNode) { //check direct nodes
        int presentNode;
        presentNode = firstNode;
        while (presentNode != 0) {
            presentNode = pathNode.get(presentNode);
            if (presentNode == secondNode) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] path = {{0, 1}, {0, 3}, {0, 7}, {8, 1}, {3, 6}, {1, 2}, {4, 7}, {7, 5}};
        int[][] order = {{5, 2}, {4, 1}};
        int n = 9;

        Solution s = new Solution();
        s.solution(n, path, order);
    }
}
