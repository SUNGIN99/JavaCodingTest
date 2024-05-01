import java.util.*;
import java.io.*;

public class Main{

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String line;
        int n = 0;
        ArrayList<StringBuilder> numbers = new ArrayList<>();
        while (true){
            st = new StringTokenizer(br.readLine());

            while(st.hasMoreTokens()){
                String str = st.nextToken();
                if(n == 0){
                    n = Integer.parseInt(str);
                }
                else{
                    numbers.add(new StringBuilder(str));
                }
            }
            //System.out.println("line : " + line);

            if(numbers.size() == n){
                break;
            }
        }
        //System.out.println(numbers);


        PriorityQueue<Long> queue = new PriorityQueue<>();
        for(StringBuilder s : numbers){
            queue.add(Long.parseLong(s.reverse().toString()));
        }

        while(!queue.isEmpty()){
            System.out.println(queue.poll());
        }
    }

}