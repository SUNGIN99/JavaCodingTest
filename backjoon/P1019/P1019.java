package backjoon.P1019;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

/***
 * 1019번 책 페이지
 * https://www.acmicpc.net/problem/1019
 *
 * 문제
 * 지민이는 전체 페이지의 수가 N인 책이 하나 있다. 첫 페이지는 1 페이지이고, 마지막 페이지는 N 페이지이다. 각 숫자가 전체 페이지 번호에서 모두 몇 번 나오는지 구해보자.
 *
 * 입력
 * 첫째 줄에 N이 주어진다. N은 1,000,000,000보다 작거나 같은 자연수이다.
 *
 * 출력
 * 첫째 줄에 0이 총 몇 번 나오는지, 1이 총 몇 번 나오는지, ..., 9가 총 몇 번 나오는지를 공백으로 구분해 출력한다.
 *
 * 예제 입력 1
 * 11
 * 예제 출력 1
 * 1 4 1 1 1 1 1 1 1 1
 * 예제 입력 2
 * 7
 * 예제 출력 2
 * 0 1 1 1 1 1 1 1 0 0
 * 예제 입력 3
 * 19
 * 예제 출력 3
 * 1 12 2 2 2 2 2 2 2 2
 * 예제 입력 4
 * 999
 * 예제 출력 4
 * 189 300 300 300 300 300 300 300 300 300
 * 예제 입력 5
 * 543212345
 * 예제 출력 5
 * 429904664 541008121 540917467 540117067 533117017 473117011 429904664 429904664 429904664 429904664
 */
public class P1019 {

    static Integer[] count = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public static void solution(int n){
        String[] numStr = String.valueOf(n).split("");
        int length = numStr.length;

        // N이 1의 자리 수일 경우
        if (length - 1 == 0){
            for (int i = 1;  i<= n; i++){
                count[i] += 1;
            }
            return;
        }

        int head = Integer.valueOf(numStr[0]);
        int maxHead = head - 1;





    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        solution(N);


    }


}
