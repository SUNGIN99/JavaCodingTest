import java.util.*;

class Solution {
    
    HashMap<Integer, ArrayList<Integer[]>> graph = new HashMap<>();
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        for(int i = 0; i<road.length; i++){
            int a = road[i][0];
            int b = road[i][1];
            int c = road[i][2];
            
            ArrayList<Integer[]> nodeInfoA = graph.getOrDefault(a, new ArrayList<>());
            nodeInfoA.add(new Integer[]{b, c});
            graph.put(a, nodeInfoA);
            
            ArrayList<Integer[]> nodeInfoB = graph.getOrDefault(b, new ArrayList<>());
            nodeInfoB.add(new Integer[]{a, c});
            graph.put(b, nodeInfoB);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        int[] visited = new int[N+1];
        
        while(!queue.isEmpty()) {
            int node = queue.poll();
            ArrayList<Integer[]> route = graph.get(node);
            for(Integer[] way : route){
                if(way[0] == 1){
                    continue;
                }
                //System.out.println(node + ": " + way[0] + ", " + way[1]);
                if (visited[way[0]] == 0 || visited[way[0]] > visited[node] + way[1]){
                    visited[way[0]] = visited[node] + way[1];
                    queue.add(way[0]);
                }
                /*else {
                    if(visited[way[0]] > visited[node] + way[1]){
                        visited[way[0]] = visited[node] + way[1];
                    }
                }*/
            }
        }
        
       //System.out.println(Arrays.toString(visited));
        
        for (int i = 1; i<= N; i++){
            if(visited[i] <= K)
                answer ++;
        }
        
        return answer;
    }
}