import java.util.*;
class Solution {
    Map<String, Integer> courseDishes = new HashMap<>();
    
    public String[] solution(String[] orders, int[] course) {
        String[] answer;
        for(String s : orders){
            char[] sorted = s.toCharArray();
            Arrays.sort(sorted);
            checkDoubledDish(sorted, 0, "");
        }
        
        boolean[] find = new boolean[11];
        for(int c : course){
            find[c] = true;
        }
        
        Map<Integer, List<String>> result = new HashMap<>();
        for(String key : courseDishes.keySet()){
            int dishCount = courseDishes.get(key);
            
            if(dishCount < 2){
                continue;
            }
            
            if(!result.containsKey(key.length())){
                List<String> temp = new ArrayList<>();
                temp.add(key);
                result.put(key.length(), temp);
            }else{
                List<String> temp = result.get(key.length());
                // 들어있는 코스 메뉴의 주문 수보다 다른 코스메뉴의 주문 수가 더 많다면?
                if(courseDishes.get(temp.get(0)) < dishCount){
                    temp = new ArrayList<>();
                    temp.add(key);
                    result.put(key.length(), temp);
                }
                // 들어있는 코스 메뉴의 주문 수과 다른 코스메뉴의 주문 수가 같다면?
                else if(courseDishes.get(temp.get(0)) == dishCount){
                    temp.add(key);
                }
            }
        }
        
        
        //System.out.println(result);
        List<String> answerList = new ArrayList<>();
        for(Integer key : result.keySet()){
            if(find[key]){
                answerList.addAll(result.get(key));
            }
        }
        //System.out.println(answerList);
        answer = new String[answerList.size()];
        for(int i = 0; i<answerList.size(); i++){
            answer[i] = answerList.get(i);
        }
        Arrays.sort(answer);
        //System.out.println(courseDishes);
        return answer;
    }
    
    void checkDoubledDish(char[] order, int index, String cd){
        if(cd.length() == 10){
            return;
        }
        
        if(cd.equals("")){
            for(int i = 0; i<order.length; i++){
                checkDoubledDish(order, i+1, cd+order[i]);
            }
        }else{
            for(int i = index; i<order.length; i++){
                String newCd = cd + order[i];
                courseDishes.put(newCd,
                                courseDishes.getOrDefault(newCd, 0) + 1);
                checkDoubledDish(order, i+1, newCd);
            }
        }
    }
}