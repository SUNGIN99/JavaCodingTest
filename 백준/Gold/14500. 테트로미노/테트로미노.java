import java.util.*;
import java.io.*;
public class Main {

    static int n, m;
    static int blocks[][];
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        blocks = new int[n][];
        for(int i = 0; i<n; i++){
            blocks[i] = new int[m];
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                blocks[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 0;
        for(int i =0; i<n; i++){
            for(int j = 0; j<m; j++){
                max = Math.max(max, straight(i, j));
                max = Math.max(max, square(i, j));
                max = Math.max(max, l(i, j));
                max = Math.max(max, z(i, j));
                max = Math.max(max, mountain(i, j));
            }
        }
        System.out.println(max);
    }

    static int straight(int row, int col){
        int max1 = 0, max2 = 0;
        int i = 0;
        while(i < 4){
            if(row + i >= n){
                max1 = 0;
                break;
            }
            max1 += blocks[row+i][col];

            i++;
        }

        i = 0;
        while(i<4){
            if(col + i >= m){
                max2 = 0;
                break;
            }
            max2 += blocks[row][col+i];
            i++;
        }

        return Math.max(max1, max2);
    }

    static int square(int row, int col){
        if(row+1 >= n || col +1 >= m){
            return 0;
        }
        return blocks[row][col] + blocks[row+1][col] + blocks[row][col+1] + blocks[row+1][col+1];
    }

    static int l(int row, int col){
        int max = 0;
        if(row - 2 >= 0 && col - 1 >= 0){ // |_
            max = Math.max(max, blocks[row][col] + blocks[row][col-1] + blocks[row-1][col-1] + blocks[row-2][col-1]);
        }
        if(row + 1 < n && col - 2 >= 0){  //__|
            max = Math.max(max, blocks[row][col] + blocks[row+1][col] + blocks[row+1][col-1] + blocks[row+1][col-2]);
        }
        if(row + 2 < n && col + 1 < m){ // ㄱ|
            max = Math.max(max, blocks[row][col] + blocks[row][col+1] + blocks[row+1][col+1] + blocks[row+2][col+1]);
        }
        if(row-1 >= 0 && col + 2 < m){ // r-
            max = Math.max(max, blocks[row][col] + blocks[row-1][col] + blocks[row-1][col+1] + blocks[row-1][col+2]);
        }
        if(row - 2 >= 0 && col + 1 < m){ //_|
            max = Math.max(max, blocks[row][col] + blocks[row][col+1] + blocks[row-1][col+1] + blocks[row-2][col+1]);
        }
        if(row - 1 >= 0 && col - 2 >= 0){ //--|
            max = Math.max(max, blocks[row][col] + blocks[row-1][col] + blocks[row-1][col-1] + blocks[row-1][col-2]);
        }
        if(row + 2 < n && col - 1 >= 0){ //|r
            max = Math.max(max, blocks[row][col] + blocks[row][col-1] + blocks[row+1][col-1] + blocks[row+2][col-1]);
        }
        if(row+1 < n && col + 2 < m){ // |__
            max = Math.max(max, blocks[row][col] + blocks[row+1][col] + blocks[row+1][col+1] + blocks[row+1][col+2]);
        }

        return max;
    }

    static int z(int row, int col){
        int max = 0;
        if(row + 1 < n && col + 2 < m){ // ㄱ_
            max = Math.max(max, blocks[row][col] + blocks[row][col+1] + blocks[row+1][col+1] + blocks[row+1][col+2]);
        }
        if(row + 1 < n && col - 2 >= 0) { // _r
            max = Math.max(max, blocks[row][col] + blocks[row][col-1] + blocks[row+1][col-1] + blocks[row+1][col-2]);
        }
        if(row + 2 < n && col + 1 < m){ // |ㄱ
            max = Math.max(max, blocks[row][col] + blocks[row+1][col] + blocks[row+1][col+1] + blocks[row+2][col+1]);
        }
        if(row + 2 < n && col - 1 >= 0){ // r|
            max = Math.max(max, blocks[row][col] + blocks[row+1][col] + blocks[row+1][col-1] + blocks[row+2][col-1]);
        }
        return max;
    }

    static int mountain(int row, int col){
        int max = 0;
        if(row + 1 < n && col - 1 >= 0 && col + 1 < m){ // ㅗ
            max = Math.max(max, blocks[row][col] + blocks[row+1][col] + blocks[row+1][col-1] + blocks[row+1][col+1]);
        }
        if(row - 1 >= 0 && row + 1 < n && col -1 >= 0){ // ㅏ
            max = Math.max(max, blocks[row][col] + blocks[row][col-1] + blocks[row+1][col-1] + blocks[row-1][col-1]);
        }
        if(row - 1 >= 0 && col - 1 >= 0 && col+1 < m){ // ㅜ
            max = Math.max(max, blocks[row][col] + blocks[row-1][col] + blocks[row-1][col-1] + blocks[row-1][col+1]);
        }
        if(row - 1 >= 0 && row + 1 < n && col + 1 < m){ // ㅓ
            max = Math.max(max, blocks[row][col] + blocks[row][col+1] + blocks[row+1][col+1] + blocks[row-1][col+1]);
        }

        return max;
    }

}
