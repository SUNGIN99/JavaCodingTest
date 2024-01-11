import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[][] houses = new int[n+1][3];

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<3; j++){
               houses[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n+1][3];
        for(int i = 1; i <= n; i++){
            dp[i][0] = houses[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
            dp[i][1] = houses[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
            dp[i][2] = houses[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
        }

        int r = dp[n][0];
        int g = dp[n][1];
        int b = dp[n][2];

        System.out.println(Math.min(r, Math.min(g, b)));



    }

}
