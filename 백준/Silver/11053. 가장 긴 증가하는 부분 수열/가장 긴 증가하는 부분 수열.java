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
        dp[0] = a[0];
        int left, right, len = 1;
        for(int i = 1; i<n; i++){
            left = 0;
            right = len - 1;
            while(left <= right){
                int mid = left + (right - left) / 2;

                if(dp[mid] > a[i]){
                    right = mid - 1;
                }else if(dp[mid] < a[i]){
                    left = mid + 1;
                }else{
                    left = mid;
                    break;
                }
            }

            if(dp[left] == 0){
                len++;
            }
            dp[left] = a[i];


            //System.out.println(left + " : " + Arrays.toString(dp));
        }

        System.out.println(len);



    }



}