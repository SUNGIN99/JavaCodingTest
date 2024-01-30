import java.util.*;
import java.io.*;

public class Main {


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 격자 크기 N x N
        int N = Integer.parseInt(st.nextToken());

        int[][] triangle = new int[N][];
        for(int i = 0; i<N; i++){
            triangle[i] = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j< i+1; j ++){
                 triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int maxSum = triangle[0][0];
        for(int i =1; i< N; i++){
            for(int j = 0; j<i + 1; j++){
                int dValue = triangle[i][j];

                if(j - 1 >= 0){
                    dValue = Math.max(dValue, triangle[i][j] + triangle [i-1][j-1]);
                }

                if(j != i){
                    dValue = Math.max(dValue, triangle[i][j] + triangle[i-1][j]);
                }

                triangle[i][j] = dValue;
                if(i == N - 1){
                    maxSum = Math.max(dValue, maxSum);
                }
            }
        }

        System.out.println(maxSum);

        /*for(int i = 0; i< N; i++){
            System.out.println(Arrays.toString(triangle[i]));
        }*/
    }
}