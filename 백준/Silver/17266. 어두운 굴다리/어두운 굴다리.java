import java.util.*;
import java.io.*;

public class Main{

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());


        int standLight;
        int height = 0;
        int prev = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<m; i++){
            standLight = Integer.parseInt(st.nextToken());
            //System.out.println(height);
            if(m == 1){
                height = Math.max(n - standLight, standLight);
                break;
            }

            if(i == 0){
                height = Math.max(height, standLight);
                prev = standLight;
                continue;
            }

            int gap = standLight - prev;
            prev = standLight;
            height = Math.max(gap / 2 + gap % 2 , height);

            if(i == m-1){
                height = Math.max(height, n - standLight);
            }
        }

        System.out.println(height);
    }



}