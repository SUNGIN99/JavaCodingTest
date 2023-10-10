import java.util.*;

class Solution {
    
    class Process {
        int seq;
        int priority;
        
        public Process(int seq, int priority){
            this.seq = seq;
            this.priority = priority;
        }
    }
    
    public int solution(int[] priorities, int location) {
        Queue<Process> queue = new LinkedList<>();
        Integer[] sortedP = new Integer[priorities.length];
        for(int i = 0; i<priorities.length; i++){
            queue.add(new Process(i, priorities[i]));
            sortedP[i] = priorities[i];
        }
        
        Arrays.sort(sortedP, Collections.reverseOrder());
        int i = 0, seq = 1;
        ArrayList<Integer> result = new ArrayList<>();
        while(!queue.isEmpty()){
            Process now = queue.poll();
            
            if(now.priority != sortedP[i]){
                queue.add(now);
            }else{
                result.add(now.seq);
                
                if(location == now.seq){
                    return seq;
                }
                
                i++; seq++;
            }
        }
        
        return 0;
    }
}