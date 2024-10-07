import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Integer.parseInt(st.nextToken());
        long k = Integer.parseInt(st.nextToken());


        PriorityQueue<Long> queue = new PriorityQueue<>();

         long curLitter = 1;
        long divide, remain;
        // 여기를 n> K라고 해서 틀림

         while(n > 1){
             //System.out.println(n);
             divide = n / 2;
             remain = n % 2;

             n = divide;
             if(remain == 1){
                 queue.add(remain * curLitter);
             }
             curLitter *= 2;
         }
         queue.add(curLitter);

        long bottle = 0;
        //System.out.println("middle : " + queue + "\n ------");

        long top1, top2;
        while(!queue.isEmpty() && queue.size() > k){
            if(queue.size() == 1){
                break;
            }
            //System.out.println(bottle + " : "  + queue);

            top1 = queue.poll();

            if(!queue.isEmpty()){
                top2 = queue.poll();

                if(top1 == top2){
                    queue.add(top1 * 2);
                }else{
                    bottle += top1;
                    if(top1 * 2 == top2){
                        n--;
                        queue.add(top2 * 2);
                    }else{
                        queue.add(top1 * 2);
                        queue.add(top2);
                    }
                }
            }
            else{
                queue.add(top1 * 2);
            }

        }
        //System.out.println("-----");
        //System.out.println("end : " + queue);

        System.out.println(bottle);

    }

    /*
    15 2
    15
    7
    3
    middle : [1, 2, 4, 8]
     ------
    0 : [1, 2, 4, 8]
    1 : [4, 8, 4]
    5 : [4, 8, 8]
    9
     */

}
