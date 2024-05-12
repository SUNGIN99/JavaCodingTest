import java.util.*;
import java.io.*;

public class Main{

    static char[] function;
    static String[] arr;
    static Deque<String> deque;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(br.readLine());

        for(int i = 0; i<t; i++){
            function = br.readLine().toCharArray();
            int n = Integer.parseInt(br.readLine());
            String nums = br.readLine();
            deque = new LinkedList<>();
            nums = nums.substring(1, nums.length() - 1);
            int from = 0, to = 0;
            for(char c : nums.toCharArray()){
                if(c == ','){
                    deque.add(nums.substring(from, to));
                    from = to+1;
                }
                to++;

            }

            if(n != 0){
                deque.add(nums.substring(from, to));
            }

            //System.out.println("deque :" + deque);
            operate();
        }
        bw.flush();
    }

    static void operate() throws Exception{
        boolean head = true;

        for(char f : function){
            if(f == 'R'){
                head = !head;
            }else{
                if(deque.isEmpty()){
                    bw.write("error\n");
                    return;
                }
                if(head){
                    deque.pollFirst();
                }else{
                    deque.pollLast();
                }
            }
        }

        bw.write("[");
        while(!deque.isEmpty()){
            if(head){
                bw.write(deque.poll());
            }else{
                bw.write(deque.pollLast());
            }
            if(deque.size() >= 1){
                bw.write(",");
            }
        }
        bw.write("]\n");
    }

}