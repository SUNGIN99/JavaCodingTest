import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String password;
        while(!(password = br.readLine()).equals("end")){
            bw.write(solution(password)+"\n");
        }
        bw.flush();
        bw.close();
    }
    static String solution(String password){
        char[] pwd = password.toCharArray();
        boolean aeiou = false;
        int even = 0; // 모음
        int odd = 0;  // 자음

        for(int i = 0; i<pwd.length; i++){
            if(i < pwd.length-1){
                if(pwd[i] == pwd[i+1] && pwd[i] !='e' && pwd[i] != 'o'){
                    return "<"+password+">" + " is not acceptable.";
                }
            }

            // 모음 하나 반드시 포함
            if(pwd[i] =='a' || pwd[i] =='e' || pwd[i] =='i' || pwd[i] =='o' || pwd[i] =='u'){
                aeiou = true;

                odd = 0;
                even ++;
            }else{
                even = 0;
                odd ++;
            }

            if(even >= 3 || odd >= 3 ){
                return "<"+password+">" + " is not acceptable.";
            }
        }

        if(!aeiou){
            return "<"+password+">" + " is not acceptable.";
        }else{
            return "<"+password+">" + " is acceptable.";
        }
        
    }

}
