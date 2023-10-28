import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public ArrayList<Integer> solution(String s) {
        // 맨 바깥족 괄호 제거 후 안쪽 통일
        String[] subs;
        s = s.substring(1, s.length());
        s = s.substring(0, s.length() -1);
        s += ",";
        
        // 각 집합 별로 분리 후 길이 별로 정렬 ({a1}, {a1,a2} ...)
        subs = s.split("\\{");
        subs = Arrays.stream(subs)
            .filter(sub -> sub.length() != 0)
            .sorted(Comparator.comparingInt( String::length ))
            .toArray(String[]::new);
        
        // 각 숫자를 정렬
        String[][] nums = new String[subs.length][];
        for(int i = 0; i<subs.length; i++){
            String withOutThesis = subs[i].substring(0, subs[i].length()-2);
            nums[i] = withOutThesis.split(",");
            //System.out.println(Arrays.toString(nums[i]));
        }
        
        HashMap<String, Integer> checked = new HashMap<>();
        ArrayList<Integer> answer = new ArrayList<>();
        for(String[] numSet : nums){
            for(String a : numSet){
                if(!checked.containsKey(a)){
                    checked.put(a, 0);
                    answer.add(Integer.parseInt(a));
                }
            }
        }
        
        return answer;
    }
}