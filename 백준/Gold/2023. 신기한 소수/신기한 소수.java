import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] oddNums = new int[]{1, 3, 5, 7, 9};
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());

        dfs(2, 1);
        dfs(3, 1);
        dfs(5, 1);
        dfs(7, 1);

    }

    public static void dfs(int num, int count){
        int temp;

        if(count == n){
            if(isPrime(num)){
                System.out.println(num);
            }
            return;
        }

        for(int i = 0; i<5; i++){
            temp = (num * 10) + oddNums[i];
            // System.out.println(temp);
            if(isPrime(temp)){
                dfs(temp, count+1);
            }
        }
    }

    public static boolean isPrime(int num){
        if(num <= 1){
            return false;
        }

        for(int i = 2; i<=Math.sqrt(num); i++){
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

}
