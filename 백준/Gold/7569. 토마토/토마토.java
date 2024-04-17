import java.util.*;
import java.io.*;

public class Main{

    static int n, m, h;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        int[][][] tomato = new int[h][n][];
        int[][][] tomatoWellDone = new int[h][n][];
        Queue<int[]> queue = new LinkedList<>();
        int doneTomato = 0;
        for(int k = 0; k<h; k++){
            tomato[k] = new int[n][];
            for(int i = 0; i<n; i++){
                tomato[k][i] = new int[m];
                tomatoWellDone[k][i] = new int[m];
                st = new StringTokenizer(br.readLine());
                for(int j = 0 ;j<m; j++){
                    tomato[k][i][j] = Integer.parseInt(st.nextToken());
                    if(tomato[k][i][j] == 1){
                        queue.add(new int[]{k, i,j});
                        tomatoWellDone[k][i][j] = 0;
                        doneTomato++;
                    }
                    else if(tomato[k][i][j] == -1){
                        doneTomato++;
                    }
                }
            }
        }


        while(!queue.isEmpty()){
            int[] currTomato = queue.poll();
            int z = currTomato[0];
            int x = currTomato[1];
            int y = currTomato[2];

            if(x-1 >= 0 && tomato[z][x-1][y] == 0 && (tomatoWellDone[z][x-1][y] == 0 || tomatoWellDone[z][x][y] + 1 < tomatoWellDone[z][x-1][y])){
                tomatoWellDone[z][x-1][y] = tomatoWellDone[z][x][y] + 1;
                queue.add(new int[]{z, x-1, y});
            }

            if(x + 1 < n && tomato[z][x+1][y] == 0 && (tomatoWellDone[z][x+1][y] == 0 || tomatoWellDone[z][x][y] + 1 < tomatoWellDone[z][x+1][y])){
                tomatoWellDone[z][x+1][y] = tomatoWellDone[z][x][y] + 1;
                queue.add(new int[]{z, x+1, y});
            }

            if(y - 1 >= 0 && tomato[z][x][y-1] == 0 && (tomatoWellDone[z][x][y-1] == 0 || tomatoWellDone[z][x][y] + 1 < tomatoWellDone[z][x][y-1])){
                tomatoWellDone[z][x][y-1] = tomatoWellDone[z][x][y] + 1;
                queue.add(new int[]{z, x, y-1});
            }

            if(y + 1 < m && tomato[z][x][y+1] == 0 && (tomatoWellDone[z][x][y+1] == 0 || tomatoWellDone[z][x][y] + 1 < tomatoWellDone[z][x][y+1])){
                tomatoWellDone[z][x][y+1] = tomatoWellDone[z][x][y] + 1;
                queue.add(new int[]{z, x, y+1});
            }

            if(z+1 < h && tomato[z+1][x][y] == 0 && (tomatoWellDone[z+1][x][y] == 0 || tomatoWellDone[z][x][y] + 1 < tomatoWellDone[z+1][x][y])){
                tomatoWellDone[z+1][x][y] = tomatoWellDone[z][x][y] + 1;
                queue.add(new int[]{z+1, x, y});
            }

            if(z-1 >= 0 && tomato[z-1][x][y] == 0 && (tomatoWellDone[z-1][x][y] == 0 || tomatoWellDone[z][x][y] + 1 < tomatoWellDone[z-1][x][y])){
                tomatoWellDone[z-1][x][y] = tomatoWellDone[z][x][y] + 1;
                queue.add(new int[]{z-1, x, y});
            }
        }

        int maxDays = 0;
        boolean noAllTomato = false;
        int checkDoneTomato = 0;
        for(int k = 0; k<h; k++){
            for(int i = 0; i<n; i++){
                //System.out.println(Arrays.toString(tomatoWellDone[i]));
                for(int j = 0; j<m; j++){
                    maxDays = Math.max(maxDays, tomatoWellDone[k][i][j]);
                    if(tomatoWellDone[k][i][j] == 0){
                        checkDoneTomato++;
                    }
                }
            }
        }


        if(checkDoneTomato == doneTomato){
            System.out.println(maxDays);
        }else{
            System.out.println(-1);
        }

    }



}