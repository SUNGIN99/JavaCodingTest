import java.util.*;
import java.io.*;

public class Main {

    static class ElectricLine implements Comparable<ElectricLine>{
        int start;
        int end;

        ElectricLine(int start, int end){
            this.start = start;
            this.end = end;
        }

        public int compareTo(ElectricLine e){
            return this.start - e.start;
        }
    }


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        ElectricLine[] lines = new ElectricLine[n];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lines[i] = new ElectricLine(start, end);
        }

        Arrays.sort(lines);

        int[] lis = new int[n];
        int max = 0;
        for(int line = 0; line< n; line++){
            lis[line] = 1;
            for(int j = 0; j<line; j++){
                if(lineUnCross(lines[line], lines[j])){
                    lis[line] = Math.max(lis[line], lis[j] + 1);
                }
            }
            
            max = Math.max(max, lis[line]);
        }

        /*for(int i = 0; i<n; i++){
            System.out.println(lines[i].start + ", " + lines[i].end + " : " + lis[i]);
        }*/

        System.out.println(n - max);
    }

    static boolean lineUnCross(ElectricLine e1, ElectricLine e2){
        if(e1.start < e2.start){
            if(e1.end < e2.end){ // (안 겹침)
                return true;
            }
            else{ // e1.end > e2.end (겹침)
                return false;
            }
        }

        else { // e1.start > e2.start (둘이 같은 경우는 X)
            if(e1.end < e2.end){ // (겹침)
                return false;
            }
            else{  // (안 겹침)
                return true;
            }
        }
    }
}
