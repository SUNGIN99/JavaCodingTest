package 두잇코테.S1_자료구조.P1숫자의합구하기;

import java.util.Scanner;

//https://www.acmicpc.net/problem/11720
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        String numStr = sc.next();

        char[] nums = new char[N];

        nums = numStr.toCharArray();

        int result = 0;
        for (int i = 0; i < N; i++){
            result += Integer.parseInt(String.valueOf(nums[i]));
        }

        System.out.println(result);
    }
}
