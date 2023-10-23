
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        long[][] d = new long[n+1][];
        for(int i = 0; i< d.length; i++){
            d[i] = new long[2];
        }

        d[1][0] = 0;
        d[1][1] = 1;

        for(int i = 2; i<= n; i++){
            d[i][0] = d[i-1][0] + d[i-1][1];
            d[i][1] = d[i-1][0];
        }

        System.out.println(d[n][0]+ d[n][1]);
    }

}
