import java.util.*;
import java.io.*;

public class Main{

    static class Vertex implements Comparable<Vertex>{
        char from;
        char to;
        int dist;

        Vertex(char f, char t , int d){
            this.from = f;
            this.to = t;
            this.dist = d;
        }

        @Override
        public int compareTo(Vertex v){
            return this.dist - v.dist;
        }

        @Override
        public boolean equals(Object o){
            Vertex oo = (Vertex) o;
            return this.to == oo.to && this.dist == oo.dist;
        }

        @Override
        public int hashCode(){
            return Objects.hash(from + to+dist+"");
        }

        public String toString(){
            return from + "->" + to + "(" + dist + ") ";
        }
    }
    static char[][] sea;
    static int n, m;
    static Map<Character, Set<Vertex>> graph;
    static int[] parent;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        sea  = new char[n][];
        int islandCount = 0;

        for(int i = 0; i<n; i++){
            sea[i] = new char[m];
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                sea[i][j] = st.nextToken().charAt(0);
            }
        }

        char landName = 'a';
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(sea[i][j] == '1'){
                    islandCount++;
                    getLand(i, j, landName++);
                }else if (sea[i][j] == '0'){
                    sea[i][j] = '_';
                }
            }
        } // 섬 분류

        /*for(int i = 0; i<n; i++){
            System.out.println(Arrays.toString(sea[i]));
        }*/

        graph = new HashMap<>();
        // 그래프 간선 구하기(가로 방향)
        for (int i = 0; i< n; i++){
            char curLand = sea[i][0];
            int curj = 0;
            for(int j = 1; j<m; j++){
                char nextLand = sea[i][j];
                if(nextLand == '_'){
                    continue;
                }

                if(curLand == nextLand){
                    curj = j;
                }

                if(curLand != nextLand){
                    if(curLand == '_'){ // 해당 줄의 첫 번째 섬이면?
                        curLand = nextLand;
                        curj = j;
                        continue;
                    }

                    if (j - curj - 1 >= 2) {
                        Set<Vertex> vSet = graph.getOrDefault(curLand, new HashSet<>());
                        vSet.add(new Vertex(curLand, nextLand, j - curj-1));
                        graph.put(curLand, vSet);

                        /*Set<Vertex> toSet = graph.getOrDefault(nextLand, new HashSet<>());
                        toSet.add(new Vertex(curLand, j- curj- 1));
                        graph.put(nextLand, toSet);*/
                    }

                    curLand = nextLand;
                    curj = j;
                }
            }
        }

        // 그래프 간선 구하기(세로 방향)
        for(int j = 0; j<m; j++){
            char curLand = sea[0][j];
            int curi = 0;
            for(int i = 1; i<n; i++){
                char nextLand = sea[i][j];
                if(nextLand == '_'){
                    continue;
                }

                if(curLand == nextLand){
                    curi = i;
                }

                if(curLand != nextLand){
                    if(curLand == '_' || i - curi - 1 == 1){ // 해당 줄의 첫 번째 섬이면?
                        curLand = nextLand;
                        curi = i;
                        continue;
                    }

                    if(i - curi - 1 >= 2){
                        Set<Vertex> vSet = graph.getOrDefault(curLand, new HashSet<>());
                        vSet.add(new Vertex(curLand, nextLand, i - curi - 1));
                        graph.put(curLand, vSet);


                        /*Set<Vertex> toSet = graph.getOrDefault(nextLand, new HashSet<>());
                        toSet.add(new Vertex(curLand, i- curi- 1));
                        graph.put(nextLand, toSet);*/
                    }

                    curLand = nextLand;
                    curi = i;
                }

            }
        }

        //System.out.println(graph);
        // 스패닝 트리로 최소 간선 끼리 묶기
        PriorityQueue<Vertex> queue = new PriorityQueue<>();

        for(Character key : graph.keySet()){
            Set<Vertex> set = graph.get(key);

            for(Vertex v : set){
                queue.add(v);
            }
        }

        boolean[] visited = new boolean[islandCount];

        // union find
        parent = new int [islandCount];
        for(int i = 0; i<islandCount; i++){
            parent[i] = i;
        }

        // spanning
        int cnt = 0;
        int result = 0;
        while(!queue.isEmpty()){ // cnt < islandCount
            Vertex curv = queue.poll();
            //System.out.println(curv);
            int from = curv.from - 'a';
            int to = curv.to - 'a';
            int dist = curv.dist;
            
            //https://www.acmicpc.net/board/view/42657
            //  더 연결 할 수 있는데 수의 방문한 섬의 개수만으로 했던 실수를 해결
            if(checkSame(from, to)){
                continue;
            }

            union(from, to);
            if(!visited[from] && !visited[to]){ // 둘다 방문하지 않은 경우
                result += dist;
                cnt += 2;
                visited[from] = visited[to] = true;
            }else if(visited[from] && !visited[to]) { // 시작점 만 방문한 경우
                result += dist;
                cnt ++;
                visited[to] = true;
            }else if(!visited[from] && visited[to]) { // 끝점만 방문한 경우
                result += dist;
                cnt ++;
                visited[from] = true;
            }else{ // 둘다 방문한 경우
                // 이미 최소 거리를 구함
                result += dist;
            }
            //System.out.println(curv + Arrays.toString(parent));
        }

        int pp = find(parent[0]);
        for(int i = 1; i<islandCount; i++){
            if(pp != find(parent[i])){
                System.out.println("-1");
                return;
            }
        }

        System.out.println(result == 0 ? -1 : result);

    }

    static boolean checkSame(int a, int b){
        return find(a)== find(b);
    }

    static void union(int top, int bot){
        int topParent = find(top);
        int botParent = find(bot);

        if(topParent != botParent){
            parent[botParent] = topParent;
        }
    }

    static int find(int num){
        if(parent[num] != num){
            return parent[num] = find(parent[num]);
        }
        return parent[num];
    }

    static void getLand(int x, int y, char landName){
        if(x < 0 || x >= n || y < 0 || y >= m || sea[x][y] == '0'){
            return;
        }

        if(sea[x][y] == '1'){
            sea[x][y] = landName;
            getLand(x-1, y, landName);
            getLand(x+1, y, landName);
            getLand(x, y-1, landName);
            getLand(x, y+1, landName);
        }
    }

    /*static void dfs(char to, int howFar, int cnt, String path){
        if(visited[to-'a']){ // 이미 방문한 섬이라면
            return;
        }

        visited[to-'a'] = true;
        Set<Vertex> toGo = graph.get(to);
        for(Vertex v : toGo){
            dfs(v.to, howFar + v.dist, cnt+1, path + " " + to);
        }

        visited[to-'a'] = false;
    }*/

}
