import java.util.*;
import java.io.*;

public class Main{

    static int n, m, d;
    static int[][] enemy;
    static int[][] copiedEnemies;
    static boolean[] archor;
    static int defenseCount;
    static int maxEnemyKill = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());


        defenseCount = n-1;
        enemy = new int[n][m];
        archor = new boolean[m];
        for(int i = 0; i<n; i++){
            enemy[i] = new int[m];
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                int enemySpot = Integer.parseInt(st.nextToken());
                enemy[i][j] = enemySpot;

                if(enemySpot == 1 && i < defenseCount){
                    defenseCount = i;
                }
            }
        }

        archorSpot(0, 0);
        System.out.println(maxEnemyKill);
    }


    static void archorSpot(int spot, int count){
        if(count == 3){
            inComing();
            return;
        }

        for(int i = spot; i<m; i++){
            archor[i] = true;
            archorSpot(i+1, count+1);
            archor[i] = false;
        }
    }

    static void inComing(){
        int dCount = defenseCount;
        int[] currArchor = new int[3];
        int a = 0;
        for(int i = 0; i<m; i++){
            if(archor[i]){
                currArchor[a++] = i;
            }
        }
        //System.out.println(Arrays.toString(currArchor));

        copiedEnemies = new int[n][];
        for(int i = 0; i<n; i++){
            copiedEnemies[i] = new int[m];
            System.arraycopy(enemy[i], 0, copiedEnemies[i], 0, m);
        }

        int enemyKilled = 0;
        int[][] nearEnemys = new int[3][3];
        while(dCount < n){
            // 가장 가까운 적 찾기
            for(int i = 0; i<3; i++){
                nearEnemys[i] = getNearEnemy(currArchor[i]);
            }

            // 적 죽음
            for(int[] ne : nearEnemys){
                int x = ne[0];
                int y = ne[1];
                if(x != n+1 && y != m+1 && copiedEnemies[x][y] == 1){
                    //System.out.println("kill+" + x + ", " + y);
                    enemyKilled++;
                    copiedEnemies[x][y] = 0;
                }
            }

            // 적 한칸 전진
            for(int i = n-1; i> 0; i--){
                for(int j = 0; j<m; j++){
                    copiedEnemies[i][j] = copiedEnemies[i-1][j];
                }
            }
            for(int j = 0; j<m; j++){
                copiedEnemies[0][j] = 0;
            }


            /*System.out.println("kill :" + enemyKilled);
            for(int i = 0; i<n; i++){
                System.out.println(Arrays.toString(copiedEnemies[i]));
            }
            System.out.println("---");*/

            dCount++;
        }



        maxEnemyKill = Math.max(maxEnemyKill, enemyKilled);

    }

    static int[] getNearEnemy(int col){
        boolean[][] visited = new boolean[n][];
        for(int i = 0; i<n; i++){
            visited[i] = new boolean[m];
        }

        int[] nearest = new int[]{n+1, m+1, d+1};
        Queue<int[]> spot = new LinkedList<>();
        spot.add(new int[]{n-1, col, 1});
        visited[n-1][col] = true;
        while(!spot.isEmpty()){
            int[] rcd = spot.poll(); // 0 : 행, 1: 열, 2: 거리
            int x = rcd[0];
            int y = rcd[1];
            int dist = rcd[2];

            if(copiedEnemies[x][y] == 1){
                if(dist < nearest[2]){
                    nearest[0] = x;
                    nearest[1] = y;
                    nearest[2] = dist;
                    //System.out.println("1: "+ Arrays.toString(nearest));
                }else if(dist == nearest[2] && y < nearest[1]){
                    nearest[0] = x;
                    nearest[1] = y;
                    nearest[2] = dist;
                    //System.out.println("2: "+ Arrays.toString(nearest));
                }

                /*if(dist > nearest[2]){
                    break;
                }*/
            }

            if(x-1 >= 0 && !visited[x-1][y] && dist + 1 <= d){
                spot.add(new int[]{x-1, y, dist+1});
                visited[x-1][y] = true;
            }
            if(x+1 <= n-1 && !visited[x+1][y] && dist +1 <= d){
                spot.add(new int[]{x+1, y, dist+1});
                visited[x+1][y] = true;
            }
            if(y-1 >= 0 && !visited[x][y-1] && dist +1 <= d){
                spot.add(new int[]{x, y-1, dist+1});
                visited[x][y-1] = true;
            }
            if(y+1 <= m-1 && !visited[x][y+1] && dist +1 <= d){
                spot.add(new int[]{x, y+1, dist+1});
                visited[x][y+1] = true;
            }

        }
        return nearest;
    }






}