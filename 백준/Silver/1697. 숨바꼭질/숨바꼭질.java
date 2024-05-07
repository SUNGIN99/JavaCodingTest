import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] visited;
        int maxLength;
        if(n >= k){
            visited = new int[n*2+1];
            maxLength = n*2;
        }else{
            visited = new int[k*2+1];
            maxLength = k*2;
        }

        if(n == k){
            System.out.println(0);
            return;
        }

        Queue<int[]> queue = new LinkedList<>();

        if(n >= 1){
            queue.add(new int[]{n-1, 1});
            visited[n-1] = 1;
        }
        queue.add(new int[]{n +1, 1});
        queue.add(new int[]{n * 2, 1});

        visited[n+1] = 1;
        visited[n*2] = 1;

        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int locate = curr[0];
            int time = curr[1];
            //System.out.println(locate + ", " + time);
            if(locate + 1 < maxLength && (visited[locate+1] == 0 || visited[locate+1] > time + 1)){
                visited[locate+1] = time + 1;
                queue.add(new int[]{locate+1, time+1});
            }

            if(locate * 2 < maxLength && (visited[locate * 2] == 0 || visited[locate * 2] > time + 1)){
                visited[locate * 2] = time + 1;
                queue.add(new int[]{locate * 2, time+1});
            }

            if(locate - 1 >= 0 && (visited[locate-1] == 0 || visited[locate-1] > time + 1)){
                visited[locate-1] = time + 1;
                queue.add(new int[]{locate-1, time+1});
            }

        }

        System.out.println(visited[k]);



    }

}