import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        
        int[] clothes = new int[n+1];
        
        for(int i = 0; i < lost.length; i++){
            clothes[lost[i]]--;
        }
        
        for(int i = 0; i< reserve.length; i++){
            clothes[reserve[i]]++;
        }
        
        for(int i = 1; i < n; i++){
            if(clothes[i] == -1 && clothes[i-1] == 1){
                clothes[i]++;
                clothes[i-1]--;
            }
            
            else if(clothes[i] == -1 && clothes[i+1] == 1){ // 마지막 학생이 없을 때도 고려해야함. (i > n)
                clothes[i]++;
                clothes[i+1]--;
            }
        }
            
        if(clothes[n] == -1 && clothes[n-1] == 1){
            clothes[n]++;
            clothes[n-1]--;
        }
        
        for(int i = 1; i <= n; i++){
            if(clothes[i] >= 0){
                answer++;
            }
        }
        return answer;
    }
}