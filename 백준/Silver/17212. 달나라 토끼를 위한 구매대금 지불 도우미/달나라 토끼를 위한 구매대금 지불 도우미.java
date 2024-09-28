import java.util.*;
import java.io.*;

public class Main{



    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] coins = new int[]{1, 2, 5, 7};
        int pay = Integer.parseInt(br.readLine());
        int[][]dp = new int[4][pay + 1];

        for(int i = 0; i<4; i++){
            if(i == 0){
                for(int j = 1; j<= pay; j++){
                    dp[i][j] = j;
                }
            }else{
                for(int j = 1; j<=pay; j++){
                    if(j-coins[i] < 0){ // 현재 코인을 쓸 수 없는 경우
                        dp[i][j] = dp[i-1][j];
                    }else if(j - coins[i] == 0){
                        dp[i][j] = 1;
                    }else{
                        dp[i][j] = Math.min(dp[i-1][j] , dp[i][j - coins[i]] + 1);
                    }
                }
            }
            //System.out.println(Arrays.toString(dp[i]));
        }

        System.out.println(dp[3][pay]);


    }


}