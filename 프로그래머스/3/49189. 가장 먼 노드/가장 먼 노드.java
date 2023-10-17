import java.util.*;
class Solution {
    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    int maxVertex = 0;
    int[] visited;
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        visited = new int[n+1];
        visited[1] = 1;
        
        for(int i = 0; i< n+1; i++){
            ArrayList<Integer> a = new ArrayList<>();
            graph.add(a);
        }
        
        for(int i = 0; i< edge.length; i++){
            int a = edge[i][0];
            int b = edge[i][1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        
        for(int i = 1; i<= n; i++){
            Collections.sort(graph.get(i));
        }
        
        search(1);
        //System.out.println(Arrays.toString(visited));
        //System.out.println(maxVertex);
         for(int i = 1; i<=n; i++){
            if(visited[i] == maxVertex){
                answer++;
            }
        }
            
        return answer;
    }
    
    public void search(int start){
        Queue<Integer> queue = new LinkedList();
       
        queue.add(start);
        visited[start] = 1;
        
        while(!queue.isEmpty()){
            int node = queue.poll();
            ArrayList<Integer> temp = graph.get(node);
            for(int i = 0; i<temp.size(); i++){
                int next = temp.get(i); // 다음노드
                
                if(visited[next] == 0 || visited[next] > visited[node] + 1){ // 이미 방문한 노드 제거
                    visited[next] = visited[node] + 1;
                    queue.add(next);
                    if(maxVertex < visited[node]+1){
                        maxVertex = visited[node] +1;
                    }
                }
            }
        }
    }
    
}