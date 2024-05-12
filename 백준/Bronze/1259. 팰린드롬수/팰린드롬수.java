import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());

        String input;
        while(!(input = br.readLine()).equals("0")){
            getPelindrom(input);
        }

    }

    static void getPelindrom(String str){
        int len = str.length();


        String prev, post;
        if(len % 2 == 0){
            prev = str.substring(0, len / 2);
            post = str.substring(len/2);
        }else{ // odd
            prev = str.substring(0, len / 2);
            post = str.substring(len/2 + 1);
        }

        post = String.valueOf(new StringBuffer(post).reverse());

        System.out.println(post.equals(prev) ? "yes" : "no");
    }
}