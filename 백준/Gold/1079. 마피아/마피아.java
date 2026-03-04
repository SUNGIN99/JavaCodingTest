import java.io.*;
import java.util.*;

public class Main {

    /*
4
500 500 500 500
1 4 3 -2
-2 1 4 3
3 -2 1 4
4 3 -2 1
1
     */

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        survivors = N = Integer.parseInt(st.nextToken());

        guiltyScore = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            guiltyScore[i] = Integer.parseInt(st.nextToken());
        }

        R = new int[N][];
        dead = new boolean[N];

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            R[i] = new int[N];
            for(int j = 0; j<N; j++){
                R[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        mafia = Integer.parseInt(br.readLine());

        int nightCount = 0;
        for(int i = 0; i<N; i++){
            if(survivors % 2 == 0){
                if(i == mafia){
                    continue;
                }

                kill(i, true);
                mafiaGame(nightCount + 1);
                revive(i, true);
            }else{
                int index = nextExecution();

                if(index == mafia){
                    break;
                }

                kill(index, false);
                mafiaGame(nightCount);
                revive(index, false);
            }
        }

        System.out.println(maxNightCnt);

    }

    static int N, mafia;
    static int[] guiltyScore;
    static int[][] R;

    static int survivors;
    static boolean isMafiaDead = false;
    static int maxNightCnt = 0;

    static boolean[] dead;

    static void mafiaGame(int nightCnt){
        if(survivors % 2 == 0){
            for(int i = 0; i<N; i++){
                if(!dead[i] && i != mafia){
                    kill(i, true);
                    mafiaGame(nightCnt + 1);
                    revive(i, true);
                }
            }
        }else{
            int index = nextExecution();
            if(index == mafia){
                maxNightCnt = Math.max(maxNightCnt, nightCnt);
                return;
            }
            kill(index, false);
            mafiaGame(nightCnt);
            revive(index, false);
        }
    }

    static void killGuilty(int index){
        for(int i = 0; i < N; i++){
            guiltyScore[i] += R[index][i];
        }
    }

    static void reviveGuilty(int index){
        for(int i = 0; i < N; i++){
            guiltyScore[i] -= R[index][i];
        }
    }

    static void kill(int index, boolean isNight){
        dead[index] = true;
        survivors--;

        if(isNight){
            killGuilty(index);
        }
    }

    static void revive(int index, boolean isNight){
        dead[index] = false;
        survivors++;

        if(isNight){
            reviveGuilty(index);
        }
    }

    static int nextExecution(){
        int index = -1;
        int maxGuilty = Integer.MIN_VALUE;

        for(int i = 0; i<N; i++){
            if(!dead[i]){
                if(maxGuilty == Integer.MIN_VALUE){
                    index = i;
                    maxGuilty = guiltyScore[i];
                }else{
                    if(maxGuilty < guiltyScore[i]){
                        index = i;
                        maxGuilty = guiltyScore[i];
                    }
                }
            }
        }
        return index;
    }
}