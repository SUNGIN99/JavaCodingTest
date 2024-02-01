import java.util.*;
class Solution {
    public int solution(int n, int[] tops) {
        int answer = 0;
        
        int[] dp = new int[n];
        int[] stack = new int[n];
        
        dp[0] = tops[0] == 1 ? 4 : 3;
        stack[0] = tops[0] == 1 ? 3 : 2;
        
        if(n >= 2){
            dp[1] = dp[0] * (tops[1] == 1 ? 3 : 2) + (tops[0] == 1 ? 3 : 2);
            stack[1] = (tops[1] == 1 ? 2 : 1) * dp[0] + stack[0];
        }
        
        for(int i = 2; i < n; i++){
            if(tops[i] == 1){ // 현재 타일에 뿔이 붙어 있다면?
                dp[i] = dp[i-1] * 3;
                stack[i] = dp[i-1] * 2 + stack[i-1];
            }else{
                dp[i] = dp[i-1] * 2;
                stack[i] = dp[i-1] + stack[i-1];
            }
            
            dp[i] += stack[i-1];
            
            dp[i] %= 10007;
            
        }
        
        //System.out.println(Arrays.toString(stack));
        //System.out.println(Arrays.toString(dp));
        answer = dp[n-1];
        return answer;
    }
}