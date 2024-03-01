import java.util.*;
import java.io.*;

public class Main {

    static BufferedWriter bw;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());


        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 1; i<= n; i++){
            dfs(n, m, 1, i + " ");
        }
        bw.flush();
        bw.close();

    }

    static void dfs(int n, int m, int count, String nums) throws IOException {
        if(count == m){
            bw.write(nums+"\n");
            return;
        }

        for(int i = 1; i<=n; i++){
            dfs(n, m, count + 1, nums + i + " ");
        }
    }








}