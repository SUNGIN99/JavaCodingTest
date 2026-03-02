import java.io.*;
import java.util.*;

public class Main {

    static int R, C;
    static char[][] lake;
    static int[][] meltDay;
    static int swanX1, swanY1, swanX2, swanY2;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        lake = new char[R][C];
        meltDay = new int[R][C];

        Queue<int[]> waterQueue = new LinkedList<>();
        boolean swan1 = false;

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                lake[i][j] = line.charAt(j);
                if (lake[i][j] == 'L') {
                    if (swan1) {
                        swanX2 = i; swanY2 = j;
                    } else {
                        swan1 = true;
                        swanX1 = i; swanY1 = j;
                    }
                }
                if (lake[i][j] != 'X') {
                    meltDay[i][j] = 0;
                    waterQueue.add(new int[]{i, j});
                } else {
                    meltDay[i][j] = -1;
                }
            }
        }

        // BFS: 모든 물에서 동시에 출발하여 얼음이 녹는 날짜 계산
        while (!waterQueue.isEmpty()) {
            int[] cur = waterQueue.poll();
            int x = cur[0], y = cur[1];
            int day = meltDay[x][y];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
                if (meltDay[nx][ny] != -1) continue;

                meltDay[nx][ny] = day + 1;
                waterQueue.add(new int[]{nx, ny});
            }
        }

        // 이분탐색: day D일에 백조가 만날 수 있는지
        int lo = 0, hi = 0;
        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++)
                hi = Math.max(hi, meltDay[i][j]);

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (canMeet(mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        System.out.println(lo);
    }

    // day D일에 meltDay <= D인 셀만 이동하여 백조1 → 백조2 도달 가능한지
    static boolean canMeet(int D) {
        boolean[][] visited = new boolean[R][C];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{swanX1, swanY1});
        visited[swanX1][swanY1] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];

            if (x == swanX2 && y == swanY2) return true;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
                if (visited[nx][ny]) continue;
                if (meltDay[nx][ny] > D) continue;

                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny});
            }
        }
        return false;
    }
}