import java.util.*;
import java.io.*;

public class Main{

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        if(n == 1){
            return;
        }

        int i = 2;
        int num = n;
        while(i <= num && n > 0){
            if(n % i == 0){
                System.out.println(i);
                n = n / i;
            }
            else{
                i++;
            }

        }

    }

}