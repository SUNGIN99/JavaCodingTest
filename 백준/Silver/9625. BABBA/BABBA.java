import java.util.*;
import java.io.*;

public class Main{

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(br.readLine());

        int[][] ab = new int[46][2];

        for(int i = 0; i<= 45; i++){
            ab[i] = new int[2];
        }

        ab[0][0] = 1;
        for(int i = 1; i<=45; i++){
            if(ab[i-1][0] > 0){
                ab[i][1] += ab[i-1][0];
            }
            if(ab[i-1][1] > 0){
                ab[i][0] += ab[i-1][1];
                ab[i][1] += ab[i-1][1];
            }
        }

        System.out.println(ab[t][0] + " " + ab[t][1]);

    }


}