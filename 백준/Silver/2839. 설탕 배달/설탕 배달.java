import java.util.*;
import java.io.*;

public class Main {
    static int[] cards;
    static boolean[] visited;
    static int n, m, result, check;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 배달할 설탕의 무게 (kg)
        n = Integer.parseInt(st.nextToken());
        int five = 0, three = 0;

        int[] sugarW = new int[5001];

        for(int i = 0; i<n+1; i++){
            sugarW[i] = -1;
        }

        sugarW[0] = 0;
        sugarW[3] = 1;
        sugarW[5] = 1;

        for(int i = 6; i<n+1; i++){
            if(sugarW[i-5] != -1){ // 5의 배수일 때
                sugarW[i] = sugarW[i-5] + 1;
            }
            if(sugarW[i-3] != -1){ // 3의 배수일 떄
                if(sugarW[i] != -1){
                    sugarW[i] = Math.min(sugarW[i-3] + 1, sugarW[i]);
                    continue;
                }
                sugarW[i] = sugarW[i-3] + 1;
            }
        }

        //for(int i = 0; i<n+1; i++){
        //    System.out.println(i + ": " + sugarW[i]);
        //}

        System.out.println(sugarW[n]);


    }



}