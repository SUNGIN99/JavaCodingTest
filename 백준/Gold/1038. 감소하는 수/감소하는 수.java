import java.util.*;
import java.io.*;

public class Main{

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        ArrayList<Long> nums = new ArrayList<>();

        for(long i = 0; i<10 ;i++){
            nums.add(i);
        }


        int start = 0;
        int end = 9;
        long canNum;
        int heads = 0;
        long makeNum = 0;
        while(makeNum < 9876543210L){
            for(int i = heads; i<=9; i++){
                for(int index = start; index<= end; index++){
                    canNum = getHeadNum(nums.get(index));
                    if(canNum >= i){
                        break;
                    }else{
                        makeNum = Long.parseLong(i+ "" + nums.get(index));
                        nums.add(makeNum);
                        //System.out.println(makeNum);
                        //System.out.println(makeNum);
                    }
                }
            }
            start = end+1;
            end = nums.size()-1;
            heads++;
        }

        try{
            System.out.println(nums.get(n));
        }catch(Exception e){
            System.out.println(-1);
        }
    }

    static long getHeadNum(long num){
        return num / (long) Math.pow(10, (""+num).length()-1 );
    }

}