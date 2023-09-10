package 두잇코테.S1_자료구조.P3구간합구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());

        String[] nums = new String[N];
        int [] stackSum = new int[N];

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i<N; i++){
            nums[i] = stringTokenizer.nextToken();
            if(i == 0){
                stackSum[0] = Integer.parseInt(nums[i]);
            }else{
                stackSum[i] = stackSum[i-1] + Integer.parseInt(nums[i]);;
            }
        }

        int[] results = new int[N];
        int start, end;
        for (int i = 0; i<M; i++){
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            start = Integer.parseInt(stringTokenizer.nextToken());
            end = Integer.parseInt(stringTokenizer.nextToken());

            if (start == 1){
                results[i] = stackSum[end - 1];
            }else{
                results[i] = stackSum[end-1] - stackSum[start-1-1];
            }

            System.out.println(results[i]);
        }


    }


}
