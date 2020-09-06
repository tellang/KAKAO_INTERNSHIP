package Track;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static int[][] board;
    public int solution(int[][] board) {
        int answer = 0;
        Solution.board = board;
        answer = costCalculate(0, 0);
        return answer;
    }
    public int costCalculate(int xPos, int yPos){
        Queue<Node> que = new LinkedList<>();
        int[][] visited = new int[board.length+1][board.length+1];
        int[] way = new int[] {-1, 0, 1, 0, -1};//(-1, 0), (0, +1), (+1, 0), (0, -1)
        char nextWay;
        int cost = Integer.MAX_VALUE;
        que.add(new Node(xPos, yPos, 0, 's'));
        board[yPos][xPos] = 1;
        while (!que.isEmpty()) {
            Node node = que.poll();
            if(node.yPos == board.length -1 && node.xPos == board.length - 1) {
                if(cost > node.cost)
                    cost = node.cost;
            }
            //visited[node.yPos][node.xPos] = node.cost; //before
            for (int i = 0; i < 4; i++) {
                if(i%2==0)
                    nextWay = 'r';
                else
                    nextWay = 'c';
                if(node.xPos + way[i] >=0 && node.xPos + way[i]< board.length &&
                        node.yPos + way[i+1] >=0 && node.yPos + way[i+1]< board.length &&
                        board[node.yPos + way[i+1]][node.xPos + way[i]] != 1){

                    int newCost=0;
                    int newXPos = node.xPos+ way[i];
                    int newYPos = node.yPos+ way[i+1];
                    if(node.way == 's' || node.way == nextWay)
                        newCost = node.cost + 100;
                    else
                        newCost = node.cost + 600;
                    if(visited[newYPos][newXPos] == 0) {
                        que.add(new Node(node.xPos + way[i], node.yPos + way[i + 1], newCost, nextWay));
                        visited[newYPos][newXPos] = newCost; //after
                    }
                    else{
                        if(visited[newYPos][newXPos] > node.cost) {
                            que.add(new Node(node.xPos + way[i], node.yPos + way[i + 1], newCost, nextWay));
                            visited[newYPos][newXPos] = newCost; //after
                        }
                    }

                }

            }
        }
        return cost;
    }
}
class Node{
    public Node (int xPos, int yPos, int cost, char way){
        this.xPos = xPos;
        this.yPos = yPos;
        this.cost = cost;
        this.way = way; // 's', 'c', 'r'
    }
    int xPos, yPos, cost;
    char way;
}
class Test
{
    public static void main(String[] args){
        Solution s = new Solution();
        int[][] map = {{0,0,0,0,0,0},{0,1,1,1,1,0},{0,0,1,0,0,0},{1,0,0,1,0,1},{0,1,0,0,0,1},{0,0,0,0,0,0}};
        //map = new int[][] {{0, 0, 1, 0}, {0, 0, 0, 0}, {0, 1, 0, 1}, {1, 0, 0, 0}};
        System.out.println(s.solution(map));
    }
}
