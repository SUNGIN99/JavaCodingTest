import java.util.*;
class Solution {
    public String solution(String[] survey, int[] choices) {
        String[][] type = new String[][]{{"R", "T"}, {"C", "F"}, {"J", "M"}, {"A", "N"}};
        
        int[] left = new int[4];
        int[] right = new int[4];
        int[] check = new int[]{-1, -1};
        for(int i = 0; i<survey.length; i++){
            String deny = survey[i].substring(0,1);
            String accept = survey[i].substring(1,2);
            int[] a = check;
            if(choices[i] < 4){
                a = getTypeIndex(deny);
            }else if(choices[i] > 4){
                a = getTypeIndex(accept);
            }
            
            if(a[0] != -1){
                if(a[0] == 0){
                    left[a[1]] += getScore(choices[i]);
                }else{
                    right[a[1]] += getScore(choices[i]);
                }
            }
        }
        
        String answer = "";
        for(int i = 0; i<4; i++){
            if(left[i] < right[i]){
                answer += type[i][1];
            }else{
                answer += type[i][0];
            }
        }
        //System.out.println(Arrays.toString(left));
        //System.out.println(Arrays.toString(right));
        
        return answer;
    }
    
    public static int[] getTypeIndex(String type){
        if(type.equals("R")){
            return new int[]{0, 0};
        }else if(type.equals("T")){
            return new int[]{1,0};
        }else if(type.equals("C")){
            return new int[]{0,1};
        }else if(type.equals("F")){
            return new int[]{1,1};
        }else if(type.equals("J")){
            return new int[]{0,2};
        }else if(type.equals("M")){
            return new int[]{1,2};
        }else if(type.equals("A")){
            return new int[]{0,3};
        }else { // N
            return new int[]{1,3};
        }
    }
    
    public static int getScore(int choice){
        if(choice == 7 || choice == 1)
            return 3;
        else if (choice == 6 || choice == 2)
            return 2;
        else if (choice == 5 || choice == 3)
            return 1;
        return 0;
    }
}