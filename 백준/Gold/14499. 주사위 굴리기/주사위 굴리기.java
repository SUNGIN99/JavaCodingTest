import java.util.*;
import java.io.*;
public class Main {

    static class Dice{
        int top, bottom, left, right, front, back;
        int x, y;
        Dice(int x, int y, int t, int f, int r, int l, int b, int bt){
            this.x = x;
            this.y = y;
            this.top = t;
            this.front = f;
            this.right = r;
            this.left = l;
            this.back = b;
            this.bottom = bt;
        }
    }

    static int n, m;
    static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for(int i = 0; i<n; i++){
            map[i] = new int[m];
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
            //System.out.println(Arrays.toString(map[i]));
        }

        int[] dir = new int[k];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<k; i++){
            dir[i] = Integer.parseInt(st.nextToken());
        }

        Dice dice = new Dice(x, y, 0, 0, 0, 0, 0, 0);

        for(Integer d : dir){
            int top = moveDice(d, dice);
            //System.out.println(dice.bottom);
            if(top != -1){
                System.out.println(top);
            }
        }
        /*for(int i = 0; i<n; i++){
            System.out.println(Arrays.toString(map[i]));
        }

        System.out.println(dice.x + ", " + dice.y);
        System.out.println(dice.top + ", " + dice.right + ", " + dice.front + ", " + dice.left + ", " + dice.back + ", " + dice.bottom);*/
        // 1. 주사위가 처음 놓인 곳은 무조건 0
        // 주사위를 굴렸을 때
        // 지도에 적힌 값이 0 이라면 주사위 바닥에 있는 수를 복사
        // 0이 아니라면, 지도에 적힌 수를 주사위 바닥에 할당, 지도는 0이

        // 이동할 때 마다, 맨위에 주사위 숫자를 알려주기
    }

    static int moveDice(int dir, Dice dice){
        int x = dice.x;
        int y = dice.y;

        int mapNum;

        if(dir == 1 && y < m-1){ // 동
            mapNum = map[x][y+1];
            dice.y++;
            moveEast(mapNum, dice);
            return dice.top;
        }else if(dir == 2 && y > 0){ // 서
            mapNum = map[x][y-1];
            dice.y--;
            moveWest(mapNum, dice);
            return dice.top;
        }else if(dir == 3 && x > 0){ // 북
            mapNum = map[x-1][y];
            dice.x--;
            moveNorth(mapNum, dice);
            return dice.top;
        }else if (dir == 4 && x < n-1 ){ // 남
            mapNum = map[x+1][y];
            dice.x++;
            moveSouth(mapNum, dice);
            return dice.top;
        }

        return -1;
    }

    static void moveEast(int mapNum, Dice dice){
        int temp = dice.bottom;
        dice.bottom = dice.right;
        dice.right = dice.top;
        dice.top = dice.left;
        dice.left = temp;

        int bottom = dice.bottom;

        if(mapNum == 0){ // 바닥에 쓰인 수가 0이면
            map[dice.x][dice.y] = bottom;
        }else{ // 바닥에 쓰인 수가 0이 아니면
            dice.bottom = map[dice.x][dice.y];
            map[dice.x][dice.y] = 0;
        }
    }

    static void moveWest(int mapNum, Dice dice){
        int temp = dice.bottom;
        dice.bottom = dice.left;
        dice.left = dice.top;
        dice.top = dice.right;
        dice.right = temp;

        int bottom = dice.bottom;

        if(mapNum == 0){ // 바닥에 쓰인 수가 0이면
            map[dice.x][dice.y] = bottom;
        }else{ // 바닥에 쓰인 수가 0이 아니면
            dice.bottom = map[dice.x][dice.y];
            map[dice.x][dice.y] = 0;
        }
    }

    static void moveNorth(int mapNum, Dice dice){
        int temp = dice.bottom;
        dice.bottom = dice.front;
        dice.front = dice.top;
        dice.top = dice.back;
        dice.back = temp;

        int bottom = dice.bottom;

        if(mapNum == 0){ // 바닥에 쓰인 수가 0이면
            map[dice.x][dice.y] = bottom;
        }else{ // 바닥에 쓰인 수가 0이 아니면
            dice.bottom = map[dice.x][dice.y];
            map[dice.x][dice.y] = 0;
        }
    }

    static void moveSouth(int mapNum, Dice dice){
        int temp = dice.bottom;
        dice.bottom = dice.back;
        dice.back = dice.top;
        dice.top = dice.front;
        dice.front = temp;

        int bottom = dice.bottom;

        if(mapNum == 0){ // 바닥에 쓰인 수가 0이면
            map[dice.x][dice.y] = bottom;
        }else{ // 바닥에 쓰인 수가 0이 아니면
            dice.bottom = map[dice.x][dice.y];
            map[dice.x][dice.y] = 0;
        }
    }



}
