import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    static ArrayList<Integer> nodes[];
    static int[] parent;
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        parent = new int[N+1];
        nodes = new ArrayList[N+1];

        for(int i = 1; i<=N; i++){
            nodes[i] = new ArrayList<>();
        }

        int a, b;
        for(int i = 0; i<N-1; i++){
            a = sc.nextInt();
            b = sc.nextInt();

            nodes[a].add(b);
            nodes[b].add(a);

        }

        dfs(1, 1);

        for(int i = 2; i<=N; i++){
            System.out.println(parent[i]);
        }
    }

    static void dfs(int currNode, int parentNode){
        for(int child : nodes[currNode]){
            if (child != parentNode){
                if(parent[child] == 0){
                    parent[child] = currNode;
                }
                dfs(child, currNode);
            }
        }
    }



}
