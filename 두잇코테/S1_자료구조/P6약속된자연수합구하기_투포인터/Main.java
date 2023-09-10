package 두잇코테.S1_자료구조.P6약속된자연수합구하기_투포인터;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int head = 1, rear = 1;
        int sum = 1, count = 1;

        while(rear < N){
            //System.out.printf("%d %d %d\n", sum, head, rear);
            if (sum == N){
                count ++;
                sum -= head;
                head ++;
            }
            else if(sum < N){
                rear ++;
                sum += rear;
            }else if(sum > N){
                sum -= head;
                head ++;

            }
        }

        System.out.println(count);
    }
}
