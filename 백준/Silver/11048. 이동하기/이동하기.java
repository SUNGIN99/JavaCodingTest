import java.util.*;
import java.io.*;

public class Main{
    static class Comb{

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] candy = new int[n][m];

        int[][] miro = new int[n][m];
        for(int i = 0; i<n; i++){
            candy[i] = new int[m];
            miro[i] = new int[m];
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                candy[i][j] =  Integer.parseInt(st.nextToken());
            }
        }

        miro[0][0] = candy[0][0];
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(i-1 >= 0){
                    miro[i][j] = Math.max(miro[i][j], miro[i-1][j] + candy[i][j]);
                }

                if(i-1>= 0 && j-1>= 0){
                    miro[i][j] = Math.max(miro[i][j], miro[i-1][j-1]+ candy[i][j]);
                }

                if(j-1>= 0){
                    miro[i][j] = Math.max(miro[i][j], miro[i][j-1] + candy[i][j]);
                }
            }
            //System.out.println(Arrays.toString(miro[i]));
        }
        System.out.println(miro[n-1][m-1]);
    }



}