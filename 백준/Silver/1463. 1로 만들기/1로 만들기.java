
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] D = new int [n+1];

        D[1] = 0;

        for(int i = 2; i< n+1; i++){
            D[i] = D[i-1] + 1;
            if(i%2 == 0) {
                D[i] = Math.min(D[i], D[i/2] + 1);
            }
            if(i%3 == 0){
                D[i] = Math.min(D[i], D[i/3] + 1);
            }
        }

        System.out.println(D[n]);
    }

}
