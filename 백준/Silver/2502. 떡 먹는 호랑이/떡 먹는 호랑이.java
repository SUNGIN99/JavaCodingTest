import java.util.*;
import java.io.*;

public class Main{

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int day = Integer.parseInt(st.nextToken());
        int riceCake = Integer.parseInt(st.nextToken());

        for(int i = riceCake - 1; i >= 1; i--){
            if(getDay(riceCake, i, day , " " + riceCake)){
                break;
            }
        }
    }


    static boolean  getDay(int today, int yesterday, int dayLeft, String cake){
        if(dayLeft > 2){
            if(today <= yesterday){
                return false;
            }
        }
        else if(dayLeft == 2){
            if(yesterday > today){
                return false;
            }

            System.out.println(yesterday);
            System.out.println(today);
            //System.out.println(cake + "\n");
            return true;
        }

        return getDay(yesterday, today - yesterday, dayLeft - 1, yesterday + " " + cake);
    }

}