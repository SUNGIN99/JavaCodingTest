import java.util.*;
import java.io.*;

public class Main{

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());
        char[] str = br.readLine().toCharArray();
        int n = str.length;
        int one = 0, zero = 0;
        int start = 0, end= 0;
        while(true){
            if(str[start] == str[end]){
                end++;
            }else{
                if(str[start] == '0'){
                    zero++;
                }else{
                    one++;
                }
                start = end;
            }

            if(end >= n){
                break;
            }
        }

        if(str[start] == '0'){
            zero++;
        }else{
            one++;
        }
        //System.out.println(one + ", " + zero);
        if(start == 0){
            System.out.println(0);
        }else{
            System.out.println(Math.min(one, zero));
        }
    }


}