import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());

        char[] aa = br.readLine().toCharArray();

        int[][] alphabet = new int[aa.length][26];

        for(int i = 0; i<aa.length; i++){
            alphabet[i] = new int[26];
            if(i >= 1) {
                System.arraycopy(alphabet[i - 1], 0, alphabet[i], 0, 26);
            }

            char strChar = aa[i];
            alphabet[i][strChar-'a']++;
        }



        int q = Integer.parseInt(br.readLine());

        for(int i = 0; i<q; i++){
            st = new StringTokenizer(br.readLine());
            char question = st.nextToken().toCharArray()[0];
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            int[] fromAlphabets = alphabet[from];
            int[] toAlphabets = alphabet[to];

            int [] betweenAlphabets = new int[26];
            for(int j = 0; j<26; j++) {
                betweenAlphabets[j] = toAlphabets[j] - fromAlphabets[j];
            }
            betweenAlphabets[aa[from] - 'a'] ++;

            System.out.println(betweenAlphabets[question - 'a']);


        }




    }

}
