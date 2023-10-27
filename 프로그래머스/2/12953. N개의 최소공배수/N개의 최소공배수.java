import java.util.*;
class Solution {
    public int solution(int[] arr) {
        int answer = 0;
        
        if(arr.length == 1){
            return arr[0];
        }
        
        Arrays.sort(arr);
        answer = getCommonDiv(arr[0], arr[1]);
        
        if(arr.length > 2){
            for(int i =2; i <arr.length; i++){
                answer = getCommonDiv(answer, arr[i]);
            }
        }
        
        return answer;
    }
    
    public int getCommonDiv(int num1, int num2){
        if(num1 > num2){
            int temp = num1;
            num1 = num2;
            num2 = temp;
        }
        
        if(num2 % num1 == 0){ // 큰수에 작은수를 나눈 나머지가 0이라면 큰수가 최소 공배수
            return num2;
        }
        
        int commonDiv = 1;
        for(int i = 2; i <= num1 / 2; i++){
            if(num1 % i == 0 && num2 % i == 0){
                commonDiv = i;
            }
        }
        return num1 * num2 / commonDiv;
        
        
    }
}