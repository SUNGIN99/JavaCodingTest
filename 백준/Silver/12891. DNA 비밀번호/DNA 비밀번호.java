import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] acgt2;
    static int[] acgt;
    static int check;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        // ACGT

        // DNA 문자열 길이
        int N = Integer.parseInt(st.nextToken());

        // 부분 문자열 길이
        int M = Integer.parseInt(st.nextToken());

        // DNA 문자열
        String dna = bf.readLine();

        st = new StringTokenizer(bf.readLine());
        acgt = new int[4];
        acgt2 = new int[4];
        check = 0;

        for(int i = 0; i<4; i++){
            acgt[i] = Integer.parseInt(st.nextToken());
            if(acgt[i] == 0){
                check++;
            }
        }

        char[] subdna = dna.toCharArray();
        int result = 0;
        for (int i = 0; i<M; i++){
            Add(subdna[i]);
        }

        if (check == 4){
            result ++;
        }

        for(int i = M; i < N; i++){
            Add(subdna[i]);
            Remove(subdna[i-M]);

            if (check == 4){
                result ++;
            }
        }

        System.out.println(result);
        bf.close();

    }

    private static void Add(char c){
        if(c == 'A'){
            acgt2[0]++;
            if (acgt2[0] == acgt[0]){
                check++;
            }
        }
        else if(c == 'C'){
            acgt2[1]++;
            if (acgt2[1] == acgt[1]){
                check++;
            }
        }
        else if(c == 'G'){
            acgt2[2]++;
            if (acgt2[2] == acgt[2]){
                check++;
            }
        }
        else if(c == 'T'){
            acgt2[3]++;
            if (acgt2[3] == acgt[3]){
                check++;
            }
        }
    }

    private static void Remove(char c){
        if(c == 'A'){
            if (acgt2[0] == acgt[0]){
                check--;
            }
            acgt2[0]--;
        }
        else if(c == 'C'){
            if (acgt2[1] == acgt[1]){
                check--;
            }
            acgt2[1]--;
        }
        else if(c == 'G'){
            if (acgt2[2] == acgt[2]){
                check--;
            }
            acgt2[2]--;
        }
        else if(c == 'T'){
            if (acgt2[3] == acgt[3]){
                check--;
            }
            acgt2[3]--;
        }
    }
}
