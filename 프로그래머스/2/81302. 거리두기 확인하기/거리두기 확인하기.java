import java.util.*;
class Solution {
    
    String[][] waitRoom;
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        for(int i = 0; i<places.length; i++){
            waitRoom = new String[5][];
            for(int j = 0; j<places[0].length; j++){
                char[] waitChar = places[i][j].toCharArray();
                waitRoom[j] = new String[5];
                for(int k = 0; k<5; k++){
                    waitRoom[j][k] = ""+ waitChar[k];
                }
                //System.out.println(Arrays.toString(waitRoom[j]));
            }
            //System.out.println(i + "-------");
            answer[i] = distance();
            //System.out.println();
        }
        
        return answer;
    }
    
    int distance(){
        for(int i = 0; i<5; i++){
            for(int j = 0; j<5; j++){
                if(!waitRoom[i][j].equals("P")) continue;
                // 대각선 위에도 바라봐야하는데 안바라봄..
                // System.out.println(i+ ", " + j);
                if(i+1 < 5 && waitRoom[i+1][j].equals("P")){
                    return 0;
                }
                if(j+1 < 5 && waitRoom[i][j+1].equals("P")){
                    return 0;
                }
                if(i+1<5 && j+1<5 && waitRoom[i+1][j+1].equals("P")){
                    if(!waitRoom[i+1][j].equals("X") || !waitRoom[i][j+1].equals("X")){
                        return 0;
                    }
                }
                
                // 여기서 실수
                if(i-1>= 0 && j+1 < 5 && waitRoom[i-1][j+1].equals("P")){
                    if(!waitRoom[i-1][j].equals("X") || !waitRoom[i][j+1].equals("X")){
                        return 0;
                    }
                }
                
                if(i+2<5 && waitRoom[i+2][j].equals("P")){
                    if(!waitRoom[i+1][j].equals("X")){
                        return 0;
                    }
                }
                if(j+2<5 && waitRoom[i][j+2].equals("P")){
                    if(!waitRoom[i][j+1].equals("X")){
                        return 0;
                    }
                }
            }
        }
        return 1;
    }
}