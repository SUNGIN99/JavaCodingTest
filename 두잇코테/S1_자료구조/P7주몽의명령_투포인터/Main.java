package 두잇코테.S1_자료구조.P7주몽의명령_투포인터;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 재료 개수 (1<= N <= 15,000)
        int N = sc.nextInt();


        // 재료의 고유 번호 (1<= M <= 10,000,000)
        int M = sc.nextInt();

        Integer[] nums = new Integer[N];

        for(int i = 0; i<N; i++){
            nums[i] = sc.nextInt();
        }

        // 내림차순 정렬
        Arrays.sort(nums);

        //System.out.println(Arrays.toString(nums));

        int start = 0, end = N-1, count =0;
        int sum;
        while (start < end){
            sum = nums[start] + nums[end];

            if (sum == M){
                start ++; end --;
                count ++;
            } else if (sum < M) {
                start ++;
            } else{
                end --;
            }
        }

        System.out.println(count);


    }
}
