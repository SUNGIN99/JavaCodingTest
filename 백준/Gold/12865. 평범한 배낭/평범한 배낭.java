import java.util.*;
import java.io.*;

public class Main{

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] backpack = new int[n+1][];
        backpack[0] = new int[k+1];

        for(int i =1; i<=n; i++){
            backpack[i] = new int[k+1];
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            for(int j = 0; j<=k; j++){
                if(j - w < 0){
                    backpack[i][j] = backpack[i-1][j];
                }else{ //j - w >= 0
                    backpack[i][j] = Math.max(backpack[i][j], backpack[i-1][j]);
                    //backpack[i][j] = Math.max(backpack[i][j], backpack[i][j-w] + v);
                    backpack[i][j] = Math.max(backpack[i][j], backpack[i-1][j-w] + v);
                }
            }
            //System.out.println(Arrays.toString(backpack[i]));
        }
        System.out.println(backpack[n][k]);


    }



}