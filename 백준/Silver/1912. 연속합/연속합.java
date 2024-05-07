import java.util.*;
import java.io.*;

public class Main {


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] nums = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int dpLeft[] = new int[n];
        dpLeft[0] = nums[0];

        int max = dpLeft[0];
        for(int i = 1; i<n; i++){
            if(dpLeft[i-1] + nums[i] > 0){
                dpLeft[i] = dpLeft[i-1] + nums[i];
            }else{
                dpLeft[i] = nums[i];
            }
            max = Math.max(dpLeft[i], max);
        }

        //System.out.println(Arrays.toString(dpLeft));

        int dpRight[] = new int[n];
        dpRight[n-1] = nums[n-1];
        max = Math.max(dpRight[n-1], max);
        for(int i = n-2; i>=0; i--){
            if(dpRight[i+1] + nums[i] > 0){
                dpRight[i] = dpRight[i+1] + nums[i];
            }else{
                dpRight[i] = nums[i];
            }
            max = Math.max(dpRight[i], max);
        }

        //System.out.println(Arrays.toString(dpRight));
        System.out.println(max);
    }


}