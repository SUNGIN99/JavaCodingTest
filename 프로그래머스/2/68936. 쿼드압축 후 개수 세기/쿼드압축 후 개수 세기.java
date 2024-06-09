class Solution {
    static int one = 0, zero = 0;
    static int[][] arrG;
    public int[] solution(int[][] arr) {
        int[] answer;
        arrG = arr;
        square(0, 0, arr.length);
        answer = new int[]{zero, one};
        return answer;
    }
    
    public void square(int standX, int standY, int limit){
        if(limit == 1){
            int whatis = arrG[standX][standY];
            if(whatis == 0){
                zero++;
            }else{
                one++;
            }
            return;
        }
        
        int onlyOne = 0, onlyZero = 0;
        for(int i = 0; i<limit; i++){
            for(int j = 0; j<limit; j++){
                if(arrG[standX+i][standY+j] == 0){
                    onlyZero++;
                }else{
                    onlyOne++;
                }
            }
        }
        
        if(onlyOne == 0){
            zero ++;
        }else if(onlyZero == 0){
            one ++;
        }else{
            square(standX, standY, limit/2);
            square(standX + limit/2, standY, limit/2);
            square(standX, standY + limit/2, limit/2);
            square(standX + limit/2, standY + limit/2, limit/2);
        }
        
    }
    
    
}