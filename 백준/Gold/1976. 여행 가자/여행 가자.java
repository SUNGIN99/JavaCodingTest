import java.util.*;
import java.io.*;

public class Main {
    static int[] rooted;
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        rooted = new int[n];
        for(int i = 0; i<n; i++){
            rooted[i] = i;
        }


        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                int connected = Integer.parseInt(st.nextToken());
                if(connected == 1 && i != j){
                    union(i, j);
                }
            }
            //System.out.println(Arrays.toString(rooted));
        }

        int[] path = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<m; i++){
            path[i] = Integer.parseInt(st.nextToken());
        }

        int prev = rooted[path[0]-1];
        boolean yn = true;
        for(int i = 1; i<m; i++){
            if(prev != rooted[path[i]-1]){
                yn = false;
                break;
            }
        }
        System.out.println(yn? "YES" : "NO");
    }

    static void union(int a, int b){
        int fa = find(a);
        int fb = find(b);
        if(a != b){
            for(int i = 0; i<n; i++){
                if(rooted[i] == fb){
                    rooted[i] = fa;
                }
            }
        }
    }

    static int find(int n){
        if(n == rooted[n]){
            return n;
        }else{
            return rooted[n] = find(rooted[n]);
        }
    }

}

