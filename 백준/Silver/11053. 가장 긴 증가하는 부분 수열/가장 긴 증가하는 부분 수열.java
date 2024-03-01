import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] a = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];
        int max = 0;
        for(int i = 0; i<n; i++){
            dp[i] = 1;
            for(int j = 0; j<i; j++){
                if(a[i] > a[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);

    }



}