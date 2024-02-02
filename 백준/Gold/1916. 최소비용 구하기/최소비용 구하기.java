import java.util.*;
import java.io.*;

public class Main {

    static class BusInfo implements Comparable<BusInfo>{
        int leave;
        int arrive;
        int cost;

        BusInfo(int l, int a, int c){
            this.leave = l;
            this.arrive = a;
            this.cost = c;
        }

        public int compareTo(BusInfo b){
            return this.cost - b.cost;
        }
    }

    static ArrayList<BusInfo>[] Infos;
    static int[] distance;
    static boolean[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        Infos = new ArrayList[N+1];
        distance = new int[N + 1];
        visited = new boolean[N+1];
        for(int i = 1; i<=N; i++){
            Infos[i] = new ArrayList<>();
            distance[i] = -1;
        }

        for(int i = 0; i< M; i++){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            Infos[l].add(new BusInfo(l, a, c));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        dijkstra(start);

        //System.out.println(Arrays.toString(distance));
        System.out.println(distance[end]);
    }

    static void dijkstra(int start){
        PriorityQueue<BusInfo> graphs = new PriorityQueue<>();

        distance[start] = 0;
        graphs.add(new BusInfo(start, start, 0));

        while(!graphs.isEmpty()){
            BusInfo curTown = graphs.poll();

            if(visited[curTown.leave]){
                continue;
            }

            visited[curTown.leave] = true;

            ArrayList<BusInfo> nextTown = Infos[curTown.leave];
            for(BusInfo town : nextTown){
                if(distance[town.arrive] == -1 || distance[town.arrive] > distance[curTown.leave] + town.cost){
                    distance[town.arrive] = distance[curTown.leave] + town.cost;
                    graphs.add(new BusInfo(town.arrive, town.arrive, distance[town.arrive]));
                }
            }
        }

    }

}

