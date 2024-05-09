import java.util.*;
import java.io.*;

public class Main{

    static class Edge{
        int to;
        int time;

        Edge(int d, int t){
            to = d;
            time = t;
        }

        public String toString(){
            return "("+to +", " + time+")";
        }
    }

    static ArrayList<Edge>[] graph;
    static int[] visited;
    static int n, m, x;

    static int[] arrived, leave;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        arrived = new int[n+1];
        leave = new int[n+1];
        for(int i = 1; i<=n; i++){
            graph[i] = new ArrayList<>();
            arrived[i] = leave[i] = Integer.MAX_VALUE;
        }

        int from, to, time;
        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            time = Integer.parseInt(st.nextToken());
            graph[from].add(new Edge(to, time));
        }

        for(int i = 1; i<=n; i++){
            //System.out.println(i + ": " +graph[i]);
            toParty(i);
        }
        //System.out.println(Arrays.toString(arrived));

        for(int i = 1; i<=n; i++){
            //System.out.println(i + ": " +graph[i]);
            toHome(i);
        }
        //System.out.println(Arrays.toString(leave));

        int max = 0;
        for(int i =1; i<=n ;i++){
            max = Math.max(max, arrived[i] + leave[i]);
        }
        System.out.println(max);

    }

    static void toParty(int student){
        visited = new int[n+1];
        Queue<Edge> queue = new LinkedList<>();

        queue.add(new Edge(student, 0));
        while(!queue.isEmpty()){
            Edge village = queue.poll();
            if(village.to == x){
                arrived[student] = Math.min(arrived[student], village.time);
            }

            ArrayList<Edge> canGo = graph[village.to];
            for(Edge e : canGo){
                if(student == e.to){
                    continue;
                }

                if(visited[e.to] == 0 || visited[e.to] > village.time + e.time){
                    visited[e.to] = village.time + e.time;
                    queue.add(new Edge(e.to, village.time + e.time));
                }
            }
            //System.out.println(Arrays.toString(visited));
        }

        //System.out.println();
    }

    static void toHome(int student){
        visited = new int[n+1];
        Queue<Edge> queue = new LinkedList<>();

        queue.add(new Edge(x, 0));
        while(!queue.isEmpty()){
            Edge village = queue.poll();
            if(village.to == student){
                leave[student] = Math.min(leave[student], village.time);
            }

            ArrayList<Edge> canGo = graph[village.to];
            for(Edge e : canGo){
                if(visited[e.to] == 0 || visited[e.to] > village.time + e.time){
                    visited[e.to] = village.time + e.time;
                    queue.add(new Edge(e.to, village.time + e.time));
                }
            }
        }
    }
}