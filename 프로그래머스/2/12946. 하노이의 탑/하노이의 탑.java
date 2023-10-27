import java.util.*;
class Solution {
    int[][] answer;
    int i;
    public int[][] solution(int n) {
        int len = (int) Math.pow(2,n) - 1;
        answer = new int[len][2];
        i = 0;
        hanoi(n, 1, 2, 3);
        
        return answer;
        
    }
    
    public void move(int n, int start, int end){
        answer[i][0] = start;
        answer[i++][1] = end;
    }
    
    public void hanoi(int n, int start, int transit, int end){
        if(n == 1){
            move(n, start, end);
        }
        
        else{
            hanoi(n-1, start, end, transit);
            move(n, start, end);
            hanoi(n-1, transit, start, end);
        }
    }
    
}