import java.util.*;
import java.io.*;

public class Main {

    static boolean[] visited;
    static int n;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n  = Integer.parseInt(st.nextToken());
        visited = new boolean[n+1];


        for(int i = 1; i<=n; i++){
            dfs(i, i + " ", 1);
        }
    }

    static void dfs(int num, String s, int length){
        if(length == n){
            System.out.println(s);
        }
        if(visited[num]){
            return;
        }
        visited[num] = true;
        for(int i = 1; i<= n; i++){
            if(!visited[i]){
                dfs(i, s + i + " ", length + 1);
            }
        }

        visited[num] = false;
    }
}