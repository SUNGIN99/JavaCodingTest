import java.util.*;
import java.io.*;

public class Main{

    static int n, m, k;
    static int[][] matrix;
    static int[][] func;
    static boolean[] visited;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        matrix = new int[n+1][m+1];
        for(int i = 1;i <=n; i++){
            matrix[i] = new int[m+1];
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=m; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        func = new int[k][3];
        for(int i = 0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            func[i] = new int[3];
            for(int j = 0; j<3; j++){
                func[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[k];

        for(int i = 0; i<k; i++){
            getSeq(i, new ArrayList<>());
        }
        System.out.println(min);
    }

    static void getSeq(int index, ArrayList<int[]> arr){

        ArrayList<int[]> copiedArr = new ArrayList<>();
        for(int i = 0; i<arr.size(); i++){
            copiedArr.add(arr.get(i).clone());
        }
        copiedArr.add(func[index]);

        if(copiedArr.size() == k){
            doFunc(copiedArr);
            return;
        }

        visited[index] = true;
        for(int i = 0; i<k; i++){
            if(!visited[i]){
                getSeq(i, copiedArr);
            }
        }
        visited[index] = false;
    }

    static void doFunc(ArrayList<int[]> arr){
        int[][] copiedMatrix = new int[n+1][];
        for(int i = 1; i<=n; i++){
            copiedMatrix[i] = new int[m+1];
            System.arraycopy(matrix[i], 1, copiedMatrix[i], 1, m);
        }

        for(int[] function : arr){
            int r = function[0];
            int c = function[1];
            int s = function[2];
            //System.out.println("{" + r + ", " + c + ", " + s + "} ");
            turning(r, c, s, copiedMatrix);
            /*for(int i = 1; i<=n; i++){
                System.out.println(Arrays.toString(copiedMatrix[i]));
            }*/
        }
        //System.out.println("---");

        getRowMinTotal(copiedMatrix);
    }

    static void turning(int r, int c, int s, int[][] cMatrix){
        int round = 1;
        while(round <= s){
            int curX = r - round;
            int curY = c - round;

            int start = cMatrix[curX][curY];

            while(curX < r + round){
                cMatrix[curX][curY] = cMatrix[curX+1][curY];
                curX++;
            }

            while(curY < c + round){
                cMatrix[curX][curY] = cMatrix[curX][curY+1];
                curY++;
            }

            while(curX > r - round){
                cMatrix[curX][curY] = cMatrix[curX-1][curY];
                curX--;
            }

            while(curY > c - round){
                cMatrix[curX][curY] = cMatrix[curX][curY-1];
                curY--;
            }
            curY++;
            cMatrix[curX][curY] = start;

            round++;
        }
    }

    static void getRowMinTotal(int[][] cMatrix){
        int sum;
        for(int i = 1; i<=n; i++){
            sum = 0;
            for(int j =1; j<=m; j++){
                sum += cMatrix[i][j];
            }
            min = Math.min(sum, min);
        }
    }
}