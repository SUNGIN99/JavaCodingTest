class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int wide, tall, carpetBrown;
        for(int i = 1; i <= Math.sqrt(yellow); i++){
            if(yellow % i == 0){
                wide = yellow / i;
                tall = yellow / wide;
                
                carpetBrown = (tall * 2) + (wide * 2)  + 4;
                
                if(carpetBrown == brown){
                    answer[0] = wide + 2;
                    answer[1] = tall + 2;
                    break;
                }
            }
        }
        
        
        return answer;
    }
    
}