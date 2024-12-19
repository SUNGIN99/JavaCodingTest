import java.io.*;
import java.util.*;

public class Main {

/*
4 <= n <=50
0 <= m <= 300
마을의 크기 N, 전사의 수 M
메두사 위치 정보 sr, sc 공원 위치정보 er, ec
m명의 전사들의 좌표 a1r, a1c, a2r, a2c
n줄에 걸쳐 마을 도로 정보
4 4
1 3 3 3
3 1 0 3 1 0 2 2
0 0 0 0
0 0 0 0
0 0 1 1
1 0 0 0

4 2 2
0 2 0
1 1 1
0 0 0
0 0 0
0
턴마다 전사가 이동한 거리의 합, 메두사로 인해 돌이 된 전사의 수, 메두사를 공격한 전사의 수
메두사가 도착하면 0을 출력하고 프로그램 종료.
메두사가 집으로부터 공원까지 이어지는 도로가 존재하지 않는다면 -1

메두사 이동, 시선, 전사 이동, 전사공격 => 한 턴
맨해튼거리로 전사이동

// 뱀 -> 상하좌우 우선순위로 먼저 이동
*/
    static class Warrior{
        int x, y;
        Warrior(int x, int y){
            this.x = x;
            this.y =y ;
        }

    }

    static int n, m;
    static int sr, sc, er, ec;
    static List<Warrior> warriors = new ArrayList<>();
    static int[][] village;
    static int[][] matrix;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        sr = Integer.parseInt(st.nextToken());
        sc = Integer.parseInt(st.nextToken());
        er = Integer.parseInt(st.nextToken());
        ec = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<m; i++){
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            warriors.add(new Warrior(x, y));
        }

        village = new int[n][];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            village[i] = new int[n];
            for(int j = 0; j<n; j++){
                village[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 뱀 이동 최단 경로 먼저 구하기
        bfs();
        /*for(int i = 0; i<n; i++){
            System.out.println(Arrays.toString(visited[i]));
        }*/

        //System.out.println(paths);

        if(paths.isEmpty()){
            System.out.println("-1");
            return;
        }

        matrix = new int[n][];
        for(int i = 0; i<n; i++){
            matrix[i] = new int[n];
        }

        for(Warrior war : warriors){
            matrix[war.x][war.y] = 3; // warrior = 3
        }

        matrix[sr][sc] = -1;
        for(char p : paths.peek().toString().toCharArray()){
            warMove = 0; warDied = 0; warAttack = 0;
            // 1) 뱀 이동
            medusaMove(p);

            // 2) 뱀 공격
            medusaAttack();
        }
    }

    static int warMove, warDied, warAttack;

    static void medusaAttack(){
        // 행마다(x마다) 열이 양쪽으로 + 2씩 되된다. 어느한쪽이 처음 혹은 마지막 열이 될때까지
        int curx = sr, cury = sc;





    }

    static void medusaMove(char p){
        int movedx = sr;
        int movedy = sc;
        if(p == '1'){ // 상
            movedx--;
        }else if(p == '2'){ // 하
            movedx++;
        }else if(p == '3'){ // 좌
            movedy--;
        }else{ // 우
            movedy++;
        }

        matrix[sr][sc] = 0;
        warDied += matrix[movedx][movedy];
        matrix[movedx][movedy] = -1;
    }

    static class Medusa{
        int x, y, f;
        StringBuilder pathSb;

        Medusa(int x, int y, int f, String path){
            this.x = x;
            this.y = y;
            this. f = f;
            this.pathSb = new StringBuilder(path);
        }
    }

    static class Path implements Comparable<Path>{
        String path;

        Path(String path){
            this.path = path;
        }

        public int compareTo(Path o){
            if(o.path.length() > path.length()){
                return -1;
            }else if (o.path.length() < path.length()){
                return 1;
            }else{
                return path.compareTo(o.path);
            }
        }

        public String toString(){
            return this.path;
        }

    }

    static PriorityQueue<Path> paths = new PriorityQueue<>();
    static int[][] visited;
    static void bfs(){
        visited = new int[n][];
        for(int i = 0; i<n; i++){
            visited[i] = new int[n];
        }

        int initX = sr;
        int initY = sc;
        visited[initX][initY] = -1;

        Queue<Medusa> queue = new LinkedList<>();
        queue.add(new Medusa(initX, initY, 0, ""));

        while(!queue.isEmpty()){
            Medusa m = queue.poll();
            int curx = m.x, cury = m.y;
            int curf = m.f;
            StringBuilder curp = m.pathSb;

            if(curx == er && cury == ec){
                paths.add(new Path(curp.toString()));
                continue;
            }

            if(!paths.isEmpty()){
                String shortest = paths.peek().toString();
                if(shortest.length() <= curp.length()){
                    continue;
                }
            }

            // 위 아래 왼 오
            int upx = curx-1;
            int downx = curx+1;
            int lefty = cury-1;
            int righty = cury+1;


            if(isValid(upx, cury) == 0 && (visited[upx][cury] == 0 || visited[upx][cury] > curf + 1)){
                visited[upx][cury] = curf+1;
                queue.add(new Medusa(upx, cury, curf+1, curp.append("1").toString()));
                curp.deleteCharAt(curp.length()-1);
            }
            if(isValid(downx, cury) == 0 && (visited[downx][cury] == 0 || visited[downx][cury] > curf + 1)){
                visited[downx][cury] = curf+1;
                queue.add(new Medusa(downx, cury, curf+1, curp.append("2").toString()));
                curp.deleteCharAt(curp.length()-1);
            }
            if(isValid(curx, lefty) == 0 && (visited[curx][lefty] == 0 || visited[curx][lefty] > curf + 1)){
                visited[curx][lefty] = curf+1;
                queue.add(new Medusa(curx, lefty, curf+1, curp.append("3").toString()));
                curp.deleteCharAt(curp.length()-1);
            }
            if(isValid(curx, righty) == 0 && (visited[curx][righty] == 0 || visited[curx][righty] > curf + 1)){
                visited[curx][righty] = curf+1;
                queue.add(new Medusa(curx, righty, curf+1, curp.append("4").toString()));
                curp.deleteCharAt(curp.length()-1);
            }

        }
    }

    static int isValid(int x, int y){
        if(x < 0 || x >= n || y < 0 || y >= n){
            return -1;
        }

        if(village[x][y] == 1){
            return -1;
        }

        return 0;
    }
}