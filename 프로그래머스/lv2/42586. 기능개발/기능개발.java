import java.util.ArrayList;
class Solution {
     public int[] solution(int[] progresses, int[] speeds) {
        int len = progresses.length;
        // 길이가 1일때는 배포 하는날이 하루밖에 없음.
        if(len == 1){
            return new int[]{1};
        }
        
        // 결과를 담을 배포하는 날에 작업 수
        ArrayList<Integer> answer = new ArrayList<>();

        // 각 결과물의 작업속도 당 배포까지 남은 일 수를 구한다.
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

        // i번째 필요 작업 일 수가 i+1번째 작업의 작업 일 수보다 작다면, 배포가 먼저됨.
        // i 번쨰 필요 작업 일 수가 i+1 번쨰 작업의 작업 일 수보다 크다면, 배포가 같이 됨.
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