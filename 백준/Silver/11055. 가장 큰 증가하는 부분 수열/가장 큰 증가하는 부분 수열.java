import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        int[] dp = new int[n];
        for(int i = 0; i<n; i++){
            arr[i] = dp[i] = Integer.parseInt(st.nextToken());
        }

        int maxVal = Integer.MIN_VALUE;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<i; j++){
                if(arr[i] > arr[j]){
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                }
            }
            maxVal = Math.max(maxVal, dp[i]);
        }

        System.out.println(maxVal);


    }

}
