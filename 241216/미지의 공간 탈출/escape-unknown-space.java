import java.io.*;
import java.util.*;

public class Main {

   /*
   8 3 2
4 0 0 0 0 0 0 0
0 1 1 1 1 1 0 0
0 1 3 3 3 1 0 1
0 1 3 3 3 1 0 1
0 1 3 3 3 0 0 0
0 1 1 1 1 1 1 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 1 1
1 1 1
0 0 0
0 1 1
1 1 1
1 0 1
1 1 1
0 0 1
1 0 0
1 0 1
0 0 0
1 0 0
1 1 1
2 0 0
0 1 0
0 0 0
0 7 1 14
6 3 3 2
    */

    static int n, m, f;
    static int[][] miji;
    static int[][] time;
    static int[][] visited;
    static Map<String, Integer> timeDanger = new HashMap<>();
    static int x6 = -1, y6 = -1;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        f = Integer.parseInt(st.nextToken());
        int goalx = 0, goaly = 0;

        // 미지의 영역
        miji = new int[n][];
        visited = new int[n][];
        for(int i = 0; i<n; i++){
            miji[i] = new int[n];
            visited[i] = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                miji[i][j] = Integer.parseInt(st.nextToken());
                if(miji[i][j] == 3 && x6 == -1 && y6 == -1){
                    x6 = i; y6 = j;
                }

                if(miji[i][j] == 3 || miji[i][j] == 1){
                    visited[i][j] = -1;
                }

                if(miji[i][j] == 4){
                    goalx = i;
                    goaly = j;
                }
            }
            //System.out.println(Arrays.toString(miji[i]));
        }

        // 타임머신 영역
        time = new int[m*3][];
        int[][] bfs = new int[m*3][];
        for(int i = 0; i<m*3; i++){
            time[i] = new int[m*3];
            bfs[i] = new int[m*3];
        }

        // 동서남북위
        for(int i = 0; i<m; i++){
            st= new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                time[m*2 - j - 1][m*2 + i] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i<m; i++){
            st= new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                time[m+j][m - i - 1] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i<m; i++){
            st= new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                time[m*2 + i][m+j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i<m; i++){
            st= new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                time[m - i - 1][m*2 - j - 1] = Integer.parseInt(st.nextToken());
            }
        }

        int tx = 0, ty = 0;
        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                time[m+i][m+j] = Integer.parseInt(st.nextToken());
                if(time[m+i][m+j] == 2){
                    tx = m + i;
                    ty = m + j;
                }
            }
        }

        /*for(int i = 0;i<m*3; i++){
            System.out.println(Arrays.toString(time[i]));
        }*/

        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(tx, ty, 0));
        bfs[tx][ty] = -1;

        while(!queue.isEmpty()){
            Location curr = queue.poll();
            int curx = curr.x;
            int cury = curr.y;
            int curd = curr.far;

            int upx = curx - 1;
            int downx = curx + 1;
            int lefty = cury - 1;
            int righty = cury + 1;

            int tempx, tempy;

            int range;
            //System.out.println("{" + curx + ", " + cury + "}");
            range = isValid(upx, cury);// 위
            if(range != -1){
                tempx = upx;
                tempy = cury;
                if(range == 1){ // 서 -> 북
                    tempx = cury;
                    tempy = curx;
                }
                if (range == 2){ // 동 -> 북
                    tempx = m * 3 - 1 - cury; // m - 1 - (m*3 - 1 - cury);
                    tempy = m * 2 - 1; // m * 3 - 1 - curx;
                }
                if(time[tempx][tempy] != 1 && (bfs[tempx][tempy] == 0 || bfs[tempx][tempy] > curd + 1)){
                    bfs[tempx][tempy] = curd + 1;
                    queue.add(new Location(tempx, tempy, curd+1));
                    //System.out.println(range + " : from {" + curx + ", " + cury + "} to {"+tempx + ", " + tempy +"}" );
                }
            }

            range = isValid(downx, cury);// 아래
            if(range != -1){
                tempx = downx;
                tempy = cury;
                if(range == 3){ // 서 -> 남
                    tempx = m * 3 - 1 - cury;
                    tempy = m;
                }
                if (range == 4){ // 동 -> 남
                    tempx = cury;
                    tempy = curx;
                }
                if(time[tempx][tempy] != 1 && (bfs[tempx][tempy] == 0 || bfs[tempx][tempy] > curd + 1)){
                    bfs[tempx][tempy] = curd + 1;
                    queue.add(new Location(tempx, tempy, curd+1));
                    //System.out.println(range + " : from {" + curx + ", " + cury + "} to {"+tempx + ", " + tempy +"}" );
                }
            }

            range = isValid(curx, lefty);// 왼쪽
            if(range != -1){
                tempx = curx;
                tempy = lefty;
                if(range == 1){ // 북 -> 서
                    tempx = cury;
                    tempy = curx;
                }
                if (range == 3){ // 남 -> 서
                    tempx = m * 2 - 1;
                    tempy = m * 3 - 1 - curx;
                }
                if(time[tempx][tempy] != 1 && (bfs[tempx][tempy] == 0 || bfs[tempx][tempy] > curd + 1)){
                    bfs[tempx][tempy] = curd + 1;
                    queue.add(new Location(tempx, tempy, curd+1));
                    //System.out.println(range + " : from {" + curx + ", " + cury + "} to {"+tempx + ", " + tempy +"}" );
                }
            }

            range = isValid(curx, righty);// 오른쪽
            if(range != -1){
                tempx = curx;
                tempy = righty;
                if(range == 2){ // 북 -> 동
                    tempx = m;
                    tempy = m * 3 - 1 - curx;
                }
                if (range == 4){ // 남 -> 동
                    tempx = cury;
                    tempy = curx;
                }
                if(time[tempx][tempy] != 1 && (bfs[tempx][tempy] == 0 || bfs[tempx][tempy] > curd + 1)){
                    bfs[tempx][tempy] = curd + 1;
                    queue.add(new Location(tempx, tempy, curd+1));
                    //System.out.println(range + " : from {" + curx + ", " + cury + "} to {"+tempx + ", " + tempy +"}" );
                }
            }
        }

       /* System.out.println();
        for(int i = 0;i<m*3; i++){
            System.out.println(Arrays.toString(bfs[i]));
        }*/

        // x6, y6
        queue = new LinkedList<>();
        for(int i = 0; i<m; i++){ // 북
            if(miji[x6 - 1][y6 + i] != 1 && bfs[0][m+i] != 0){
                queue.add(new Location(x6 - 1, y6 + i, bfs[0][m+i] + 1));
                visited[x6-1][y6+i] = bfs[0][m+i] +1;
            }
        }
        for(int i = 0; i<m; i++){ // 남
            if(miji[x6 + m][y6 + i] != 1 && bfs[m*3-1][m+i] != 0){
                queue.add(new Location(x6 + m, y6 + i, bfs[m*3-1][m+i] + 1));
                visited[x6+m][y6+i] = bfs[m*3-1][m+i] +1;
            }
        }
        for(int i = 0; i<m; i++){ // 동
            if(miji[x6 + i][y6 + m] != 1 && bfs[m+i][m*3-1] != 0){
                queue.add(new Location(x6 + i, y6 + m, bfs[m+i][m*3-1] + 1));
                visited[x6+i][y6+m] = bfs[m+i][m*3-1] +1;
            }
        }
        for(int i = 0; i<m; i++){ // 서
            if(miji[x6 + i][y6 - 1] != 1 && bfs[m+i][0] != 0){
                queue.add(new Location(x6 + i, y6 - 1, bfs[m+i][0] + 1));
                visited[x6+i][y6-1] = bfs[m+i][0] +1;
            }
        }
        /*System.out.println();
        for(int i = 0;i<n; i++){
            System.out.println(Arrays.toString(visited[i]));
        }*/

        /*
        0 7 1 14
        6 3 3 2
         */
        for(int i = 0; i<f; i++){ // r c d (0동 1서 2남 3북) t
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int value = 0;
            if(d == 0){ // 동
                while(c < n && miji[r][c] == 0){
                    int time = timeDanger.getOrDefault(r+","+c, Integer.MAX_VALUE);
                    time = Math.min(time, value);
                    timeDanger.put(r+","+c, time);
                    value += v;
                    c++;
                }
            }else if(d == 1){ // 서
                while(c >= 0 && miji[r][c] == 0){
                    int time = timeDanger.getOrDefault(r+","+c, Integer.MAX_VALUE);
                    time = Math.min(time, value);
                    timeDanger.put(r+","+c, time);
                    value += v;
                    c--;
                }
            }else if(d == 2){ // 남
                while(r < n && miji[r][c] == 0){
                    int time = timeDanger.getOrDefault(r+","+c, Integer.MAX_VALUE);
                    time = Math.min(time, value);
                    timeDanger.put(r+","+c, time);
                    value += v;
                    r++;
                }
            }else{ // d == 3 //북
                while(r >= 0 && miji[r][c] == 0){
                    int time = timeDanger.getOrDefault(r+","+c, Integer.MAX_VALUE);
                    time = Math.min(time, value);
                    timeDanger.put(r+","+c, time);
                    value += v;
                    r--;
                }
            }
            //System.out.println("1");
        }

        //System.out.println(timeDanger);
        //System.out.println(queue);

        while(!queue.isEmpty()){
            Location curr = queue.poll();
            //System.out.println(curr);
            int curx = curr.x;
            int cury = curr.y;
            int curd = curr.far;

            int upx = curx - 1;
            int downx = curx + 1;
            int lefty = cury - 1;
            int righty = cury + 1;

            int range;
            range = isValid2(upx, cury, curd + 1); // 위
            if(range != -1){
                if(visited[upx][cury] == 0 || visited[upx][cury] > curd + 1){
                    visited[upx][cury] = curd + 1;
                    if(range == 0){
                        queue.add(new Location(upx, cury, curd+1));
                    }
                }
            }
            range = isValid2(downx, cury, curd + 1); // 아래
            if(range != -1){
                if(visited[downx][cury] == 0 || visited[downx][cury] > curd + 1){
                    visited[downx][cury] = curd + 1;
                    if(range == 0){
                        queue.add(new Location(downx, cury, curd+1));
                    }
                }

            }
            range = isValid2(curx, lefty, curd + 1); // 왼
            if(range != -1){
                if(visited[curx][lefty] == 0 || visited[curx][lefty] > curd + 1){
                    visited[curx][lefty] = curd + 1;
                    if(range == 0){
                        queue.add(new Location(curx, lefty, curd+1));
                    }
                }

            }
            range = isValid2(curx, righty, curd + 1); // 오
            if(range != -1){
                if(visited[curx][righty] == 0 || visited[curx][righty] > curd + 1){
                    visited[curx][righty] = curd + 1;
                    if(range == 0){
                        queue.add(new Location(curx, righty, curd+1));
                    }
                }
            }
        }

        /*System.out.println();
        for(int i = 0;i<n; i++){
            System.out.println(Arrays.toString(visited[i]));
        }*/

        System.out.println(visited[goalx][goaly] == 0 ? -1 : visited[goalx][goaly]);


    }

    static int isValid2(int x, int y, int d){
        if(x<0 || x>=n || y<0 || y>=n){
            return -1;
        }

        if(visited[x][y] == -1){
            return -1;
        }

        String key = x+","+y;
        if(timeDanger.containsKey(key) && timeDanger.get(key) <= d){
            return -1;
        }

        if(miji[x][y] == 4){
            return 1;
        }

        return 0;
    }

    static int isValid(int x, int y){
        if(x < 0 || x >= m*3 || y < 0 || y >= m*3){
            return -1;
        }
        if(time[x][y] == 1){
            return -1;
        }

        if(x < m && y < m){
            return 1; // 북서쪽
        }
        else if(x < m && y >= m*2){
            return 2; //북동쪽
        }
        else if(x >= m*2 && y < m){
            return 3; // 남서쪽
        }
        else if(x >= m*2 && y >= m*2){
            return 4; // 남동쪽
        }else{
            return 0;
        }





    }

    static class Location{
        int x, y;
        int far;

        Location(int x, int y, int f){
            this.x = x;
            this.y =y ;
            this.far = f;
        }

        public String toString(){
            return  "{"+x +", " + y + ", " + far + "}";
        }

    }

}