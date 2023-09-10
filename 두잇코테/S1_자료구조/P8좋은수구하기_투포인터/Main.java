package 두잇코테.S1_자료구조.P8좋은수구하기_투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] nums = new int[N];

        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i<N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        int count = 0;
        if (N > 2){
            int start, end;
            int sum = 0;

            for(int i = 2; i< N; i++){
                start = 0;
                end = i - 1;
                while(start < end){
                    sum = nums[start] + nums[end];

                    if (sum == nums[i]){
                        count ++;
                        break;
                    }else if (sum < nums[i]){
                        start ++;
                    }else{
                        end --;
                    }
                }
            }

        }

        System.out.println(count);
    }

}
