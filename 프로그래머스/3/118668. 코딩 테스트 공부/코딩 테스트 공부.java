class Solution {
    static int[][] visited;
    static int maxAlp, maxCop;
    public int solution(int alp, int cop, int[][] problems) {
        
        int maxAlp = alp, maxCop = cop;
        for(int i = 0; i<problems.length; i++){
            maxAlp = Math.max(maxAlp, problems[i][0]);
            maxCop = Math.max(maxCop, problems[i][1]);
        }
        if(alp >= maxAlp && cop >= maxCop){
            return 0;
        }
        
        visited = new int[maxAlp+1][maxCop+1];
        for(int i = alp; i<= maxAlp; i++){
            for(int j = cop; j<=maxCop; j++){
                visited[i][j] = i-alp + j-cop;
            }
        }
        
        for(int i = alp; i<=maxAlp; i++){
            for(int j = cop; j<=maxCop; j++){
                if (i+1 <= maxAlp) visited[i+1][j] = Math.min(visited[i][j] + 1, visited[i+1][j]);
                if (j+1 <= maxCop) visited[i][j+1] = Math.min(visited[i][j] + 1, visited[i][j+1]);
                
                 for(int[] p : problems){
                    int alpReq = p[0];
                    int copReq = p[1];
                    int alpAdd = p[2];
                    int copAdd = p[3];
                    int time = p[4];
                     
                    if(i >= alpReq && j>=copReq){
                        if(i+alpAdd <= maxAlp && j+copAdd <= maxCop){
                            visited[i+alpAdd][j+copAdd] = Math.min(visited[i+alpAdd][j+copAdd], visited[i][j]+time);
                        }
                        else if(i+alpAdd > maxAlp && j+copAdd <= maxCop){
                             visited[maxAlp][j+copAdd] = Math.min(visited[maxAlp][j+copAdd], visited[i][j] + time);
                        } 
                        else if(j+copAdd > maxCop && i+alpAdd <= maxAlp){
                             visited[i+alpAdd][maxCop] = Math.min(visited[i+alpAdd][maxCop], visited[i][j] + time);
                        }else{
                            visited[maxAlp][maxCop] = Math.min(visited[maxAlp][maxCop], visited[i][j] + time);
                        }
                     }
                 }
                //System.out.println(i+ ", "+ j + ", " + visited[i][j]);
            }
        }
        
        
        return visited[maxAlp][maxCop];
    }
    
    

}