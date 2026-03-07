import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        String road = br.readLine().trim();

        // 상태 (T%3, G%3, F%3, P%3) -> 등장 횟수
        Map<String, Long> stateCount = new HashMap<>();

        int t = 0, g = 0, f = 0, p = 0;

        // 인덱스 0 (아무것도 선택 안 한 상태)도 포함
        String initState = "0,0,0,0";
        stateCount.put(initState, 1L);

        long answer = 0;

        for (int i = 0; i < n; i++) {
            char c = road.charAt(i);
            if (c == 'T') t++;
            else if (c == 'G') g++;
            else if (c == 'F') f++;
            else if (c == 'P') p++;

            String state = (t%3) + "," + (g%3) + "," + (f%3) + "," + (p%3);

            // 같은 상태가 이미 k번 나왔다면, 새로운 쌍이 k개 추가됨
            long cnt = stateCount.getOrDefault(state, 0L);
            answer += cnt;
            stateCount.put(state, cnt + 1);
        }

        System.out.println(answer);
    }
}