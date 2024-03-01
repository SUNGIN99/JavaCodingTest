import java.util.*;
import java.io.*;

public class Main {

    static int m, n;
    static int[][] cabbage;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());

        for(int i = 0; i<t; i++){
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            cabbage = new int[n][m];

            for(int j = 0; j<k; j++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                cabbage[y][x] = 1;
            }

            getWarm();
        }
    }

    static void getWarm(){
        int warm = 0;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(cabbage[i][j] == 1){
                    warm++;
                    dfs(i, j);
                }
            }
        }

        System.out.println(warm);
    }

    static void dfs(int y, int x){
        if(y < 0 || y >= n){
            return;
        }
        if(x< 0 || x >= m){
            return;
        }

        if(cabbage[y][x] != 1){
            return;
        }

        cabbage[y][x] = 0;

        dfs(y-1, x);
        dfs(y+1, x);
        dfs(y, x-1);
        dfs(y, x+1);
    }





}