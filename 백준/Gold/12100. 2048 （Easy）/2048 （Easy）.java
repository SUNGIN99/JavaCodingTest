import java.util.*;
import java.io.*;
public class Main {

    static int n;
    static int maxNum;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int[][] games = new int[n][n];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                games[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        up(games, 1);
        down(games, 1);
        left(games, 1);
        right(games, 1);
        System.out.println(maxNum);

    }

    static void up(int[][] games, int count){
        int[][] copiedGames = new int[n][n];
        for(int i = 0; i<n; i++){
            System.arraycopy(games[i], 0, copiedGames[i], 0, n);
        }

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n-1; j++){
                //copiedGames[j][i];
                int k = j+1;
                while(k<n){
                    if(copiedGames[j][i] == 0){ // 현재 칸이 빈칸일 때
                        if(copiedGames[k][i] != 0){ // 댕겨올만한 블럭이 있다면? 빈칸에 칸을 채우기
                            copiedGames[j][i] = copiedGames[k][i];
                            copiedGames[k][i] = 0;
                            // break;
                            // break를 썼었는데 단순히 빈칸에 밀면 된다고 생각했지만, 밀림과 동시에 합쳐지는 케이스를 고려하지 못했음
                            // 그래서 밀림과 동시에 합쳐질 수 있도록 break를 해제하고 실행
                        }else{

                        }
                    } else if(copiedGames[j][i] != 0){ // 현재칸이 빈칸이 아닐 때,
                        if(copiedGames[j][i] == copiedGames[k][i]){ // 같이 합칠 수 있는 칸이 존재한다면?
                            copiedGames[j][i] *= 2;
                            copiedGames[k][i] = 0;
                            break;
                        }else{
                            // break;
                            // 1) 여기에 break를 안 써주면, 현재 칸과 다른 칸을 맞이하고도 그 다음 칸을 바라보아서 틀리게됨
                            // break 추가
                            // 인줄 알았는데 같은 칸이 아닌 경우는 0도 포함되기 때문에 추가해서 한칸만 땡기는게 아니라 쭉 당길 수 있도록 함..
                            if(copiedGames[k][i] != 0){
                                break;
                            }else{

                            }
                        }
                    }
                    k++;
                }
            }
        }


        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                maxNum = Math.max(maxNum, copiedGames[i][j]);
            }
        }

        if(count < 5){
            up(copiedGames, count+1);
            down(copiedGames, count+1);
            left(copiedGames, count+1);
            right(copiedGames, count+1);
        }

    }

    static void down(int[][] games, int count){
        int[][] copiedGames = new int[n][n];
        for(int i = 0; i<n; i++){
            System.arraycopy(games[i], 0, copiedGames[i], 0, n);
        }

        for(int i = 0; i<n; i++){
            for(int j = n-1; j > 0; j--){
                //copiedGames[j][i];
                int k = j-1;
                while(k>=0){
                    if(copiedGames[j][i] == 0){ // 현재 칸이 빈칸일 때
                        if(copiedGames[k][i] != 0){ // 댕겨올만한 블럭이 있다면? 빈칸에 칸을 채우기
                            copiedGames[j][i] = copiedGames[k][i];
                            copiedGames[k][i] = 0;
                        }else{

                        }
                    } else if(copiedGames[j][i] != 0){ // 현재칸이 빈칸이 아닐 때,
                        if(copiedGames[j][i] == copiedGames[k][i]){ // 같이 합칠 수 있는 칸이 존재한다면?
                            copiedGames[j][i] *= 2;
                            copiedGames[k][i] = 0;
                            break;
                        }else{
                            if(copiedGames[k][i] != 0){
                                break;
                            }else{

                            }
                        }
                    }
                    k--;
                }
            }
        }

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                maxNum = Math.max(maxNum, copiedGames[i][j]);
            }
        }

        if(count < 5){
            up(copiedGames, count+1);
            down(copiedGames, count+1);
            left(copiedGames, count+1);
            right(copiedGames, count+1);
        }
    }

    static void left(int[][] games, int count){
        int[][] copiedGames = new int[n][n];
        for(int i = 0; i<n; i++){
            System.arraycopy(games[i], 0, copiedGames[i], 0, n);
        }

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n-1; j++){
                //copiedGames[j][i];
                int k = j+1;
                while(k<n){
                    if(copiedGames[i][j] == 0){ // 현재 칸이 빈칸일 때
                        if(copiedGames[i][k] != 0){ // 댕겨올만한 블럭이 있다면? 빈칸에 칸을 채우기
                            copiedGames[i][j] = copiedGames[i][k];
                            copiedGames[i][k] = 0;
                        }else{

                        }
                    } else if(copiedGames[i][j] != 0){ // 현재칸이 빈칸이 아닐 때,
                        if(copiedGames[i][j] == copiedGames[i][k]){ // 같이 합칠 수 있는 칸이 존재한다면?
                            copiedGames[i][j] *= 2;
                            copiedGames[i][k] = 0;
                            break;
                        }else{
                            if(copiedGames[i][k] != 0){
                                break;
                            }else{
                                
                            }
                            
                        }
                    }
                    k++;
                }
            }
        }

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                maxNum = Math.max(maxNum, copiedGames[i][j]);
            }
        }

        if(count < 5){
            up(copiedGames, count+1);
            down(copiedGames, count+1);
            left(copiedGames, count+1);
            right(copiedGames, count+1);
        }

    }

    static void right(int[][] games, int count){
        int[][] copiedGames = new int[n][n];
        for(int i = 0; i<n; i++){
            System.arraycopy(games[i], 0, copiedGames[i], 0, n);
        }

        for(int i = 0; i<n; i++){
            for(int j = n-1; j>0; j--){
                //copiedGames[j][i];
                int k = j-1;
                while(k>=0){
                    if(copiedGames[i][j] == 0){ // 현재 칸이 빈칸일 때
                        if(copiedGames[i][k] != 0){ // 댕겨올만한 블럭이 있다면? 빈칸에 칸을 채우기
                            copiedGames[i][j] = copiedGames[i][k];
                            copiedGames[i][k] = 0;
                        }else{

                        }
                    } else if(copiedGames[i][j] != 0){ // 현재칸이 빈칸이 아닐 때,
                        if(copiedGames[i][j] == copiedGames[i][k]){ // 같이 합칠 수 있는 칸이 존재한다면?
                            copiedGames[i][j] *= 2;
                            copiedGames[i][k] = 0;
                            break;
                        }else{
                            if(copiedGames[i][k] != 0){
                                break;
                            }else{

                            }
                        }
                    }
                    k--;
                }
            }
        }

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                maxNum = Math.max(maxNum, copiedGames[i][j]);
            }
        }

        if(count < 5){
            up(copiedGames, count+1);
            down(copiedGames, count+1);
            left(copiedGames, count+1);
            right(copiedGames, count+1);
        }
    }

    static void print(int[][] games){
        for(int i = 0; i<n; i++){
            System.out.println(Arrays.toString(games[i]));
        }
        System.out.println("---");
    }

}
