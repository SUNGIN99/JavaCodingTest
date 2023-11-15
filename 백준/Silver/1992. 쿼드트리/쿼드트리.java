import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static char[][] videos;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        videos = new char[n][];
        for(int i = 0 ;i <n; i++){
            st = new StringTokenizer(br.readLine());
            videos[i] = st.nextToken().toCharArray();
            //System.out.println(Arrays.toString(videos[i]));
        }

        System.out.println(dfs(0,0,n));

    }

    public static String dfs(int row, int col, int range){
        int leftTopRow = row;
        int leftTopCol = col;

        int rightTopRow = row;
        int rightTopCol = col + range/2;

        int leftBotRow = row + range/2;
        int leftBotCol = col;

        int rightBotRow = row + range/2;
        int rightBotCol = col + range/2;

        if (range >= 4){
            String leftTop = dfs(leftTopRow, leftTopCol, range/2);
            String rightTop = dfs(rightTopRow, rightTopCol, range/2);
            String leftBot = dfs(leftBotRow, leftBotCol, range/2);
            String rightBot = dfs(rightBotRow, rightBotCol, range/2);

            if (leftTop.equals("0") && rightTop.equals("0") && leftBot.equals("0") && rightBot.equals("0")){
                return "0";
            }else if (leftTop.equals("1") && rightTop.equals("1") && leftBot.equals("1") && rightBot.equals("1")){
                return "1";
            }else{
                return "(" + leftTop + rightTop + leftBot + rightBot + ")";
            }
        }else{
            char leftTop = videos[leftTopRow][leftTopCol];
            char rightTop = videos[rightTopRow][rightTopCol];
            char leftBot = videos[leftBotRow][leftBotCol];
            char rightBot = videos[rightBotRow][rightBotCol];

            if(leftTop == '0' && rightTop == '0' && leftBot == '0' && rightBot == '0'){
                return "0";
            }else if(leftTop == '1' && rightTop == '1' && leftBot == '1' && rightBot == '1'){
                return "1";
            }else{
                return "(" + leftTop + rightTop + leftBot + rightBot + ")";
            }
        }
    }
}
