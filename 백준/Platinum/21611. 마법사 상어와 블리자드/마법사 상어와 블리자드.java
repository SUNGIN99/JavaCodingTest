import java.io.*;
import java.util.*;

public class Main {

    /*
7 1
0 0 0 0 0 0 0
3 2 1 3 2 3 0
2 1 2 1 2 1 0
2 1 1 0 2 1 1
3 3 2 3 2 1 2
3 3 3 1 3 3 2
2 3 2 2 3 2 3
2 2
     */

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new int[N][];
        for(int i = 0; i<N; i++){
            matrix[i] = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        xy = new int[N * N][2];
        for(int i = 0; i<N*N; i++){
            xy[i] = new int[2];
        }

        initXY();

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());


            //printMatrix();

            // 블리자드 사용
            blizzard(d, s);

            //printMatrix();

            // 구슬 채우기 및 폭파(반복)
            bizMoveNBoom();

            //printMatrix();

            // 구슬 추가
            bizSplit();

            //printMatrix();
        }

        //System.out.println(Arrays.toString(boomResult));
        System.out.println(boomResult[0]  + boomResult[1] * 2 + boomResult[2] * 3);

    }

    static int[][] xy;
    static int[][] matrix;
    static int N, M;

    static int[] boomResult = new int[3];

    public static void bizSplit(){
        //구슬 A의 번호는 그룹에 들어있는 구슬의 개수이고, B는 그룹을 이루고 있는 구슬의 번호이다.
        int[] arr = bizArrange();

        int seq = 1;
        int curNum = arr[1];

        int[] splited = new int[N*N];
        int index = 1;
        for(int i = 2; i < N*N; i++){
            if(arr[i] == 0){
                break;
            }

            if(curNum == arr[i]){
                seq ++;
            }else{
                splited[index++] = seq;
                if(index >= N*N) break;
                splited[index++] = curNum;
                if(index >= N*N) break;

                seq = 1;
                curNum = arr[i];
            }
        }

        if(curNum != 0 && index < N*N ){
            splited[index++] = seq;
        }
        if(curNum != 0 && index < N*N ){
            splited[index] = curNum;
        }


        for(int i = 1; i<N*N; i++){
            int x = xy[i][0];
            int y = xy[i][1];
            matrix[x][y] = splited[i];
        }


        //System.out.println(Arrays.toString(splited));
    }


    public static void bizMoveNBoom(){

        while(true){
            int[] moved = bizMove();
            int boomed = bizBoom(moved);

            if(boomed == 0){
                break;
            }
        }

    }

    public static int bizBoom(int[] moved){

        int seq = 1;
        int start = 1;
        int curNum = moved[1];
        int boom = 0;

        //System.out.println(Arrays.toString(moved));
        for(int i = 2; i < N*N; i++){
            if(moved[i] == 0){
                break;
            }

            if(curNum == moved[i]){
                seq ++;
            }else{
                if(seq >= 4){
                    for(int j = start; j<= i-1; j++){
                        moved[j] = 0;
                    }
                    boomResult[curNum - 1] +=  seq;
                    boom += seq;
                }
                seq = 1;
                start = i;
                curNum = moved[i];
            }
        }

        if(seq >= 4){
            for(int j = start; j<N*N; j++){
                moved[j] = 0;
            }
            boomResult[curNum - 1] +=  seq;
            boom += seq;
        }

        for(int i = 1; i<N*N; i++){
            int x = xy[i][0];
            int y = xy[i][1];
            matrix[x][y] = moved[i];
        }

        return boom;
    }

    public static int[] bizMove(){
        int[] arr = bizArrange();

        int val = 1;
        int[] result = new int[N*N];
        for(int i = 1; i<N*N; i++){
            while(val != N*N && arr[val] == 0){
                val++;
            }

            if(val == N*N){
                break;
            }

            int x = xy[i][0];
            int y = xy[i][1];
            matrix[x][y] = result[i] = arr[val++];
        }

        return result;
    }

    public static int[] bizArrange(){
        int[] arr = new int[N*N];

        for(int i = 1; i< N*N ; i++){
            int x = xy[i][0];
            int y = xy[i][1];
            arr[i] = matrix[x][y];
        }

        return arr;
    }

    public static void blizzard(int dir, int dist){
        // 총 4가지 방향 ↑, ↓, ←, →가 있고, 정수 1, 2, 3, 4로
        // 1 : 위, 2: 아래, 3: 왼, 4: 오른

        int[][] dirs = new int[][] {
                {0, 0},
                {-1, 0}, // 1: 위
                {1, 0},  // 2: 아래
                {0, -1}, // 3: 왼
                {0, 1}   // 4: 오른
        };

        int sharkX = N/2;
        int sharkY = N/2;

        for(int i = 1; i <= dist; i++){
            int nextX = sharkX + dirs[dir][0] * i;
            int nextY = sharkY + dirs[dir][1] * i;

            if(isValid(nextX, nextY)){
                matrix[nextX][nextY] = 0;
            }
        }
    }

    static boolean isValid(int x, int y){
        if(x< 0 || x >= N || y < 0 || y >= N){
            return false;
        }

        return true;
    }

    public static void initXY(){

        int[][] dirs = {{0,-1}, {1,0}, {0,1}, {-1,0}};
        int times = 1;
        int turn = 0;
        int add = 0;
        int curx = xy[0][0] = N/2, cury = xy[0][1] = N/2;

//        int[][] matrix = new int[N][];
//        for(int i = 0; i<N; i++){
//            matrix[i] = new int[N];
//        }

        int val = 1;
        boolean arrive = false;
        while(!arrive){
            int[] dir = dirs[turn];
            for(int i = 0; i<times; i++){
                curx = curx + dir[0];
                cury = cury + dir[1];
                //matrix[curx][cury] = val++;
                xy[val][0] = curx;
                xy[val][1] = cury;
                val++;
                if(curx == 0 && cury == 0){
                    arrive = true;
                    break;
                }
            }
            turn = (turn + 1) % 4;
            add = (add + 1) % 2;
            if(add == 0){
                times ++;
            }
        }
    }

    public static void printMatrix(){
        for(int i =0 ; i<N; i++){
            System.out.println(Arrays.toString(matrix[i]));
        }

        System.out.println("-------- \n");
    }
}