import java.io.*;
import java.util.*;

public class Main {

    static int[] hacked;
    static HashMap<Integer, ArrayList<Integer>> computes;
    static boolean visited[];
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 도시의 개수  (2 ~ 300,000)
        int M = Integer.parseInt(st.nextToken()); // 도로의 개수  (1 ~ 1,000,000)

        computes = new HashMap<>();

        for(int i =1 ; i <= N; i++){
            ArrayList<Integer> a = new ArrayList<>();
            computes.put(i, a);
        }

        int v, e;
        for(int i = 0; i< M; i++){
            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            ArrayList<Integer> trusted = computes.get(v);
            trusted.add(e);

            computes.put(v, trusted);
        }

        hacked = new int[N+1];
        visited = new boolean[N+1];

        for(int i = 1; i<= N; i++){
            visited = new boolean[N+1];
            dfs(i);
        }

        int maxVal = 0;
        for(int i = 0; i<= N; i++){
            maxVal = Math.max(maxVal, hacked[i]);
        }

        for(int i = 0; i <= N; i++){
            if(maxVal == hacked[i]){
                System.out.print(i + " ");
            }
        }
    }

    static void dfs(int node){
        if(visited[node] == false) {
            hacked[node]++;
            visited[node] = true;
        }


        for(Integer v : computes.get(node)){
            if(visited[v] == false)
                dfs(v);
        }

    }


}
