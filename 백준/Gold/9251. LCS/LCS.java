import java.util.*;
import java.io.*;

public class Main {


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        char[] str1 = st.nextToken().toCharArray();

        st = new StringTokenizer(br.readLine());
        char[] str2 = st.nextToken().toCharArray();

        int n1 = str1.length;
        int n2 = str2.length;
        int[][] dp = new int[n1+1][];

        for(int i = 0; i<=n1; i++){
            dp[i] = new int[n2+1];
        }

        for(int i = 1; i<= n1 ; i++){
            for(int j = 1; j<= n2; j++){
                if(str1[i-1] == str2[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i][j-1] , dp[i-1][j]);
                }
            }
        }

        System.out.println(dp[n1][n2]);

        /*System.out.print("  ");
        for(int i = 0; i < n2 ; i++){
            System.out.print(str2[i] + " ");
        }
        System.out.println();
        for(int i = 1; i<n1+1; i++){
            System.out.print(str1[i-1]+" ");
            for(int j = 1; j< n2 +1; j++){

                System.out.print(dp[i][j] + " ");
            }

            System.out.println();
        }*/


    }
}
