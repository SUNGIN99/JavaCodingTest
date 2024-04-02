import java.util.*;
import java.io.*;
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] a = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        long result = 0;
        for(int i = 0; i<a.length; i++){
            //if(b >= c){ // b가 더 클때
                if(a[i] <= b){
                    result ++; // 일단 총 감독관 한 명 들어감
                }
                else{ // 총 감독관 한명으로 부족하면
                    if((a[i] - b) % c == 0){ // 부 감독관 얼마나 쓸지 결정
                        result += (long) (a[i] - b) / c;
                    }else {
                        result += (long) (a[i] - b) / c + 1;
                    }
                    result++;

                }
                //System.out.println(result);
            /*}else {
                if(a[i] <= c){ // 부 감독관 한명 쓰기
                    result ++;
                }else{ // 부감독관 2명이상 쓰기
                    if(a[i] % c == 0){
                        result += (long) a[i] / c;
                    }else{
                        result += (long) a[i] / c + 1;
                    }
                }
            }*/

        }
        System.out.println(result);
    }

}
