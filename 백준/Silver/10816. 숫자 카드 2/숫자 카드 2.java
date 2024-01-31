import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

    
        HashMap<Integer, Integer> cardCount = new HashMap<>();

        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            int cardNum = Integer.parseInt(st.nextToken());
            int cards = cardCount.getOrDefault(cardNum , 0);

            cardCount.put(cardNum, cards + 1);
        }


        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int[] toFind = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<m; i++){
            toFind[i] = Integer.parseInt(st.nextToken());
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i<m; i++){
            bw.write(cardCount.getOrDefault(toFind[i], 0) + " ");
        }

        bw.flush();
        br.close();
        bw.close();

    }

}
