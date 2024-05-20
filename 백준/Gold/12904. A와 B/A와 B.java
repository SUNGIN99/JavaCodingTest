import java.util.*;
import java.io.*;

public class Main{

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());

        String s = br.readLine();
        String t = br.readLine();

        char lastWord;
        boolean rightWay;

        // 마지막 문자가 B 라면 빼고 뒤집기
        // 마지막 문자가 A 라면 그냥 빼고 냅두기
        while(true){
            if(s.length() == t. length()){
                break;
            }
            lastWord = t.charAt(t.length()-1);
            if(lastWord == 'A'){
                rightWay = true;
                t = t.substring(0, t.length() - 1);
            }else{
                rightWay = false;
                t = t.substring(0, t.length() - 1);
                StringBuffer sb = new StringBuffer(t);
                t = sb.reverse().toString();
            }
        }
        //System.out.println(s);
        //System.out.println(t);
        System.out.println(s.equals(t)?1:0);
    }



}