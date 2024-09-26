import java.util.*;
import java.io.*;

public class Main{

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] mars  = new int[n][];
        int[][] dp = new int[n][];

        for(int i = 0; i<n; i++){
            mars[i] = new int[m];
            dp[i] = new int[m];
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                mars[i][j] = Integer.parseInt(st.nextToken());
            }
            //System.out.println(Arrays.toString(mars[i]));
        }

        dp[0][0] = mars[0][0];
        for(int i = 1; i<m; i++){
            dp[0][i] = mars[0][i] + dp[0][i-1];
        }

        for(int i = 1; i<n; i++){
            int[] left = new int[m];
            int[] right = new int[m];
            left[0] = dp[i-1][0] + mars[i][0];
            right[m-1] = dp[i-1][m-1] + mars[i][m-1];

            for(int j = 1; j<= m-1; j++){
                left[j] = mars[i][j] + Math.max(dp[i-1][j], left[j-1]);
                right[m-j-1] = mars[i][m-j-1] + Math.max(dp[i-1][m - j - 1], right[m-j]);
            }
/*
            System.out.println(Arrays.toString(left));
            System.out.println(Arrays.toString(right));
            System.out.println();
*/

            for(int j = 0; j<m; j++){
                dp[i][j] = Math.max(left[j], right[j]);
            }

            //System.out.println(Arrays.toString(dp[i]));
        }
        System.out.println(dp[n-1][m-1]);


    }

}