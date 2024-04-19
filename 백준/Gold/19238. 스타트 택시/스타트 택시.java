import java.util.*;
import java.io.*;

public class Main{

    static class Passenger implements Comparable<Passenger>{
        int passenger;
        int x, y;
        int fuel;

        Passenger(int num, int x, int y, int f){
            this.passenger= num;
            this.x = x;
            this.y = y;
            this.fuel = f;
        }

        public int compareTo(Passenger p){
            if(this.fuel == p.fuel){
                if(this.x == p.x){
                    return this.y - p.y;
                }else{
                    return this.x - p.x;
                }
            }else{
                return this.fuel - p.fuel;
            }
        }

        public String toString(){
            return passenger + " : " + "("+ x+ ", "+ y + ")-" +fuel;
        }

    }
    static int[][] map;
    static int[][] start;
    static HashMap<Integer, Integer>[][] target;
    static int taxiX, taxiY;
    static int n, m;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int fuel = Integer.parseInt(st.nextToken());

        map = new int[n][];
        start = new int[n][];
        target = new HashMap[n][];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            map[i] = new int[n];
            start[i] = new int[n];
            target[i] = new HashMap[n];
            for(int j = 0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                target[i][j] = new HashMap<>();
            }
        }

        st = new StringTokenizer(br.readLine());
        taxiX = Integer.parseInt(st.nextToken()) - 1;
        taxiY = Integer.parseInt(st.nextToken()) - 1;

        int sx, sy, tx, ty;
        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            sx = Integer.parseInt(st.nextToken()) - 1;
            sy = Integer.parseInt(st.nextToken()) - 1;
            tx = Integer.parseInt(st.nextToken()) - 1;
            ty = Integer.parseInt(st.nextToken()) - 1;
            start[sx][sy] = i+1;
            target[tx][ty].put(i+1, i+1);
        }

        int pass = 0;
        while(pass < m){
            Passenger take = findPassenger(fuel);
            //System.out.println(take);
            if(take == null){
                break;
            }

            fuel -= take.fuel;
            taxiX = take.x;
            taxiY = take.y;
            start[taxiX][taxiY] = 0;

            Passenger dest = findDestination(take.passenger, fuel);
            //System.out.println(dest);
            if(dest == null){
                break;
            }
            fuel += dest.fuel;
            //System.out.println(fuel);
            //System.out.println();
            taxiX = dest.x;
            taxiY = dest.y;
            target[taxiX][taxiY].remove(dest.passenger);
            pass++;

        }

        if(pass == m){
            System.out.println(fuel);
        }else{
            System.out.println(-1);
        }



    }

    static Passenger findDestination(int passenger, int fuel){
        int[][] visited = new int[n][];
        for(int i = 0; i<n; i++){
            visited[i] = new int[n];
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{taxiX, taxiY, 0});
        while(!queue.isEmpty()){
            int[] path = queue.poll();
            int curX = path[0];
            int curY = path[1];
            int curF = path[2];

            if(target[curX][curY].containsKey(passenger)){
                return new Passenger(passenger, curX, curY, curF);
            }else{
                if(curX-1 >= 0 && map[curX-1][curY] == 0 && curF+1 <= fuel
                        && (visited[curX-1][curY] == 0 || visited[curX-1][curY] > curF+1)){

                    visited[curX-1][curY] = curF+1;
                    queue.add(new int[]{curX-1, curY, curF+1});
                }

                if(curX+1 < n && map[curX+1][curY] == 0 && curF+1 <= fuel
                        && (visited[curX+1][curY] == 0 || visited[curX+1][curY] > curF+1)){
                    visited[curX+1][curY] = curF+1;
                    queue.add(new int[]{curX+1, curY, curF+1});
                }

                if(curY-1 >= 0 && map[curX][curY-1] == 0 && curF+1 <= fuel
                        && (visited[curX][curY-1] == 0 || visited[curX][curY-1] > curF+1)){
                    visited[curX][curY-1] = curF+1;
                    queue.add(new int[]{curX, curY-1, curF+1});
                }

                if(curY+1 < n && map[curX][curY+1] == 0 && curF+1 <= fuel
                        && (visited[curX][curY+1] == 0 || visited[curX][curY+1] > curF+1)){
                    visited[curX][curY+1] = curF+1;
                    queue.add(new int[]{curX, curY+1, curF+1});
                }
            }
        }

        return null;
    }

    static Passenger findPassenger(int fuel){
        int[][] visited = new int[n][];
        for(int i = 0; i<n; i++){
            visited[i] = new int[n];
        }

        PriorityQueue<Passenger> findPassenger = new PriorityQueue<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{taxiX, taxiY, 0});
        while(!queue.isEmpty()){
            int[] path = queue.poll();
            int curX = path[0];
            int curY = path[1];
            int curF = path[2];


            if(start[curX][curY] != 0){
                findPassenger.add(new Passenger(start[curX][curY], curX, curY, curF));
            }else{
                if(curX-1 >= 0 && map[curX-1][curY] == 0 && curF+1 <= fuel
                        && (visited[curX-1][curY] == 0 || visited[curX-1][curY] > curF+1)){

                    visited[curX-1][curY] = curF+1;
                    queue.add(new int[]{curX-1, curY, curF+1});
                }

                if(curX+1 < n && map[curX+1][curY] == 0 && curF+1 <= fuel
                        && (visited[curX+1][curY] == 0 || visited[curX+1][curY] > curF+1)){
                    visited[curX+1][curY] = curF+1;
                    queue.add(new int[]{curX+1, curY, curF+1});
                }

                if(curY-1 >= 0 && map[curX][curY-1] == 0 && curF+1 <= fuel
                        && (visited[curX][curY-1] == 0 || visited[curX][curY-1] > curF+1)){
                    visited[curX][curY-1] = curF+1;
                    queue.add(new int[]{curX, curY-1, curF+1});
                }

                if(curY+1 < n && map[curX][curY+1] == 0 && curF+1 <= fuel
                        && (visited[curX][curY+1] == 0 || visited[curX][curY+1] > curF+1)){
                    visited[curX][curY+1] = curF+1;
                    queue.add(new int[]{curX, curY+1, curF+1});
                }
            }

        }

        if(findPassenger.isEmpty()){
            return null;
        }
        return findPassenger.poll();
    }



}