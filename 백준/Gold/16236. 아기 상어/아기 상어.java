import java.util.*;
import java.io.*;
public class Main {

    static class Location implements Comparable<Location>{
        int x;
        int y;

        int dist;

        Location(int x, int y, int d){
            this.x = x;
            this.y = y;
            this.dist = d;
        }

        public int compareTo(Location l){
            if(this.dist == l.dist){
                if(this.x == l.x){
                    return this.y - l.y;
                }else{
                    return this.x - l.x;
                }
            }else{
                return this.dist - l.dist;
            }
        }

        public String toString(){
            return "(" + x + ", " + y + ", " + dist + ")";
        }
    }

    static int n;
    static int [][] fishes;
    static int sharkSize = 2;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        fishes = new int[n][n];
        int sharkR = 0, sharkC = 0;
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            fishes[i] = new int[n];
            for(int j = 0; j<n; j++){
                int num = Integer.parseInt(st.nextToken());
                if(num == 9){
                    sharkR = i;
                    sharkC = j;
                    continue;
                }
                fishes[i][j] = num;
            }
        }

        Location fish;
        int sharkAte = 0;
        int times = 0;
        while(true){
            fish = checkEatable(sharkR, sharkC);
            if(fish.x == sharkR && fish.y == sharkC){
                break;
            }else{
                sharkR = fish.x;
                sharkC = fish.y;
                sharkAte ++;
                times += fish.dist;

                if(sharkAte == sharkSize){
                    sharkAte = 0;
                    sharkSize++;
                }

                fishes[sharkR][sharkC] = 0;
            }
        }

        System.out.println(times);

    }

    static Location checkEatable(int sharkR, int sharkC){
        Queue<Location> queue = new LinkedList<>();

        queue.add(new Location(sharkR, sharkC, 0));

        Location[][] visited = new Location[n][];
        for(int i = 0; i<n; i++){
            visited[i] = new Location[n];
        }

        PriorityQueue<Location> eatable = new PriorityQueue<>();
        while(!queue.isEmpty()){
            Location location = queue.poll();
            int x = location.x;
            int y = location.y;
            int dist = location.dist;
            Location visit;
            if(x > 0){ // up
                visit = new Location(x-1, y, dist+1);
                if(visited[x-1][y] != null){
                    // 0이거나 상어와 같은 크기의 물고기가 먼저 순서에 와야하는데, 상어보다 작은 크기의 물고기를 찾는 조건문이 나와서 무한루프 돌음
                    // 그래서 순서를 앞뒤로 바꿔줌. 2,1 -> 1, 2
                    if(visited[x-1][y].dist > visit.dist && (fishes[x-1][y] == sharkSize || fishes[x-1][y] == 0)){  //1
                        queue.add(visit);
                    }else if(visited[x-1][y].dist > visit.dist && fishes[x-1][y] < sharkSize){ //2
                        eatable.add(visit);
                    }
                }else{
                    if(fishes[x-1][y] == sharkSize || fishes[x-1][y] == 0){ // 크기가 같은 물고기라면
                        queue.add(visit);
                    }else if(fishes[x-1][y] < sharkSize){ // 상어가 먹을 수 있는 물고기라면
                        eatable.add(visit);
                    }
                }
                visited[x-1][y] = visit;
            }

            if(y > 0){ // left
                visit = new Location(x, y-1, dist+1);
                if(visited[x][y-1] != null){
                    if(visited[x][y-1].dist > visit.dist && (fishes[x][y-1] == sharkSize || fishes[x][y-1] == 0)){
                        queue.add(visit);
                    }else if(visited[x][y-1].dist > visit.dist && fishes[x][y-1] < sharkSize){
                        eatable.add(visit);
                    }
                }else{
                    if(fishes[x][y-1] == sharkSize || fishes[x][y-1] == 0){ // 크기가 같은 물고기라면
                        queue.add(visit);
                    }else if(fishes[x][y-1] < sharkSize){ // 상어가 먹을 수 있는 물고기라면
                        eatable.add(visit);
                    }
                }
                visited[x][y-1] = visit;
            }

            if(y<n-1){ //right
                visit = new Location(x, y+1, dist+1);
                if(visited[x][y+1] != null){
                    if(visited[x][y+1].dist > visit.dist && (fishes[x][y+1] == sharkSize || fishes[x][y+1] == 0)){
                        queue.add(visit);
                    }else if(visited[x][y+1].dist > visit.dist && fishes[x][y+1] < sharkSize){
                        eatable.add(visit);
                    }
                }else{
                    if(fishes[x][y+1] == sharkSize || fishes[x][y+1] == 0){ // 크기가 같은 물고기라면
                        queue.add(visit);
                    }else if(fishes[x][y+1] < sharkSize){ // 상어가 먹을 수 있는 물고기라면
                        eatable.add(visit);
                    }
                }
                visited[x][y+1] = visit;
            }

            if(x < n-1){ // down
                visit = new Location(x+1, y, dist+1);
                if(visited[x+1][y] != null){
                    if(visited[x+1][y].dist > visit.dist && (fishes[x+1][y] == sharkSize || fishes[x+1][y] == 0)){
                        queue.add(visit);
                    }else if(visited[x+1][y].dist > visit.dist && fishes[x+1][y] < sharkSize){
                        eatable.add(visit);
                    }
                }else{
                    if(fishes[x+1][y] == sharkSize || fishes[x+1][y] == 0){ // 크기가 같은 물고기라면
                        queue.add(visit);
                    }else if(fishes[x+1][y] < sharkSize){ // 상어가 먹을 수 있는 물고기라면
                        eatable.add(visit);
                    }
                }
                visited[x+1][y] = visit;
            }
        }

        //System.out.println(eatable);

        if(!eatable.isEmpty()){
            return eatable.poll();
        }else{
            return new Location(sharkR, sharkC, 0);
        }
    }
}
