import java.util.*;
import java.io.*;
public class Main {

    static class XY{
        int x;
        int y;
        XY(int x, int y){
            this.x = x;
            this.y = y;
        }

        public String toString(){
            return "[" + x + ", " + y + "]";
        }
    }
    static class DragonCurve{
        ArrayList<XY> curves;
        int direction;
        int generation;
        DragonCurve(int x, int y, int d, int g){
            this.curves = new ArrayList<>();
            curves.add(new XY(x, y));
            this.direction = d;
            this.generation = g;
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        DragonCurve[] dragonC = new DragonCurve[n];
        int x, y, d, g;
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            dragonC[i] = new DragonCurve(x, y, d, g);

            if(d == 0){ // x 가 증가하는 방향 ->
                dragonC[i].curves.add(new XY(x+1, y));
            }else if(d == 1){ // y가 증가하는 방향 ^
                dragonC[i].curves.add(new XY(x, y-1));
            }else if(d == 2){ // x 가 감소하는 방향 <-
                dragonC[i].curves.add(new XY(x-1, y));
            }else{ // d == 3 y가 감소하는 방향 v
                dragonC[i].curves.add(new XY(x, y+1));
            }
        }

        boolean[][] board = new boolean[101][101];
        for(int i = 0; i<=100; i++){
            board[i] = new boolean[101];
        }

        for(DragonCurve dc : dragonC){
            order(dc);
            //System.out.println(dc.curves);

            for(XY xy : dc.curves){
                board[xy.y][xy.x] = true;
            }
        }

        int count = 0;
        for(int i = 0; i<100; i++){
            for(int j =0; j<100; j++){
                //System.out.print((board[i][j] ? 1: 0) + " ");
                if(board[i][j] && board[i][j+1] && board[i+1][j] && board[i+1][j+1]){
                    count++;
                }
            }
            //System.out.println();
        }
        System.out.println(count);


    }

    static void order(DragonCurve dc){
        ArrayList<XY> curves = dc.curves;
        int curX, curY, nextX, nextY;
        for(int i = 0; i<dc.generation; i++){
            int curvSize = curves.size() - 1;
            nextX = curX = curves.get(curvSize).x;
            nextY = curY = curves.get(curvSize).y;
            int nextDir = dc.direction;
            for(int j = curvSize - 1; j >=0 ; j--){
                int prevX = curves.get(j).x;
                int prevY = curves.get(j).y;
                nextDir = getDir(curX, curY, prevX, prevY);
                // 드래곤 커브 세대 진화
                if(nextDir == 0){
                    nextX++;
                }else if(nextDir == 1){
                    nextY--;
                }else if(nextDir == 2){
                    nextX--;
                }else{
                    nextY++;
                }
                curves.add(new XY(nextX, nextY));

                curX = prevX;
                curY = prevY;
            }
            dc.direction = nextDir;
        }
    }

    static int getDir(int curX, int curY, int prevX, int prevY){
        int difX = curX - prevX;
        int difY = curY - prevY;

        if(difX == 0 && curY < prevY){ // 이전 좌표는 아래 방향
            // 왼쯕으로 갈것
            return 2;
        }else if(difX == 0 && curY > prevY){ // 이전 좌표는 위 방향
            // 오른쪽으로 갈것
            return 0;
        }else if(curX > prevX && difY == 0){ // 이전 좌표는 왼쪽 방향
            // 위쪽으로 갈것
            return 1;
        }else if(curX < prevY && difY == 0){ // 이전 좌표는 오른쪽 방향
            // 아래로 갈것
            return 3;
        }
        return -1;
    }


}
