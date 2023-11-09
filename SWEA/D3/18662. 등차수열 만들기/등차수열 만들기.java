
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        int tc = Integer.parseInt(st.nextToken());
        for(int i = 0; i<tc; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            System.out.println("#" + (i+1) + " " + solution(a,b,c));
        }
    }

    public static double solution(int a, int b, int c){
        if (b-a == c-b){
            return 0.0;
        }
        double v12 = (double) b-a;
        double v13 = (double) c-a;
        double v23 = (double) c-b;

        double x1 = Math.abs(v12 + v12 + (double) a - (double) c);
        double x2 = Math.abs(v13 - (v13/2) + (double) a - (double) b);
        double x3 = Math.abs((double) c - (v23 + v23) - (double) a);

        return Math.min(x1, Math.min(x2, x3));
    }

}