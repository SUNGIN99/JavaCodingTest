class Solution {
    public int[] solution(int n) {
        int nums = (n * (n + 1)) / 2;
        int[] answer = new int[nums];
        
        
        int number = 0;
        int index = 0;
        int append = 0;
        while(n != 0 && number < nums){
            for(int i = 0; i< n; i++){ // 왼쪽 밑으로
                //System.out.print(index + ", ");
                answer[index] = ++number;
                if(i < n-1)
                    index += (append++) + 1;
            }
            n--;
            
            for(int i =0; i< n; i++){ // 밑변 오른쪽
                
                index++;
                //System.out.print(index + ", ");
                answer[index] = ++number;
            }
            n--;
            
            for(int i = 0; i<n; i++){ // 왼쪽 위로
                
                index -= ((append--) + 1);
                
                //System.out.print(index + ", ");
                answer[index] = ++number;
            }
            n--;
            index += (append++) + 1;
        }
        
            
        return answer;
    }
}