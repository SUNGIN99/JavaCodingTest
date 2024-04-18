import java.util.*;
import java.io.*;

public class Main{

    static int n, m;
    static int[][] square;
    static int answer = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        square = new int[n][];
        String[] nums;
        for(int i = 0; i<n; i++){
            square[i] = new int[m];
            st = new StringTokenizer(br.readLine());
            nums = st.nextToken().split("");
            for(int j = 0; j<m; j++){
                square[i][j] = Integer.parseInt(nums[j]);
            }
        }

        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                getSquare(i, j);
            }
        }

        System.out.println(answer);
    }

    static void getSquare(int x, int y){
        int i = 0;
        while(x+i < n && y+i < m){
            if(square[x][y] == square[x+i][y]
                    && square[x][y] == square[x+i][y+i]
                    && square[x][y] == square[x][y+i]){
                answer = Math.max((i+1) * (i+1), answer);
            }
            i++;
        }
    }


}