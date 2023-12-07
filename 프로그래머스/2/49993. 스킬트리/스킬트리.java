import java.util.*;
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        char[] skillC = skill.toCharArray();
        
        int[] skillTree = new int[26];
        for(int i = 0; i<skillC.length; i++){
            int index = skillC[i] - 'A';
            
            skillTree[index] = i + 1;
        }
        
        boolean valid;
        for(String st : skill_trees){
            char[] check = st.toCharArray();
            
            int i = 1;
            valid = true;
            for(char c : check){
                int currSt = skillTree[c - 'A'];
                if(currSt == 0){
                    continue;
                }
                else if(currSt == i){
                    i++;
                }else{
                    valid = false;
                    break;
                }
            }
            
            if(valid){
                answer++;
            }
        }
        
        return answer;
    }
}