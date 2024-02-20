import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] nums = new int[n+1];

        for(int i = 1; i<= n; i++){
            nums[i] = i;
        }

        int[] popNum = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<m; i++){
            popNum[i] = Integer.parseInt(st.nextToken());
        }

        int i = 0;
        int leftright = 0;
        int index = 1;
        while(i<m){
            int left = 0;
            int right = 0;

            int target = popNum[i];
            // 왼쪽으로 이동하는 횟수 구하기
            int L = index;
            while(target != nums[L]){
                if(nums[L] != 0){
                    left++;
                }
                L++;

                if(L == n + 1){
                    L = 1;
                }
            }

            // 오른쪽으로 이동하는 횟수 구하기
            int R = index;
            while(target != nums[R]){
                if(nums[R] != 0){
                    right++;
                }
                R--;

                if(R == 0){
                    R = n;
                }
            }

            leftright += Math.min(left, right);

            if(left > right){
                index = R;
            }else{
                index = L;
            }

            nums[index] = 0;
            i++;

            if(i >= m){
                break;
            }

            while(nums[index] == 0){
                index++;
                if(index > n){
                    index = 1;
                }
            }

            //System.out.println(target + "("+leftright +")" + ", " + Arrays.toString(nums) );
        }
        System.out.println(leftright);

    }

}

