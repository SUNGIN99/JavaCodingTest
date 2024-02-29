import java.util.*;
import java.io.*;

public class Main {

    static boolean visited[];
    static int n, k;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        visited = new boolean[n];

        for(int i = 0; i<n; i++){
            dfs(i, "" + (i+1) + " ", 1);
        }


    }

    static void dfs(int index, String result, int len){
        if(len == k){
            System.out.println(result);
        }

        visited[index] = true;
        for(int i = 0; i<n; i++){
            if(!visited[i]){
                dfs(i, result + (i+1) + " ", len + 1);
            }
        }
        visited[index] = false;
    }


}