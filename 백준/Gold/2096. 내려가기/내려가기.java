import java.util.*;
import java.io.*;

public class Main {


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[][] cage = new int[n][3];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            cage[i][0] = Integer.parseInt(st.nextToken());
            cage[i][1] = Integer.parseInt(st.nextToken());
            cage[i][2] = Integer.parseInt(st.nextToken());
        }

        int[][] max = new int[n][3];
        int[][] min = new int[n][3];

        max[0][0] = min[0][0] = cage[0][0];
        max[0][1] = min[0][1] = cage[0][1];
        max[0][2] = min[0][2] = cage[0][2];

        for(int i = 1; i<n; i++){
            max[i][0] = cage[i][0] + Math.max(max[i-1][0], max[i-1][1]);
            max[i][1] = cage[i][1] + Math.max(max[i-1][2], Math.max(max[i-1][0], max[i-1][1]));
            max[i][2] = cage[i][2] + Math.max(max[i-1][1], max[i-1][2]);

            min[i][0] = cage[i][0] + Math.min(min[i-1][0], min[i-1][1]);
            min[i][1] = cage[i][1] + Math.min(min[i-1][2], Math.min(min[i-1][0], min[i-1][1]));
            min[i][2] = cage[i][2] + Math.min(min[i-1][1], min[i-1][2]);
        }

        int maxNum = Math.max(max[n-1][2], Math.max(max[n-1][0], max[n-1][1]));
        int minNum = Math.min(min[n-1][2], Math.min(min[n-1][0], min[n-1][1]));

        System.out.println(maxNum + " " + minNum);
    }
}
