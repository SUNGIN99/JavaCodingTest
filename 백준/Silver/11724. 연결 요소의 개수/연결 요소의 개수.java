import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int from, to;

        graph = new ArrayList<>();
        visited = new boolean[n+1];

        for(int i = 0; i<n+1; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i<m; i++) {
            st = new StringTokenizer(in.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            graph.get(from).add(to);
            graph.get(to).add(from);

            Collections.sort(graph.get(from));
            Collections.sort(graph.get(to));
        }

        int answer = 0;
        for(int i = 1; i<=n; i++){
            if(dfs(i) == 1){
                answer++;
            }
        }
        System.out.println(answer);
    }

    public static int dfs(int node){
        if(visited[node]){
            return 0;
        }
        visited[node] = true;

        ArrayList<Integer> nextNode = graph.get(node);
        for(int i = 0; i<nextNode.size(); i++){
            int next = nextNode.get(i);
            if(!visited[next]){
                dfs(next);
            }
        }

        return 1;
    }

}
