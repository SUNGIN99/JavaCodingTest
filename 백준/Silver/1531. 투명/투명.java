import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] xy = new int[n][4]; // [0] = 왼쪽 아래 x, [1] = 왼쪽 아래 y, [2] = 오른쪽 위 x, [3] = 오른쪽 위 y
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<4; j++){
                xy[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] draws = new int[101][101];

        for(int i = 0; i<n; i++){
            int x1 = xy[i][0];
            int y1 = xy[i][1];
            int x2 = xy[i][2];
            int y2 = xy[i][3];
            for(int j = x1; j<= x2; j++){
                for(int k = y1; k <= y2 ;k++){
                    draws[j][k]++;
                }
            }
        }

        int result = 10000;
        for(int i = 1; i<=100; i++){
            for(int j = 1; j<=100; j++){
                if(draws[i][j] <= m){
                    result --;
                }
            }
        }


        System.out.println(result);

    }


}