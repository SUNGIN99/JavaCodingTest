import java.util.*;
import java.io.*;
public class Main {

    static int[][] apple;
    static int[][] snake;
    static char[] turn;
    static int n, snakeR, snakeC,snakeD;
    static Deque<int[]> currSnake;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 보드 크기
        n = Integer.parseInt(st.nextToken());
        apple = new int[n][n];
        snake = new int[n][];
        turn = new char[10001];

        for(int i = 0; i<n; i++){
            apple[i] = new int[n];
            snake[i] = new int[n];
        }

        // 사과 위치
        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        for(int i = 0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            apple[row-1][col-1] = 1;
        }

        // 머리 돌기 정보
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<=10000; i++){
            turn[i] = 'X';
        }

        int l = Integer.parseInt(st.nextToken());
        for(int i = 0; i<l; i++){
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char leftOrRight = st.nextToken().toCharArray()[0];
            turn[time] = leftOrRight;
        }

        currSnake = new LinkedList<>();
        snakeR = 0;
        snakeC = 0;
        snakeD = 1; // 1 : 오른쪽, 2: 아래, 3: 왼쪽, 4: 위
        snake[0][0] = 1;
        currSnake.add(new int[]{0,0});
        int time = 0;
        while(true){
            time++;
            //snake[snakeR][snakeC] = 1;
            if(goStraight()){ // 1. 뱀이 한칸 이동
                snake[snakeR][snakeC] = 1; // 이동한 칸 채우기
                currSnake.add(new int[]{snakeR, snakeC}); // 몸길이 늘리기

                if(apple[snakeR][snakeC] == 1){ // 사과가 있다면 몸 길이 그대로 유지
                    apple[snakeR][snakeC] = 0;
                }else{ // 사과가 없다면 꼬리 한 칸 줄이기
                    int[] snakeTail = currSnake.pollFirst();
                    int tailR = snakeTail[0];
                    int tailC = snakeTail[1];
                    snake[tailR][tailC] = 0;
                }
            }else{
                break;
            }

            if(turn[time] == 'L'){
                snakeD = snakeD - 1 == 0 ? 4 : snakeD - 1;
            }else if(turn[time] == 'D'){
                snakeD = snakeD + 1 == 5 ? 1 : snakeD + 1;
            }
            //System.out.println(snakeR + ", " + snakeC + ", " + snakeD);
            /*for(int i = 0; i<n; i++){
                System.out.println(Arrays.toString(snake[i]));
            }
            System.out.println("---");*/
        }

        System.out.println(time);

    }

    static boolean goStraight(){
        if(snakeD == 1){ // east : ->
            if(snakeC < n-1 && snake[snakeR][snakeC+1] != 1){ // 벽이 아니고, 내 몸이 위치한 칸이 아닐 때
                snakeC++;
                return true;
            }
        }else if(snakeD == 2){ // south v
            if(snakeR < n-1 && snake[snakeR+1][snakeC] != 1){
                snakeR++;
                return true;
            }
        }else if(snakeD == 3){ // west <-
            if(snakeC > 0 && snake[snakeR][snakeC-1] != 1){
                snakeC--;
                return true;
            }
        }else{ // north ^
            if(snakeR > 0 && snake[snakeR-1][snakeC] != 1){
                snakeR--;
                return true;
            }
        }
        return false;
    }
}
