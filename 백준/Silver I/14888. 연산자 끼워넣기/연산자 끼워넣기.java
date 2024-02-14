import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[] nums;
    static int[] op;

    static int max, min;

    static int count;
    static String[] strop;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // n 의 개수 입력
        n = Integer.parseInt(st.nextToken()); // 최대 100개

        nums = new int[n];
        op = new int[4];

        // A1 ~ An 입력
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // 연산자 개수
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<4; i++){
            op[i] = Integer.parseInt(st.nextToken());
        }

        strop = new String[]{" + " , " - ", " * ", " / "};

        max = (-1) * Integer.MAX_VALUE;
        min = Integer.MAX_VALUE;
        count = 1;
        for(int i = 0; i<4; i++){
            int opCount = op[i];
            if(opCount > 0){
                int firstR = calculator(nums[0], nums[1], i);
                backTracking(firstR, 2, i, nums[0] + strop[i] +  nums[1]);
            }
        }

        System.out.println(max);
        System.out.println(min);

    }

    static void backTracking(int exR, int index, int exOp, String cal){
        // 이전 식 까지의 결과 값, 현재 계산을 해줘야할 추가 숫자, 이전에 계산한 연산자

        if(index >= n){
            max = Math.max(exR, max);
            min = Math.min(exR, min);
            //System.out.println(count + ": " + cal + " = " + exR);
            count ++;

            return;
        }

        op[exOp]--;
        for(int i = 0 ;i <4; i++){
            int opCount = op[i];
            if(opCount > 0){
                int nextR = calculator(exR, nums[index], i);
                backTracking(nextR, index + 1, i , cal + strop[i] + nums[index]);
            }
        }

        op[exOp]++;

    }

    static int calculator(int n1, int n2, int opp){
        if(opp == 0){
            return n1 + n2;
        }else if (opp == 1) {
            return n1 - n2;
        }else if (opp == 2) {
            return n1 * n2;
        }else { // opp == 3
            if(n1 < 0 && n2 > 0){
                return -1 * ((-1 * n1) / n2);
            }else{
                return n1 / n2;
            }
        }
    }


}

