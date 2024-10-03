import java.util.*;
class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[]{0, 0, 0, 0};
        
        Map<Integer, ArrayList<Integer>> output = new HashMap<>();
        Map<Integer, ArrayList<Integer>> input = new HashMap<>();
        
        for(int i = 0; i<edges.length; i++){
            int from = edges[i][0];
            int to = edges[i][1];
            
            ArrayList<Integer> toList = output.getOrDefault(from, new ArrayList<>());
            toList.add(to);
            output.put(from, toList);
            
            ArrayList<Integer> fromList = input.getOrDefault(to, new ArrayList<>());
            fromList.add(from);
            input.put(to, fromList);
        }
    
        for(Integer key : output.keySet()){
            if(output.get(key).size() >= 2 && !input.containsKey(key)){
                answer[0] = key;
                break;
            }
        }
        
        //System.out.println(output);
        //System.out.println(input);
        ArrayList<Integer> originGraphs = output.get(answer[0]);
        
        for(Integer target : originGraphs){
            
            Queue<Integer> queue = new LinkedList<>();
            queue.add(target);
            
            Map<Integer, Boolean> visited = new HashMap<>();
            int node = 1;
            int vertex = 0;
            visited.put(target, true);
            
            while(!queue.isEmpty()){
                int curNode = queue.poll();
                ArrayList<Integer> nextNodes = output.getOrDefault(curNode, new ArrayList<>());
                
                for(Integer next : nextNodes){
                    if(next != answer[0]){
                        if(!visited.containsKey(next)){
                            queue.add(next);
                            visited.put(next, true);
                            node ++;
                        }
                        vertex++;
                    }
                }
            }
            
            if(node + 1 == vertex){
                answer[3] ++;
            }else if(node -1 == vertex){
                answer[2]++;
            }else{
                answer[1]++;
            }
        }
        return answer;
    }
}