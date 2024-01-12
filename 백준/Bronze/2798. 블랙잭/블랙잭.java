import java.util.*;
import java.io.*;

public class Main {
    static int[] cards;
    static boolean[] visited;
    static int n, m, result, check;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 카드의 개수 n
        n = Integer.parseInt(st.nextToken());

        // 만들어야할 숫자 m
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        // 카드목록 cards
        cards = new int[n];

        // dfs 방문 기록
        visited = new boolean[n];

        check = Integer.MAX_VALUE;
        for(int i = 0; i<n; i++){
            cards[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i<n-2; i++){
            dfs(cards[i], i, 2);
        }
        System.out.println(result);

    }

    public static void dfs(int value, int index, int count){
        visited[index] = true;
        if (count == 0){
            if(m - value >= 0 && check >= m - value){
                check = m - value;
                result = value;

                //System.out.println(value + ", " + index + ", " + count + ", " + Arrays.toString(visited));
            }

            visited[index] = false;
            return;
        }

        for(int i = index + 1; i< n; i++){
            if(!visited[i]){
                dfs(value + cards[i], i, count - 1);
            }
        }

        visited[index] = false;

    }


}
