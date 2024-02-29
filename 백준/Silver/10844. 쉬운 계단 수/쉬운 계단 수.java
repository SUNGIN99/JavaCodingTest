import java.util.*;
import java.io.*;

public class Main {

    static long count;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        long[][] dp = new long[10][n];

        for(int i = 1; i<10; i++){
            dp[i][0] = 1;
        }


        for(int j = 0; j<n-1; j++){
            for(int i = 0; i<10; i++){
                if(i>= 0 && i <=8){
                    dp[i+1][j+1] += dp[i][j] % 1000000000;
                }

                if(i>= 1 && i <= 9){
                    dp[i-1][j+1] += dp[i][j] % 1000000000;
                }
            }
        }

        /*for(int i = 0; i<10; i++){
            System.out.println(i + ": " + Arrays.toString(dp[i]));
        }*/

        long sum = 0;
        for(int i = 0; i<10; i++){
            sum += dp[i][n-1] % 1000000000;
        }

        System.out.println(sum % 1000000000);

    }







}