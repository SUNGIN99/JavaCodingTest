import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] grapeDrinks = new int[n+1];

        for(int i = 1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            grapeDrinks[i]= Integer.parseInt(st.nextToken());

        }

        int[] dp = new int[n+1];
        dp[1] = grapeDrinks[1];
        
        if(n == 1){
            System.out.println(dp[1]);
            return;
        }
        
        dp[2] = dp[1] + grapeDrinks[2];
        for(int i = 3; i<= n; i++){
            int a1 = dp[i-1];
            int a2 = dp[i-2] + grapeDrinks[i];
            int a3 = grapeDrinks[i] + grapeDrinks[i-1] + dp[i-3];

            dp[i] = Math.max(a1, a2);
            dp[i] = Math.max(dp[i], a3);
        }

        System.out.println(dp[n]);
        //System.out.println(Arrays.toString(grapeDrinks));
        //System.out.println(Arrays.toString(dp));
    }

}
