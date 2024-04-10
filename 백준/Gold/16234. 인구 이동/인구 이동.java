import java.util.*;
import java.io.*;

public class Main{

    static int n, l, r;
    static int[][] visited;
    static int[][] popularity;
    static int[] unionPopularity;
    static int[] unionCount;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        popularity = new int[n][];
        visited = new int[n][];
        for(int i = 0; i<n; i++){
            popularity[i] = new int[n];
            visited[i] = new int[n];

            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                popularity[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        int night = 0;
        while(true){
            int unionNum = 0;
            initVisited();
            unionPopularity = new int[n*n];
            unionCount = new int[n*n];
            for(int i = 0; i<n; i++){
                for(int j = 0; j<n; j++){
                    if(visited[i][j] == -1){
                        getUnion(i, j, unionNum++);
                    }
                }
            }

            if(unionNum >= n * n){
                break;
            }
            night++;

            unUnion();
        }

        System.out.println(night);

    }

    static void unUnion(){
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                int unionNum = visited[i][j];
                popularity[i][j] = unionPopularity[unionNum] / unionCount[unionNum];
            }
        }
    }

    static void getUnion(int row, int col, int unionNum){
        if(visited[row][col] != -1){
            return;
        }

        //System.out.println("[" + row + ", " + col + "] : " +unionNum);
        visited[row][col] = unionNum;
        unionPopularity[unionNum] += popularity[row][col];
        unionCount[unionNum]++;
        if(row-1 >= 0){
            int gap = Math.abs(popularity[row][col] - popularity[row-1][col]);
            if(gap>=l && gap<=r){
                getUnion(row-1, col, unionNum);
            }

        }

        if(row + 1 < n){
            int gap = Math.abs(popularity[row][col] - popularity[row+1][col]);
            if(gap>=l && gap<=r){
                getUnion(row+1, col, unionNum);
            }
        }

        if(col-1 >= 0){
            int gap = Math.abs(popularity[row][col] - popularity[row][col-1]);
            if(gap>=l && gap<=r){
                getUnion(row, col-1, unionNum);
            }
        }

        if(col + 1 < n){
            int gap = Math.abs(popularity[row][col] - popularity[row][col+1]);
            if(gap>=l && gap<=r){
                getUnion(row, col+1, unionNum);
            }
        }
    }

    static void initVisited(){
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                visited[i][j] = -1;
            }
        }
    }


}