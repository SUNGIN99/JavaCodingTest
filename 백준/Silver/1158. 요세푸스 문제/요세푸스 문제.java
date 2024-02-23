import java.util.*;
import java.io.*;

public class Main {

    static class Node{
        int num;
        Node left;
        Node right;

        Node (int n){
            this.num = n;
        }

        void link(Node n){
            this.right = n;
            n.left = this;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Node curr = new Node(1);
        Node start = new Node(-1);

        start.link(curr);

        for(int i = 2; i<=n; i++){
            curr.link(new Node(i));
            curr = curr.right;
        }

        curr.link(start.right);
        curr = curr.right;

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write("<");

        int i = 1;
        while(i <= n){
            int j = 1;
            while(j < k) {
                curr = curr.right;
                j++;
            }
            //System.out.println(curr.num);
            if(i != n){
                bw.write(curr.num + ", ");
            }else{
                bw.write(curr.num + ">");
            }

            curr.right.left = curr.left;
            curr.left.right = curr.right;

            curr = curr.right;
            i++;
        }

        bw.flush();
        bw.close();


    }


}

