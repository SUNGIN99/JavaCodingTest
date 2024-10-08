import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] city= new ArrayList[n+1];

        for(int i = 1; i <= n; i++){
            city[i] = new ArrayList<>();
        }

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            city[from].add(to);
        }

        int[] route = new int[n+1];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, 0});

        while(!queue.isEmpty()){
            int[] vertex = queue.poll();

            int node = vertex[0];
            int weight = vertex[1];

            ArrayList<Integer> edge = city[node];

            for(Integer e : edge){
                if(e != x && (route[e] == 0 || route[e] > weight + 1)){
                    route[e] = weight + 1;
                    queue.add(new int[]{e, weight + 1});
                }
            }
        }

        //System.out.println(Arrays.toString(route));
        boolean none = true;
        for(int i = 1; i<=n; i++){
            if(route[i] == k ){
                System.out.println(i);
                none = false;
            }
        }

        if(none){
            System.out.println(-1);
        }


    }

}
