import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(br.readLine());

        if(n <= 100000 && n % 2024 == 0 ){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }
    }


}
