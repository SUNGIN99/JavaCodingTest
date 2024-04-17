import java.util.*;
import java.io.*;

public class Main{

    static int n, m;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        int[][] tomato = new int[n][];
        int[][] tomatoWellDone = new int[n][];
        Queue<int[]> queue = new LinkedList<>();
        int doneTomato = 0;
        for(int i = 0; i<n; i++){
            tomato[i] = new int[m];
            tomatoWellDone[i] = new int[m];
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ;j<m; j++){
                tomato[i][j] = Integer.parseInt(st.nextToken());
                if(tomato[i][j] == 1){
                    queue.add(new int[]{i,j});
                    tomatoWellDone[i][j] = 0;
                    doneTomato++;
                }
                else if(tomato[i][j] == -1){
                    doneTomato++;
                }
            }
        }

        while(!queue.isEmpty()){
            int[] currTomato = queue.poll();
            int x = currTomato[0];
            int y = currTomato[1];

            if(x-1 >= 0 && tomato[x-1][y] == 0 && (tomatoWellDone[x-1][y] == 0 || tomatoWellDone[x][y] + 1 < tomatoWellDone[x-1][y])){
                tomatoWellDone[x-1][y] = tomatoWellDone[x][y] + 1;
                queue.add(new int[]{x-1, y});
            }

            if(x + 1 < n && tomato[x+1][y] == 0 && (tomatoWellDone[x+1][y] == 0 || tomatoWellDone[x][y] + 1 < tomatoWellDone[x+1][y])){
                tomatoWellDone[x+1][y] = tomatoWellDone[x][y] + 1;
                queue.add(new int[]{x+1, y});
            }

            if(y - 1 >= 0 && tomato[x][y-1] == 0 && (tomatoWellDone[x][y-1] == 0 || tomatoWellDone[x][y] + 1 < tomatoWellDone[x][y-1])){
                tomatoWellDone[x][y-1] = tomatoWellDone[x][y] + 1;
                queue.add(new int[]{x, y-1});
            }

            if(y + 1 < m && tomato[x][y+1] == 0 && (tomatoWellDone[x][y+1] == 0 || tomatoWellDone[x][y] + 1 < tomatoWellDone[x][y+1])){
                tomatoWellDone[x][y+1] = tomatoWellDone[x][y] + 1;
                queue.add(new int[]{x, y+1});
            }
        }

        int maxDays = 0;
        boolean noAllTomato = false;
        int checkDoneTomato = 0;
        for(int i = 0; i<n; i++){
            //System.out.println(Arrays.toString(tomatoWellDone[i]));
            for(int j = 0; j<m; j++){
                maxDays = Math.max(maxDays, tomatoWellDone[i][j]);
                if(tomatoWellDone[i][j] == 0){
                    checkDoneTomato++;
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