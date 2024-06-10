class Solution {
    public int solution(int n) {
        int answer = 0;
        
        if(n == 1){
            return 1;
        }else if(n== 2){
            return 1;
        }
        
        
        int start = 1, end = 2;
        int sum = start + end;
        while(end <= n){
            //System.out.println(sum);
            if(sum < n){
                sum += ++end;
            }else if(sum > n){
                sum -= start++;
            }else{
                answer++;
                sum += ++end;
            }
        }
        
        return answer;
    }
}