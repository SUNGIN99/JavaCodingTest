import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    static int[] nodes;
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        nodes = new int[n+1];

        for(int i = 0; i<n+1; i++){
            nodes[i] = i;
        }

        int func, a, b;
        for(int i = 0; i<m; i++){
            func = sc.nextInt();
            a = sc.nextInt();
            b = sc.nextInt();

            if(func == 0){
                union(a, b);
            }
            else{
                System.out.println(checkSame(a, b)?"YES": "NO");
            }

        }
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);

        if (a != b){
            nodes[b] = a;
        }

    }

    static int find(int num){
       if (num != nodes[num]){
           return nodes[num] = find(nodes[num]);
       }
       return nodes[num];
    }

    static boolean checkSame(int a, int b){
        return find(a) == find(b);
    }


}
