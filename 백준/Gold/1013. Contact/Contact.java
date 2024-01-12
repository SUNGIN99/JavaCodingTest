import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        Pattern pattern = Pattern.compile("(100+1+|01)+");

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            String signal = st.nextToken();
            Matcher matcher = pattern.matcher(signal);

            if(matcher.matches()){
                System.out.println("YES");
            }
            else{
                System.out.println("NO");
            }

        }

    }


}
