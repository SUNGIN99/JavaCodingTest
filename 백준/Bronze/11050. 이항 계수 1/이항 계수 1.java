import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();
        
        
        int head = 1, bottom = 1;
        for(int i = K+1; i <= N; i++){
            head *= i;
        }
        for(int j =2; j<= N - K; j++){
            bottom *= j;
        }

        System.out.println(head/bottom);

    }
}