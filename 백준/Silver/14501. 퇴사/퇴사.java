import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[] t = new int[n+1];
        int[] p = new int[n+1];

        for(int i = 1; i<= n; i++){
            st = new StringTokenizer(br.readLine());

            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        int[] d = new int[n+2];

        if (t[n] == 1) {
            d[n] = p[n];
        }

        for(int i = n-1; i>= 1; i--){
            d[i] = d[i+1];
            if(i + t[i] - 1<= n){
                d[i] = Math.max(d[i], d[i + t[i]] + p[i]);
            }
        }
        //System.out.println(Arrays.toString(d));
        System.out.println(d[1]);
    }

}
