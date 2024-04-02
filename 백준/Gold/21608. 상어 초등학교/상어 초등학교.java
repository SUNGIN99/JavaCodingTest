import java.util.*;
import java.io.*;
public class Main {

    static int n;
    static int [][] sharks = new int[n+1][n+1];
    static int [][] seatPrefer;
    static ArrayList<Integer[]> checkSeat;
    static int[][] bestFriends;
    static int max;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        sharks = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            sharks[i] = new int[n + 1];
        }

        ArrayList<Integer> seatSeq = new ArrayList<>();
        bestFriends = new int[n*n + 1][n*n + 1];
        for (int i = 0; i < n * n; i++) {
            st = new StringTokenizer(br.readLine());
            int shark = Integer.parseInt(st.nextToken());
            bestFriends[shark] = new int[n*n+1];
            seatSeq.add(shark);
            for (int j = 1; j < 5; j++) {
                int friend = Integer.parseInt(st.nextToken());
                bestFriends[shark][friend] = 1;
            }
            //System.out.println(Arrays.toString(bestFriends[shark]));
        }


        //1) 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
        //2) 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
        //3) 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
        for(Integer shark : seatSeq){
            checkSeat(shark);
        }

        for(int i = 1; i<= n; i++){
            //System.out.println(Arrays.toString(sharks[i]));
        }

        int result = 0;
        for(int i = 1; i<=n; i++){
            for(int j =1 ; j<= n; j++){
                result += getScore(sharks[i][j], i, j);
            }
        }
        System.out.println(result);
    }

    static void checkSeat(int shark){
        int friendCount = 0, blankCount = 0;
        int tempC = 0, tempB = 0;
        int row = 0, col = 0;
        for(int i = 1; i<=n; i++){
            for(int j = 1; j <= n; j++){
                if(sharks[i][j] != 0){
                    continue;
                }
                tempC = checkFriendCount(shark, i, j);
                 if(tempC > friendCount){
                     friendCount = tempC;
                     row = i;
                     col = j;
                     blankCount = checkBlankSeat(i, j);
                     //System.out.println("1) shark : " + shark + ", fc : " + tempC + ", bc : " + tempB + ", row : " + row + ", col : " + col);
                 }else if(tempC == friendCount){
                     tempB = checkBlankSeat(i, j);
                     if(tempB > blankCount){
                         blankCount = tempB;
                         row = i;
                         col = j;
                         //System.out.println("2) shark : " + shark + ", fc : " + tempC + ", bc : " + tempB + ", row : " + row + ", col : " + col);
                     }else if(tempB == blankCount){
                         if(row == 0 && col == 0){
                             row = i;
                             col = j;
                             //System.out.println("3) shark : " + shark + ", fc : " + tempC + ", bc : " + tempB + ", row : " + row + ", col : " + col);
                         }else {
                             if(row > i){
                                 row = i;
                                 //System.out.println("4) shark : " + shark + ", fc : " + tempC + ", bc : " + tempB + ", row : " + row + ", col : " + col);
                             }
                             else if (row == i){
                                 if(col > j){
                                     col = j;
                                     //System.out.println("5) shark : " + shark + ", fc : " + tempC + ", bc : " + tempB + ", row : " + row + ", col : " + col);
                                 }
                             }
                         }
                     }
                 }
            }
        }

        sharks[row][col] = shark;
    }

    static int getScore(int shark, int r, int c){
        int scoreCount = 0;
        if(r<n){
            if(bestFriends[shark][sharks[r+1][c]] == 1){
                scoreCount++;
            }
        }
        if(r > 1){
            if(bestFriends[shark][sharks[r-1][c]] == 1){
                scoreCount++;
            }

        }
        if(c<n){
            if(bestFriends[shark][sharks[r][c+1]] == 1){
                scoreCount++;
            }
        }
        if(c > 1){
            if(bestFriends[shark][sharks[r][c-1]] == 1){
                scoreCount++;
            }
        }

        if(scoreCount == 0){
            return 0;
        }else if(scoreCount == 1){
            return 1;
        }else if(scoreCount == 2){
            return 10;
        }else if(scoreCount == 3){
            return 100;
        }else {
            return 1000;
        }
    }

    static int checkBlankSeat(int r, int c){
        int blankCount = 0;
        if (r < n){
            if(sharks[r+1][c] == 0){
                blankCount++;
            }
        }

        if(r > 1){
            if(sharks[r-1][c] == 0){
                blankCount++;
            }
        }

        if(c < n){
            if(sharks[r][c+1] == 0){
                blankCount++;
            }
        }

        if(c > 1){
            if(sharks[r][c-1] == 0){
                blankCount++;
            }
        }
        return blankCount;
    }

    static int checkFriendCount(int shark, int r, int c){
        int count = 0;
        int friendShark;
        if(r < n){
            friendShark = sharks[r+1][c];
            if(bestFriends[shark][friendShark] == 1){
                count++;
            }
        }

        if(r > 1){
            friendShark = sharks[r-1][c];
            if(bestFriends[shark][friendShark] == 1){
                count++;
            }
        }

        if(c < n){
            friendShark = sharks[r][c+1];
            if(bestFriends[shark][friendShark] == 1){
                count++;
            }
        }

        if(c > 1){
            friendShark = sharks[r][c-1];
            if(bestFriends[shark][friendShark] == 1){
                count++;
            }
        }

        return count;
    }

}
