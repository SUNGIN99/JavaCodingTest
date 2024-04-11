import java.util.*;
import java.io.*;

public class Main{

    static int[][] lab;
    static int n, m;
    static ArrayList<int[]> virusXY;
    static int minTime = Integer.MAX_VALUE;
    static boolean[][] virusLocated;
    static boolean untouchable = true;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        lab = new int[n][n];
        virusLocated = new boolean[n][n];
        virusXY = new ArrayList<>();
        int state;
        for(int i =0; i<n; i++){
            lab[i] = new int[n];
            virusLocated[i] = new boolean[n];

            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<n; j++){
                lab[i][j] = state = Integer.parseInt(st.nextToken());
                if(state == 2){
                    virusXY.add(new int[]{i, j, 0});
                    lab[i][j] = 0;
                    virusLocated[i][j] = true;
                }else if(state == 1){
                    lab[i][j] = -1;
                }

            }
        }

        //activateVirus(0, 0, new ArrayList<>());
        for(int i = 0; i<virusXY.size(); i++){
            // 여기서 실수 
            activateVirus(i, 1, new ArrayList<>());
        }
        

        System.out.println(untouchable ? -1 : minTime);
    }

    static void activateVirus(int index, int count, ArrayList<int[]> prevVirus){
        ArrayList<int[]> copiedVirus = new ArrayList<>();
        for(int i = 0; i< prevVirus.size(); i++){
            copiedVirus.add(prevVirus.get(i).clone());
        }

        copiedVirus.add(virusXY.get(index));

        if(count == m){
            bfs(copiedVirus);
            return;
        }

        // for(int i = index; i<size; i++){
        //  activateVirus(i+1, count+1, )
        //}
        // 이런 식으로해서 전체 탐색이 불가능했다. 재귀함수 맨 처음 호출부에서 처리해줘서 0부터시작하도록 바꿈
        for(int i = index+1; i<virusXY.size(); i++){
            activateVirus(i, count + 1, copiedVirus);

        }
    }

    static void bfs(ArrayList<int[]> activatedVirus){
        boolean[][] visited = new boolean[n][];
        int[][] copiedLab = new int[n][];
        for(int i = 0; i<n; i++){
            copiedLab[i] = new int[n];
            visited[i] = new boolean[n];
            System.arraycopy(lab[i], 0, copiedLab[i], 0, n);
        }

        Queue<int[]> virusCompacted = new LinkedList<>();
        for(int i = 0; i<m; i++){
            int[] av = activatedVirus.get(i);
            //System.out.println(activatedVirus.get(i)[0] + ", " + activatedVirus.get(i)[1]);
            virusCompacted.add(av);
            visited[av[0]][av[1]] = true;

        }
        /*for(int i = 0; i<n; i++){
            System.out.println(Arrays.toString(copiedLab[i]));
        }
        System.out.println("===");*/


        int totalTime = 0;
        while(!virusCompacted.isEmpty()){
            int[] virus = virusCompacted.poll();
            int x = virus[0];
            int y = virus[1];
            int time = virus[2];

            //visited[x][y] = true;
            // 활성화된 바이러스의 주변이
            // 연구실 범위 밖이 아니고, 방문하지 않은 방이며, 벽이 아닐 경우
            // 또 방문한 시간이 더 짧은 경우
            if(x - 1 >= 0 &&
                    (!visited[x-1][y] || (copiedLab[x-1][y] != 0 && copiedLab[x-1][y] > time + 1))
                    && copiedLab[x-1][y] != -1){
                virusCompacted.add(new int[]{x-1 , y, time+1});
                copiedLab[x-1][y] = time + 1;
                visited[x-1][y] = true;
            }
            if(x + 1 <= n - 1 &&
                    (!visited[x+1][y] || (copiedLab[x+1][y] != 0 && copiedLab[x+1][y] > time + 1))
                    && copiedLab[x + 1][y] != -1){
                virusCompacted.add(new int[]{x+1 , y, time+1});
                copiedLab[x+1][y] = time + 1;
                visited[x+1][y] = true;
            }
            if(y - 1 >= 0 &&
                    (!visited[x][y-1] || (copiedLab[x][y-1] != 0 && copiedLab[x][y-1] > time + 1))
                    && copiedLab[x][y-1] != -1){
                virusCompacted.add(new int[]{x, y-1, time+1});
                copiedLab[x][y-1] = time + 1;
                visited[x][y-1] = true;
            }
            if(y + 1 <= n - 1 &&
                    (!visited[x][y+1] || (copiedLab[x][y+1] != 0 && copiedLab[x][y+1] > time + 1))
                    && copiedLab[x][y+1] != -1){
                virusCompacted.add(new int[]{x, y+1, time+1});
                copiedLab[x][y+1] = time + 1;
                visited[x][y+1] = true;
            }
        }

        int mCount = 0;
        int unCompacted = 0;
        for(int i = 0; i<n; i++){
            //System.out.println(Arrays.toString(copiedLab[i]));
            for(int j = 0; j<n; j++){
                if(!virusLocated[i][j]){
                    totalTime = Math.max(totalTime, copiedLab[i][j]);
                }
                if(copiedLab[i][j] == 0){
                    if(virusLocated[i][j]){
                        mCount++;
                    }else{
                        unCompacted++;
                    }
                }
            }
        }

        //https://www.acmicpc.net/board/view/119236
        // 반례 (벽에 같혀있어서 감염이 되진 않지만, 비활성 상태로 그대로 바이러스가 존재하는 경우)
        if(unCompacted == 0){
            untouchable = false;
            minTime = Math.min(minTime, totalTime);
        }
/*
        System.out.println("---");
        System.out.println("#############");*/


    }
}