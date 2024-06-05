import java.util.*;
import java.io.*;

public class Main{
    static int[][] blue = new int[4][];
    static int[][] green = new int[6][];
    static int score = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i<4; i++){
            blue[i] = new int[6];
        }

        for(int i = 0; i<6; i++){
            green[i] = new int[4];
        }

        for(int i = 0; i<n; i++){
            // t = 1: 크기가 1×1인 블록을 (x, y)에 놓은 경우   - O
            // t = 2: 크기가 1×2인 블록을 (x, y), (x, y+1)에 놓은 경우  - 00
            // t = 3: 크기가 2×1인 블록을 (x, y), (x+1, y)에 놓은 경우  - 세로 00
            st= new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            blueDrop(x, y, t);
            greenDrop(x, y, t);
        }

        System.out.println(score);

        int tiles = 0;
        for(int i = 0; i<blueRow; i++){
            //System.out.println("              " + Arrays.toString(blue[i]));
            for(int j = 0; j<blueCol; j++){
                if(blue[i][j] != 0){
                    tiles++;
                }
            }
        }

        for(int i = 0; i<greenRow; i++){
            //System.out.println(Arrays.toString(green[i]));
            for(int j = 0; j<greenCol; j++){
                if(green[i][j] != 0){
                    tiles++;
                }
            }
        }

        System.out.println(tiles);
    }

    static final int blueRow = 4, blueCol = 6;
    static void blueDrop(int x, int y, int t){
        // 1. 쌓기
        if(t == 1){ // t = 1: 크기가 1×1인 블록을 (x, y)에 놓은 경우   - O
            int i = 0;
            for(; i<blueCol-1; i++){
                if(blue[x][i+1] != 0){
                    break;
                }
            }
            blue[x][i] = 1;

        }else if(t == 2){ // t = 2: 크기가 1×2인 블록을 (x, y), (x, y+1)에 놓은 경우  - 00
            int i = 0;
            for(; i<blueCol-2; i++){
                if(blue[x][i+2] != 0){
                    break;
                }
            }
            blue[x][i] = blue[x][i+1] = 2;

        }else{ // t = 3: 크기가 2×1인 블록을 (x, y), (x+1, y)에 놓은 경우  - 세로 00
            int i = 0;
            for(; i<blueCol-1; i++){
                if(blue[x][i+1] != 0 || blue[x+1][i+1] != 0){
                    break;
                }
            }
            blue[x][i] = blue[x+1][i] = 3;
        }

        // 한 열이 완성되면 터뜨리기
        int[][] tempBlue = new int[4][6];
        for(int i = 0; i<4; i++){
            tempBlue[i] = new int[6];
        }

        int tempC = blueCol-1;
        for(int i = blueCol-1; i>=0; i--){
            boolean straight = true;
            for(int j = 0; j<blueRow; j++){
                if(blue[j][i] == 0){
                    straight = false;
                    break;
                }
            }

            if(!straight){
                for(int j = 0; j<blueRow; j++){
                    tempBlue[j][tempC] = blue[j][i];
                }
                tempC--;
            }else{
                score++;
            }
        }

        blue = tempBlue;

        int needClear = 0;
        boolean line1 = false, line2=  false;
        for(int i = 0; i<blueRow; i++){
            if(blue[i][0] != 0){
                line1 = true;
            }
            if(blue[i][1] != 0){
                line2 = true;
            }
        }

        // 0~1 사이에 있는 블럭 수 만큼 맨밑에 없애기
        if(line1){
            for(int i =0; i<blueRow; i++){
                System.arraycopy(blue[i], 0, blue[i], 2, 4);
            }
        }else if(line2){
            for(int i =0; i<blueRow; i++){
                System.arraycopy(blue[i], 1, blue[i], 2, 4);
            }
        }

        for(int i = 0; i<blueRow; i++){
            blue[i][0] = 0;
            blue[i][1] = 0;
        }

        /*for(int i = 0; i<4; i++){
            System.out.println(Arrays.toString(blue[i]));
        }*/


        /*for(int i = 0; i<blueRow; i++){
            System.out.println(Arrays.toString(tempBlue[i]));
        }*/


    }

    static final int greenRow = 6, greenCol = 4;
    static void greenDrop(int x, int y, int t){
        // 1. 쌓기
        if(t == 1){
            int i = 0;
            for(;i<greenRow-1; i++){
                if(green[i+1][y] != 0){
                    break;
                }
            }
            green[i][y] = 1;

        }else if(t == 2){ // t = 2: 크기가 1×2인 블록을 (x, y), (x, y+1)에 놓은 경우  - 00
            int i = 0;
            for(; i<greenRow-1; i++){
                if(green[i+1][y] != 0 || green[i+1][y+1] != 0){
                    break;
                }
            }
            green[i][y] = green[i][y+1] = 2;

        }else{
            int i = 0;
            for(; i<greenRow - 2; i++){
                if(green[i+2][y] != 0){
                    break;
                }
            }

            green[i][y] = green[i+1][y] = 3;
        }

        int[][] tempGreen = new int[6][4];
        for(int i = 0; i<6; i++){
            tempGreen[i] = new int[4];
        }

        int tempR = greenRow -1;
        for(int i = greenRow-1; i>=0; i--){
            boolean straight = true;
            for(int j = 0; j<greenCol; j++){
                if(green[i][j] == 0){
                    straight = false;
                    break;
                }
            }

            if(!straight){
                System.arraycopy(green[i], 0, tempGreen[tempR], 0, greenCol);
                tempR--;
            }else{
                score++;
            }
        }

        green = tempGreen;

        boolean line1 = false, line2= false;
        for(int i = 0; i<greenCol; i++){
            if(green[0][i] != 0){
                line1 = true;
            }

            if(green[1][i] != 0){
                line2 = true;
            }

        }

        if(line1){
            for(int i = greenRow-1; i>= 2; i--){
                System.arraycopy(green[i-2], 0, green[i], 0, 4);
            }
        }else if(line2){
            for(int i = greenRow-1; i>= 1; i--){
                System.arraycopy(green[i-1], 0, green[i], 0, 4);
            }
        }

        green[0] = new int[greenCol];
        green[1] = new int[greenCol];



       /* System.out.println();
        System.out.println(t + ", " + x + ", "+ y);
        for(int i = 0; i<greenRow; i++){
            System.out.println(Arrays.toString(tempGreen[i]));
        }*/
    }

}