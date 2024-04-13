import java.util.*;
import java.io.*;
public class Main {


    static boolean[][] visited, nonTarget;
    static int[][] lab;
    static int n, m;
    static int max = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        lab = new int[n][m];
        visited = new boolean[n][m];
        nonTarget = new boolean[n][m];
        for(int i = 0; i<n; i++){
            visited[i] = new boolean[m];
            nonTarget[i] = new boolean[m];
            lab[i] = new int[m];
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                lab[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(lab[i][j] != 1 && lab[i][j]!= 2){
                    checkWall(i, j, 1);
                    nonTarget[i][j] = true;
                }
            }
        }

        System.out.println(max);
    }

    static void checkWall(int row, int col, int wallCount){
        if(visited[row][col]){
            return;
        }

        if(lab[row][col] == 1 || lab[row][col] == 2){
            return;
        }

        lab[row][col] = 1;
        if(wallCount==3){
            int unCompact = getVirusCount();
            max = Math.max(max, unCompact);
            lab[row][col] = 0;
            return;
        }

        visited[row][col] = true;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(((i == row && j > col) || i > row) && lab[i][j] != 1 && lab[i][j] !=2){
                    checkWall(i, j, wallCount+1);
                }
            }
        }

        lab[row][col] = 0;
        visited[row][col] = false;
    }

    static boolean[][] movedVisited;
    static int getVirusCount(){
        int[][] copiedLab = new int[n][];
        movedVisited = new boolean[n][];
        for(int i = 0; i<n; i++){
            movedVisited[i] = new boolean[m];
            copiedLab[i] = new int[m];
            System.arraycopy(lab[i], 0 ,copiedLab[i], 0, m);
        }

        Queue<int[]> virus = new LinkedList<>();
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(copiedLab[i][j] == 2){
                    virus.add(new int[]{i, j});
                    movedVisited[i][j] = true;
                }
            }
        }

        while(!virus.isEmpty()){
            int[] currVirus = virus.poll();
            int x = currVirus[0];
            int y = currVirus[1];

            if(x - 1 >= 0 && !movedVisited[x-1][y]) {
                if(copiedLab[x-1][y] == 0){
                    copiedLab[x-1][y] = 2;
                    movedVisited[x-1][y] = true;
                    virus.add(new int[]{x-1, y});
                }
            }

            if(x + 1 < n && !movedVisited[x+1][y]){
                if(copiedLab[x+1][y] == 0){
                    copiedLab[x+1][y] = 2;
                    movedVisited[x+1][y] = true;
                    virus.add(new int[]{x+1, y});
                }
            }

            if(y - 1 >= 0 && !movedVisited[x][y-1]){
                if(copiedLab[x][y-1] == 0){
                    copiedLab[x][y-1] = 2;
                    movedVisited[x][y-1] = true;
                    virus.add(new int[]{x, y-1});
                }
            }

            if(y + 1 < m && !movedVisited[x][y+1]){
                if(copiedLab[x][y+1] == 0){
                    copiedLab[x][y+1] = 2;
                    movedVisited[x][y+1] = true;
                    virus.add(new int[]{x, y+1});
                }
            }
        }

        int unCompact = 0;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++) {
                if (copiedLab[i][j] == 0) {
                    unCompact++;
                }
            }
        }

        /*if(unCompact > max){
            for(int i = 0; i<n; i++){
                System.out.println(Arrays.toString(copiedLab[i]));
            }
            System.out.println("---");
        }*/

        return unCompact;

    }

}
