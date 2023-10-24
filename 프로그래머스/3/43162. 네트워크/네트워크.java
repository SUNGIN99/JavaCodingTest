import java.util.*;
class Solution {
    int[] visited;
    int[][] computerArr;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        computerArr = computers;
        visited = new int[n];
        for(int i = 0; i<n; i++){
            visited[i] = -1;
        }
        
        for(int i = 0; i<n; i++){
            dfs(i, computers[i]);
        }
        
        HashMap<Integer, Integer> result = new HashMap<>();
        
        for(int i = 0; i<n; i++){
            if(!result.containsKey(visited[i])){
                result.put(visited[i], 0);
            }
        }
        
        return result.size();
    }
    
    public void dfs(int currentNode, int[] nextNodes){
        if(visited[currentNode] == -1){
            // 첫 방문 시
            visited[currentNode] = currentNode;
        }
        
        
        for(int i = 0; i<nextNodes.length; i++){
            if(i == currentNode){
                continue;
            }
            
            if(nextNodes[i] == 1 && visited[i] == -1){
                // 나한테서 이어지는 노드 중에 아직 방문 안한 노드 일 경우
                visited[i] = currentNode;
                if(visited[currentNode] != currentNode){
                    // 근데 내가 뿌리가 아닌 경우
                    visited[i] = visited[currentNode];
                }
                dfs(i, computerArr[i]);
            }
        }
    }
}