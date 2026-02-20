import java.util.*;
import java.io.*;

/*
7
5 3
2
2
1
7
2
6
8
3
 */
public class Main {
    static int k, n, m;
    static int[] a, b;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = new int[n];
        b = new int[m];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            b[i] = Integer.parseInt(st.nextToken());
        }

        //System.out.println(Arrays.toString(a));

        makeASum();
        makeBSum();

        //System.out.println(asum);
        //System.out.println(bsum);

        long result = 0;
        for(Long key : asum.keySet()){
            int acount = asum.get(key);

            long bkey = (long)k - key;
            int bcount = bsum.getOrDefault(bkey, -1);

            if(bcount != -1){
                result += acount * bcount;
            }
        }

        System.out.println(result + asum.getOrDefault((long)k, 0) + bsum.getOrDefault((long)k, 0));

    }

    static HashMap<Long, Integer> asum = new HashMap<>();
    static HashMap<Long, Integer> bsum = new HashMap<>();

    static HashSet<String> acheck = new HashSet<>();
    static HashSet<String> bcheck = new HashSet<>();


    static void makeASum(){
        int length = a.length;

        int first = 0;

        while(first < length){
            long sum = 0;

            for(int i = 0; i<length; i++){
                int next = a[(first + i) % length];

                sum += next;

                if(first != 0 && i == length -1){
                    break;
                }

                int val = asum.getOrDefault(sum, 0) + 1;

                if(sum <= k){
                    asum.put(sum, val);
                }
            }

            first ++;
        }
    }

    static void makeBSum(){
        int length = b.length;

        int first = 0;

        while(first < length){
            long sum = 0;

            for(int i = 0; i<length; i++){
                int next = b[(first + i) % length];

                sum += next;

                if(first != 0 && i == length -1){
                    break;
                }

                int val = bsum.getOrDefault(sum, 0) + 1;

                if(sum <= k){
                    bsum.put(sum, val);
                }
            }

            first ++;
        }
    }


}