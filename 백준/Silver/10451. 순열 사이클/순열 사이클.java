import java.util.*;
import java.io.*;

public class Main{

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        for(int i = 0; i<t; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[] arr = new int[n+1];
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=n; j++){
                arr[j] = Integer.parseInt(st.nextToken());
            }
            cycle(n, arr);
        }

    }

    static void cycle(int n, int[] arr){
        int[] visited = new int[n+1];
        int count = 0;
        int index = 1;
        int root = index;
        int i = 1;
        while(count<n){
            if(visited[index] == 0){
                //System.out.println(index + ", " + arr[index]);
                visited[index] = root;
                if(index != arr[index]){
                    index = arr[index];
                }
                count++;
            }else{
                root = index = i++;
            }
        }

        Set<Integer> set = new HashSet<>();
        for(int j= 1; j<=n; j++){
            set.add(visited[j]);
        }

        System.out.println(set.size());

    }


}