import java.util.*;
class Solution {
    char[][] island;
    public ArrayList<Integer> solution(String[] maps) {
        island = new char[maps.length][];
        
        // 섬 모양 2차원 배열
        for(int i = 0; i<maps.length; i++){
            island[i] = maps[i].toCharArray();
        }
        
        // 배열 상, 하, 좌, 우 탐색하기
        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0; i<island.length; i++){
            for(int j = 0; j<island[i].length; j++){
                if(island[i][j] != 'X'){
                    int land = dfs(i, j);
                    if(land != 0){
                        result.add(land);
                    }
                }
            }
        }
        
        if(result.size() == 0){
            result.add(-1);
        }
        else{
            Collections.sort(result);
        }
        
        
        return result;
    }
    
    public int dfs(int row, int col){
        if(row < 0 || row >= island.length){
            return 0;
        }
        if(col < 0 || col >= island[0].length){
            return 0;
        }
        if(island[row][col] == 'X'){
            return 0;
        }
        
        // 탐색 후 탐색한 배열 'x'로 만들어서 재귀 방쥐
        // char이라서 값 처리 잘 해줘야함.
        int islandSize = Integer.parseInt(String.valueOf(island[row][col]));
        island[row][col] = 'X';
        
        islandSize += dfs(row - 1, col);
        islandSize += dfs(row + 1, col);
        islandSize += dfs(row, col - 1);
        islandSize += dfs(row, col + 1);
        
        return islandSize;
        
        
    }
    
}