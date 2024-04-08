import java.util.*;
import java.io.*;

import java.util.*;
import java.io.*;
public class Main {

    static int n, m, h;
    static boolean visited[][];
    static boolean totalVisited[][];
    static int min = 4;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 세로 줄
        m = Integer.parseInt(st.nextToken()); // 가로 연결 줄
        h = Integer.parseInt(st.nextToken()); // 놓을 수 있는 가로 길이
        visited = new boolean[n+1][h+1];
        totalVisited = new boolean[n+1][h+1];
        HashMap<Integer, int[]> ledder = new HashMap<>();

        for(int i = 1; i<=n; i++){
            ledder.put(i, new int[h+1]);
            visited[i] = new boolean[h+1];
            totalVisited[i] = new boolean[h+1];
        }

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int[] v1 = ledder.get(b);
            int[] v2 = ledder.get(b+1);
            visited[b][a] = totalVisited[b][a] = true;
            visited[b+1][a] = totalVisited[b+1][a] = true;

            v1[a] = b+1;
            v2[a] = b;

            ledder.put(b,v1);
            ledder.put(b+1,v2);
        }

        /*for(Integer a : ledder.keySet()){
            System.out.println(Arrays.toString(ledder.get(a)));
        }*/

        if (goalIn(ledder)) {
            System.out.println(0);
            return;
        }

        for(int i = 1; i<n; i++){
            int[] v1 = ledder.get(i);
            int[] v2 = ledder.get(i+1);
            for(int j= 1; j<=h; j++){
                if(v1[j] == 0 && v2[j] == 0){
                    v1[j] = i+1;
                    v2[j] = i;
                    ledder.put(i, v1);
                    ledder.put(i+1, v2);

                    addLedder(ledder, 1, i, j);
                    visited[i][j] = true;
                    v1[j] = 0;
                    v2[j] = 0;
                    ledder.put(i, v1);
                    ledder.put(i+1, v2);
                }
            }
        }

        if(min == 4){
            System.out.println(-1);
        }else{
            System.out.println(min);
        }

    }

    static void addLedder(HashMap<Integer, int[]> ledder, int addCount, int row, int col){
        if(min <= addCount){
            return;
        }

        if(visited[row][col]){
            return;
        }

        if(addCount == 0){
        }else if(addCount >= 1 && addCount <= 3){
            /*int[][] arr = new int[n+1][];
            for(Integer a : ledder.keySet()){
                arr[a] = ledder.get(a);
            }
            System.out.println(addCount + "[" + row + ", " + col + "]");
            for(int i = 1; i<=h; i++){
                for(int j  = 1; j<=n; j++){
                    System.out.print(arr[j][i] + " " );
                }
                System.out.println();
            }
            System.out.println("---");*/
            if(goalIn(ledder)){
                min = Math.min(min, addCount);
                return;
            }
            if(addCount == 3){
                visited[row][col] = false;
                return;
            }

        }else{
            return;
        }

        HashMap<Integer, int[]> copiedLedder = new HashMap<>();
        for(int i = 1; i<=n; i++){
            int[] copy = new int[h+1];
            int[] origin = ledder.get(i);
            System.arraycopy(origin, 1, copy, 1, h);
            copiedLedder.put(i, copy);
        }

        visited[row][col] = true;
        for(int i = 1; i<n; i++){
            int[] v1 = copiedLedder.get(i);
            int[] v2 = copiedLedder.get(i+1);
            for(int j= 1; j<=h; j++){
                if(v1[j] == 0 && v2[j] == 0 &&((i==row && j >col) || (i>row)) &&!visited[i][j] && !totalVisited[i][j]){
                    // 지나왔던 경로를 다시 지나지 않기 위해서, 탐색으로 들오온 세로줄과, 연결 가능한 가로줄 높이보다 큰 위치만 고르도록 설정..
                    // (i==row && j >col) || (i>row)
                    // totalVisited는 필요 없을수도?
                    v1[j] = i+1;
                    v2[j] = i;
                    copiedLedder.put(i, v1);
                    copiedLedder.put(i+1, v2);

                    //System.out.print("[" + i + ", " + j + "] ");
                    addLedder(copiedLedder, addCount+1, i, j);
                    v1[j] = 0;
                    v2[j] = 0;
                    copiedLedder.put(i, v1);
                    copiedLedder.put(i+1, v2);
                }
            }
        }

        visited[row][col] = false;
    }

    static boolean goalIn(HashMap<Integer, int[]> ledder){
        for(Integer num : ledder.keySet()){
            int[] line = ledder.get(num);
            int start = num;
            int end = num;
            for(int i = 1; i< line.length; i++){
                if(line[i] != 0){
                    end = line[i];
                    line = ledder.get(line[i]);
                }
            }

            if(start != end){
                return false;
            }
        }
        return true;
    }

}