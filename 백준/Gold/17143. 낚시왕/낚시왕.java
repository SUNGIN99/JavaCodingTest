import java.util.*;
import java.io.*;

public class Main{

    static class Block{
        int row, col;
        Deque<Shark> sharks;

        Block(int r, int c){
            this.row =r;
            this.col = c;
            sharks = new LinkedList<>();
        }
    }
    static class Shark{
        int row, col, velocity, direction, size;
        int whenMoved;
        Shark(int r, int c, int v, int d, int s){
            this.row = r;
            this.col = c;
            this.velocity = v;
            this.direction = d;
            this.size = s;
            whenMoved = 0;
        }
        public String toString(){
            return "{" + velocity + ", " + direction + ", " + size + ", " + whenMoved + "} ";
        }
    }
    static int r, c, m;
    static Block[][] blocks;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        blocks = new Block[r][];
        for(int i = 0; i<r; i++){
            blocks[i] = new Block[c];
            for(int j = 0; j<c; j++){
                blocks[i][j] = new Block(i, j);
            }
        }

        int sr, sc, s, d, z;
        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            sr = Integer.parseInt(st.nextToken());
            sc = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            z = Integer.parseInt(st.nextToken());
            blocks[sr-1][sc-1].sharks.add(new Shark(sr-1, sc-1, s, d, z));
        }

        int fishmanCol = 0;
        int totalShark = 0;
        while(fishmanCol < c){
            totalShark += getShark(fishmanCol);
            sharkMoving(fishmanCol);
            sharkEatShark();

            fishmanCol++;
        }
        System.out.println(totalShark);
    }

    static int getShark(int fishmanCol){
        int sharkSize = 0;
        for(int i = 0; i<r; i++){
            if(!blocks[i][fishmanCol].sharks.isEmpty()){
                sharkSize = blocks[i][fishmanCol].sharks.pollFirst().size;
                break;
            }
        }

        return sharkSize;
    }

    static void sharkMoving(int fishmanCol){
        // 방향 = {1: 위, 2: 아래, 3: 오른쪽, 4: 왼쪽}
        for(int i = 0; i<r; i++){
            for(int j = 0; j<c; j++){
                Block block = blocks[i][j];
                int sharkR = i;
                int sharkC = j;
                if(!block.sharks.isEmpty()){
                    Shark shark = block.sharks.getFirst();
                    if(shark.whenMoved == fishmanCol){
                        shark = block.sharks.pollFirst();
                    }else{
                        continue;
                    }

                    int moved = 0;
                    while(moved < shark.velocity){
                        if(shark.direction == 1){
                            if(sharkR - 1 >= 0){
                                sharkR--;
                            }else{
                                shark.direction = 2;
                                sharkR++;
                            }
                        }else if(shark.direction ==2){
                            if(sharkR + 1 < r){
                                sharkR++;
                            }else{
                                shark.direction = 1;
                                sharkR--;
                            }
                        }else if(shark.direction ==3){
                            if(sharkC + 1 < c){
                                sharkC++;
                            }else{
                                shark.direction = 4;
                                sharkC--;
                            }
                        }else {// if(shark.direction ==4)
                            if(sharkC - 1 >= 0){
                                sharkC--;
                            }else{
                                shark.direction = 3;
                                sharkC++;
                            }
                        }
                        moved++;
                    }

                    shark.row = sharkR;
                    shark.col = sharkC;
                    shark.whenMoved = fishmanCol + 1;
                    blocks[sharkR][sharkC].sharks.add(shark);
                }
            }
        }
    }

    static void sharkEatShark(){
        for(int i = 0; i<r; i++){
            for(int j = 0; j<c; j++){
                Block block = blocks[i][j];
                Deque<Shark> sharks = block.sharks;
                if(!block.sharks.isEmpty()){
                    Shark shark1 = block.sharks.pollFirst();
                    while(!sharks.isEmpty()){
                        Shark shark2 = block.sharks.pollFirst();
                        if(shark1.size < shark2.size){
                            shark1 = shark2;
                        }
                    }

                    block.sharks.add(shark1);
                }
            }
        }
    }


}