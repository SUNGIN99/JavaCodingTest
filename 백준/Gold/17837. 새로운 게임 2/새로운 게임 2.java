import java.util.*;
import java.io.*;

public class Main{
    static class Horse{
        int row, col;
        int num;
        int direction;
        Horse(int num, int row, int col, int d){
            this.num = num;
            this.row =row;
            this.col = col;
            this.direction = d;
        }
    }

    static class Board{
        int row, col;
        int type; //0: 흰, 1: 빨, 2: 파
        Horse[] curHorses;
        Stack<Horse> stackHorses;
        int curBoardHigh = 0;
        Board(int r, int c, int t){
            this.row = r;
            this.col = c;
            this.type = t;
            curHorses = new Horse[10];
            stackHorses = new Stack<>();
        }

        void horseAppend(Horse h){
            curHorses[curBoardHigh++] = h;
        }

        int moveToWhite(int index, Board next){
            int a = curBoardHigh;
            for(int i = index; i< curBoardHigh; i++){
                Horse h = curHorses[i];
                h.row = next.row;
                h.col = next.col;

                next.horseAppend(h);
                curHorses[i] = null;
                a--;
            }
            curBoardHigh = a;
            return next.curBoardHigh;
        }

        int moveToRed(int index, Board next){
            int a= curBoardHigh;
            for(int i = index; i<curBoardHigh; i++){
                Horse h = curHorses[i];
                h.row = next.row;
                h.col = next.col;

                next.stackHorses.push(h);
                curHorses[i] = null;
                a--;
            }
            curBoardHigh = a;

            while(!next.stackHorses.isEmpty()){
                next.horseAppend(next.stackHorses.pop());
                if(next.curBoardHigh >= 4){
                    break;
                }
            }

            return next.curBoardHigh;
        }
    }
    static int n, k;
    static Board[][] boards;
    static Horse[] horseInfo;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        boards = new Board[n][n];
        horseInfo = new Horse[k];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            boards[i] = new Board[n];
            for(int j = 0; j<n; j++){
                int color = Integer.parseInt(st.nextToken());
                boards[i][j] = new Board(i, j, color);
            }
        }

        for(int i = 0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            //→, ←, ↑, ↓
            Horse horse =  new Horse(i, r-1,c-1,d);
            boards[r-1][c-1].horseAppend(horse);
            horseInfo[i] = horse;
        }

        int turn = 1;
        while(boardUnder4()){
            for(Horse h : horseInfo){
                int hx = h.row;
                int hy = h.col;
                int hd = h.direction;
                // 1: →, 2: ←, 3: ↑, 4: ↓
                // 1) 현재 칸에서 체스말이 어느 높이에 있는지 확인
                Board curBoard = boards[hx][hy];
                Horse[] curHorse = curBoard.curHorses;
                int i = 0;
                for(; i<=curHorse.length; i++){
                    if(curHorse[i].num == h.num){
                        break;
                    }
                }

                // 옮길 위치 확인하기
                int[] nextXYD = returnXYD(hx, hy, hd);
                int checkD1 = nextXYD[2];
                int nextX = nextXYD[0];
                int nextY = nextXYD[1];

                h.direction = nextXYD[2];
                // 옮길 위치도 벽이거나 파란색 일경우
                if((nextX < 0 || nextX >=n || nextY < 0 || nextY>=n)  || boards[nextX][nextY].type == 2){
                    continue;
                }

                int nextBoardHigh = 0;
                if(boards[nextX][nextY].type == 0){ // 흰색
                    nextBoardHigh = curBoard.moveToWhite(i, boards[nextX][nextY]);
                }
                else if(boards[nextX][nextY].type == 1){ // 빨간색
                    nextBoardHigh = curBoard.moveToRed(i, boards[nextX][nextY]);
                }

                if(nextBoardHigh >= 4){
                    System.out.println(turn);
                    return;
                }
            }
            turn ++;

            if(turn > 1000){
                System.out.println(-1);
                return;
            }
        }

        System.out.println(turn);

    }

    static int[] returnXYD(int x, int y, int d){
        // 1: →, 2: ←, 3: ↑, 4: ↓
        if(d == 1){
            return validBoard(x, y+1, d);
        }else if(d == 2){
            return validBoard(x, y-1, d);
        }else if(d == 3){
            return validBoard(x-1, y, d);
        }else{
            return validBoard(x+1, y, d);
        }
    }

    static int[] validBoard(int x, int y, int d){
        if ((x < 0 || x >=n || y < 0 || y>=n) || boards[x][y].type == 2 ) {
            switch(d) {
                case 1:
                    y -= 2;
                    d = 2;
                    break;
                case 2:
                    y += 2;
                    d= 1;
                    break;
                case 3:
                    x += 2;
                    d= 4;
                    break;
                case 4:
                    x -= 2;
                    d= 3;
                    break;
            }
        }
        return new int[]{x, y, d};
    }



    static boolean boardUnder4(){
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(boards[i][j].curBoardHigh >= 4){
                    return false;
                }
            }
        }

        return true;
    }





}