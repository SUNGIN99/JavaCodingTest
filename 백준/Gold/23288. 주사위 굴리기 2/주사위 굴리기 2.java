import java.util.*;
import java.io.*;

public class Main{


    static class Dice{
        int top, down;
        int left, right;
        int front, back;
        int direction;
        int x, y;

        public String toString(){
            return "x: " + x + ", y: " + y + ", dir: " + direction + " = "+
                    "(top: " + top + ", left: " + left  +
                    ", front: " + front+ ", back: " + back + ", right: " + right + ", down: " + down + ")";
        }

        Dice(int t, int d, int l, int r, int f, int b){
            this.top = t;
            this.down = d;
            this.left = l;
            this.right = r;
            this.front = f;
            this.back = b;
            this.direction = 1; // 1: 동, 2: 남, 3: 서, 4: 북
            this.x = 0;
            this.y = 0;
        }

        void turn(int boardNum){
            if(down > boardNum){ // 90도 시계 방향
                direction = direction + 1 == 5 ? 1: direction + 1;
            }else if(down < boardNum){ // 90도 반 시계방향
                direction = direction - 1 == 0 ? 4: direction - 1;
            }else{ // 회전 없음
            }
        }

        void oppositeDir(){
            if(direction == 1){
                direction = 3;
            }else if(direction == 2){
                direction = 4;
            }else if(direction == 3){
                direction = 1;
            }else if(direction == 4){
                direction = 2;
            }
        }

        void turnEast(){
            int temp = top;
            top = left;
            left = down;
            down = right;
            right = temp;
        }

        void turnWest(){
            int temp = top;
            top = right;
            right = down;
            down = left;
            left = temp;
        }

        void turnNorth(){
            int temp = top;
            top = front;
            front = down;
            down = back;
            back = temp;
        }

        void turnSouth(){
            int temp = top;
            top = back;
            back = down;
            down = front;
            front = temp;
        }

    }

    static int[][] board;
    static boolean[][] visited;
    static int n, m, k;
    static Dice dice;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n][];

        for(int i = 0; i<n; i++){
            board[i] = new int[m];
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 위, 아래, 왼, 오, 앞, 뒤
        dice = new Dice(1, 6, 4, 3, 5, 2);

        int i = 0, sum = 0;
        while(i< k){
            diceMove();
            sum += getScore();
            dice.turn(board[dice.x][dice.y]);
            //System.out.println(dice);
            i++;
        }
        System.out.println(sum);

    }

    static int getScore(){
        int x = dice.x;
        int y = dice.y;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        initVisited();
        int pathValue = board[x][y];
        visited[x][y] = true;
        int sum = pathValue;
        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int curX = curr[0], curY = curr[1];
            //System.out.println(curX + ", " + curY);

            if(curX + 1 < n && !visited[curX+1][curY] && board[curX+1][curY] == pathValue){
                queue.add(new int[]{curX+1, curY});
                visited[curX+1][curY] = true;
                sum += pathValue;
            }

            if(curX - 1 >= 0 && !visited[curX-1][curY] && board[curX-1][curY] == pathValue){
                queue.add(new int[]{curX-1, curY});
                visited[curX-1][curY] = true;
                sum += pathValue;
            }

            if(curY + 1 < m && !visited[curX][curY+1] && board[curX][curY+1] == pathValue){
                queue.add(new int[]{curX, curY+1});
                visited[curX][curY+1] = true;
                sum += pathValue;
            }

            if(curY - 1 >= 0 && !visited[curX][curY-1] && board[curX][curY-1] == pathValue){
                queue.add(new int[]{curX, curY-1});
                visited[curX][curY-1] = true;
                sum += pathValue;
            }

        }

        /*for(int i = 0; i<n; i++){
            System.out.println(Arrays.toString(visited[i]));
        }
        System.out.println();*/

        return sum;
    }

    static void diceMove(){
        int dir = dice.direction;
        int x = dice.x;
        int y = dice.y;
        if(dir == 1){ // 동
            if(y + 1 < m){
                dice.turnEast();
                dice.y++;
            }else{
                dice.oppositeDir();
                diceMove();
            }
        }else if(dir == 2){ // 남
            if(x + 1 < n){
                dice.turnSouth();
                dice.x++;
            }else{
                dice.oppositeDir();
                diceMove();
            }
        }else if(dir == 3){ // 서
            if(y - 1 >= 0){
                dice.turnWest();
                dice.y--;
            }else{
                dice.oppositeDir();
                diceMove();
            }
        }else if(dir == 4){ // 북
            if(x - 1 >= 0){
                dice.turnNorth();
                dice.x--;
            }else{
                dice.oppositeDir();
                diceMove();
            }
        }
    }


    static void initVisited(){
        visited = new boolean[n][];
        for(int i = 0; i<n; i++){
            visited[i] = new boolean[m];
        }
    }


}