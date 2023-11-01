import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        int testcase = Integer.parseInt(st.nextToken());
        for(int i = 0; i<testcase; i++){
            solution(i+1);
        }
    }
    public static class Customer{
        int x;
        int y;
        int far;

        public Customer(int x, int y){
            this.x = x;
            this.y = y;
        }
    }


    static boolean[] visited;
    static Customer company, home;
    static Customer[] infos;
    public static void solution(int testcaseNum) throws IOException{
        st = new StringTokenizer(br.readLine());
        int n =Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int companyX = Integer.parseInt(st.nextToken());
        int companyY = Integer.parseInt(st.nextToken());
        int homeX = Integer.parseInt(st.nextToken());
        int homeY = Integer.parseInt(st.nextToken());

        infos = new Customer[n];
        for (int i = 0; i<n; i++){
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            Customer info = new Customer(x, y);
            infos[i] = info;
        }

        company = new Customer(companyX, companyY);
        home = new Customer(homeX, homeY);
        visited = new boolean[n];
        int shortestRoot = Integer.MAX_VALUE;
        for(int i = 0; i<n; i++){
            int far = getDiff(company, infos[i]);
            shortestRoot = Math.min(shortestRoot, dfs(i, far, 1));
        }

        System.out.println("#"+testcaseNum + " " + shortestRoot);
    }

    public static int getDiff(Customer c1, Customer c2){
        return Math.abs(c1.x - c2.x) + Math.abs(c1.y - c2.y);
    }

    public static int dfs(int index, int far, int count){
        int n = visited.length;
        if(count == n){
            return far + getDiff(infos[index], home);
        }

        visited[index] = true;
        int shortestRoot = Integer.MAX_VALUE;
        for(int i = 0; i<n; i++){
            if (i != index && !visited[i]){
                shortestRoot = Math.min(shortestRoot, dfs(i, far + getDiff(infos[index], infos[i]), count + 1));
            }
        }

        visited[index] = false;
        return shortestRoot;
    }
}
