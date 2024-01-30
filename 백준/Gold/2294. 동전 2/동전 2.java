import java.util.*;
import java.io.*;

public class Main {


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n+1][k+1];

        int[] coins = new int[n];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            coins[i] = Integer.parseInt(st.nextToken());
        }


        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= k; j++){
                int gap = j - coins[i-1];
                if(gap < 0){
                    dp[i][j] = dp[i-1][j];
                }else{ // gap >= 0
                    if(gap == 0){
                        dp[i][j] = 1;
                    }
                    else if(dp[i][gap] == 0){
                        dp[i][j] = dp[i-1][j];
                    }
                    else{
                        if(dp[i][gap] != 0){
                            dp[i][j] = dp[i][gap] + 1;
                            if(dp[i-1][j] != 0){
                                dp[i][j] = Math.min(dp[i][j], dp[i-1][j]);
                            }
                        }

                    }
                }
            }
        }


        /*for(int i = 1; i<=n; i++){
            System.out.print(coins[i-1] + " ");
            for(int j = 0; j<= k; j++){
                System.out.print(j + " : " + dp[i][j] + ", ");
            }
            System.out.println();
        }*/


        System.out.println(dp[n][k] == 0 ? -1 : dp[n][k]);
    }
}
