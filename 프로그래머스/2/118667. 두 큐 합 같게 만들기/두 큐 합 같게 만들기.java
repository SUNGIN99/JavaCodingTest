import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        Queue<Integer> check1 = new LinkedList<>();
        Queue<Integer> check2 = new LinkedList<>();
        long sum1 = 0, sum2 = 0;
        for(int i = 0 ; i<queue1.length; i++){
            sum1 += queue1[i];
            sum2 += queue2[i];
            q1.add(queue1[i]);
            q2.add(queue2[i]);
            check1.add(queue1[i]);
            check2.add(queue2[i]);
        }
        
        int count = 0;
        while(sum1 != sum2){
            int popNum;
            if (sum1 > sum2 && !q1.isEmpty()){
                popNum = q1.poll();
                q2.add(popNum);
                sum1 -= popNum;
                sum2 += popNum;
            }else if(sum1 < sum2 && !q2.isEmpty()) {
                popNum = q2.poll();
                q1.add(popNum);
                sum1 += popNum;
                sum2 -= popNum;
            }
            
            if(count > queue1.length * 8){
                return -1;
            }
            
            count++;
        }
        
        return count;
    }
}