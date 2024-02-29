import java.util.*;
import java.io.*;

public class Main {

    static int[] cases;
    static int count;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        cases = new int[n];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            cases[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i<n; i++){
            count = 0;
            for(int sum = 1; sum<= 3; sum++){
                getSum(sum, cases[i]);
            }
            System.out.println(count);
        }
    }

    static void getSum(int sum, int target){
        if(sum == target){
            count++;
        }else if(sum > target){
            return;
        }else{
            for(int i = 1; i<= 3; i++){
                if(sum + i <= target){
                    getSum(sum + i, target);
                }
            }
        }
    }



}