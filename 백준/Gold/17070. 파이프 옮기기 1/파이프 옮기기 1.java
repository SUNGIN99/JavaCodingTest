import java.util.*;
import java.io.*;

public class Main {

    static class Pipe{
        int x, y;
        int direction;

        Pipe(int x, int y, int d){
            this.x = x;
            this.y =y ;
            this.direction = d;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] matrix = new int[n][];
        for(int i = 0; i<n; i++){
            matrix[i] = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if(matrix[n-1][n-1] == 1){
            System.out.println(0);
            return;
        }
        
        Queue<Pipe> queue = new LinkedList<>();
        queue.add(new Pipe(0,1,1));
        // 1 : 가로
        // 2 : 세로
        // 3 : 대각선

        int result = 0;
        while(!queue.isEmpty()){
            Pipe curPipe = queue.poll();
            int curX = curPipe.x;
            int curY = curPipe.y;
            int curD = curPipe.direction;

            if(curX >= n || curY >= n){
                continue;
            }

            if(curD == 1){
                if(curX == n-1 && curY == n-1){
                    result++;
                    continue;
                }

                if(curY+1 < n && matrix[curX][curY+1] == 0){
                    queue.add(new Pipe(curX, curY+1, 1));
                }

                if(curX+1 < n && curY+1 < n && matrix[curX+1][curY] == 0 && matrix[curX+1][curY+1] == 0 && matrix[curX][curY+1] == 0){
                    queue.add(new Pipe(curX+1, curY+1, 3));
                }
            }else if(curD == 2){
                if(curX == n-1 && curY == n-1){
                    result++;
                    continue;
                }

                if(curX+1 < n && matrix[curX+1][curY] == 0){
                    queue.add(new Pipe(curX+1, curY, 2));
                }

                if(curX+1 < n && curY+1 < n && matrix[curX+1][curY] == 0 && matrix[curX+1][curY+1] == 0 && matrix[curX][curY+1] == 0){
                    queue.add(new Pipe(curX+1, curY+1, 3));
                }
            }else if(curD == 3){
                if(curX == n-1 && curY == n-1){
                    result++;
                    continue;
                }

                if(curY+1 < n && matrix[curX][curY+1] == 0){
                    queue.add(new Pipe(curX, curY+1, 1));
                }

                if(curX+1 < n && matrix[curX+1][curY] == 0){
                    queue.add(new Pipe(curX+1, curY, 2));
                }

                if(curX+1 < n && curY+1 < n && matrix[curX+1][curY] == 0 && matrix[curX+1][curY+1] == 0 && matrix[curX][curY+1] == 0){
                    queue.add(new Pipe(curX+1, curY+1, 3));
                }
            }
        }

        System.out.println(result);

    }


}