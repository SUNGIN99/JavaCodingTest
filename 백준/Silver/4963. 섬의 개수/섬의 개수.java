import java.util.*;
import java.io.*;

public class Main{

    static int w,h;
    static int[][] island;
    static int islandCount;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        while(true){
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if(w == 0 && h == 0){
                break;
            }
            //System.out.println(w + " , " +h);
            islandCount = 0;
            island = new int[h][];
            for(int i = 0; i<h; i++){
                st = new StringTokenizer(br.readLine());
                island[i] = new int[w];
                for(int j = 0; j<w; j++){
                    island[i][j] = Integer.parseInt(st.nextToken());
                }
                //System.out.println(Arrays.toString(island[i]));
            }

            for(int i = 0; i<h; i++){
                for(int j = 0; j<w; j++){
                    if(island[i][j] == 1){
                        islandCount++;
                        getIsland(i, j);
                    }
                }
            }
            System.out.println(islandCount);
        }
    }

    static void getIsland(int x, int y){
        if(x < 0 || x >= h || y < 0 || y >= w){
            return;
        }

        if(island[x][y] == 1){
            island[x][y] = 0;
            getIsland(x-1, y);
            getIsland(x+1, y);
            getIsland(x, y-1);
            getIsland(x, y+1);
            getIsland(x+1, y+1);
            getIsland(x-1, y+1);
            getIsland(x+1, y-1);
            getIsland(x-1, y-1);
        }else{
            return;
        }
    }

}