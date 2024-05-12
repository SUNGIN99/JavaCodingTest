import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(br.readLine());

        char [] fileName = br.readLine().toCharArray();
        int[] count = new int[fileName.length];
        for(int i = 1; i<n; i++){
            int j = 0;
            for(char c : br.readLine().toCharArray()){
                if(c != fileName[j]){
                    count[j]++;
                }
                j++;
            }
        }

        for(int i = 0; i<fileName.length; i++){
            if(count[i] > 0){
                System.out.print("?");
            }else{
                System.out.print(fileName[i]);
            }
        }
    }

}