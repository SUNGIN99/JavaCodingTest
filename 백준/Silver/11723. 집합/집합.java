import java.util.*;
import java.io.*;

public class Main{

    static BufferedWriter bw;
    static int[] S;
    static int[] init;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        S= new int[21];
        init = new int[21];
        for(int i = 1; i<=20 ;i++){
            S[i] = i;
        }

        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(st.nextToken());
        String op;
        int num = 0;
        int[] arr = new int[21];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            op = st.nextToken();
            if(!op.equals("all") && !op.equals("empty")){
                num = Integer.parseInt(st.nextToken());
            }
            operate(op, num, arr);
            //System.out.println(op + " : " + Arrays.toString(arr));
        }

        bw.flush();
        bw.close();

    }

    static void operate(String op, int num, int[] arr) throws IOException {
        if(op.equals("add")){
            arr[num] = num;
        }else if(op.equals("remove")){
            arr[num] = 0;
        }else if(op.equals("check")){
            bw.write(arr[num] == num ? 1+"\n": 0+"\n");
        }else if(op.equals("toggle")){
            arr[num] = arr[num] == 0 ? num : 0;
        }else if(op.equals("all")){
            System.arraycopy(S, 0, arr, 0,21);
        }else{
            System.arraycopy(init, 0, arr, 0, 21);
        }
    }


}