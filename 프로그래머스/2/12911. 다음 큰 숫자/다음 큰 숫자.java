class Solution {
    public int solution(int n) {
        int answer = 0;
        int oneCount = 0;
        for(char b : Integer.toBinaryString(n).toCharArray()){
            if(b == '1'){
                oneCount++;
            }
        }
        
        int i;
        for(i = n+1; i<1000000; i++){
            int checkCount = getBinaryNum(i);
            if(oneCount == checkCount){
                break;
            }
        }
        answer= i;
        return answer;
    }
    
    int getBinaryNum(int num){
        String binary = Integer.toBinaryString(num);
        
        int oneCount = 0;
        for(char b : binary.toCharArray()){
            if(b == '1'){
                oneCount++;
            }
        }
        
        return oneCount;
    }
}