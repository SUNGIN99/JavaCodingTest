import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    /*
    6
4 2 6 3 1 5
     */
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; // = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(br.readLine());
        int[] chips = new int[n];
        int left = 0, right = 0, len = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){

            int a = Integer.parseInt(st.nextToken());
            // chips[i] = a;

            left = 0;
            right = len;
            while(left < right){
                int mid = (left + right) / 2;
                if(chips[mid] < a){
                    left = mid + 1;
                }else{
                    right = mid;
                }
                //System.out.println(1);
            }

            chips[right] = a;
            if(right + 1 > len){
                len = right + 1;
            }
        }

        System.out.println(len);

    }

}