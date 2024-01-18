import java.util.*;
import java.io.*;

public class Main {

    static class Fish{
        int x, y;
        Deque<Integer> fishes;

        Fish(int x, int y){
            this.x = x;
            this.y = y;
            this.fishes = new LinkedList<>();
        }
    }

    static int[][] visited;
    static int[][] shark;
    static int[][] smell;
    static Fish[][] fishState;
    static String sharksPath;
    static int sx, sy;

    static int pathMaxFishes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 물고기 수
        int m = Integer.parseInt(st.nextToken());

        // 상어 마법 연습 횟수
        int s = Integer.parseInt(st.nextToken());


        fishState = new Fish[5][5];
        initFishFields(fishState);

        // 물고기 첫 위치
        for(int i = 0; i< m; i++){
            st = new StringTokenizer(br.readLine());
            int fx = Integer.parseInt(st.nextToken());
            int fy = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            fishState[fx][fy].fishes.add(d);
        }

        // 상어 첫 위치
        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken());
        shark = new int[5][5];
        shark[sx][sy] = 1;
        visited = new int[5][5];

        // 물고기 냄새
        smell = new int[5][5];

        // 물고기 복사 마법 실행(횟수 만큼)
        for(int i = 0; i< s; i++){
            // 1. 물고기 복사
            Fish[][] fishCopy = fishCopy();

            // 2. 물고기 이동
            fishMove(i+1);

            // 3. 상어 이동
            sharkExceptsFish(i+1);

            // 4. 두번 전물고기 냄새 사라짐
            smellGone(i+1);

            // 5. 물고기 복사 완료
            for(int x = 1; x <= 4; x++){
                for(int y = 1; y <= 4; y++){
                    fishState[x][y].fishes.addAll(fishCopy[x][y].fishes);
                }
            }

        }

        // 마법 종료 후 남아있는 물고기 수 확인
        int result = 0;
        for(int x = 1; x <= 4; x++){
            for(int y = 1; y <= 4; y++){
                result += fishState[x][y].fishes.size();
            }
        }

        System.out.println(result);


    }
    static Fish[][] fishCopy(){ // 1) 물고기 복사
        Fish[][] copy = new Fish[5][5];
        initFishFields(copy);

        for(int i = 1; i<= 4; i++){
            for(int j =1; j<=4; j++){
                copy[i][j].fishes.addAll(fishState[i][j].fishes);
            }
        }

        return copy;
    }

    static void fishMove(int pracCount){ // 2) 물고기 한칸 이동
        Fish[][] movedFishFields = new Fish[5][5];
        initFishFields(movedFishFields);

        for(int i = 1; i<= 4; i++){
            for(int j = 1; j<=4; j++){
                Deque<Integer> fieldsFish = fishState[i][j].fishes;
                int remainFish = fieldsFish.size(); // 실수부분
                for(int f = 0; f<remainFish; f++){
                    int[] whereToMove = getFishDirection(i, j, fieldsFish.poll(), 1); // return moved R, C, Dir
                    int movedI = whereToMove[0];
                    int movedJ = whereToMove[1];
                    int movedD = whereToMove[2];
                    movedFishFields[movedI][movedJ].fishes.add(movedD);
                }
            }
        }

        fishState = movedFishFields;
    }

    static int[] getFishDirection(int i, int j, int dir, int spin){ // 2-1) 물고기 이동 방향 설정
        if(spin > 8){
            return new int[]{i, j, dir};
        }

        int movedR = i;
        int movedC = j;

        if(dir == 1){        // 왼쪽
            movedC--;
        }else if (dir == 2){ // 왼쪽 대각선 위
            movedR--;
            movedC--;
        }else if (dir == 3){ // 위
            movedR--;
        }else if (dir == 4){ // 오른쪽 대각선 위
            movedR--;
            movedC++;
        }else if (dir == 5){ // 오른쪽
            movedC++;
        }else if (dir == 6){ // 오른쪽 대각선 아래
            movedR++;
            movedC++;
        }else if (dir == 7){ // 아래
            movedR++;
        }else if (dir == 8){ // 왼쪽 대각선 아래
            movedR++;
            movedC--;
        }


        if(movedR < 1 || movedR > 4 || movedC < 1 || movedC > 4){
            return getFishDirection(i, j, dir - 1 == 0 ? 8 : dir -1, spin + 1);
        }
        else if(smell[movedR][movedC] != 0){
            return getFishDirection(i, j, dir - 1 == 0 ? 8 : dir -1, spin + 1);
        }
        else if(shark[movedR][movedC] != 0){
            return getFishDirection(i, j, dir - 1 == 0 ? 8 : dir -1, spin + 1);
        }

        return new int[]{movedR, movedC, dir};
    }

    // 3) 상어 이동
    static void sharkExceptsFish(int pracCount){
        sharksPath = "555";
        pathMaxFishes = 0;

        if(sx-1 >= 1 && sx <= 4){
            sharksMoving(sx-1, sy, "1", fishState[sx-1][sy].fishes.size()); // 상
        }
        if(sy - 1 >= 1 && sy <= 4){
            sharksMoving(sx, sy-1, "2", fishState[sx][sy-1].fishes.size()); // 좌
        }
        if(sx + 1 <= 4 && sx >= 1){
            sharksMoving(sx+1, sy, "3", fishState[sx+1][sy].fishes.size()); // 하
        }
        if(sy + 1 <= 4 && sy >= 1){
            sharksMoving(sx, sy+1, "4", fishState[sx][sy+1].fishes.size()); // 우
        }

        char[] pathSeq = sharksPath.toCharArray();

        // 물고기 냄새
        fishSmell(pracCount, pathSeq);
    }

    // 3-1) 상어 상/하/좌/우 이동
    static void sharksMoving(int i, int j, String path, int exceptFishes){
        int currPathFishes = exceptFishes;

        if(visited[i][j] >= 1) {
            // 상어 이동 시에 갔던곳을 다시 방문 할 수 있다.
            // 그렇기 때문에, 방문했던 곳의 물고기 수는 빼지않음
            currPathFishes = exceptFishes - fishState[i][j].fishes.size();
        }

        visited[i][j]++;

        if(path.length() == 3){
            if(pathMaxFishes < currPathFishes){
                pathMaxFishes = currPathFishes;
                sharksPath = path;
            }else if(pathMaxFishes == currPathFishes && path.compareTo(sharksPath) < 0){
                pathMaxFishes = currPathFishes;
                sharksPath = path;
            }
            else{
            }

            visited[i][j]--;
            return;
        }

        if(i - 1 >= 1 && i <= 4) {
            sharksMoving(i - 1, j, path + "1", currPathFishes + fishState[i - 1][j].fishes.size()); // 상
        }
        if(j - 1 >= 1 && j <= 4) {
            sharksMoving(i, j - 1, path + "2", currPathFishes + fishState[i][j - 1].fishes.size()); // 좌
        }
        if(i + 1 <= 4 && i >= 1){
            sharksMoving(i+1, j, path + "3", currPathFishes + fishState[i+1][j].fishes.size()); // 하
        }
        if(j + 1 <= 4 && j >= 1){
            sharksMoving(i, j+1, path + "4", currPathFishes + fishState[i][j+1].fishes.size()); // 우
        }

        visited[i][j]--;
    }

    // 3-2) 상어 지나간 자리 물고기 있으면 물고기 냄새
    static void fishSmell(int pracCount, char[] pathSeq){
        shark[sx][sy] = 0;
        for(char s : pathSeq){
            if(s == '1'){
                sx--;
            }else if(s == '2'){
                sy--;
            }else if(s == '3'){
                sx++;
            }else if(s == '4'){
                sy++;
            }
            if(fishState[sx][sy].fishes.size() >= 1){
                fishState[sx][sy].fishes = new LinkedList<>();
                smell[sx][sy] = pracCount;
            }
        }

        shark[sx][sy] = 1;
    }

    static void smellGone(int pracCount){ //
        for(int i = 1; i<=4 ; i++){
            for(int j = 1; j<= 4; j++){
                if(smell[i][j] <= pracCount - 2){
                    smell[i][j] = 0;
                }
            }
        }
    }

    static void printFields(Fish[][] fList){
        for(int i = 1; i<=4 ; i++){
            for(int j =1 ; j<=4; j++){
                System.out.print(fList[i][j].fishes);
            }
            System.out.println();
        }

        System.out.println();
    }

    static void printFieldsSize(Fish[][] fList){
        for(int i = 1; i<=4 ; i++){
            for(int j =1 ; j<=4; j++){
                System.out.print(fList[i][j].fishes.size()+ " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    static void initFishFields(Fish[][] field){ // 물고기 필드 초기화
        for(int i = 1; i<= 4; i++){
            for(int j = 1; j<= 4; j++){
                field[i][j] = new Fish(i, j);
            }
        }
    }
}
