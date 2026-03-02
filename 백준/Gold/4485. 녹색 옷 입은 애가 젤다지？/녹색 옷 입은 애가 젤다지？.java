import java.io.*;
import java.util.*;


/*

 */
public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n;
        int[][] matrix;
        int cnt = 1;
        while(true){
            n = Integer.parseInt(br.readLine());

            if(n == 0) break;

            matrix = new int[n][n];
            for(int i = 0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                matrix[i] = new int[n];
                for(int j = 0; j<n; j++){
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bfs(matrix, cnt++);
        }
    }

    static void bfs(int[][] matrix, int cnt){
        int sum = 0;

        int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0,1}, {0, -1}};

        int n = matrix.length;
        int[][] visited = new int[n][n];
        for(int i = 0 ; i<n; i++){
            visited[i] = new int[n];
        }

        Path start = new Path(0, 0, matrix[0][0]);
        visited[0][0] = -1;

        PriorityQueue<Path> pq = new PriorityQueue<>();
        pq.add(start);

        while(!pq.isEmpty()){
            Path p = pq.poll();

            if(p.x == n-1 && p.y == n-1){
                System.out.println("Problem " + cnt + ": " + p.rupee);
                return;
            }

            for(int i = 0; i<4; i++){
                int nextX = p.x  + dirs[i][0];
                int nextY = p.y  + dirs[i][1];

                if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= n){

                }else {
                    if(visited[nextX][nextY] == 0 || visited[nextX][nextY] > p.rupee + matrix[nextX][nextY]){
                        visited[nextX][nextY] = p.rupee+matrix[nextX][nextY];
                        pq.add(new Path(nextX, nextY, p.rupee + matrix[nextX][nextY]));
                    }
                }
            }
        }

    }

    static class Path implements Comparable<Path>{
        int x, y;

        int rupee;

        Path(int x, int y, int r){
            this.x = x;
            this.y = y;
            this.rupee= r;
        }

        public int compareTo(Path p){
            if(this.rupee == p.rupee){
                if(this.x == p.x){
                    return p.y - this.y;
                }else{
                    return p.x - this.x;
                }
            }

            return this.rupee - p.rupee;
        }


    }

}
