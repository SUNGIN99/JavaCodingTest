import java.util.*;
class Solution {
    public String solution(String p) {
        String answer = "";
        
        if(validClose(p) == 0){
            return p;
        }
        
        answer = balanced(p);
        return answer;
    }
    
    public int getUV(String w){
        char[] close = w.toCharArray();
        int i;
        int left = 0, right = 0;
        for(i = 0; i<close.length; i++){
            if(close[i] == '('){
                left++;
            }
            else{
                right++;
            }
            if(left!= 0 && right != 0 && left==right){
                break;
            }
        }
        
        return i;
    }
    
    public String balanced(String u){
        if(u.length() == 0){
            return "";
        }
        
        if(validClose(u) == 0){
            return u;
        }
        
        int i = getUV(u);
        
        String v = u.substring(i+1, u.length());
        String subU = u.substring(0,i+1);
        
        if(validClose(subU) == 0){
            return subU + balanced(v);
        }
        
        String switchedU = "";
        if(subU.length() == 2){
            switchedU = "";
        }else{
            subU = u.substring(1,i);
            for(String s : subU.split("")){
                if(s.equals("(")){
                    switchedU += ")";
                }else{
                    switchedU += "(";
                }
            }
        }
        return "(" + balanced(v) + ")"  + switchedU;
            
    }
    
    public int validClose(String u){
        Stack<String> stack = new Stack<>();
        String[] close = u.split("");
        
        for(String c : close){
            if(c.equals("(")){
                stack.push(c);
            }else{
                if(!stack.isEmpty() && stack.peek().equals("(")){
                    stack.pop();
                }else{
                    stack.push(c);
                }
            }
        }
        
        return stack.size();
        
    }
}