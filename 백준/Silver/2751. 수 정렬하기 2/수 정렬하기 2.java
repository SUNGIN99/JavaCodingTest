import java.util.*;
import java.io.*;

public class Main{

    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        arr = new int[n];
        for(int i = 0; i<n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        mergeSort(0, n);

        //System.out.println(Arrays.toString(arr));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i<n; i++){
            bw.write(arr[i]+"\n");
        }
        bw.flush();
        bw.close();
    }

    static void mergeSort(int start, int end){
        int mid = start + (end - start) / 2;

        if(end - start <= 1){
            return;
        }

        mergeSort(start, mid);
        mergeSort(mid, end);


        //System.out.print(start + ", " +  end + ": ");

        int left = start;
        int right = mid;
        int[] sorted = new int[end - start];
        int i = 0;
        while(left < mid && right < end){
            if(arr[left] >= arr[right]){
                sorted[i++] = arr[right++];
            }else{
                sorted[i++] = arr[left++];
            }
        }

        for(int j = left; j<mid; j++){
            sorted[i++] = arr[j];
        }

        for(int j = right; j<end; j++){
            sorted[i++] = arr[j];
        }
        //System.out.println(Arrays.toString(sorted));
        System.arraycopy(sorted, 0, arr, start, end- start);

    }


}