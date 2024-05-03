import java.util.*;
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        Deque<String> curItem = new LinkedList<>();
        
        HashMap<String, Integer> wantMap = new HashMap<>();
        HashMap<String, Integer> curMap = new HashMap<>();
        
        for(int i = 0; i<want.length; i++){
            wantMap.put(want[i], number[i]);
        }
        
        int count;
        for(int i = 0; i<10; i++){
            count = curMap.getOrDefault(discount[i], 0);
            curMap.put(discount[i], ++count);
            curItem.add(discount[i]);
        }
        
        boolean possible = true;
        for(String item : wantMap.keySet()){
            if(!curMap.containsKey(item) || curMap.get(item) != wantMap.get(item)){
                possible = false;
                break;
            }
        }
        if(possible){
            answer++;
        }
        
        String outItem;
        for(int i = 10; i<discount.length; i++){
            //하루 밀기
            outItem = curItem.pollFirst();
            count = curMap.get(outItem);
            curMap.put(outItem, --count);
            
            count = curMap.getOrDefault(discount[i], 0);
            curMap.put(discount[i], ++count);
            curItem.add(discount[i]);
            
            possible = true;
            for(String item : wantMap.keySet()){
                if(!curMap.containsKey(item) || curMap.get(item) != wantMap.get(item)){
                    possible = false;
                    break;
                }
            }
            if(possible){
                answer++;
            }
            
        }
        
        
        
        return answer;
    }
}