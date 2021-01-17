package Drone;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    int[][] boardTick;
    int[][] board;
    int rotate = -1, columnMode = 1, rowMode = -1;
    Queue<DronePoint> queue;
    public int solution(int[][] board) {
        this.board = board;
        setBoardTick();
        queue = new LinkedList<>();
        queue.add(new DronePoint(0, 0, 1, 0, rowMode, 0));

        while (true){
            if (!queue.isEmpty()){
                if (isFinished(queue.peek())){
                    return queue.peek().tick;
                }
                DronePoint presentPoint = queue.poll();
                pathFinder(presentPoint);

            } else {
                System.err.println("queue empty err");
                return -1;
            }
        }
    }
    private void setBoardTick(){
        boardTick = new int[board.length][board.length];
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board.length; j++) {
                this.boardTick[j][i] = Integer.MAX_VALUE;
            }
        }
    }

    private boolean isValidPosition(int x, int y){
        return x < this.board.length && y < this.board.length
                && x >= 0 && y >= 0
                && this.board[y][x] != 1;
    }
    private void setTick(DronePoint dronePoint){
        this.boardTick[dronePoint.y0][dronePoint.x0] = dronePoint.tick;
        this.boardTick[dronePoint.y1][dronePoint.x1] = dronePoint.tick;
    }
    private boolean isValidPosition(DronePoint dronePoint){
        if(this.boardTick[dronePoint.y0][dronePoint.x0] <= dronePoint.tick
                && this.boardTick[dronePoint.y1][dronePoint.x1] <= dronePoint.tick) {
            return false;
        } else {
            setTick(dronePoint);
            return true;
        }
    }
    private boolean isFinished(DronePoint dronePoint){
        return (dronePoint.x0 == board.length - 1 && dronePoint.y0 == board.length - 1)
                ||(dronePoint.x1 == board.length - 1 && dronePoint.y1 == board.length - 1);
    }

    private void pathFinder(DronePoint beforePoint){
        DronePoint afterPoint;
        if (beforePoint.mode == columnMode){
            if (isValidPosition(beforePoint.x0, beforePoint.biggerPoint() + 1)){
                if (isValidPosition(afterPoint = new DronePoint(beforePoint, columnMode, 1))){
                    this.queue.add(afterPoint);
                }
            } if (isValidPosition(beforePoint.x0, beforePoint.smallerPoint() - 1)){
                if (isValidPosition(afterPoint = new DronePoint(beforePoint, columnMode, -1))){
                    this.queue.add(afterPoint);
                }
            } if (isValidPosition(beforePoint.x0 - 1, beforePoint.y0)
                    && isValidPosition(beforePoint.x1 - 1, beforePoint.y1)){
                if (isValidPosition(afterPoint = new DronePoint(beforePoint, rowMode, -1))){
                    this.queue.add(afterPoint);
                    afterPoint = new DronePoint(beforePoint, rowMode, 0);
                    afterPoint.x0--;
                    afterPoint.y0 = afterPoint.smallerPoint();
                    afterPoint.y1 = afterPoint.smallerPoint();
                    afterPoint.mode = rowMode;
                    this.queue.add(afterPoint);
                    setTick(afterPoint);
                    this.queue.add(afterPoint = new DronePoint(afterPoint, columnMode, 1));
                    setTick(afterPoint);
                }
            } if (isValidPosition(beforePoint.x0 + 1, beforePoint.y0)
                    && isValidPosition(beforePoint.x1 + 1, beforePoint.y1)){
                if (isValidPosition(afterPoint = new DronePoint(beforePoint, rowMode, 1))){
                    this.queue.add(afterPoint);
                    afterPoint = new DronePoint(beforePoint, rowMode, 0);
                    afterPoint.x0++;
                    afterPoint.y0 = afterPoint.smallerPoint();
                    afterPoint.y1 = afterPoint.smallerPoint();
                    afterPoint.mode = rowMode;
                    this.queue.add(afterPoint);
                    setTick(afterPoint);
                    this.queue.add(afterPoint = new DronePoint(afterPoint, columnMode, 1));
                    setTick(afterPoint);
                }
            }
        } else if (beforePoint.mode == rowMode){ //rowMode
            if (isValidPosition(beforePoint.biggerPoint() + 1, beforePoint.y0)){
                if (isValidPosition(afterPoint = new DronePoint(beforePoint, rowMode, 1))){
                    this.queue.add(afterPoint);
                }
            } if (isValidPosition(beforePoint.smallerPoint() - 1, beforePoint.y0)){
                if (isValidPosition(afterPoint = new DronePoint(beforePoint, rowMode, -1))){
                    this.queue.add(afterPoint);
                }
            } if (isValidPosition(beforePoint.x0, beforePoint.y0 - 1)
                    && isValidPosition(beforePoint.x1, beforePoint.y1 - 1)){
                if (isValidPosition(afterPoint = new DronePoint(beforePoint, columnMode, -1))){
                    this.queue.add(afterPoint);
                    afterPoint = new DronePoint(beforePoint, columnMode, 0);
                    afterPoint.x0 = afterPoint.smallerPoint();
                    afterPoint.x1 = afterPoint.smallerPoint();
                    afterPoint.y0--;
                    afterPoint.mode = columnMode;
                    this.queue.add(afterPoint);
                    setTick(afterPoint);
                    this.queue.add(afterPoint = new DronePoint(afterPoint, rowMode, 1));
                    setTick(afterPoint);
                }

            } if (isValidPosition(beforePoint.x0, beforePoint.y0 + 1)
                    && isValidPosition(beforePoint.x1, beforePoint.y1 + 1)){
                if (isValidPosition(afterPoint = new DronePoint(beforePoint, columnMode, 1))){
                    this.queue.add(afterPoint);
                    afterPoint = new DronePoint(beforePoint, columnMode, 0);
                    afterPoint.x0 = afterPoint.smallerPoint();
                    afterPoint.x1 = afterPoint.smallerPoint();
                    afterPoint.y0++;
                    afterPoint.mode = columnMode;
                    this.queue.add(afterPoint);
                    setTick(afterPoint);
                    this.queue.add(afterPoint = new DronePoint(afterPoint, rowMode, 1));
                    setTick(afterPoint);
                }
            }
        } else {
            System.err.println("mode is "+beforePoint.mode);
        }
    }
}
class DronePoint {
    int columnMode = 1, rowMode = -1;
    public int x0, y0, x1, y1, mode, tick;
    public DronePoint(DronePoint before, int way, int value){
        if (way == columnMode){
            this.x0 = before.x0;
            this.x1 = before.x1;
            this.y0 = before.y0 + value;
            this.y1 = before.y1 + value;
        } else if (way == rowMode){
            this.x0 = before.x0 + value;
            this.x1 = before.x1 + value;
            this.y0 = before.y0;
            this.y1 = before.y1;
        } else {
            System.err.println("way point is "+way);
        }
        this.tick = before.tick + 1;
        this.mode = before.mode;
    }

    public DronePoint(int x0, int y0, int x1, int y1, int mode, int tick){
        this.x0 = x0;
        this.x1 = x1;
        this.y0 = y0;
        this.y1 = y1;
        this.mode = mode;
        this.tick = tick;
    }
    public int biggerPoint(){
        if (mode == columnMode)
            return Math.max(this.y0, this.y1);
        else
            return Math.max(this.x0, this.x1);
    }
    public int smallerPoint(){
        if (mode == columnMode)
            return Math.min(this.y0, this.y1);
        else
            return Math.min(this.x0, this.x1);
    }
}

class MainApp {
    public static void main(String[] args){
        int[][] arr = {{0, 0, 0, 1, 1},{0, 0, 0, 1, 0},{0, 1, 0, 1, 1},{1, 1, 0, 0, 1},{0, 0, 0, 0, 0}};
        Solution s = new Solution();
        System.out.println(s.solution(arr));
    }

}
