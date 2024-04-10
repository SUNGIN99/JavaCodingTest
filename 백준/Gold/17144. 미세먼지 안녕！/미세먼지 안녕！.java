import java.util.*;
import java.io.*;

public class Main{

    static int r, c, t;
    static int[][] dust;
    static int machineTop, machineBot;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        dust = new int[r][c];
        int value;
        for(int i = 0; i<r; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<c; j++){
                value = Integer.parseInt(st.nextToken());
                dust[i][j] = value;
                if(value == -1){
                    if(machineTop == 0){
                        machineTop = i;
                    }else{
                        machineBot = i;
                    }
                }
            }
        }

        int time = 0;
        while(time < t){
            dustMoving();
            machineOperate();
            time++;
        }

        /*for(int i = 0; i<r; i++){
            System.out.println(Arrays.toString(dust[i]));
        }*/

        int totalDust= 0 ;
        for(int i = 0; i<r; i++){
            for(int j = 0; j<c ;j++) {
                if (dust[i][j] > 0) {
                    totalDust += dust[i][j];
                }
            }
        }

        System.out.println(totalDust);

    }

    static void dustMoving(){
        int[][] newDust = new int[r][c];
        for(int i = 0;i<r; i++){
            newDust[i] = new int[c];
        }

        for(int i = 0; i<r; i++){
            for(int j = 0; j<c; j++){
                int dustCount = 0;
                if(dust[i][j] == -1){
                    newDust[i][j] = -1;
                    continue;
                }

                if(dust[i][j] > 0){
                    if(i-1 >= 0 && dust[i-1][j] != -1){
                        dustCount++;
                        newDust[i-1][j] += dust[i][j] / 5;
                    }
                    if(i+1 < r && dust[i+1][j] != -1){
                        dustCount++;
                        newDust[i+1][j] += dust[i][j] / 5;
                    }
                    if(j-1 >= 0 && dust[i][j-1] != -1){
                        dustCount++;
                        newDust[i][j-1] += dust[i][j] / 5;
                    }
                    if(j+1 < c && dust[i][j+1] != -1){
                        dustCount++;
                        newDust[i][j+1] += dust[i][j] / 5;
                    }
                    newDust[i][j] += dust[i][j] - (dust[i][j] / 5) * dustCount;
                   /* System.out.println(dust[i][j]);
                    for(int k = 0; k<r; k++){
                        System.out.println(Arrays.toString(newDust[k]));
                    }*/
                }

            }
        }

        dust = newDust;
    }

    static void machineOperate(){
        int topR = machineTop-2;
        int topC = 0;
        int botR = machineBot+1;
        int botC = 0;

        while(topR >= 0){
            dust[topR+1][topC] = dust[topR][topC];
            topR--;
        }

        topR = 0;
        while(topC < c-1){
            dust[topR][topC] = dust[topR][topC+1];
            topC++;
        }

        while(topR < machineTop){
            dust[topR][topC] = dust[topR+1][topC];
            topR++;
        }

        while(topC > 1){
            dust[topR][topC] = dust[topR][topC-1];
            topC--;
        }

        dust[topR][topC] = 0;

        while(botR < r-1){
            dust[botR][botC] = dust[botR+1][botC];
            botR++;
        }

        while(botC < c-1){
            dust[botR][botC] = dust[botR][botC+1];
            botC++;
        }
        // botR = r-1;
        // botC = c-1;

        while(botR > machineBot){
            dust[botR][botC] = dust[botR-1][botC];
            botR--;
        }

        while(botC > 1){
            dust[botR][botC] = dust[botR][botC-1];
            botC--;
        }

        dust[botR][botC] = 0;

    }

}