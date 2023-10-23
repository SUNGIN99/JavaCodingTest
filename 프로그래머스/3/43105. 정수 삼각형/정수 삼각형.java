class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int tLength = triangle.length;
        int[][] DP = new int[tLength][];
        
        DP[tLength-1] = new int[triangle[tLength-1].length];
        
        for(int i = 0; i < triangle[tLength-1].length; i++){
            DP[tLength-1][i] = triangle[tLength-1][i];
        }
        
        for(int i = tLength-2; i>= 0; i--){
            DP[i] = new int[triangle[i].length];
            for(int j = 0; j< DP[i].length; j++){
                DP[i][j] = triangle[i][j] + Math.max(DP[i+1][j], DP[i+1][j+1]);
            }
        }
        
        return DP[0][0];
    }
}