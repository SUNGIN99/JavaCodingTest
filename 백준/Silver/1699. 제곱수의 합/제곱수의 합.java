import java.util.*;
import java.io.*;

public class Main {


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] result = new int[100001];
        for(int i = 1; i<= 100000; i++){
            int square = (int) Math.sqrt(i);
            if( i - square * square == 0){
                result[i] = 1;
            }else{
                result[i] = i;
            }
        }

        int a, b;
        for(int i = 2; i<=n; i++){
            a = i - 1;
            b = 1;
            //System.out.print(i + ": " + result[i] + " -> " );
            while(a>=b){
                result[i] = Math.min(result[a--] + result[b++], result[i]);
            }
            //System.out.println(result[i]);
        }
        //System.out.println(Arrays.toString(result));
        System.out.println(result[n]);
    }


}