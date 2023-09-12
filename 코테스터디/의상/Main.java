package 코테스터디.의상;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        String[][] a= new String[][]{
                {"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}
        };
        solution(a);

        // headgear = 2개, eyewear = 1개
        // 2 * 1 - 1 -> 모든 경우의 수
    }

    public static int solution(String[][] clothes) {
        HashMap<String, Integer> closet = new HashMap<>();

        // 각 옷 종류 별 가능한 경우의수 모두 곱하고 ,  전부다 안입는 겨우의수 1 빼기

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

        System.out.println(mx);
        return mx;
    }

}
