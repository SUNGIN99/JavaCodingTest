import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        char[] str1Char = str1.toCharArray();
        char[] str2Char = str2.toCharArray();
        
        List<String> set1 = new ArrayList<>();
        List<String> set2 = new ArrayList<>();
        
        for(int i = 0; i<str1Char.length-1; i++){
            char chr1 = str1Char[i];
            char chr2 = str1Char[i+1];
            if(chr1 >= 'a' && chr1 <= 'z' && chr2 >= 'a' && chr2 <= 'z'){
                set1.add(String.valueOf(chr1) + String.valueOf(chr2));
            }
        }
        
        for(int i = 0; i<str2Char.length-1; i++){
            char chr1 = str2Char[i];
            char chr2 = str2Char[i+1];
            if(chr1 >= 'a' && chr1 <= 'z' && chr2 >= 'a' && chr2 <= 'z'){
                set2.add(String.valueOf(chr1) + String.valueOf(chr2));
            }
        }
        
        Map<String, Integer> str1Count = new HashMap<>();
        Map<String, Integer> str2Count = new HashMap<>();
        Map<String, Integer> union = new HashMap<>();
        
        
        for(String str : set1){
            int curCount = str1Count.getOrDefault(str, 0);
            str1Count.put(str, curCount+1);
            union.put(str, 1);
        }
        for(String str : set2){
            int curCount = str2Count.getOrDefault(str, 0);
            str2Count.put(str, curCount+1);
            
            union.put(str, 1);
        }
        
        int unionCount = 0, intersectCount = 0;
        for(String key : union.keySet()){
            int item1 = str1Count.getOrDefault(key, 0);
            int item2 = str2Count.getOrDefault(key, 0);
            
            if(item1 == 0){
                unionCount += item2;   
            }else if(item2 == 0){
                unionCount += item1;
            }else{
                unionCount += Math.max(item1, item2);
                intersectCount += Math.min(item1, item2);
            }
        }
        
        double answer = 0;
        //System.out.println(intersectCount + " / " + unionCount);
        if(set1.size() == 0 && set2.size() == 0){
            answer = 65536;
        }else{
        answer = Double.valueOf(intersectCount) / Double.valueOf(unionCount);
        answer *= 65536.0;
        answer = (answer * 10) / 10.0;
        }
        
        
        return (int) answer;
    }
}