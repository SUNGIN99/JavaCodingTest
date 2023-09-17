import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    static ArrayList<Integer> nodes[];
    static int answer, removeNode;
    static boolean visited[];
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        nodes = new ArrayList[N];
        visited = new boolean[N];
        for(int i = 0; i<N; i++){
            nodes[i] = new ArrayList<>();
        }

        int parent, root = 0;
        for(int i = 0; i<N; i++){
            parent = sc.nextInt();
            if(parent != -1){
                nodes[parent].add(i);
                nodes[i].add(parent);
            }else{
                root = i;
            }
        }

        removeNode = sc.nextInt();

        answer = 0;
        if(removeNode != root)
            leaf(root);


        System.out.println(answer);

    }

    static void leaf(int node){
        visited[node] = true;
        int cNode = 0;
        
        for(int next : nodes[node]){
            if(next != removeNode && visited[next] != true) {
                cNode++;
                leaf(next);
            }
        }
        if(cNode == 0){
            answer++;
        }
        
    }
}
