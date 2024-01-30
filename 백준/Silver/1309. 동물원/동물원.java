import java.util.*;
import java.io.*;

public class Main {


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[][] cage = new int[n+1][3];

        cage[1][0] = 1;
        cage[1][1] = 1;
        cage[1][2] = 1;
        for(int i = 2; i<= n; i++){
            cage[i][0] = (cage[i-1][0] + cage[i-1][1] + cage[i-1][2]) % 9901;
            cage[i][1] = (cage[i-1][0] + cage[i-1][2]) % 9901;
            cage[i][2] = (cage[i-1][0] + cage[i-1][1]) % 9901;
        }

        System.out.println((cage[n][0] + cage[n][1] + cage[n][2])%9901);


    }
}
