import java.util.*;
import java.io.*;

public class Main {

    static class Class implements Comparable<Class>{
        long start, end;

        Class (long s, long e){
            this.start = s;
            this.end = e;
        }

        public int compareTo(Class c){
            if(this.start < c.start){
                return -1;
            }else if(this.start > c.start){
                return 1;
            }else{
                if(this.end < c.end){
                    return -1;
                }else if(this.end > c.end){
                    return 1;
                }else{
                    return 0;
                }
            }
        }

        public String toString(){
            return "(" + start + ", " + end+")";
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Class> queue = new PriorityQueue<>();

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            long start = Integer.parseInt(st.nextToken());
            long end = Integer.parseInt(st.nextToken());
            queue.add(new Class(start, end));
        }

        PriorityQueue<Long> endTimes = new PriorityQueue<>();
        int count =0;
        while(!queue.isEmpty()){
            Class clas = queue.poll();
            long start = clas.start;
            long end = clas.end;

            if(endTimes.isEmpty()){
                count++;
                endTimes.add(end);
            }else{
                if(endTimes.peek() <= start){
                    endTimes.add(end);
                    endTimes.poll();
                }else{
                    endTimes.add(end);
                    count++;
                }
            }
            //System.out.println(endTimes);

        }


        System.out.println(count);
    }


}