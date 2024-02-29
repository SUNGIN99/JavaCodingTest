import java.util.*;
import java.io.*;

public class Main {

    static boolean visited[];
    static int n, k;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        HashMap<Integer, Integer> cards = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            int num = Integer.parseInt(st.nextToken());
            cards.put(num, 1);
        }

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int[] check = new int[m];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<m; i++){
            check[i] = Integer.parseInt(st.nextToken());
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i<m; i++){
            if(cards.containsKey(check[i])){
                bw.write("1 ");
            }else{
                bw.write("0 ");
            }
        }

        bw.flush();
        bw.close();


    }



}