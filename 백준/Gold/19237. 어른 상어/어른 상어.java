import java.util.*;
import java.io.*;

public class Main{

    static class Smell{
        int whenSmelled;
        int whichShark;

        Smell(int sm, int sh){
            this.whenSmelled = sm;
            this.whichShark = sh;
        }

        public String toString(){
            return "{"+whenSmelled + ", " + whichShark + "} ";
        }
    }
    static class Shark{
        int row, col;
        int direction;
        int num;
        int[][] priority;
        Shark(int r, int c, int d, int n){
            this.row = r;
            this.col = c;
            this.direction = d;
            this.num = n;
            initPriority();
        }

        void initPriority(){
            this.priority = new int[5][4];
            for(int i = 1; i<=4; i++){
                priority[i] = new int[4];
            }
        }

        public String toString(){
            return "("+row +", " + col + ") : " + num+ " & " + direction +" ";
        }
    }

    static int n, m, k;
    static int[][] shark;
    static Shark[] sharkInfo;
    static Smell[][] smellInfo;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        shark = new int[n][n];
        sharkInfo = new Shark[m+1];
        smellInfo = new Smell[n][n];
        for(int i = 0; i<n; i++){
            shark[i] = new int[n];
            smellInfo[i] = new Smell[n];
            st = new StringTokenizer(br.readLine());
            int sharkNum;
            for(int j =0; j<n; j++){
                sharkNum = shark[i][j] = Integer.parseInt(st.nextToken());
                if(sharkNum >= 1){
                    sharkInfo[sharkNum] = new Shark(i, j, 0, sharkNum);
                }
                smellInfo[i][j] = new Smell(0, 0);
            }
        }
        //System.out.println(Arrays.toString(sharkInfo));

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=m; i++){
            sharkInfo[i].direction = Integer.parseInt(st.nextToken());
            // 1: 위, 2: 아래, 3: 왼쪽, 4: 오른쪽
        }

        for(int i = 1; i<=m; i++){
            for(int j = 1; j<=4; j++){
                st = new StringTokenizer(br.readLine());
                for(int t = 0; t<4; t++){
                    sharkInfo[i].priority[j][t] = Integer.parseInt(st.nextToken());
                }
            }
        }

        // 입력 끝 ------
        sharkSmell(0);

        /*for(int i = 0; i<n; i++){
            System.out.println(Arrays.toString(shark[i]));
        }
        System.out.println("---");*/

        int time = 0;
        while(checkOtherShark()){
            time++;
            //System.out.println(time);
            sharkMoving();
            sharkExit();
            sharkSmell(time);
            smellGone(time);
            /*for(int i = 0; i<n; i++){
                System.out.println(Arrays.toString(shark[i]));
            }
            System.out.println("---");
            for(int i = 0; i<n; i++){
                System.out.println(Arrays.toString(smellInfo[i]));
            }*/
            if(time > 1000){
                System.out.println(-1);
                return;
            }
        }
        System.out.println(time);
    }

    static void smellGone(int time){
        for(int i = 0; i<n; i++){
            for(int j = 0 ; j < n; j++){
                int smellTime = smellInfo[i][j].whenSmelled;
                if(Math.abs(smellTime - time) >= k){
                    smellInfo[i][j].whenSmelled = 0;
                    smellInfo[i][j].whichShark = 0;
                }
            }
        }
    }

    static void sharkExit(){
        int sharkInfoLen = sharkInfo.length;
        HashMap<String, ArrayList<Shark>> sharkAt = new HashMap<>();

        for(int i = 1; i<sharkInfo.length; i++){
            if(sharkInfo[i] == null){
                continue;
            }
            String key = sharkInfo[i].row + "," +sharkInfo[i].col;
            ArrayList<Shark> xy = sharkAt.getOrDefault(key, new ArrayList<>());
            xy.add(sharkInfo[i]);
            sharkAt.put(key, xy);
        }

        /*System.out.println(sharkAt);
        System.out.println(Arrays.toString(sharkInfo));*/

        for(String key : sharkAt.keySet()){
            ArrayList<Shark> ss = sharkAt.get(key);
            for(Shark s : ss){
                int sharkNum = shark[s.row][s.col];
                if(sharkNum >= 1){
                    if(sharkNum > s.num){
                        sharkInfo[sharkNum] = null;
                        shark[s.row][s.col] = s.num;
                    }else if(sharkNum < s.num){
                        sharkInfo[s.num] = null;
                    }
                }
                else if(sharkNum == 0){
                    shark[s.row][s.col] = s.num;
                }
            }
        }


    }

    static void sharkMoving(){
        for(int i = 1; i<sharkInfo.length; i++){
            Shark curShark = sharkInfo[i];
            if(curShark == null){
                continue;
            }
            int sharkX = curShark.row;
            int sharkY = curShark.col;
            int sharkNum = curShark.num;
            int sharkDir = curShark.direction;
            int[][] priorityS = curShark.priority;

            shark[sharkX][sharkY] = 0;

            boolean canSharkGo = false;
            int[] dirPriority = priorityS[sharkDir];
            int[] mySmell = new int[4];
            for(int d = 0; d<4; d++){ // 1
                if(dirPriority[d] == 1){ // 위
                    // 이동 가능하고, 냄새가 없는 곳 일 때
                    if(sharkX-1 >= 0 && smellInfo[sharkX-1][sharkY].whichShark == 0){
                        sharkX--;
                        sharkDir = 1;
                        canSharkGo = true;
                    }
                    if(sharkX-1 >= 0 && smellInfo[sharkX-1][sharkY].whichShark == sharkNum){
                        mySmell[d] = 1;
                    }
                }else if(dirPriority[d] == 2){ // 아래
                    if(sharkX+1 < n && smellInfo[sharkX+1][sharkY].whichShark == 0){
                        sharkX++;
                        sharkDir = 2;
                        canSharkGo = true;
                    }
                    if(sharkX+1 < n && smellInfo[sharkX+1][sharkY].whichShark == sharkNum){
                        mySmell[d] = 2;
                    }
                }else if(dirPriority[d] == 3){ // 왼쪽
                    if(sharkY-1 >= 0 && smellInfo[sharkX][sharkY-1].whichShark == 0){
                        sharkY--;
                        sharkDir = 3;
                        canSharkGo = true;
                    }
                    if(sharkY-1 >= 0 && smellInfo[sharkX][sharkY-1].whichShark == sharkNum){
                        mySmell[d] = 3;
                    }
                }else { // 오른쪽
                    if(sharkY+1 < n && smellInfo[sharkX][sharkY+1].whichShark == 0){
                        sharkY++;
                        sharkDir = 4;
                        canSharkGo = true;
                    }
                    if(sharkY+1 < n && smellInfo[sharkX][sharkY+1].whichShark == sharkNum){
                        mySmell[d] = 4;
                    }
                }

                if(canSharkGo){
                    break;
                }
            }

            if(!canSharkGo){ // 주변에 냄새가 없는 칸이 없을 경우
                //System.out.println(sharkNum + ": " + Arrays.toString(mySmell));
                for(int d = 0; d<4; d++){
                    // 내 냄새 찾기
                    if(mySmell[d] != 0){
                        if(mySmell[d] == 1 && sharkX - 1 >= 0){
                            sharkX--;
                            sharkDir = 1;
                            break;
                        }else if(mySmell[d] == 2 && sharkX + 1 < n){
                            sharkX++;
                            sharkDir = 2;
                            break;
                        }else if(mySmell[d] == 3 && sharkY - 1 >= 0){
                            sharkY--;
                            sharkDir = 3;
                            break;
                        }else if(mySmell[d] == 4 && sharkY +1 < n){
                            sharkY++;
                            sharkDir = 4;
                            break;
                        }
                    }
                }
            }

            curShark.row = sharkX;
            curShark.col = sharkY;
            curShark.direction = sharkDir;
        }
    }

    static void sharkSmell(int time){
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(shark[i][j] >= 1){
                    smellInfo[i][j].whenSmelled = time;
                    smellInfo[i][j].whichShark = shark[i][j];
                }
            }
        }
    }

    static boolean checkOtherShark(){
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(shark[i][j] > 1){
                    return true;
                }
            }
        }
        return false;
    }


}