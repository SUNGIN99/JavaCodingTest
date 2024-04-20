import java.util.*;
import java.io.*;

public class Main{

    static int seq = 0;
    static int r, c;
    static boolean finish = false;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        zSplit(n, 0, 0);
        System.out.println(seq);
    }

    static void zSplit(int n, int row, int col) {
        if(n == 0){
            return;
        }

        if(r <= row + Math.pow(2, n-1)-1 && c <= col + Math.pow(2, n-1) - 1) { // 첫번쨰 방문 위치
            //System.out.println("1");
            zSplit(n-1, row, col);
        }
        else if(r <= row + Math.pow(2, n-1)-1 && c <= col + Math.pow(2, n) - 1) {
            //System.out.println("2");
            if(n == 1){
                seq += 1;
            }else{
                seq += (int) Math.pow(4, n-1);
                zSplit(n-1, row, col + (int) Math.pow(2, n-1));
            }
        }
        else if(r <= row + Math.pow(2, n) - 1 && c <= col + Math.pow(2, n-1) - 1) {
            //System.out.println("3");
            if(n == 1){
                seq += 2;
            }else{
                seq += (int) Math.pow(4, n-1) * 2;
                zSplit(n-1, row + (int) Math.pow(2, n-1), col);
            }
        }
        else if(r <= row + Math.pow(2, n) - 1 && c <= col + Math.pow(2, n) - 1) {
            //System.out.println("4");
            if(n == 1){
                seq += 3;
            }else{
                seq += (int) Math.pow(4, n-1) * 3;
                zSplit(n-1, row+ (int) Math.pow(2, n-1), col + (int) Math.pow(2, n-1));
            }

        }
    }


}