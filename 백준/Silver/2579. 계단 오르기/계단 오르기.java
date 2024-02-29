import java.util.*;
import java.io.*;

public class Main {

    static boolean visited[];
    static int n, k;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int score[] = new int[n];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            score[i] = Integer.parseInt(st.nextToken());
        }

        //dp[i] = Math.max(dp[i-2] + score[i], score[i] + score[i-1] + dp[i-3]);
        if(n == 1){
            System.out.println(score[0]);
        }else if(n == 2){
            System.out.println(score[0] + score[1]);
        }else if(n == 3){
            System.out.println(Math.max(score[1]+ score[2], Math.max(score[0] + score[1] , score[0] +score[2])));
        }else{
            int dp[][] = new int[n][2];
            // 0 : 한 칸 전으로부터 온 거리
            // 1 : 두 칸 전으로부터 온 거리
            for(int i = 0; i<n; i++){
                dp[i] = new int[2];
                if(i == 0){
                    dp[0][0] = dp[0][1] = score[0];
                }else if(i == 1){
                    dp[1][0] = dp[0][0] + score[1];
                    dp[1][1] = score[1];
                }else{
                    dp[i][0] = dp[i-1][1] + score[i];
                    dp[i][1] = Math.max(dp[i-2][0], dp[i-2][1]) + score[i];
                }
                //System.out.println(Arrays.toString(dp[i]));
            }

            System.out.println(Math.max(dp[n-1][0], dp[n-1][1]));
        }



    }



}