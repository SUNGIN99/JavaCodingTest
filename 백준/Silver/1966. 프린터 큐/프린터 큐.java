import java.util.*;
import java.io.*;

public class Main{

    static class Node{
        int value;
        int index;

        Node(int v, int i){
            this.value = v;
            this.index = i;
        }

        public String toString(){
            return "("+value + ", "+index +")";
        }

    }

    static Integer[] priority;
    static Queue<Node> queue;
    static Node toFind;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        int n, index;
        for(int i = 0; i<t; i++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            index = Integer.parseInt(st.nextToken());
            priority = new Integer[n];
            queue = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                int value = Integer.parseInt(st.nextToken());
                if (j == index){
                    toFind = new Node(value, j);
                }
                priority[j] = value;
                queue.add(new Node(value, j));
            }

            getSequence(n);
        }
    }

    static void getSequence(int n){
        Arrays.sort(priority, Collections.reverseOrder());

        int priorIndex = 0;
        int seq = 1;
        while(priorIndex < n){
            //System.out.println(queue);
            Node a = queue.poll();

            if(a.value == priority[priorIndex]){
                if(a.value == toFind.value && a.index == toFind.index){
                    break;
                }
                priorIndex ++;
                seq ++;
            }else{
                queue.add(a);
            }

        }

        System.out.println(seq);
    }

}