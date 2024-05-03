import java.util.*;
import java.io.*;

public class Main{

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }


        int[] lis = new int[n];
        lis[0] = arr[0];
        int length = 1;
        for(int i = 1; i <n; i++){
            int left = 0, right = length;
            boolean insert = true;
            while(left < right){
                int mid = left + (right - left) / 2;

                if(lis[mid] < arr[i]){// 더 크면 거기 값 넣기..?
                    right = mid;
                }else if(lis[mid] > arr[i]){
                    left = mid + 1;
                }else{
                    insert = false;
                    break;
                }
            }

            if(insert){
                lis[left] = arr[i];
                if(left >= length){
                    length++;
                }
            }
        }

        System.out.println(length);

    }

}