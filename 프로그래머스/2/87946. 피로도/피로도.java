class Solution {
    
    int canGo = 0;
    int[][] dungeonsG;
    boolean[] visited;
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        dungeonsG = dungeons;
        visited = new boolean[dungeons.length];
        
        for(int i = 0; i<dungeons.length ;i++){
            int need = dungeons[i][0];    
            int waste = dungeons[i][1];
            if(k >= need){
                dfs(k - waste, i, 1);
            }
        }
        
        answer = canGo;
        return answer;
    }
    
    void dfs(int k, int index, int traveled){
        //System.out.println(k +" , " + index + ", " + traveled);
        if(visited[index]){
            return;
        }
        
        visited[index] = true;
        canGo = Math.max(canGo, traveled);
        
        for(int i = 0; i<dungeonsG.length ;i++){
            if(visited[i]){
                continue;
            }
            int need = dungeonsG[i][0];    
            int waste = dungeonsG[i][1];
            if(k >= need){
                dfs(k - waste, i, traveled+1);
            }
        }
        
        visited[index] = false;
    }
}