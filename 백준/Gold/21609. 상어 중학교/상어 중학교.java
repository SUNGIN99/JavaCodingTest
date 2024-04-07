import java.util.*;
import java.io.*;
public class Main {
    static int[][] block;
    static boolean[][] visited;
    static boolean[][] normalVisited;
    static int [][] nextBlock;
    static int n, maxGroup = 0, maxRainbow =0, maxR, maxC, traceG = 0, traceRain;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        block = new int[n][];
        visited = new boolean[n][];
        normalVisited = new boolean[n][];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            block[i] = new int[n];
            visited[i] = new boolean[n];
            normalVisited[i] = new boolean[n];
            for(int j = 0; j<n; j++){
                block[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int score = 0;
        while(true){
            findBlock();
            if(maxGroup == 0){
                break;
            }else{
                score += maxGroup * maxGroup;
                for(int i = 0; i<n; i++){
                    block[i] = nextBlock[i].clone();
                    // clone 해주지 않고, block[i] = new bloc[i]; 로 해버리면, 메모리 포인터가 넘어가기 때문에 값이 이상하게 변함
                }

                //System.out.println(maxGroup);
                /*for(int i = 0; i<n; i++){
                    System.out.println(Arrays.toString(block[i]));
                }*/
                //System.out.println();
                gravity();

                turn();

                for(int i = 0; i<n; i++){
                    block[i] = nextBlock[i].clone();
                }

                gravity();

            }



        }

        System.out.println(score);
    }

    static void findBlock(){
        int bc = 0;
        maxGroup = 0;
        maxRainbow = 0;
        maxR = -1;
        maxC = -1;
        initNormalVisited(); // 블럭그룹 찾을 때 마다 방문한 일반블럭을 설정해줘야하는데, 이걸 안해줘서 틀렸음
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(block[i][j] != -1 && block[i][j] != 0 && block[i][j] != 6 && !normalVisited[i][j]){
                    // 검은블럭, 무지개블럭 뿐만 아니라 빈칸 블럭도 조건문에 넣어줘서 활용
                    //System.out.println("find: " + i + ", " + j + ", " + maxGroup);
                    traceG = 0; // 현재 일반블럭의 그룹 수
                    traceRain = 0; // 현재 일반블럭 그룹 내의 무지개 블럭 수
                    initVisited();
                    normalVisited[i][j] = true;
                    getNormalBlock(block[i][j], i, j, 1, 0, i, j);
                }
            }
        }
    }

    static void getNormalBlock(int blockNum, int row, int col, int bc, int rainbow, int standR, int standC){
        if(row < 0 || row >= n || col < 0 || col >= n){ // 범위를 초과했다면
            return;
        }

        if(visited[row][col]){ // 이미 방문한 블럭이라면
            return;
        }

        if((block[row][col] != 0 && blockNum != block[row][col]) || blockNum == -1){ // 같은 색의 블럭이 아니거나 무지개 블럭이 아니라면
            return;
        }
       // System.out.println(blockNum + " : " + row + ", " + col + ", " + bc);

        visited[row][col] = true;

        if(block[row][col] == 0){
            traceRain++;
        }
        else{
            normalVisited[row][col] = true;
            if(standR > row){
                standR = row;
            }
            if(standC > col){
                standC = col;
            }
        }


        getNormalBlock(blockNum, row-1, col, bc+1, rainbow, standR, standC);
        getNormalBlock(blockNum, row+1, col, bc+1, rainbow, standR, standC);
        getNormalBlock(blockNum, row, col-1, bc+1, rainbow, standR, standC);
        getNormalBlock(blockNum, row, col+1, bc+1, rainbow, standR, standC);

        traceG++;

        if(traceG >= 2){
            if(maxGroup < traceG){
                maxGroup = traceG;
                maxRainbow = traceRain;
                maxR = standR;
                maxC = standC;
                groupCheck();
            }else if(maxGroup == traceG){
                if(maxRainbow < traceRain){
                    maxRainbow = traceRain;
                    maxR = standR;
                    maxC = standC;
                    groupCheck();
                }else if(maxRainbow == traceRain){
                    if(maxR < standR){
                        maxR = standR;
                        maxC = standC;
                        groupCheck();
                    }else if(maxR == standR){
                        if(maxC < standC){
                            maxC = standC;
                            groupCheck();
                        }
                    }
                }
            }
        }
    }

    static void groupCheck(){ // 그룹 지우기
        nextBlock = new int[n][];
        for(int i = 0; i<n; i++){
            nextBlock[i] = new int[n];
            for(int j = 0; j<n ;j++){
                if(visited[i][j]){
                    nextBlock[i][j] = 6;
                }else{
                    nextBlock[i][j] = block[i][j];
                }
            }
        }

    }

    static void gravity(){
        for(int i = 0; i<n; i++){
            for(int j = n-1; j>0 ;j--){
                int k = j - 1;
                if(block[j][i] == 6){
                    while(k>=0){
                        if(block[k][i] == -1){
                            break;
                        }else if(block[k][i] == 6){
                            k--;
                        }else{
                            block[j][i] = block[k][i];
                            block[k][i] = 6;
                            break;
                        }
                    }
                }else{ // 검은 블럭이거나, 이미 블럭이 있는 경우(빈 블럭이 아닌 경우)
                }
            }
        }

        /*System.out.println("gravity");
        for(int i = 0; i<n; i++){
            System.out.println(Arrays.toString(block[i]));
        }
        System.out.println();*/
    }

    static void turn(){
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                nextBlock[n - j - 1][i] = block[i][j];
                //System.out.print(block[i][j] + " " );
            }
            //System.out.println();
        }

    }





    static void initVisited(){
        for(int i = 0; i<n; i++){
            visited[i] = new boolean[n];
        }
    }

    static void initNormalVisited(){
        for(int i = 0; i<n; i++){
            normalVisited[i] = new boolean[n];
        }
    }






}
