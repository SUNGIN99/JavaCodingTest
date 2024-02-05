import java.util.*;
import java.io.*;

public class Main {

    static ArrayList<Integer>[] matrix;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        matrix = new ArrayList[n];

        int[][] result = new int[n][n];
        for(int i = 0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            matrix[i] = new ArrayList<>();
            result[i] = new int[n];
            for (int j = 0; j < n; j++) {
                int valid = Integer.parseInt(st.nextToken());
                if(valid == 1){
                    matrix[i].add(j);
                }
            }
        }



        boolean[] visited;
        for(int i = 0; i<n; i++){
            Queue<Integer> q = new LinkedList<>();
            q.add(i);

            visited = new boolean[n];

            while(!q.isEmpty()){
                int currNode = q.poll();
                ArrayList<Integer> edge = matrix[currNode];

                for(Integer e : edge){
                    if(!visited[e]){
                        visited[e] = true;
                        q.add(e);
                        result[i][e] = 1;
                    }
                }
            }
        }

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }

    }
}

