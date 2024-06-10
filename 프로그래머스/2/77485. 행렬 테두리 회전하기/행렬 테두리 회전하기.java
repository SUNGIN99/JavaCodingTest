import java.util.*;
class Solution {
    int[][] matrix;
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        
        matrix = new int[rows][];
        int num = 1;
        for(int i = 0; i<rows; i++){
            matrix[i] = new int[columns];
            for(int j = 0; j<columns; j++){
                matrix[i][j] = num++;
            }
            //System.out.println(Arrays.toString(matrix[i]));
        }
        
        int index = 0;
        for(int[] q : queries){
            answer[index++] = rotation(q[0]-1, q[1]-1, q[2]-1, q[3]-1);
            /*for(int i = 0; i<rows; i++){
                System.out.println(Arrays.toString(matrix[i]));
            }
            System.out.println();*/
        }
        
        
        
        return answer;
    }
    
    int rotation(int x1, int y1, int x2, int y2){
        int first;
        int minNum = first = matrix[x1][y1];
        
        for(int i = x1; i < x2; i++){
            minNum = Math.min(minNum, matrix[i][y1] = matrix[i+1][y1]);
            //System.out.println(matrix[i+1][y1]);
        }
        //System.out.println();
        
        for(int i = y1; i<y2; i++){
            minNum = Math.min(minNum, matrix[x2][i] = matrix[x2][i+1]);
            //System.out.println(matrix[x2][i+1]);
        }
        
        for(int i = x2; i > x1; i--){
            minNum = Math.min(minNum, matrix[i][y2] = matrix[i-1][y2]);
            //System.out.println(matrix[i-1][y2]);
        }
        
        for(int i = y2; i> y1; i--){
            minNum = Math.min(minNum, matrix[x1][i] = matrix[x1][i-1]);
            //System.out.println(matrix[x1][i-1]);
        }
        
        matrix[x1][y1+1] = first;
        return minNum;
    }
}