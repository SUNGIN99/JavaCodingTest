import java.util.*;
class Solution {
    ArrayList<ArrayList<Integer>> graph;
    int[] visited;
    public int solution(int n, int[][] wires) {
        int answer = n+1;
        graph = new ArrayList<>();
        for(int i = 0; i<= n; i++){
            graph.add(new ArrayList<>());
        }
        
        // 특정 노드에 연결된 노드 수 확인
        for(int[] wire : wires){
            int a = wire[0];
            int b = wire[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
         //System.out.println(graph);
        //
        for(int[] wire : wires){
            int a = wire[0];
            int b = wire[1];
            int divDiff = div(a,b,n);
            if (answer > divDiff){
                answer = divDiff;
            }
        }
        
        
        return answer;
    }
    
    public int div(int root1, int root2, int n){
        visited = new int[n+1];
        int rooted1 = dfs(root1, root2);
        visited = new int[n+1];
        int rooted2 = dfs(root2, root1);
        
        //System.out.println(rooted1 + ", " + rooted2);
        
        return Math.abs(rooted1 - rooted2);
    }
    
    public int dfs(int root, int divRoot){
       //System.out.println(Arrays.toString(visited));
        if(visited[root] == 1)
            return 0;
        
        visited[root] = 1;
        if(graph.get(root).size() == 1){
            return 1;
        }
        
        int child = 0;
        for(Integer next: graph.get(root)){
            if(next != root && next != divRoot)
                child += dfs(next, divRoot);
        }
        return child+1;
    }
}