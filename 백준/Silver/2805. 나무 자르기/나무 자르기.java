import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Integer> trees = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            trees.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(trees, Collections.reverseOrder());
        //System.out.println(trees);

        int saw = trees.get(0);
        int sum = 0;
        int i;
        for(i = 1; i<n; i++){
            int nextTree = trees.get(i);

            int gap = saw - nextTree;

            int temp = sum + gap * i;

            if(temp < m){
               sum = temp;
               saw = nextTree;
            }else if(temp == m){
                sum = temp;
                saw = nextTree;
                break;
            }else{
                int remain = m - sum;
                float remainF = remain * 1.0f;
                remainF /= i;
                //System.out.println("remainF : " + remainF);

                int ceilF = (int) Math.ceil(remainF);
                saw -= ceilF;
                sum += ceilF * i;
                break;
            }
            //System.out.println(sum);
        }

        if(sum < m) {
            int remain = m - sum;
            float remainF = remain * 1.0f;
            remainF /= i;


            int ceilF = (int) Math.ceil(remainF);
            //System.out.println(ceilF);
            saw -= ceilF;
            sum += ceilF * i;
            //System.out.println(sum + ", " + saw);
            System.out.println(saw);
        }else{
            //System.out.println(sum + ", " + saw);
            System.out.println(saw);
        }





    }

}
