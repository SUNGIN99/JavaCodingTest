import java.util.*;
import java.io.*;
public class Main {

    static int n, m;
    static int[][] room;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int robotR = Integer.parseInt(st.nextToken());
        int robotC = Integer.parseInt(st.nextToken());
        int robotD = Integer.parseInt(st.nextToken());
        // 0 1 2 3 북 동 남 서

        // 0: 청소안됨, 1: 청소됨
        room = new int[n][m];
        for(int i = 0; i<n ;i++){
            st = new StringTokenizer(br.readLine());
            room[i] = new int[m];
            for(int j = 0; j<m; j++){
                room[i][j] = Integer.parseInt(st.nextToken());
            }
            //System.out.println(Arrays.toString(room[i]));
        }

        //1) 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
        //2) 현재 칸의 주변 칸 중 청소되지 않은 빈 칸이 없는 경우,
        //  2-1) 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
        //  2-2) 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
        //3) 현재 칸의 주변 칸 중 청소되지 않은 빈 칸이 있는 경우,
        //  3-1) 반시계 방향으로 회전한다.
        //  3-2) 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
        //  3-3) 1) 으로 돌아간다.

        int cleaned = 0;
        while(true){
            //System.out.println("r: " + robotR + ", c: " + robotC + ", d: " + robotD + ", bb: " + cleaned);
            if(room[robotR][robotC] == 0){
                room[robotR][robotC] = -1;
                cleaned++;
            }

            if(checkRound(robotR, robotC)){
                int[] goBack = checkBack(robotR, robotC, robotD);
                if(goBack[0] == -1){
                    break;
                }else{
                    robotR = goBack[0];
                    robotC = goBack[1];
                }
            }else{
                // 0 1 2 3 북 동 남 서

                robotD = robotD - 1 < 0 ? 3 : robotD - 1;
                //System.out.println("turned: " + robotD);
                // 앞쪽 칸이 청소되지 않은 빈 칸인지 확인
                if(checkFront(robotR, robotC, robotD)){
                    // 청소 안 되었다면 앞으로 전진
                    if(robotD == 0){ // 북
                        robotR--;
                    }else if(robotD == 1){ // 동
                        robotC++;
                    }else if(robotD == 2){ // 남
                        robotR++;
                    }else if(robotD == 3){ // 서
                        robotC--;
                    }
                }

                    // 벽쪽은 청소할 수 없으니 다시 90도 회전
                    // 청소한 빈칸이라면, 다시 반시계방향으로 90도 회전
                    // 벽쪽으로 청소할 수 없거나, 청소가 된 방일경우 90도 회전

            }
            /*for(int i = 0; i<n ;i++){
                System.out.println(Arrays.toString(room[i]));
            }
            System.out.println("---");*/
        }
        System.out.println(cleaned);
        /*for(int i = 0; i<n ;i++){
            System.out.println(Arrays.toString(room[i]));
        }*/

    }

    static boolean checkFront(int r, int c, int d){
        // 0: 청소안됨, 1: 청소됨
        if(d == 0 && room[r-1][c] == 0){ // 북
            return true;
        }else if(d == 1 && room[r][c+1] == 0){ // 동
            return true;
        }else if(d == 2 && room[r+1][c] == 0){ // 남
            return true;
        }else if(d == 3 && room[r][c-1] == 0){ // 서
            return true;
        }

        return false;
    }

    static int[] checkBack(int r, int c, int d){
        if(d == 0 && room[r+1][c] != 1){ // 북
            return new int[]{r+1, c, d};
        }else if(d == 1 && room[r][c-1] != 1){ // 동
            return new int[]{r, c-1, d};
        }else if(d == 2 && room[r-1][c] != 1){ // 남
            return new int[]{r-1, c, d};
        }else if(d == 3 && room[r][c+1] != 1){ // 서
            return new int[]{r, c+1, d};
        }

        return new int[]{-1, -1, -1};
    }

    static boolean checkRound(int r, int c){
        int uncleaned = 0;
        if(room[r+1][c] == 0){
            uncleaned++;
        }
        if(room[r-1][c] == 0){
            uncleaned++;
        }
        if(room[r][c+1] == 0){
            uncleaned++;
        }
        if(room[r][c-1] == 0){
            uncleaned++;
        }

        if(uncleaned == 0){
            return true;
        }
        return false;
    }


}
