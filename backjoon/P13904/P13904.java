package backjoon.P13904;

import java.util.ArrayList;
import java.util.Scanner;

public class P13904 {

    static int result = 0;
    public static int greedy(ArrayList<int[]> inputs, int N, int totalScore){
       int i = 0;
       int todaysScores = totalScore;
       ArrayList<int[]> copied;

       while(true){
           copied = new ArrayList<>(inputs);
           if(inputs.size() == 0){
               return totalScore;
           }
           else{
               totalScore += copied.get(i)[1];
               copied.remove(i);

               int j = 0;
               while(copied.size() != 0){
                   if(copied.get(j)[0] - 1 == 0){
                       copied.remove(j);
                   }else{
                       copied.set(j, new int[]{copied.get(j)[0] - 1, copied.get(j)[1]});
                       j++;
                   }
               }

               int a = greedy(copied, copied.size(), totalScore);

               if (totalScore < a){
                   totalScore = a;
               }

               i++;

           }

           i++;
       }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int d, w;

        ArrayList<int[]> inputs = new ArrayList<>();

        for(int i = 0; i<N; i++){
            d = sc.nextInt(); // 과제 마감일 까지 남은 일수
            w = sc.nextInt(); // 과제의 점수

            inputs.add(new int[]{d, w});
        }

        /*for(int[] a : inputs){
            System.out.println(a[0] +" " + a[1]);
        }*/
        System.out.println(greedy(inputs, N, 0));


    }
}
