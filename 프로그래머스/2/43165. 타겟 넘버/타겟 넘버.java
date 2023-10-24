class Solution {
    int[] numberArr;
    int arrLen, answer;
    public int solution(int[] numbers, int target) {
        answer = 0;
        numberArr = numbers;
        arrLen = numbers.length;
        
        dfs(0, 0, target);
        
        return answer;
    }
    
    public void dfs(int num, int currentIndex, int target){
        if (currentIndex == arrLen){
            if(num == target)
                answer++;
        }else{
            dfs(num + numberArr[currentIndex], currentIndex+1, target);
            dfs(num - numberArr[currentIndex], currentIndex+1, target);
        }
        
    }
}