import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(64);

        int ruler = 64;
        int part;
        int half;
        while(true){
            if(ruler > n){
                part = queue.poll();
                half = part / 2;
                if(ruler - half >= n){
                    queue.add(half);
                    ruler -= half;
                }else{
                    queue.add(half);
                    queue.add(half);
                }
            }else if(ruler == n){
                break;
            }
        }

        System.out.println(queue.size());
    }



}