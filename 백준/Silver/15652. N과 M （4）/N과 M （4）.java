import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static BufferedWriter bw;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());


        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 1; i<= n; i++){
            dfs(i, 1, i + " ");
        }
        bw.flush();
        bw.close();

    }

    static void dfs(int lastNum, int count, String nums) throws IOException {
        if(count == m){
            bw.write(nums+"\n");
            return;
        }

        for(int i = lastNum; i<=n; i++){
            dfs(i, count + 1, nums + i + " ");
        }
    }








}