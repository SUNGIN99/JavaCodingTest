import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);

        
        int[][] matrix = new int[31][31];
        for(int i = 0 ; i< 31; i++){
            matrix[i][0] = 1;
            matrix[i][i] = 1;
            matrix[i][1] = i;
        }
        
        for(int i = 2 ; i<= 30 ;i++){
            for(int j = 2; j<i; j++){
                matrix[i][j] = matrix[i-1][j] + matrix[i-1][j-1];
            }
        }

        int T = sc.nextInt();
        int n, m;
        for(int i = 0; i< T; i++){
            n = sc.nextInt();
            m = sc.nextInt();

            System.out.println(matrix[m][n]);
        }
        
        



    }

}
