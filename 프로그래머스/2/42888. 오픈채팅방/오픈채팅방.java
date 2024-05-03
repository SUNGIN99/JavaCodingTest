import java.util.*;
class Solution {
    
    class Active{
        String uid;
        String message;
        
        Active(String u, String m){
            this.uid = u;
            this.message = m;
        }
    }
        
    public String[] solution(String[] record) {
        String[] answer;
        
        HashMap<String, String> nickName = new HashMap<>();
        ArrayList<Active> chatting = new ArrayList<>();
        
        int count = 0;
        for(String r : record){
            String[] cmd = r.split(" ");
            //System.out.println(Arrays.toString(cmd));
            if(cmd[0].equals("Enter")){
                nickName.put(cmd[1], cmd[2]);
                chatting.add(new Active(cmd[1], "님이 들어왔습니다."));
                count++;
            }else if(cmd[0].equals("Leave")){
                chatting.add(new Active(cmd[1], "님이 나갔습니다."));
                count++;
            }else{ // change
                nickName.put(cmd[1], cmd[2]);
            }
        }
        
        answer = new String[count];
        
        int j = 0;
        for(Active chat : chatting){
            answer[j++] = nickName.get(chat.uid) + chat.message;
            //System.out.println(nickName.get(chat.uid)+ chat.message);
        }
        
        return answer;
    }
}