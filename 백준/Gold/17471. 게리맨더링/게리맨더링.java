import java.util.*;
import java.io.*;

public class Main{

    static ArrayList<Integer>[] graph;
    static int[] popularity;
    static boolean[] visited;
    static int n, min = Integer.MAX_VALUE;
    static int[] voteGroup;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        popularity = new int[n+1];
        visited = new boolean[n+1];
        graph = new ArrayList[n+1];
        voteGroup = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=n; i++){
            graph[i] = new ArrayList<>();
            popularity[i] = Integer.parseInt(st.nextToken());
        }

        int connected, target;
        for(int i = 1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            connected = Integer.parseInt(st.nextToken());
            for(int j = 0; j<connected; j++){
                target = Integer.parseInt(st.nextToken());
                graph[i].add(target);
            }
        }
        dfs(1);
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    static void dfs(int num){
        if(num == n+1){
            int popular1 = 0, popular2 = 0;
            for(int i = 1; i<=n; i++){
                if(voteGroup[i] == 1){
                    popular1 += popularity[i];
                }else{
                    popular2 += popularity[i];
                }
            }

            visited = new boolean[n+1];

            int link = 0;
            for(int i = 1; i<=n; i++){
                if(!visited[i]){
                    bfs(i, voteGroup[i]);
                    link++;
                }
            }

            if(link == 2){
                min = Math.min(min, Math.abs(popular1 - popular2));
            }

            return;
        }

        voteGroup[num] = 1;
        dfs(num+1);

        voteGroup[num] = 2;
        dfs(num+1);
    }

    static void bfs(int num, int groupN){
        Queue<Integer> search = new LinkedList<>();
        search.add(num);

        while(!search.isEmpty()){
            int curN = search.poll();
            ArrayList<Integer> vertex = graph[curN];
            for(int v : vertex){
                if(!visited[v] && voteGroup[v] == groupN){
                    search.add(v);
                    visited[v] = true;
                }
            }
        }
    }


}