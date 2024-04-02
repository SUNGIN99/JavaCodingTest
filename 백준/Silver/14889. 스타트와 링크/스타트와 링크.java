import java.util.*;
import java.io.*;
public class Main {

    static boolean[] visited;
    static int n;
    static int result = Integer.MAX_VALUE;
    static int[][] values;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        values = new int[n][n];
        visited = new boolean[n];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            values[i] = new int[n];
            for(int j = 0; j<n; j++){
                values[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i<n; i++){
            visited = new boolean[n];
            getDiff(i, 1);
        }
        System.out.println(result);
    }

    static void getDiff(int num, int count){
        if(visited[num]){
            return;
        }

        visited[num] = true;

        if(count == n/2){
            int power = getTeamPower();
            //System.out.println(Arrays.toString(visited) + " : " + power);
            result = Math.min(result, power);
            visited[num] = false;
            return;
        }

        for(int i = num; i< n; i++){
            if(i == num || visited[i]){
                continue;
            }
            getDiff(i, count + 1);
        }
        visited[num] = false;
    }

    static int getTeamPower(){
        int team1 = 0, team2 = 0;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(visited[i] && visited[j]){
                    team1 += values[i][j];
                }
                else if(!visited[i] && !visited[j]){
                    team2 += values[i][j];
                }
            }
        }
        //System.out.print("team1 : " + team1 + ", team2 : " + team2 + " ");
        return Math.abs(team1 - team2);
    }

}
