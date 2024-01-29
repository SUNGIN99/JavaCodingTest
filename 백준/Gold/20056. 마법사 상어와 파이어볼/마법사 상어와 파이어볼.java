import java.util.*;
import java.io.*;

public class Main {

    public static class FireBallBox{
        int row, col;

        ArrayList<FireBall> fireBalls;

        FireBallBox (int r, int c){
            this.row = r;
            this.col = c;
            this.fireBalls = new ArrayList<>();
        }

        void fireBallInput(FireBall fb){
            fireBalls.add(fb);
        }
    }

    public static class FireBall{
        int row, col, mass, speed, direction;

        FireBall(int r, int c, int m, int s, int d){
            this.row = r;
            this.col = c;
            this.mass = m;
            this.speed = s;
            this.direction = d;
        }
    }

    static FireBallBox[][] matrix;
    static int N, M, k;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 격자 크기 N x N
        N = Integer.parseInt(st.nextToken());

        // 파이어 볼 개수
        M = Integer.parseInt(st.nextToken());

        // 상어 이동 명령 횟수
        k = Integer.parseInt(st.nextToken());

        // 격자 초기화
        matrix = initMatrix();

        // m 개 입력 방법 r, c, m, s, d
        // 행, 열, 질량, 속력, 방향
        for(int i =0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            matrix[r][c].fireBallInput(new FireBall(r, c, m, s, d));
        }

        for(int i = 0; i<k; i++){
            // 1. 모든 파이어볼이 자신의 방향 di로 속력 si칸 만큼 이동한다.
            fireBallForward();

            // 2. 이동이 모두 끝난 뒤, 2개 이상의 파이어볼이 있는 칸에서는 일이 일어난다.
            fireBallSplit();
        }

        int remainMasses = getAllFireBallMass();
        System.out.println(remainMasses);

    }

    static int getAllFireBallMass(){
        int allMass = 0;
        for(int i = 1; i<= N; i++){
            for(int j =1 ; j<= N; j++){
                for(FireBall fb : matrix[i][j].fireBalls){
                    allMass += fb.mass;
                }
            }
        }

        return allMass;
    }

    // 2-1) 두 개 이상 파이어볼이 있는 칸
    static void fireBallSplit(){
        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=N; j++){
                int fbSize = matrix[i][j].fireBalls.size();

                if(fbSize >= 2){
                    int fbSumM = 0, fbSumS = 0;
                    boolean allEven = true;
                    boolean allOdd = true;

                    for(FireBall fb : matrix[i][j].fireBalls){
                        if(fb.direction % 2 == 1){
                            allEven = false;
                        }
                        
                        if(fb.direction % 2 == 0){
                            allOdd = false;
                        }

                        fbSumM += fb.mass;
                        fbSumS += fb.speed;
                    }

                    matrix[i][j].fireBalls = new ArrayList<>();

                    fbSumM = fbSumM / 5;
                    fbSumS = fbSumS / fbSize;

                    if(fbSumM != 0){
                        matrix[i][j].fireBallInput(new FireBall(i, j, fbSumM, fbSumS, allEven | allOdd ? 0 : 1));
                        matrix[i][j].fireBallInput(new FireBall(i, j, fbSumM, fbSumS, allEven | allOdd ? 2 : 3));
                        matrix[i][j].fireBallInput(new FireBall(i, j, fbSumM, fbSumS, allEven | allOdd ? 4 : 5));
                        matrix[i][j].fireBallInput(new FireBall(i, j, fbSumM, fbSumS, allEven | allOdd ? 6 : 7));
                    }

                }
            }

        }
    }

    // 격자 초기화
    static FireBallBox[][] initMatrix(){
        FireBallBox[][] newMatrix = new FireBallBox[N+1][];
        for(int i = 1; i<=N; i++){
            newMatrix[i] = new FireBallBox[N+1];

            for(int j = 1; j<= N; j++){
                newMatrix[i][j] = new FireBallBox(i, j);
            }
        }

        return newMatrix;
    }

    // 1-1) 파이어볼 첫번쨰 이동
    static void fireBallForward(){
        FireBallBox[][] movedFireBall = initMatrix();

        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=N; j++){
                for(FireBall fb : matrix[i][j].fireBalls){
                    FireBall newFb = ballDirecNSpeed(fb);
                    movedFireBall[newFb.row][newFb.col].fireBallInput(newFb);
                }
            }
        }

        matrix = movedFireBall;
    };

    // 1-2) 파이어볼 속력과 방향으로 이동
    static FireBall ballDirecNSpeed(FireBall fb){
        int curRow = fb.row;
        int curCol = fb.col;

        for(int i = 0; i<fb.speed; i++){
            if(fb.direction == 0){ // 위
                curRow--;
            }else if(fb.direction == 1){ // 오른쪽 대각선 위
                curRow--;
                curCol++;
            }else if(fb.direction == 2){ // 오른쪽
                curCol++;
            }else if(fb.direction == 3){ // 오른쪽 대각선 아래
                curRow++;
                curCol++;
            }else if(fb.direction == 4){ // 아래
                curRow++;
            }else if(fb.direction == 5){ // 왼쪽 대각선 아래
                curRow++;
                curCol--;
            }else if(fb.direction == 6){ // 왼쪽
                curCol--;
            }else if(fb.direction == 7){ // 왼쪽 대각선 위
                curRow--;
                curCol--;
            }

            if(curRow < 1){
                curRow = N;
            }else if(curRow > N){
                curRow = 1;
            }

            if(curCol < 1){
                curCol = N;
            }else if(curCol > N){
                curCol = 1;
            }
        }

        return new FireBall(curRow, curCol, fb.mass, fb.speed, fb.direction);

    }



}