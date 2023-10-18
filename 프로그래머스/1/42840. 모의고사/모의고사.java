import java.util.*;

class Solution {
    int[] collect = new int[]{0,0,0};
    public ArrayList<Integer> solution(int[] answers) {
        
        int[] one = {1,2,3,4,5};
        int[] two = {2,1,2,3,2,4,2,5};
        int[] three = {3,3,1,1,2,2,4,4,5,5};
        
        matchAnswer(0, one, answers);
        matchAnswer(1, two, answers);
        matchAnswer(2, three, answers);
        
        ArrayList<Integer> result = new ArrayList<>();
        int maxNum = 0;
        for(int i = 0; i<3; i++){
            if(collect[i] > maxNum){
                result = new ArrayList<>();
                maxNum = collect[i];
                result.add(i+1);
            }else if(collect[i] == maxNum){
                result.add(i+1);
            }
        }
        
        
        return result;
    }
    
    public void matchAnswer(int supoja, int[] marked, int[] answers){
        int j = 0;
        for(int i = 0; i<answers.length; i++){
            if(answers[i] == (marked[j++ % marked.length])){
                collect[supoja]++;
            }
        }
    }
    
}