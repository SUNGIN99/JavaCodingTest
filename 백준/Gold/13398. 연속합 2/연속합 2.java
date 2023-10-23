
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] a = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i< n; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }

        int[] l = new int[n];
        l[0] = a[0];
        int result = l[0];
        for(int i = 1; i< n; i++){
            l[i] = Math.max(a[i], l[i-1] + a[i]); // 더한 값이 음수가 발생하면, stop 하고 그다음부터 다시 원래대로 더함
            result = Math.max(result, l[i]); // 수열 왼쪽부터 1개도 제거하지 않았을 때의결과
            //System.out.println(Arrays.toString(l));
        }
        // System.out.println(result);
        int[] r = new int[n];
        r[n-1] = a[n-1];
        for(int i = n-2; i>= 0; i--){
            r[i] = Math.max(a[i], r[i+1] + a[i]);
            //System.out.println(Arrays.toString(r));
        }

        for(int i = 1; i< n -1; i++){
            int temp = l[i-1] + r[i+1]; // 하나를 뺀값을 더함
            result = Math.max(result, temp);
        }
        //System.out.println(Arrays.toString(l));
        //System.out.println(Arrays.toString(r));

        System.out.println(result);
        br.close();


    }

}

/**
 * 10
 * 10 -4 3 1 5 6 -35 12 21 -1
 * [10, 6, 0, 0, 0, 0, 0, 0, 0, 0]
 * [10, 6, 9, 0, 0, 0, 0, 0, 0, 0]
 * [10, 6, 9, 10, 0, 0, 0, 0, 0, 0]
 * [10, 6, 9, 10, 15, 0, 0, 0, 0, 0]
 * [10, 6, 9, 10, 15, 21, 0, 0, 0, 0]
 * [10, 6, 9, 10, 15, 21, -14, 0, 0, 0]
 * [10, 6, 9, 10, 15, 21, -14, 12, 0, 0]
 * [10, 6, 9, 10, 15, 21, -14, 12, 33, 0]
 * [10, 6, 9, 10, 15, 21, -14, 12, 33, 32]
 * 33
 * [0, 0, 0, 0, 0, 0, 0, 0, 21, -1]
 * [0, 0, 0, 0, 0, 0, 0, 33, 21, -1]
 * [0, 0, 0, 0, 0, 0, -2, 33, 21, -1]
 * [0, 0, 0, 0, 0, 6, -2, 33, 21, -1]
 * [0, 0, 0, 0, 11, 6, -2, 33, 21, -1]
 * [0, 0, 0, 12, 11, 6, -2, 33, 21, -1]
 * [0, 0, 15, 12, 11, 6, -2, 33, 21, -1]
 * [0, 11, 15, 12, 11, 6, -2, 33, 21, -1]
 * [21, 11, 15, 12, 11, 6, -2, 33, 21, -1]
 * [10, 6, 9, 10, 15, 21, -14, 12, 33, 32]
 * [21, 11, 15, 12, 11, 6, -2, 33, 21, -1]
 * 54
 */
