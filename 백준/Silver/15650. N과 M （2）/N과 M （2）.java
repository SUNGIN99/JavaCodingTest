import java.util.*;
import java.io.*;

public class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for(int i = 1; i<= n; i++){
            getNum(i, 1, k, i + " ", n);
        }
    }

    static void getNum(int lastNum, int len, int targetLen, String nums, int n){

        if(len == targetLen){
            System.out.println(nums);
            return;
        }

        for(int i = lastNum + 1; i<=n; i++){
            getNum(i, len + 1, targetLen, nums + i + " ", n);
        }
    }







}