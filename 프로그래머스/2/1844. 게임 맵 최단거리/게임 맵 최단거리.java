import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int answer = 0;
        
        int[][] visited;
        int n = maps.length;
        int m = maps[0].length;
        
        
        visited = new int[n][];
        for(int i = 0; i<n; i++){
            visited[i] = new int[m];
            for(int j = 0; j< m; j++){
                if(maps[i][j] == 1){
                    visited[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        
        Queue<int[]> queue = new LinkedList<>();
        visited[0][0] = 1;
        queue.add(new int[]{0, 0, 1});
        
        while(!queue.isEmpty()){
            int[] currIndex = queue.poll();
            
            int row = currIndex[0];
            int col = currIndex[1];
            int dist = currIndex[2];
            
            
            if(maps[row][col] == 0){
                continue;
            }
            
            if(row < n - 1 && visited[row+1][col] > dist + 1){
                visited[row+1][col] = dist + 1;
                queue.add(new int[]{row + 1, col, dist + 1});
            }
            
            if(row > 0 && visited[row-1][col] > dist + 1){
                visited[row-1][col] = dist + 1;
                queue.add(new int[]{row - 1, col, dist + 1});
            }
            
            if(col < m - 1 && visited[row][col+1] > dist + 1){
                visited[row][col+1] = dist + 1;
                queue.add(new int[]{row, col + 1, dist + 1});
            }
            
            if(col > 0 && visited[row][col-1] > dist + 1){
                visited[row][col-1] = dist + 1;
                queue.add(new int[]{row, col - 1, dist + 1});
            }
        }
            
            
        //for(int i = 0; i<n; i++){
        //    System.out.println(Arrays.toString(visited[i]));
        //}
        
        answer = visited[n-1][m-1] == Integer.MAX_VALUE ? -1 : visited[n-1][m-1];
        return answer;
    }
   
}