import java.util.*;
import java.io.*;
public class Main {
    static char[][] wheels;
    static boolean[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 0: 12시, 2: 3시, 4: 6시, 6: 9시
        wheels = new char[4][8];
        for(int i = 0; i<4; i++){
            wheels[i] = new char[8];
            st = new StringTokenizer(br.readLine());
            wheels[i] = st.nextToken().toCharArray();
        }

        // n극 = 0, s극 = 1
        // 톱니바퀴 번호, 방향(1= 시계, -1=반시계)
        st = new StringTokenizer(br.readLine());
        int time = Integer.parseInt(st.nextToken());
        ArrayList<Integer[]> direction = new ArrayList<>();
        for(int i = 0; i<time; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            direction.add(new Integer[]{num, dir});
        }

        for(Integer[] dir : direction){
            visited = new boolean[4];
            //System.out.println("--------");
            dfs(dir[0], dir[1]);
        }

        int[][] score = new int[][]{
                {0, 1},
                {0, 2},
                {0, 4},
                {0, 8}
        };

        int result = 0;
        for(int i = 0; i< 4 ;i++){
            result += score[i][wheels[i][0] - '0'];
        }


        System.out.println(result);
    }

    static void dfs(int num, int dir){
        int curWheel = num -1;

        if(visited[curWheel]){
            return;
        }

        visited[curWheel] = true;
        // 0: 12시, 2: 3시, 4: 6시, 6: 9시
        if(curWheel == 0){ // 오른쪽 확인하기 // 1
            if(visited[curWheel+1]){
                round(num, dir);
                return;
            }

            if(wheels[curWheel][2] != wheels[curWheel+1][6]){
                round(num, dir);
                //round(num + 1, dir == 1 ? -1 : 1);
                dfs(num + 1, dir == 1 ? -1 : 1);
            }else{
                round(num, dir);
            }
        }

        if(curWheel == 3){ // 4
            if(visited[curWheel-1]){
                round(num, dir);
                return;
            }

            if(wheels[curWheel][6] != wheels[curWheel-1][2]){
                round(num, dir);
                //round(num - 1, dir == 1 ? -1 : 1);
                dfs(num - 1, dir == 1 ? -1 : 1);
            }else{
                round(num, dir);
            }
        }

        boolean left = false, right = false;
        if(curWheel == 1 || curWheel == 2){ // 2, 3
            if(!visited[curWheel+1] && (wheels[curWheel][2] != wheels[curWheel+1][6])){ // 오른쪽이 다를 때
                right = true;
            }
            if(!visited[curWheel-1] && (wheels[curWheel][6] != wheels[curWheel-1][2])){ // 왼쪽이 다를 때
                left = true;
            }
            //System.out.println(right + ", " + left);
            round(num,  dir);
            if(right && !left){
                //round(num + 1, dir == 1 ? -1 : 1);
                dfs(num + 1, dir == 1 ? -1 : 1);
            }else if(!right && left){
                //round(num - 1, dir == 1 ? -1 : 1);
                dfs(num - 1, dir == 1 ? -1 : 1);
            }else if(right && left){
                //round(num + 1, dir == 1 ? -1 : 1);
                dfs(num + 1, dir == 1 ? -1 : 1);
                //round(num - 1, dir == 1 ? -1 : 1);
                dfs(num - 1, dir == 1 ? -1 : 1);
            }
        }

    }

    static void round(int num, int dir){
        int curWheel = num -1;
        char temp;
        if(dir == 1){
            temp = wheels[curWheel][7];
            for(int i = 7; i>= 1; i--){
                wheels[curWheel][i] = wheels[curWheel][i-1];
            }
            wheels[curWheel][0] = temp;
        }else{
            temp = wheels[curWheel][0];
            for(int i = 0; i<7; i++){
                wheels[curWheel][i] = wheels[curWheel][i+1];
            }
            wheels[curWheel][7] = temp;
        }
        //System.out.println("num : " + num + ", dir : " + dir + " " +Arrays.toString(wheels[num-1]));
    }


}
