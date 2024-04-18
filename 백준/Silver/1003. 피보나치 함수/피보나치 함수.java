import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        for(int i = 0; i<t; i++){
            solution(Integer.parseInt(br.readLine()));
        }

    }

    static int[][] factoResult;
    static void solution(int n){
        factoResult = new int[n+1][];
        int[] r = factorial(n);
        System.out.println(r[0] + " " + r[1]);
    }

    static int[] factorial(int num){
        if(factoResult[num] == null){
            if(num == 0){
                int[] r = new int[]{1, 0};
                factoResult[num] = r;
                return r;
            }else if(num == 1){
                int[] r = new int[]{0, 1};
                factoResult[num] = r;
                return r;
            }else{
                int[] r1 = factorial(num - 1);
                int[] r2 = factorial(num - 2);
                int[] r3 = new int[]{r1[0] + r2[0], r1[1] + r2[1]};
                factoResult[num] = r3;
                return r3;
            }
        }else{
            return factoResult[num];
        }


    }


}