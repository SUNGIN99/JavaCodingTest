import java.util.*;
import java.io.*;

public class Main{

    static int[] a, b;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        int sum;
        for(int i = 0; i<t; i++){
            st = new StringTokenizer(br.readLine());
            int aCount = Integer.parseInt(st.nextToken());
            int bCount = Integer.parseInt(st.nextToken());
            a = new int[aCount];
            b = new int[bCount];

            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<aCount; j++){
                a[j] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<bCount; j++){
                b[j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(a);
            //System.out.println(Arrays.toString(a));
            System.out.println(getCouples());

        }
    }

    static int getCouples(){

        int sum = 0;
        for(int bNum : b){
            int left = 0, right = a.length;
            int mid;
            while(left < right){
                mid = left + (right - left) / 2;

                if(a[mid] <= bNum){
                    left = mid + 1;
                }else{ // a[mid] > bNum
                    right = mid;
                }
            }
            //System.out.println("left : " + left);
            sum += a.length - left;

        }

        return sum;

    }

}