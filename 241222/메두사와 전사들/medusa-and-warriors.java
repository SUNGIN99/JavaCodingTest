

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
            matrix[war.x][war.y] = 1; // warrior = 3
        }
        //System.out.println(paths);
        matrix[sr][sc] = -1;
        int lastCheck = 1;
        for(char p : paths.peek().toString().toCharArray()){
            /*for(int i = 0; i<n; i++){
                System.out.println(Arrays.toString(matrix[i]));
            }
            System.out.println();*/
            warMove = 0; warStone = 0; warAttack = 0;
            // 1) 뱀 이동
            medusaMove(p);

            if(sr == er && sc == ec){
                System.out.println("0");
                return;
            }

            // 2) 뱀 공격
            HashSet<String> stopped = medusaAttack();
            /*for(int i = 0; i<n; i++){
                for(int j = 0; j<n; j++){
                    System.out.print(stonedArea[i][j]? "돌 ": "O  ");
                }
                System.out.println();
            }*/
            // 3) 전사 이동 & 전사공격
            //System.out.println(stopped);
            warriorMove(stopped);
            //System.out.println();

            System.out.println(warMove + " " + warStone + " " + warAttack);
        }

        System.out.println("0");
    }

    static int warMove, warStone, warAttack;

    static void warriorMove(HashSet<String> stopped){
        int medusaX = sr, medusaY = sc;

        int[][] movedMatrix = new int[n][];
        for(int i = 0; i<n; i++){
            movedMatrix[i] = new int[n];
        }

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(matrix[i][j] >= 1){
                    int minPath = Integer.MAX_VALUE;
                    int manHuton = 0;
                    int targetx=0, targety=0;
                    int up1 = i-1, up2 = i-2, down1 = i+1, down2 = i+2;
                    int left1 = j-1, left2 = j-2, right1 = j + 1, right2 =j+2;


                    if(isValid2(i,j) && !stonedArea[i][j]){
                        manHuton = Math.abs(sr-i) + Math.abs(sc-j);
                        if(manHuton < minPath){
                            targetx = i;
                            targety = j;
                            minPath = manHuton;
                        }
                    }

                    // up1
                    if(isValid2(up1, j) && !stonedArea[up1][j]){
                        manHuton = Math.abs(sr - up1) + Math.abs(sc - j);
                        if(manHuton < minPath){
                            targetx = up1;
                            targety = j;
                            minPath = manHuton;
                        }
                    }

                    // up1, left1
                    if(isValid2(up1, left1) && !stonedArea[up1][j] && !stonedArea[up1][left1]){
                        manHuton = Math.abs(sr - up1) + Math.abs(sc - left1);
                        if(manHuton < minPath){
                            targetx = up1;
                            targety = left1;
                            minPath = manHuton;
                        }
                    }

                    // up1, right1
                    if(isValid2(up1, right1) && !stonedArea[up1][j] && !stonedArea[up1][right1]){
                        manHuton = Math.abs(sr - up1) + Math.abs(sc - right1);
                        if(manHuton < minPath){
                            targetx = up1;
                            targety = right1;
                            minPath = manHuton;
                        }
                    }

                    // up2
                    if(isValid2(up2, j) && !stonedArea[up1][j] && !stonedArea[up2][j]){
                        manHuton = Math.abs(sr - up2) + Math.abs(sc - j);
                        if(manHuton < minPath){
                            targetx = up2;
                            targety = j;
                            minPath = manHuton;
                        }
                    }

                    // down1
                    if(isValid2(down1, j) && !stonedArea[down1][j]){
                        manHuton = Math.abs(sr - down1) + Math.abs(sc - j);
                        if(manHuton < minPath){
                            targetx = down1;
                            targety = j;
                            minPath = manHuton;
                        }
                    }

                    // down1, left1
                    if(isValid2(down1, left1) && !stonedArea[down1][j] && !stonedArea[down1][left1]){
                        manHuton = Math.abs(sr - down1) + Math.abs(sc - left1);
                        if(manHuton < minPath){
                            targetx = down1;
                            targety = left1;
                            minPath = manHuton;
                        }
                    }

                    // down1, right1
                    if(isValid2(down1, right1) && !stonedArea[down1][j] && !stonedArea[down1][right1]){
                        manHuton = Math.abs(sr - down1) + Math.abs(sc - right1);
                        if(manHuton < minPath){
                            targetx = down1;
                            targety = right1;
                            minPath = manHuton;
                        }
                    }

                    // down2
                    if(isValid2(down2, j) && !stonedArea[down1][j] && !stonedArea[down2][j]){
                        manHuton = Math.abs(sr - down2) + Math.abs(sc - j);
                        if(manHuton < minPath){
                            targetx = down2;
                            targety = j;
                            minPath = manHuton;
                        }
                    }

                    // left1
                    if(isValid2(i, left1) && !stonedArea[i][left1]){
                        manHuton = Math.abs(sr - i) + Math.abs(sc - left1);
                        if(manHuton < minPath){
                            targetx = i;
                            targety = left1;
                            minPath = manHuton;
                        }
                    }

                    // left2
                    if(isValid2(i, left2) && !stonedArea[i][left1] && !stonedArea[i][left2]){
                        manHuton = Math.abs(sr - i) + Math.abs(sc - left2);
                        if(manHuton < minPath){
                            targetx = i;
                            targety = left2;
                            minPath = manHuton;
                        }
                    }

                    // left1, up1
                    if(isValid2(up1, left1) && !stonedArea[i][left1] && !stonedArea[up1][left1]){
                        manHuton = Math.abs(sr - up1) + Math.abs(sc - left1);
                        if(manHuton < minPath){
                            targetx = up1;
                            targety = left1;
                            minPath = manHuton;
                        }
                    }

                    // left1, down1
                    if(isValid2(down1, left1) && !stonedArea[i][left1] && !stonedArea[down1][left1]){
                        manHuton = Math.abs(sr - down1) + Math.abs(sc - left1);
                        if(manHuton < minPath){
                            targetx = down1;
                            targety = left1;
                            minPath = manHuton;
                        }
                    }

                    // right1
                    if(isValid2(i, right1) && !stonedArea[i][right1]){
                        manHuton = Math.abs(sr - i) + Math.abs(sc - right1);
                        if(manHuton < minPath){
                            targetx = i;
                            targety = right1;
                            minPath = manHuton;
                        }
                    }

                    // right2
                    if(isValid2(i, right2) && !stonedArea[i][right1] && !stonedArea[i][right2]){
                        manHuton = Math.abs(sr - i) + Math.abs(sc - right2);
                        if(manHuton < minPath){
                            targetx = i;
                            targety = right2;
                            minPath = manHuton;
                        }
                    }

                    // right1, up1
                    if(isValid2(up1, right1) && !stonedArea[i][right1] && !stonedArea[up1][right1]){
                        manHuton = Math.abs(sr - up1) + Math.abs(sc - right1);
                        if(manHuton < minPath){
                            targetx = up1;
                            targety = right1;
                            minPath = manHuton;
                        }
                    }

                    // right1, down1
                    if(isValid2(down1, right1) && !stonedArea[i][right1] && !stonedArea[down1][right1]){
                        manHuton = Math.abs(sr - down1) + Math.abs(sc - right1);
                        if(manHuton < minPath){
                            targetx = down1;
                            targety = right1;
                            minPath = manHuton;
                        }
                    }

                    if(stopped.contains(i+","+j)){
                        movedMatrix[i][j] += matrix[i][j];
                    }else{

                        //System.out.println("(warrior)" + "("+i+"," + j+") -> "+targetx + ", " + targety + " : " + manHuton);
                        if(matrix[targetx][targety] == -1){
                            warAttack += matrix[i][j];
                        }else{
                            movedMatrix[targetx][targety] += matrix[i][j];
                            //System.out.println("warriorMove : " + targetx + ", " + targety);
                        }
                        warMove += matrix[i][j] * (Math.abs(targetx - i) + Math.abs(targety - j));
                    }
                }
            }
        }

        movedMatrix[sr][sc] = -1;
        matrix = movedMatrix;
    }

    static boolean isValid2(int xx, int yy){
        if(xx < 0 || xx >=n || yy <0 || yy >= n){
            return false;
        }
        return true;
    }

    static boolean[][] stonnedUp, stonnedDown, stonnedLeft, stonnedRight;
    static HashSet<String> medusaAttack(){
        int curx = sr, cury = sc;
        int attackCount = 0;

        List<int[]>[] warr = new List[4];

        warr[0] = medusaAttackUp();
        warr[1] = medusaAttackDown();
        warr[2] = medusaAttackLeft();
        warr[3] = medusaAttackRight();

        List<int[]> makeAtt = new ArrayList<>();
        int stoned = -1;
        for(int i = 0; i<4; i++){
            if (makeAtt.size() < warr[i].size()) {
                makeAtt = warr[i];
                stoned = i;
            }
        }

        if(stoned == 0){
            stonedArea = stonnedUp;
        }else if(stoned == 1){
            stonedArea = stonnedDown;
        }else if(stoned == 2){
            stonedArea = stonnedLeft;
        }else if(stoned == 3){
            stonedArea = stonnedRight;
        }else{
            stonedArea = stonnedUp;
        }


        HashSet<String> set = new HashSet<>();
        for(int[] at : makeAtt){
            set.add(at[0]+","+at[1]);
            warStone ++;
        }

        return set;
    }

    static boolean[][] stonedArea;
    static List<int[]> medusaAttackRight(){
        int curx = sr, cury = sc;
        int attackCount = 0;
        int index;
        int attX = -1, attY= -1;

        stonnedRight = new boolean[n][];
        for(int i = 0; i<n; i++){
            stonnedRight[i] = new boolean[n];
        }

        List<int[]> war = new ArrayList<>();
        // 우
        for(int i = sc+1; i<n; i++){
            stonnedRight[curx][i] = true;
            if(matrix[curx][i] >= 1){
                war.add(new int[]{curx, i});
                break;
            }
        }

        index = sc+1;
        for(int i = sr + 1; i<n; i++){
            for(int j = index; j<n; j++){
                if(attX != -1 && attY != -1 && (Math.abs(attX - i) == Math.abs(attY - j)) && attX < i && attY < j){
                    break;
                }
                stonnedRight[i][j] = true;
                if(matrix[i][j] >= 1){
                    war.add(new int[]{i, j});
                    attX = i;
                    attY = j;
                    break;
                }
            }
            index++;
        }

        index = sc+1;
        attX = -1; attY = -1;
        for(int i = sr -1; i>=0; i--){
            for(int j = index; j<n; j++){
                if(attX != -1 && attY != -1 && (Math.abs(attX - i) == Math.abs(attY - j)) && attX > i && attY < j){
                    break;
                }
                stonnedRight[i][j] = true;
                if(matrix[i][j] >= 1){
                    war.add(new int[]{i, j});
                    attX = i;
                    attY = j;
                    break;
                }
            }
            index++;
        }

        return war;
    }

    static List<int[]> medusaAttackLeft(){
        int curx = sr, cury = sc;
        int attackCount = 0;
        int index;
        int attX = -1, attY= -1;

        stonnedLeft = new boolean[n][];
        for(int i = 0; i<n; i++){
            stonnedLeft[i] = new boolean[n];
        }

        List<int[]> war = new ArrayList<>();
        // 왼
        for(int i = sc-1; i>=0; i--){
            stonnedLeft[curx][i] = true;
            if(matrix[curx][i] >= 1){
                war.add(new int[]{curx, i});
                break;
            }
        }

        index = sc-1;
        for(int i = sr + 1; i<n; i++){
            for(int j = index; j>=0; j--){
                if(attX != -1 && attY != -1 && (Math.abs(attX - i) == Math.abs(attY - j)) && attX < i && attY > j){
                    break;
                }
                stonnedLeft[i][j] = true;
                if(matrix[i][j] >= 1){
                    war.add(new int[]{i, j});
                    attX = i;
                    attY = j;
                    break;
                }
            }
            index--;
        }

        index = sc-1;
        attX = -1; attY = -1;
        for(int i = sr -1; i>=0; i--){
            for(int j = index; j>=0; j--){
                if(attX != -1 && attY != -1 && (Math.abs(attX - i) == Math.abs(attY - j)) && attX > i && attY > j){
                    break;
                }
                stonnedLeft[i][j] = true;
                if(matrix[i][j] >= 1){
                    war.add(new int[]{i, j});
                    break;
                }
            }
            index--;
        }

        return war;
    }

    static List<int[]> medusaAttackDown(){
        int curx = sr, cury = sc;
        int attackCount = 0;
        int index;
        int attX = -1, attY= -1;

        stonnedDown = new boolean[n][];
        for(int i = 0; i<n; i++){
            stonnedDown[i] = new boolean[n];
        }
        List<int[]> war = new ArrayList<>();

        // 아래
        for(int i = sr+1; i<n; i++){
            stonnedDown[i][cury] = true;
            if(matrix[i][cury] >= 1){
                war.add(new int[]{i, cury});
                break;
            }
        }
        index = sr + 1;
        for(int j = sc + 1; j<n; j++){
            for(int i = index; i<n; i++){
                if(attX != -1 && attY != -1 && (Math.abs(attX - i) == Math.abs(attY - j)) && attX < i && attY < j){
                    break;
                }
                stonnedDown[i][j] = true;
                if(matrix[i][j] >= 1){
                    war.add(new int[]{i, j});
                    attX = i;
                    attY = j;
                    break;
                }
            }
            index++;
        }
        index = sr + 1;
        attX = -1; attY = -1;
        for(int j = sc - 1; j>=0; j--){
            for(int i = index; i<n; i++){
                if(attX != -1 && attY != -1 && (Math.abs(attX - i) == Math.abs(attY - j)) && attX < i && attY > j){
                    break;
                }
                stonnedDown[i][j] = true;
                if(matrix[i][j] >= 1){
                    war.add(new int[]{i, j});
                    attX = i;
                    attY = j;
                    break;
                }
            }
            index++;
        }

        return war;
    }

    static List<int[]> medusaAttackUp(){
        int curx = sr, cury = sc;
        int attackCount = 0;

        stonnedUp = new boolean[n][];
        for(int i = 0; i<n; i++){
            stonnedUp[i] = new boolean[n];
        }

        int attX = -1, attY= -1;

        int index;
        List<int[]> war = new ArrayList<>();

        // 위
        for(int i = sr-1; i>=0; i--){
            stonnedUp[i][cury] = true;
            if(matrix[i][cury] >= 1){
                war.add(new int[]{i, cury});
                break;
            }
        }
        index = sr - 1;
        for(int j = sc + 1; j<n; j++){
            for(int i = index; i>=0; i--){
                if(attX != -1 && attY != -1 && (Math.abs(attX - i) == Math.abs(attY - j)) && attX > i && attY < j){
                    break;
                }
                stonnedUp[i][j] = true;
                if(matrix[i][j] >= 1){
                    war.add(new int[]{i, j});
                    attX = i;
                    attY = j;
                    break;
                }
            }
            index--;
        }
        index = sr - 1;
        attX = -1; attY = -1;
        for(int j = sc - 1; j>=0; j--){
            for(int i = index; i>=0; i--){
                if(attX != -1 && attY != -1 && (Math.abs(attX - i) == Math.abs(attY - j)) && attX > i && attY > j){
                    break;
                }
                stonnedUp[i][j] = true;
                if(matrix[i][j] >= 1){
                    war.add(new int[]{i, j});
                    attX = i;
                    attY = j;
                    break;
                }
            }
            index--;
        }
        return war;
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
        matrix[movedx][movedy] = -1;

        sr=movedx;
        sc=movedy;
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