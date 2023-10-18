class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int rowMax = 0, colMax = 0;
        
        int row, col;
        for(int i = 0; i<sizes.length; i++){
            row = sizes[i][0];
            col = sizes[i][1];
            
            if(row < col){
                int temp = col;
                col = row;
                row = temp;
            }
            
            if (rowMax < row){
                rowMax = row;
            }
            
            if (colMax < col){
                colMax = col;
            }            
        }
        
        
        return rowMax * colMax;
    }
}