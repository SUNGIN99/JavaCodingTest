import java.util.*;
import java.io.*;

public class Main{

    static int[][] stickers;
    static int[][] dp;
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        int t= Integer.parseInt(br.readLine());
        for(int i = 0; i<t; i++){
            n = Integer.parseInt(br.readLine());
            stickers = new int[2][];
            stickers[0] = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                stickers[0][j] = Integer.parseInt(st.nextToken());
            }
            stickers[1] = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                stickers[1][j] = Integer.parseInt(st.nextToken());
            }

            dp = new int[2][];
            dp[0] = new int[n];
            dp[1] = new int[n];
            getHighScore();

        }

    }
    static void getHighScore(){

        if(n == 1){
            System.out.println(Math.max(stickers[0][0], stickers[1][0]));
        }else if(n >= 2){
            dp[0][0] = stickers[0][0];
            dp[1][0] = stickers[1][0];
            dp[0][1] = stickers[0][1] + stickers[1][0];
            dp[1][1] = stickers[0][0] + stickers[1][1];
            for(int i = 2; i<n; i++){
                dp[0][i] = stickers[0][i] + Math.max(dp[1][i-1], dp[1][i-2]);
                dp[1][i] = stickers[1][i] + Math.max(dp[0][i-1], dp[0][i-2]);
            }

            System.out.println(Math.max(dp[0][n-1] , dp[1][n-1]));
            //System.out.println(Arrays.toString(dp[0]));
            //System.out.println(Arrays.toString(dp[1]));
        }
    }


}