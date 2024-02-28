import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coins = new int[n];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            coins[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(coins);

        int[][] dp = new int[n+1][k+1];
        for(int i = 0; i<=n; i++){
            dp[i] = new int[k+1];
        }

        for(int i = 1; i<=n; i++){
            dp[i][0] = 1;
        }

        for(int i = 1; i<=n; i++){
            for(int j = 1; j<= k; j++){
                int gap = j - coins[i-1];

                if(gap >= 0){
                    dp[i][j] = dp[i-1][j] + dp[i][gap];
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
            //System.out.println(Arrays.toString(dp[i]));
        }
        
        System.out.println(dp[n][k]);




    }


}