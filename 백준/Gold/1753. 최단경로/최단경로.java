import java.util.*;
import java.io.*;

public class Main {

    static int[] path;
    static boolean[] visited;
    static ArrayList<Edge>[] matrix;
    static int V, E, K;

    static class Edge implements Comparable<Edge>{
        int vertex;
        int weight;

        public Edge(int to, int w){
            vertex = to;
            weight = w;
        }

        public int compareTo(Edge e){
            return this.weight - e.weight;
        }
    }


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        path = new int[V+1];
        visited = new boolean[V+1];
        matrix = new ArrayList[V+1];

        for(int i = 0; i<=V; i++){
            matrix[i] = new ArrayList<>();
            path[i] = -1;
        }

        for(int i = 0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            matrix[u].add(new Edge(v, w));
        }

        path[K] = 0;
        dijkstra(K);
        //System.out.println(Arrays.toString(path));

        for(int i = 1; i<= V; i++){
            if(visited[i]){
                System.out.println(path[i]);
            }else{
                System.out.println("INF");
            }
        }

    }

    static void dijkstra(int start){
        PriorityQueue<Edge> edges = new PriorityQueue<>();

        edges.add(new Edge(start, 0));

        while(!edges.isEmpty()){
            Edge edge = edges.poll();

            if(visited[edge.vertex]) continue;

            visited[edge.vertex] = true;

            ArrayList<Edge> nextEdge = matrix[edge.vertex];
            for(Edge next : nextEdge){
                if(path[next.vertex] == -1 || path[next.vertex] > path[edge.vertex] + next.weight){
                    path[next.vertex] = path[edge.vertex] + next.weight;
                    //System.out.println(Arrays.toString(path));
                    edges.add(new Edge(next.vertex, path[next.vertex]));
                }
            }
        }
    }

}