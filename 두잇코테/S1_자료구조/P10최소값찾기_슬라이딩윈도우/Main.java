package 두잇코테.S1_자료구조.P10최소값찾기_슬라이딩윈도우;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

/*

 출력을 bufferwriter로 하니까 통과했다...?
 미치겟네 진짜
        //result.add(stack.getFirst().value);

        for (Integer a : result){
            System.out.printf("%d ", a);
        }*/
//https://www.acmicpc.net/problem/11003
public class Main {

    public static final Scanner sc = new Scanner(System.in);

    private static class Node{
        int index;
        int value;

        public Node(int i, int num) {
            this.index = i;
            this.value = num;
        }

        public int getIndex() {
            return index;
        }

        public int getValue() {
            return value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        Deque<Node> mydeque = new LinkedList<>();

        for(int i = 0; i< N; i++){
            int now = Integer.parseInt(st.nextToken());

            while(!mydeque.isEmpty() && mydeque.getLast().value > now){
                mydeque.removeLast();
            }

            mydeque.addLast(new Node(i, now));

            if (mydeque.getFirst().index <= i - L){
                mydeque.removeFirst();
            }

            bw.write(mydeque.getFirst().getValue() + " ");
        }
        bw.flush();
        bw.close();
    }
}

