package Hotel_Booking;

import java.util.*;
public class Solution {
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        Map<Long, Long> bookedRoom = new HashMap(); //present room, recommend room
        long recommend, present;
        for (int i = 0; i < room_number.length; i++) {
            if(bookedRoom.containsKey(room_number[i])){
                present = room_number[i];
                recommend = bookedRoom.get(present);
                while (true){
                    present = recommend;
                    if(!bookedRoom.containsKey(present)){
                        bookedRoom.put(recommend, recommend + 1);
                        bookedRoom.replace(room_number[i], recommend + 1);
                        answer[i] = recommend;
                        break;
                    }
                    //recommend = bookedRoom.get(present);
                }
            }
            else {
                bookedRoom.put(room_number[i], room_number[i] + 1);
                answer[i] = room_number[i];
            }
        }
        //System.out.println(Arrays.toString(answer));
        return answer;
    }
}

class MainApp{
    public static void main(String[]args){
        long[] room_number = {1,3,4,1,3,1};
        long k = 10;
        Solution s = new Solution();
        s.solution(k, room_number);
    }
}
