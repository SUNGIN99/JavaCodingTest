import java.util.*;
class Solution {
  public String solution(String[] participant, String[] completion) {

        Arrays.sort(participant);
        Arrays.sort(completion);
        //System.out.println(Arrays.toString(participant));
        //System.out.println(Arrays.toString(completion));
        int i = 0, j = 0;
        String result = "";
        while(i< participant.length && j< completion.length){
            if (participant[i].equals(completion[j])){
                i++; j++;
            }
            else {
                return participant[i];
            }
        }
        return participant[i];
    }
}