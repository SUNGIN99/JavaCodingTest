import java.util.*;
import java.io.*;

public class Main{


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        long sum = 4 * (long)n;
        System.out.println(sum);

    }
}