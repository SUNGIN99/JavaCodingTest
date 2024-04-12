import java.util.*;
import java.io.*;

public class Main{

    static class Fish{
        int row, col;
        int num;
        int direction;
        boolean ate;
        //↑, ↖, ←, ↙, ↓, ↘, →, ↗
        Fish(int r, int c, int n, int d, boolean a){
            this.row  = r;
            this.col = c;
            this.num = n;
            this.direction = d;
            ate = a;
        }

        static Fish getFishCopy(Fish f){
            return new Fish(f.row, f.col, f.num, f.direction, f.ate);
        }

        public String toString(){
            char[] dir = "↑↖←↙↓↘→↗".toCharArray();
            //return "{" + "("+ row + ", "  + col+ ") : " +  num + ", " + direction + "}(" +(ate?"X":"O")+")";
            return "{" +  num + ", " + (dir[direction-1]) + "}(" +(ate?"X":"O")+")";
        }

    }

    static int n = 4;
    static int max = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int sharkX=0, sharkY=0, sharkD = 0;
        Fish[][] fishes = new Fish[4][4];
        Fish[] fishSeq = new Fish[16];

        int fishNum, fishDir;
        for(int i = 0; i<4; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<4; j++){
                fishNum = Integer.parseInt(st.nextToken());
                fishDir = Integer.parseInt(st.nextToken());
                fishSeq[fishNum-1] = fishes[i][j] = new Fish(i, j, fishNum, fishDir, false);

                if(i==0 && j==0){
                    sharkD = fishDir;
                }
            }
        }

        //↑:1 , ↖:2 , ←:3 , ↙:4 , ↓:5 , ↘:6 , →:7 , ↗:8
        boyShark(sharkX, sharkY, sharkD, fishes, fishSeq, 0);
        System.out.println(max);
    }

    static void boyShark(int sharkX, int sharkY, int sharkD, Fish[][] fishes, Fish[] fishSeq, int eat){
        // 물고기 이동
        Fish[][] copiedFishes = new Fish[4][4];
        Fish[] copiedFishSeq= new Fish[16];
        for(int i = 0; i<4; i++){
            for(int j = 0; j<4; j++){
                Fish copyFish = Fish.getFishCopy(fishes[i][j]);
                copiedFishes[i][j] = copyFish;
                copiedFishSeq[copyFish.num-1] = copyFish; // 객체 포인터 복사

            }
        }

        Fish fishAteByShark = copiedFishes[sharkX][sharkY];
        int ateFishNum = fishAteByShark.num;
        int ateFishDir =  fishAteByShark.direction;
        eat += ateFishNum;
        copiedFishSeq[ateFishNum-1].ate = true;

        fishMoving(sharkX, sharkY, copiedFishes, copiedFishSeq);

        //System.out.println("===");
        //// 물고기 이동 끝
        //System.out.println(Arrays.toString(copiedFishSeq));
        /*for(int j = 0; j<4; j++){
            System.out.println(Arrays.toString(copiedFishes[j]));
        }
        System.out.println("---");*/

        ArrayList<int[]> sharkMovings = getMovedLocation(sharkX, sharkY, ateFishDir, copiedFishes);

        if(sharkMovings.isEmpty()){
            //System.out.println( "{" +sharkX + ", " +sharkY+"}  = " +max);
            max = Math.max(max, eat);
            return;
        }

        for(int[] sm : sharkMovings){
            //System.out.println("from : " + "{" +sharkX + ", " +sharkY+"} => to: {" +sm[0] + ", " + sm[1] + "}");
            boyShark(sm[0], sm[1], sharkD, copiedFishes, copiedFishSeq, eat);
        }
    }

    static ArrayList<int[]> getMovedLocation(int sharkX, int sharkY, int sharkD, Fish[][] fishes){
        ArrayList<int[]> sharkMoving = new ArrayList<>();
        while(sharkX >= 0 && sharkX <= n-1 && sharkY >= 0 && sharkY <= n-1){
            if(sharkD == 1){ // ↑
                if(sharkX - 1 >= 0 && !fishes[sharkX-1][sharkY].ate) {
                    sharkMoving.add(new int[]{sharkX-1, sharkY});
                }
                sharkX--;
            }else if(sharkD == 2){// ↖
                if(sharkX - 1 >= 0 && sharkY - 1 >= 0 && !fishes[sharkX-1][sharkY-1].ate){
                    sharkMoving.add(new int[]{sharkX-1, sharkY-1});
                }
                sharkX--;
                sharkY--;
            }else if(sharkD == 3){// ←
                if(sharkY - 1 >= 0 && !fishes[sharkX][sharkY-1].ate){
                    sharkMoving.add(new int[]{sharkX, sharkY-1});
                }
                sharkY--;
            }else if(sharkD == 4){// ↙
                if(sharkX + 1 <= n-1 && sharkY - 1 >= 0 && !fishes[sharkX+1][sharkY-1].ate){
                    sharkMoving.add(new int[]{sharkX+1, sharkY-1});
                }
                sharkX++;
                sharkY--;
            }else if(sharkD == 5){// ↓
                if(sharkX + 1 <= n-1 && !fishes[sharkX+1][sharkY].ate){
                    sharkMoving.add(new int[]{sharkX+1, sharkY});
                }
                sharkX++;
            }else if(sharkD == 6){// ↘
                if(sharkX + 1 <= n-1 && sharkY + 1 <= n-1 && !fishes[sharkX+1][sharkY+1].ate){
                    sharkMoving.add(new int[]{sharkX+1, sharkY+1});
                }
                sharkX++;
                sharkY++;
            }else if(sharkD == 7){// →
                if(sharkY + 1 <= n-1 && !fishes[sharkX][sharkY+1].ate){
                    sharkMoving.add(new int[]{sharkX, sharkY+1});
                }
                sharkY++;
            }else if(sharkD == 8){// ↗
                if(sharkX - 1 >= 0 && sharkY + 1 <= n-1 && !fishes[sharkX-1][sharkY+1].ate){
                    sharkMoving.add(new int[]{sharkX-1, sharkY+1});
                }
                sharkX--;
                sharkY++;
            }
        }
        return sharkMoving;
    }

    static void fishMoving(int sharkX, int sharkY, Fish[][] fishes, Fish[] fishSeq){
        for(int i = 0; i<16; i++){
            Fish f = fishSeq[i];
            int count = 0;
            int moved = 0;
            //System.out.println(f.num + ", " +f.direction);
            if(f.ate){
                //System.out.println((i+1)+ ": " + sharkX + ", " + sharkY + " ");
                continue;
            }
            while(moved < 1 && count < 8){
                int curFx = f.row;
                int curFy = f.col;
                int curFd = f.direction;
                //↑, ↖, ←, ↙, ↓, ↘, →, ↗
                if(curFd == 1){ // ↑
                    if(curFx - 1 >= 0 && !isSharkThere(sharkX, sharkY, curFx-1, curFy)){
                        Fish thereFish = fishes[curFx-1][curFy];
                        fishes[curFx-1][curFy] = f;
                        fishes[curFx][curFy] = thereFish;

                        fishSeq[f.num-1].row = curFx-1;
                        fishSeq[thereFish.num-1].row = curFx;
                        moved++;
                    }else{
                        f.direction++;
                    }
                }else if(curFd == 2){// ↖
                    if(curFx - 1 >= 0 && curFy - 1 >= 0 && !isSharkThere(sharkX, sharkY, curFx-1, curFy-1)){
                        Fish thereFish = fishes[curFx-1][curFy-1];
                        fishes[curFx-1][curFy-1] = f;
                        fishes[curFx][curFy] = thereFish;

                        fishSeq[f.num-1].row = curFx-1;
                        fishSeq[f.num-1].col = curFy-1;
                        fishSeq[thereFish.num-1].row = curFx;
                        fishSeq[thereFish.num-1].col = curFy;
                        moved++;
                    }else{
                        f.direction++;
                    }
                }else if(curFd == 3){// ←
                    if(curFy - 1 >= 0 && !isSharkThere(sharkX, sharkY, curFx, curFy-1)){
                        Fish thereFish = fishes[curFx][curFy-1];
                        fishes[curFx][curFy-1] = f;
                        fishes[curFx][curFy] = thereFish;

                        fishSeq[f.num-1].col = curFy-1;
                        fishSeq[thereFish.num-1].col = curFy;
                        moved++;
                    }else{
                        f.direction++;
                    }
                }else if(curFd == 4){// ↙
                    if(curFx + 1 <= n-1 && curFy - 1 >= 0 && !isSharkThere(sharkX, sharkY, curFx+1, curFy-1)){
                        Fish thereFish = fishes[curFx+1][curFy-1];
                        fishes[curFx+1][curFy-1] = f;
                        fishes[curFx][curFy] = thereFish;

                        fishSeq[f.num-1].row = curFx+1;
                        fishSeq[f.num-1].col = curFy-1;
                        fishSeq[thereFish.num-1].row = curFx;
                        fishSeq[thereFish.num-1].col = curFy;
                        moved++;
                    }else{
                        f.direction++;
                    }
                }else if(curFd == 5){// ↓
                    if(curFx + 1 <= n-1 && !isSharkThere(sharkX, sharkY, curFx+1, curFy)){
                        Fish thereFish = fishes[curFx+1][curFy];
                        fishes[curFx+1][curFy] = f;
                        fishes[curFx][curFy] = thereFish;

                        fishSeq[f.num-1].row = curFx+1;
                        fishSeq[thereFish.num-1].row = curFx;
                        moved++;
                    }else{
                        f.direction++;
                    }
                }else if(curFd == 6){// ↘
                    if(curFx + 1 <= n-1 && curFy + 1 <= n-1 && !isSharkThere(sharkX, sharkY, curFx+1, curFy+1)){
                        Fish thereFish = fishes[curFx+1][curFy+1];
                        fishes[curFx+1][curFy+1] = f;
                        fishes[curFx][curFy] = thereFish;

                        fishSeq[f.num-1].row = curFx+1;
                        fishSeq[f.num-1].col = curFy+1;
                        fishSeq[thereFish.num-1].row = curFx;
                        fishSeq[thereFish.num-1].col = curFy;
                        moved++;
                    }else{
                        f.direction++;
                    }
                }else if(curFd == 7){// →
                    if(curFy + 1 <= n-1 && !isSharkThere(sharkX, sharkY, curFx, curFy+1)){
                        Fish thereFish = fishes[curFx][curFy+1];
                        fishes[curFx][curFy+1] = f;
                        fishes[curFx][curFy] = thereFish;

                        fishSeq[f.num-1].col = curFy+1;
                        fishSeq[thereFish.num-1].col = curFy;
                        moved++;
                    }else{
                        f.direction++;
                    }
                }else if(curFd == 8){// ↗
                    if(curFx - 1 >= 0 && curFy + 1 <= n-1 && !isSharkThere(sharkX, sharkY, curFx-1, curFy+1)){
                        Fish thereFish = fishes[curFx-1][curFy+1];
                        fishes[curFx-1][curFy+1] = f;
                        fishes[curFx][curFy] = thereFish;

                        fishSeq[f.num-1].row = curFx-1;
                        fishSeq[f.num-1].col = curFy+1;
                        fishSeq[thereFish.num-1].row = curFx;
                        fishSeq[thereFish.num-1].col = curFy;
                        moved++;
                    }else{
                        f.direction = 1;
                    }
                }
                count++;
            }
        }
    }

    static boolean isSharkThere(int sx, int sy, int fx, int fy){
        return sx==fx && sy==fy;
    }


}