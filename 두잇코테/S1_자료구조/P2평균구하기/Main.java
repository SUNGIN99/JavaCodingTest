package 두잇코테.S1_자료구조.P2평균구하기;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] scores = new int[N];
        int maxNum = 0;

        for (int i = 0; i<N; i++){
            scores[i] = sc.nextInt();

            if(scores[i] > maxNum){
                maxNum = scores[i];
            }
        }

        double sum = 0, avg;
        for(int i = 0; i<N; i++){
            sum += (double) scores[i] / maxNum * 100;
        }

        avg = sum / N;
        System.out.println(avg);
    }
}
