import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int logCount = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int[] logList = new int[logCount];

            for(int j = 0; j<logCount; j++){
                logList[j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(logList);
            System.out.println(solution(logCount, logList));
        }

    }

    public static int solution(int logCount, int[] logList){
        Deque<Integer> logComb = new LinkedList<>();

        logComb.add(logList[0]);
        logComb.addFirst(logList[1]);
        int leftDiff, rightDiff, difficulty = 0;
        for(int i = 2; i< logCount; i++){
            leftDiff = Math.abs(logList[i] - logComb.getFirst());
            rightDiff = Math.abs(logList[i] - logComb.getLast());

            if(leftDiff <= rightDiff){
                logComb.addLast(logList[i]);

                difficulty = Math.max(difficulty, rightDiff);
            }
            else{
                logComb.addFirst(logList[i]);
                difficulty = Math.max(difficulty, leftDiff);
            }
        }

        //System.out.println(logComb);
        return difficulty;
    }

}
