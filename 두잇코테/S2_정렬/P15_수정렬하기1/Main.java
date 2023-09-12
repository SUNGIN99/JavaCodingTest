package 두잇코테.S2_정렬.P15_수정렬하기1;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] nums = new int[N];
        for (int i = 0 ;i <N; i ++){
            nums[i] = sc.nextInt();
        }

        int temp;
        for(int i = 0; i<N; i++){
            for(int j = 1; j<N-i; j++){
                if(nums[j] < nums[j-1]){
                    temp = nums[j-1];
                    nums[j-1] = nums[j];
                    nums[j] = temp;
                }
            }
        }

        //System.out.println(Arrays.toString(nums));

        Arrays.stream(nums).filter(
                n -> {
                    System.out.println(n);
                    return true;
                }
        ).count();

    }
}
