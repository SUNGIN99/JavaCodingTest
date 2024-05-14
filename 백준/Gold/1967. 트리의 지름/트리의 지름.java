import java.util.*;
import java.io.*;

public class Main{

    static class Edge{
        int from;
        int to;
        int weight;

        Edge(int t, int w){
            this.to = t;
            this.weight = w;
        }

        public String toString(){
            return "("+to + ", " + weight+")";
        }
    }

    static ArrayList<Edge>[] tree;
    static int[] radius;
    static int maxRadius = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n+1];
        radius = new int[n+1];
        for(int i = 1; i<=n; i++){
            tree[i] = new ArrayList<>();
        }

        for(int i = 0; i<n-1; i++){
            st= new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            tree[from].add(new Edge(to, weight));
        }

        int max1, max2, temp;
        for(int i = 1; i<=n; i++){
            max1 = 0;
            max2 = 0;
            for(Edge next : tree[i]){
                if(radius[next.to] == -1){
                    temp = next.weight;
                    radius[i] = Math.max(temp, radius[i]);
                }else if(radius[next.to] != 0){
                    temp = next.weight + radius[next.to];
                    radius[i] = Math.max(temp, radius[i]);
                }else{ //radius[next.to] == 0
                    temp = next.weight + getRadius(i, next);
                    radius[i] = Math.max(radius[i], temp);
                }

                if(temp > max1){
                    max2 = max1;
                    max1 = temp;
                }else{
                    if(temp > max2){
                        max2 = temp;
                    }
                }
            }
            maxRadius = Math.max(maxRadius, max1 + max2);
        }
        //System.out.println(Arrays.toString(radius));
        System.out.println(maxRadius);
    }

    static int getRadius(int from, Edge node){
        if(tree[node.to].isEmpty()){
            radius[node.to] = -1;
            return 0;
        }

        int max1 = 0, max2 = 0, temp;
        int curNode = node.to;
        for(Edge next : tree[curNode]){
            if(radius[next.to] == -1){
                temp = next.weight;
                radius[curNode] = Math.max(temp, radius[curNode]);
            }else if(radius[next.to] != 0){
                temp = next.weight + radius[next.to];
                radius[curNode] = Math.max(temp, radius[curNode]);
            }else{ //radius[next.to] == 0
                temp = next.weight + getRadius(curNode, next);
                radius[curNode] = Math.max(radius[curNode], temp);
            }

            if(temp > max1){
                max2 = max1;
                max1 = temp;
            }else{
                if(temp > max2){
                    max2 = temp;
                }
            }
        }

        maxRadius = Math.max(maxRadius, max1 + max2);

        return radius[curNode];
    }



}