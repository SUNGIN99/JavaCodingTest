import java.util.*;
class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        
        int gameNumCount = 0;
        int seq = 0;
        while (answer.length() != t){
            ArrayList<String> haveToSay = n_nary(gameNumCount, n);
            for(String say : haveToSay){
                if(seq + 1 == p){
                    answer += say;
                }
                seq = (seq + 1) % m;
                if(answer.length() == t){
                    break;
                }
            }
            gameNumCount ++;
        }
        
        
        return answer;
    }
    
    public String[] chars = new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B","C", "D", "E", "F"};
    public ArrayList<String> n_nary(int num, int n){
        ArrayList<String> numToNary = new ArrayList<>();
        if(num == 0){
            numToNary.add("0");
            return numToNary;
        }
        
        while(num != 0){
            int remain = num % n;
            num = num / n;
            
            numToNary.add(0, chars[remain]);
        }
        return numToNary;
    }
}