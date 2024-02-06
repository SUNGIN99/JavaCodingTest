import java.util.*;
import java.io.*;

public class Main {

    static class Num{
        int value;
        int index;

        Num (int v, int i){
           this.value = v;
           this.index = i;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Num[] nums = new Num[n];
        for(int i = 0; i<n;i++){
            nums[i] = new Num(Integer.parseInt(st.nextToken()), i);
        }

        Stack<Num> stack = new Stack<>();
        int[] NGE = new int[n];
        for(int i = 0; i<n; i++){
            while(!stack.isEmpty() && stack.peek().value < nums[i].value){
                Num top = stack.pop();
                NGE[top.index] = nums[i].value;
            }
        
            stack.push(nums[i]);
        }

        while(!stack.isEmpty()){
            Num top = stack.pop();
            NGE[top.index] = -1;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        for(int i = 0; i<n; i++){
            bw.write(NGE[i] + " " );
        }
        bw.write("\n");
        bw.flush();
        
        br.close();
        bw.close();

    }

}

