import java.util.*;
import java.io.*;
public class Main {

    static class Node implements Comparable<Node>{
        int num;
        int count;

        Node(int n, int c){
            this.num = n;
            this.count = c;
        }

        public int compareTo(Node n){
            if(this.count == n.count){
                return this.num - n.num;
            }else{
                return this.count - n.count;
            }
        }


    }

    static int[][] arr;

   public static void main(String[] args) throws Exception{

       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());

       int r = Integer.parseInt(st.nextToken());
       int c = Integer.parseInt(st.nextToken());
       int k = Integer.parseInt(st.nextToken());

       arr = new int[3][];
       for(int i = 0; i<3; i++){
           st = new StringTokenizer(br.readLine());
           arr[i] = new int[3];
           for(int j = 0; j<3; j++){
               arr[i][j] = Integer.parseInt(st.nextToken());
           }
       }
       //System.out.println(arr);

       int R = 3;
       int C = 3;
       int time = 0;
       while(time <= 100){
           if(R-1 >= r-1 && C-1 >= c-1){
               if(arr[r-1][c-1] == k){
                   break;
               }
           }

           if(R>=C){
               C = Math.max(C, funcR(R, C));
               setArrSize("R", R, C);
           }else{ //R<C
               R = Math.max(R, funcC(R, C));
           }

           time++;
       }

       /*for(int i = 0; i<R; i++){
           System.out.println(Arrays.toString(arr[i]));
       }*/

       if(time == 101){
           System.out.println(-1);
           return;
       }

       System.out.println(time);
   }

   static void setArrSize(String RorC, int R, int C){
       if(RorC.equals("R")){
           for(int i = 0; i<R; i++){
               int[] setArr = new int[C];
               System.arraycopy(arr[i], 0, setArr, 0, arr[i].length);
               arr[i] = setArr;
           }
       }else{
       }
   }

   static int funcC(int R, int C){
       HashMap<Integer, Node> map;
       PriorityQueue<Node> queue = new PriorityQueue<>();
       int[][] newArr = new int[C][];
       int maxR = R;
       for(int i = 0; i<C; i++){
           map = new HashMap<>();
           for(int j = 0; j<R; j++){
               int num = arr[j][i];
               if(num == 0){
                   continue;
               }
               Node node = map.getOrDefault(num, new Node(num, 0));
               node.count ++;
               map.put(num, node);
           }
           for(Integer n : map.keySet()){
               queue.add(map.get(n));
           }
           int[] sortedArr = new int[map.size() * 2];
           int j = 0;
           while(!queue.isEmpty() && j < 100){
               Node node = queue.poll();
               sortedArr[j] = node.num;
               sortedArr[j+1] = node.count;
               j+=2;
           }
           maxR = Math.max(sortedArr.length, maxR);
           newArr[i] = sortedArr;
       }

       arr = new int [maxR][C];
       for(int i = 0; i<maxR; i++){
           arr[i] = new int[C];
       }

       for(int i = 0; i<C; i++){
           for(int j = 0; j<newArr[i].length; j++){
               arr[j][i] = newArr[i][j];
           }
       }
       return maxR;
   }

   static int funcR(int R, int C){
       HashMap<Integer, Node> map;
       PriorityQueue<Node> queue = new PriorityQueue<>();
       int[][] newArr = new int[R][C];
       int maxC = C;
       for(int i = 0; i<R; i++){
           map = new HashMap<>();
           for(Integer num : arr[i]){
               if(num == 0){
                   continue;
               }
               Node node = map.getOrDefault(num, new Node(num, 0));

               node.count++;
               map.put(num, node);
           }
           for(Integer n : map.keySet()){
               queue.add(map.get(n));
           }

           int[] sortedArr = new int[map.size() * 2];
           int j = 0;
           while(!queue.isEmpty() && j < 100){
               Node node = queue.poll();
               sortedArr[j] = node.num;
               sortedArr[j+1] = node.count;
               j+=2;
           }

           maxC = Math.max(sortedArr.length, maxC);
           newArr[i] = sortedArr;
       }
       arr = newArr;

       return maxC;
   }
}
