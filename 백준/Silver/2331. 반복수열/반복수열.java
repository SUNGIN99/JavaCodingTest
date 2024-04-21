import java.util.*;
import java.io.*;

public class Main{

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        TreeMap<Integer, Integer> nums = new TreeMap<>();

        int nextnum = getNextNum(n, p);
        nums.put(n, 1);
        if(n == nextnum){
            System.out.println(0);
            return;
        }
        nums.put(nextnum, 1);
        int cycled = 0;
        while(true){
            nextnum = getNextNum(nextnum, p);
            //System.out.println(nextnum);
            if(cycled == 1){
                if(nums.containsKey(nextnum)){
                    nums.remove(nextnum);
                    continue;
                }
                else{
                    break;
                }
            }

            if(nums.containsKey(nextnum)){
                nums.remove(nextnum);
                cycled = 1;
            }

            if(cycled != 1){
                nums.put(nextnum, 1);
            }
        }
        System.out.println(nums.size());

    }

    static int getNextNum(int n, int p){
        int num = n;
        int sum = 0;
        while(num != 0){
            sum += Math.pow(num % 10, p);
            num /= 10;
        }
        return sum;
    }

}