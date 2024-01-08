import java.util.*;
import java.io.*;

public class Main {

    static char[] strC1;
    static char[] strC2;
    static int[][] matrix;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        strC1 = st.nextToken().toCharArray();
        st = new StringTokenizer(br.readLine());
        strC2 = st.nextToken().toCharArray();

        matrix = new int[strC1.length+1][strC2.length+1];

        for(int i =0; i<=strC1.length;i++){
            matrix[i][0] = 0;
        }

        for(int i = 0; i<=strC2.length; i++){
            matrix[0][i] = 0;
        }

        for(int i = 1; i <= strC1.length; i++){
            for(int j = 1; j <= strC2.length; j++){
                if (strC1[i-1] == strC2[j-1]){
                    matrix[i][j] = matrix[i-1][j-1] + 1;
                }else{
                    matrix[i][j] = Math.max(matrix[i-1][j], matrix[i][j-1]);
                }
            }
        }

        System.out.println(matrix[strC1.length][strC2.length]);

        System.out.println(getText(strC1.length, strC2.length));



    }

    private static String getText(int row, int col){
        if(row == 0 || col == 0){
            return "";
        }

        if (strC1[row-1] == strC2[col-1]){
            return getText(row-1, col-1) + strC1[row-1];
        }else{
            if(matrix[row-1][col] > matrix[row][col-1]){
                return getText(row-1, col);
            }else{
                return getText(row, col-1);
            }
        }
    }

}
