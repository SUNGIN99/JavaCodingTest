import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());
        char[] a = br.readLine().toCharArray();

        int i = 0;
        int wCount = 0;
        boolean isWord = true;
        while(i < a. length){
            if(a[i++] == ' '){
                isWord = true;
            }else{
                if(isWord){
                    wCount++;
                    isWord = false;
                }
            }
        }
        System.out.println(wCount);
    }

}