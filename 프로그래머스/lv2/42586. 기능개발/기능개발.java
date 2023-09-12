import java.util.ArrayList;
class Solution {
     public int[] solution(int[] progresses, int[] speeds) {
        int len = progresses.length;

        if(len == 1){
            return new int[]{1};
        }

        ArrayList<Integer> answer = new ArrayList<>();

        // 남은 일수를 담기.
        int [] leftDays = new int[len];
        int integer, remain, leftday;
        for(int i = 0; i<len; i++){
            leftday = 100 - progresses[i];
            integer = leftday / speeds[i];
            remain = leftday % speeds[i];

            if(remain != 0){
                integer += 1;
            }
            leftDays[i] = integer;
        }


        int count = 1, prev = leftDays[0];
        for(int i = 1; i<len; i++){
            if(prev >= leftDays[i]){
                count++;
            }else{ // leftDays[i-1]< leftDays[i]
                answer.add(count);
                prev = leftDays[i];
                count = 1;
            }
        }
        answer.add(count);
        return answer.stream().mapToInt(i->i).toArray();
    }
}