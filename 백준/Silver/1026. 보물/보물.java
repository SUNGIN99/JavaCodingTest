import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> a = new PriorityQueue<>();

        st =new StringTokenizer(br.readLine());
        for(int i = 0; i< n ;i++){
            a.add(Integer.parseInt(st.nextToken()));
        }


        PriorityQueue<Integer> b = new PriorityQueue<>();

        st =new StringTokenizer(br.readLine());
        for(int i = 0; i< n ;i++){
            b.add(-1 * Integer.parseInt(st.nextToken()));
        }

        int result = 0;
        for(int i = 0; i<n; i++){
            result += a.poll() * (-1 * b.poll());
        }

        System.out.println(result);
    }


}

