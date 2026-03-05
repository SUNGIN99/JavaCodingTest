import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static String[][] arr;
    static boolean[][][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new String[N][];
        for (int i = 0; i < N; i++) {
            String a = br.readLine();
            arr[i] = a.split("");
        }

        visited = new boolean[K + 1][N][M];
        visited[K][0][0] = true;

        Queue<Path> queue = new LinkedList<>();
        queue.add(new Path(0, 0, K, 1));

        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        while (!queue.isEmpty()) {
            Path p = queue.poll();
            int x = p.x;
            int y = p.y;
            int k = p.canBreak;
            int len = p.length;

            if (x == N - 1 && y == M - 1) {
                System.out.println(len);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = x + dirs[i][0];
                int nextY = y + dirs[i][1];

                int canGo = isValid(nextX, nextY);

                if (canGo == 1) { // 벽이 아닐 때
                    if (!visited[k][nextX][nextY]) {
                        visited[k][nextX][nextY] = true;
                        queue.add(new Path(nextX, nextY, k, len + 1));
                    }
                } else if (canGo == 0) { // 벽일 때
                    if (k >= 1 && !visited[k - 1][nextX][nextY]) {
                        visited[k - 1][nextX][nextY] = true;
                        queue.add(new Path(nextX, nextY, k - 1, len + 1));
                    }
                }
            }
        }

        System.out.println(-1);
    }

    static int isValid(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= M) return -1;
        if (arr[x][y].equals("1")) return 0;
        return 1;
    }

    static class Path {
        int x, y, canBreak, length;

        Path(int x, int y, int c, int l) {
            this.x = x;
            this.y = y;
            this.canBreak = c;
            this.length = l;
        }
    }
}