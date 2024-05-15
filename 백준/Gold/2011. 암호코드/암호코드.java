import java.util.*;
import java.io.*;

public class Main{

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());
        String password = br.readLine();

        if(password.length() == 0 || password.substring(0,1).equals("0")){
            System.out.println(0);
            return;
        }else if(password.length() == 1){
            System.out.println(1);
            return;
        }

        String[] nums = password.split("");
        //System.out.println(Arrays.toString(nums));

        int n = nums.length;
        int[] dp = new int[n];

        dp[0] = 1;
        // 현재 자리수가 0인경우를 생각해야함.
        for(int i = 1; i<n; i++){
            int subPw = Integer.parseInt(nums[i-1] + nums[i]);

            if(nums[i].equals("0")){ // 현재 자리수가 0 일 경우
                if(subPw == 10 || subPw == 20){// 끝자리가 0 인것은 해당 수가 확정적으로 J 또는 T라는 것
                    if(i>=2){
                        dp[i] = dp[i-2] % 1000000;
                    }else{
                        dp[i] = 1;
                    }
                }else{ // 0 또는 30이상 부터는 잘못된 암호
                    System.out.println(0);
                    return;
                    //dp[i] = dp[i-1] % 1000000;
                }
            }else if(nums[i-1].equals("0")){ // 내 앞에 자리수가 0 일 경우
                dp[i] = dp[i-1] % 1000000;
            }else{
                if(subPw <= 26){
                    if(i >= 2){
                        dp[i] = (dp[i-1] + dp[i-2]) % 1000000;
                    }else{
                        dp[i] = dp[i-1] + 1;
                    }
                }else{
                    dp[i] = dp[i-1] % 1000000;
                }
            }
        }

        //System.out.println(Arrays.toString(dp));
        System.out.println(dp[n-1]);

    }


}