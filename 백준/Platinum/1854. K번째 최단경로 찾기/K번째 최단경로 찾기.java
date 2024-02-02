import java.util.*;
import java.io.*;

public class Main {

    static class Node implements Comparable<Node>{
        int vertex;
        int time;
        int stackedTime;

        public int compareTo(Node n){
            return this.stackedTime - n.stackedTime;
        }

        Node(int n, int t, int st){
            this.vertex = n;
            this.time = t;
            this.stackedTime = st;
        }
    }

    static ArrayList<Node>[] edges;
    static ArrayList<Integer>[] distance;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());;
        int k = Integer.parseInt(st.nextToken());;

        edges = new ArrayList[n+1];
        distance = new ArrayList[n+1];
        for(int i = 1; i<=n; i++){
            edges[i] = new ArrayList<>();
            distance[i] = new ArrayList<>();
        }

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine()); 
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges[a].add(new Node(b, c, c));
        }

        recursiveDijkstra(1, k);

        for(int i = 1; i<=n; i++){
            if(distance[i].size() < k){
                System.out.println(-1);
            }else{
                System.out.println(distance[i].get(k-1));
            }
        }

        for(int i = 1 ;i <=n ; i++){

        }

    }
    
    static void recursiveDijkstra(int start, int k){
        PriorityQueue<Node> queue = new PriorityQueue<>();
        
        queue.add(new Node(start, 0, 0));
        distance[start].add(0);
        
        while(!queue.isEmpty()){
            Node currNode = queue.poll();
            
            ArrayList<Node> edge = edges[currNode.vertex];
            for(Node node : edge){
                int newDist = currNode.stackedTime + node.time;

                int nextNodeDistSize = distance[node.vertex].size();
                if(nextNodeDistSize < k || distance[node.vertex].get(k-1) > newDist){
                    binarySearchInsert(node.vertex, newDist);
                    queue.add(new Node(node.vertex, node.time, newDist));
                }
            }
        }
    }

    static void binarySearchInsert(int vertex, int value){
        ArrayList<Integer> currDistances = distance[vertex];

        int left = 0;
        int right = currDistances.size() - 1;

        int mid;
        while(left <= right){
            mid = left + (right - left) / 2;
            if(currDistances.get(mid) < value){
                left = mid + 1;
            }else if (currDistances.get(mid) > value){
                right = mid - 1;
            }else{
                left = mid;
                break;
            }
        }

        currDistances.add(left, value);

    }

}

