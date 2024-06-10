import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        
        for(int i = 0; i< works.length; i++){
           queue.add(works[i] * -1); 
        }
        
        int i = 0; 
        while(i<n){
            int num = queue.poll();
            if(num == 0){
                break;
            }
            queue.add(num + 1);
            i++;
        }
        
        while(!queue.isEmpty()){
            long num = Long.valueOf(queue.poll());
            answer += num * num;
        }
        
        return answer;
    }
}