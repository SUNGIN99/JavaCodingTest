import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n, m;

        n = Integer.parseInt(st.nextToken());

        int[] p = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            p[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());

        String[][] dp = new String[n][m+1];

        for(int i = 0; i< n ;i++){
            for(int j = 0; j<= m; j++){
                if (i == 0){
                    if(j - p[i] >= 0){
                        String b = i + dp[i][j - p[i]];

                        dp[i][j] = b;
                    }else{
                        dp[i][j] = "";
                    }
                }else{
                    if(j - p[i] >= 0){
                        String max = "";
                        String a, b;
                        for(int k = 0; k <= i -1; k++){
                            a = dp[k][j];
                            b = i + dp[k][j-p[i]];

                            StringNum sn1 = new StringNum(a);
                            StringNum sn2 = new StringNum(b);

                            String c = sn1.compareTo(sn2) > 0 ? a : b;

                            StringNum sn3 = new StringNum(max);
                            StringNum sn4 = new StringNum(c);

                            max = sn3.compareTo(sn4) > 0 ? max : c;
                        }

                        String d = i + dp[i][j-p[i]];

                        StringNum sn5 = new StringNum(max);
                        StringNum sn6 = new StringNum(d);

                        dp[i][j] = sn5.compareTo(sn6) > 0 ? max : d;
                    }else{
                        dp[i][j] = dp[i-1][j];
                    }
                }
            }
            //System.out.println(Arrays.toString(dp[i]));
        }

        if(dp[n-1][m].charAt(0) == '0'){
            System.out.println(0);
        }else{
            System.out.println(dp[n-1][m]);
        }


        //StringNum sn1 = new StringNum("00");
        //StringNum sn2 = new StringNum("1");

        //System.out.println(sn1.compareTo(sn2));
        //000000000000000000
        //000000000000000000
    }

    static class StringNum implements Comparable<StringNum>{
        String value;

        StringNum(String v){
            value = v;
        }

        public int compareTo(StringNum o){
            if(this.value.length() == o.value.length()){
                for(int i = 0; i<value.length(); i++){
                    int num1 = Integer.parseInt(String.valueOf(this.value.charAt(i)));
                    int num2 = Integer.parseInt(String.valueOf(o.value.charAt(i)));

                    if(num1 > num2){
                        return 1;
                    }else if(num1 < num2){
                        return -1;
                    }
                }
                return 0;
            }else{
                if(!this.value.isEmpty() && !o.value.isEmpty()){
                    if(this.value.charAt(0) == '0' && o.value.charAt(0) != '0'){
                        return -1;
                    }else if(this.value.charAt(0) != '0' && o.value.charAt(0) == '0'){
                        return 1;
                    }
                }

                return this.value.length() - o.value.length();
            }
        }
    }


}