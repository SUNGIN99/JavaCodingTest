import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Long> queue = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            queue.add(Long.parseLong(st.nextToken()) * 2);
        }

        while(queue.size() >= 2){
            long num1 = queue.poll();
            long num2 = queue.poll();

            long uclid = getUclid(num1, num2);

            queue.add(num1 / uclid * num2);
        }

        System.out.println(queue.peek());
    }

    static long getUclid(long n1, long n2){
        if(n2 > n1){
            long temp = n1;
            n1 = n2;
            n2= temp;
        }

        if(n1 % n2 == 0){
            return n2;
        }else{
            return getUclid(n2, n1 % n2);
        }

    }

}
