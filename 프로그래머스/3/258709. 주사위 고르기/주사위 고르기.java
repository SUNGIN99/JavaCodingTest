import java.util.*;
class Solution {
    
    int[][] dice_;
    int n;
    boolean[] myDice;
    Map<String, ArrayList<Integer>> scores;
    Map<String, String> abMap;
    public int[] solution(int[][] dice) {
        int[] answer;
        
        n = dice.length;
        dice_ = dice;
        
        scores = new TreeMap<>();
        abMap = new HashMap<>();
        for(int i = 0; i<=n/2; i++){
            myDice = new boolean[n];
            myDice[i] = true;
            
            selectDice(i, 1);
        }
        
        //System.out.println(scores);
        // 3. 선택한 경우의 수 중 이기는 확률 모두 구하기
        ArrayList<String> abab = new ArrayList<>(scores.keySet());
        int maxWin = 0;
        String maxComb = "";
        for(int i = 0; i< abab.size(); i++){
            String aKey = abab.get(i);
            String bKey = abMap.get(aKey);
            //System.out.println(key + " vs " + abMap.get(key));
            
            ArrayList<Integer> aResult = scores.get(aKey);
            ArrayList<Integer> bResult = scores.get(bKey);
            //System.out.println(aResult);
            //System.out.println(bResult);
            int pointerA = 0, pointerB = 0;
            int canWin = 0, canLose = 0, canDraw = 0;
            int va, vb;
            for(pointerA= 0 ; pointerA<aResult.size(); pointerA++){
                va = aResult.get(pointerA);  
                int nowDraw = 0;
                while(pointerB < bResult.size()){
                    vb = bResult.get(pointerB);
                    if(va > vb){
                        pointerB++;
                    }else if(va == vb){
                        nowDraw ++;
                        break;
                    }else { // va < vb
                        break;
                    }
                }
                canWin += pointerB;
                //System.out.print((pointerB) + " ");

            }
            //System.out.println(aKey + " : " + canWin);
            if(maxWin < canWin){
                maxWin = canWin;
                maxComb = aKey;
            }
           
        }
        //System.out.println(maxComb);
        maxComb = maxComb.substring(1, maxComb.length() - 1);
        //System.out.println(maxComb);
        String[] please = maxComb.split(", ");
        
        answer = new int[please.length];
        for(int i = 0; i<please.length; i++){
            answer[i] = Integer.parseInt(please[i]) + 1;
        }
        return answer;
    }
    
    // 1. 주사위 선택 경우의 수 구하기
    void selectDice(int prevIndex, int selectCnt){
        if(selectCnt == n/2){
            getDiceComb();
            //System.out.println(Arrays.toString(myDice));
            return;
        }
    
        for(int i = prevIndex + 1; i < n; i++){
            myDice[i] = true;
            selectDice(i, selectCnt + 1);
            myDice[i] = false;
        }
    }
    
    // 2. 선택한 주사위 합 경우의 수 모두 구하기
    ArrayList<Integer> aScore, bScore;
    void getDiceComb(){
        aScore = new ArrayList<>();
        
        int[] aDice = new int[n/2];
        int[] bDice = new int[n/2];
        
        int ai = 0, bi=0;
        for(int i = 0; i<n; i++){
            if(myDice[i]){
                aDice[ai++] = i;
            }else{
                bDice[bi++] = i;
            }
        }
        //System.out.println(Arrays.toString(aDice));
        
        if(!scores.containsKey(aDice.toString())){
            for(int a = 0; a<6; a++){
                int valueA = dice_[aDice[0]][a];
                if(n/2 == 1){
                    aScore.add(valueA);
                    continue;
                }
                for(int b = 0; b<6; b++){
                    int valueB = dice_[aDice[1]][b];
                    if(n/2 == 2){
                        aScore.add(valueA + valueB);
                        continue;
                    }
                    
                    for(int c=0; c<6; c++){
                        int valueC = dice_[aDice[2]][c];
                        if(n/2 == 3){
                            aScore.add(valueA + valueB + valueC);
                            continue;
                        }
                        for(int d = 0; d<6; d++){
                            int valueD = dice_[aDice[3]][d];
                            if(n/2 == 4){
                                aScore.add(valueA + valueB + valueC + valueD);
                                continue;
                            }
                            for(int e=0; e<6; e++){
                                int valueE = dice_[aDice[4]][e];
                                aScore.add(valueA + valueB + valueC + valueD + valueE);
                                
                            }
                        }
                    }
                }
            }
            Collections.sort(aScore);        
            scores.put(Arrays.toString(aDice), aScore);
            abMap.put(Arrays.toString(aDice), Arrays.toString(bDice));

        }
        
        //System.out.println(aScore + "\n");
        
    }
    
    
}