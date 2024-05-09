import java.util.*;
import java.io.*;

public class Main{

    static class Node{
        int value;
        Node left, right;

        Node(int v){
            this.value = v;
        }

        void insert(int val){
            if(value > val){ //-> 왼쪽
                if(left != null){
                    left.insert(val);
                }else{
                    left = new Node(val);
                }
            }else{ // value < val -> 오른쪽
                if(right != null){
                    right.insert(val);
                }else{
                    right = new Node(val);
                }
            }
        }

        void prefix(){
            System.out.println(value);
            if(left != null){
                left.prefix();
            }

            if(right != null){
                right.prefix();
            }
        }

        void postfix(){
            if(left != null){
                left.postfix();
            }

            if(right != null){
                right.postfix();
            }

            System.out.println(value);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());


        StringTokenizer st = new StringTokenizer(br.readLine());
        Node root = new Node(Integer.parseInt(st.nextToken()));
        String key;
        //st = new StringTokenizer(br.readLine());
        while((key = br.readLine()) != null){
            root.insert(Integer.parseInt(key));
        }

        //root.prefix();
        //System.out.println();
        root.postfix();
    }

}