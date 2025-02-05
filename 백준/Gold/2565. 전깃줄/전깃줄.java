import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    /*
    8
1 8
3 9
2 2
4 1
6 4
10 10
9 7
7 6

4
1 4
2 1
3 2
4 3
     */
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; // = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(br.readLine());
        electrics = new int[n][];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            electrics[i] = new int[] {a, b};

        }

        // 앞에 전기줄을 모두 확인하기 위해
        Arrays.sort(electrics, new Comparator<int[]> () {
            public int compare(int[] a, int [] b){
                return a[0] - b[0];
            }
        }) ;


        for(int i = 0; i<n; i++){
            //System.out.println(electrics[i][0] + ", " + electrics[i][1]);
            binary1(electrics[i][0]);
            binary2(electrics[i][1]);
        }

        //System.out.println(Arrays.toString(aa));
        //System.out.println(Arrays.toString(bb));

        //System.out.println(lenA);
        System.out.println(n - lenB);

    }

    static int[][] electrics;
    static int[] aa = new int[500];
    static int lenA = 0;
    static int[] bb = new int[500];
    static int lenB = 0;
    static void binary1(int a){
        int left = 0;
        int right = lenA;

        while(left < right){
            int mid = (left + right) / 2;

            if(aa[mid] < a){
                left = mid + 1;
            }else if(aa[mid] > a){
                right = mid;
            }else{
                break;
            }
        }

        if(right+1 > lenA){
            lenA = right+1;
        }

        aa[right] = a;
    }

    static void binary2(int a){
        int left = 0;
        int right = lenB;

        while(left < right){
            int mid = (left + right) / 2;

            if(bb[mid] < a){
                left = mid + 1;
            }else if(bb[mid] > a){
                right = mid;
            }else{
                break;
            }
        }

        if(right+1 > lenB){
            lenB = right+1;
        }

        bb[right] = a;
    }


}