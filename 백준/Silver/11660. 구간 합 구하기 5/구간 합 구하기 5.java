import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());

        int[][] matrix = new int[N+1][N+1];

        for (int i = 1; i< N+1; i++){
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            for (int j = 1; j<N+1; j++){
                matrix[i][j] = matrix[i-1][j] + matrix[i][j-1] - matrix[i-1][j-1] + Integer.parseInt(stringTokenizer.nextToken());
            }
        }


       // printMatrix(matrix, N);

        int x1, y1, x2, y2;
        for (int i = 0; i< M; i++){
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            x1 = Integer.parseInt(stringTokenizer.nextToken());
            y1 = Integer.parseInt(stringTokenizer.nextToken());
            x2 = Integer.parseInt(stringTokenizer.nextToken());
            y2 = Integer.parseInt(stringTokenizer.nextToken());

            int result = matrix[x2][y2] - matrix[x1-1][y2] - matrix[x2][y1-1] + matrix[x1-1][y1-1];
            System.out.println(result);

        }

    }

    public static void printMatrix(int[][] matrix, int N){
        for (int i = 1; i<N+1; i++){
            for (int j = 1; j < N + 1; j++) {
                System.out.printf("%d ", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
