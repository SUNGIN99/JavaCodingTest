import java.util.*;

class Solution {
    public static int solution(String[][] clothes) {
        HashMap<String, Integer> closet = new HashMap<>();


        int answer = 0;
        // O(n) => 최대 30
        for (int i = 0; i<clothes.length; i++){
            if (!closet.containsKey(clothes[i][1])){
                closet.put(clothes[i][1], 1);
            }else{
                int temp = closet.get(clothes[i][1]) + 1;
                closet.put(clothes[i][1], temp);
            }
        }

        int mx = 1;
        for (Integer a : closet.values()){
            mx *= (a+1);
        }

        mx -= 1;

        //System.out.println(mx);
        return mx;
    }
}