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
import java.io.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

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
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] nums = new int[N];

        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i<N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Deque<Node> stack = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();
        int i = 0;
        while(i < N){

            if (!stack.isEmpty() && stack.getLast().getValue() > nums[i]){
                stack.removeLast();
                continue;
            }
            if(!stack.isEmpty() && stack.getFirst().getIndex() <= i - L){
                stack.removeFirst();
            }

            stack.addLast(new Node(i, nums[i]));
            bw.write(stack.getFirst().getValue() + " ");
            i ++;
        }

        bw.flush();
        bw.close();
    }
}

