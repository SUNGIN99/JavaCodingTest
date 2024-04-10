import java.util.*;
import java.io.*;
public class Main {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());

        for(int i = 0; i<t; i++){
            st = new StringTokenizer(br.readLine());
            int turnCount = Integer.parseInt(st.nextToken());
            ArrayList<String[]> turnType = new ArrayList<>();
            String[] turns = br.readLine().split(" ");
            for(int j = 0; j<turnCount; j++){
                turnType.add(turns[j].split(""));
            }
            cube(turnCount, turnType);
        }

        bw.flush();
        bw.close();
    }

    static Deque<String>[] up, down,front, back, left, right;
    static void initCube(){
        up = new Deque[3];
        down = new Deque[3];
        front = new Deque[3];
        back = new Deque[3];
        left = new Deque[3];
        right = new Deque[3];

        for(int i = 0; i<3; i++){
            up[i] = new LinkedList<>();
            down[i] = new LinkedList<>();
            front[i] = new LinkedList<>();
            back[i] = new LinkedList<>();
            left[i] = new LinkedList<>();
            right[i] = new LinkedList<>();
        }

        // 흰색은 w, 노란색은 y, 빨간색은 r, 오렌지색은 o, 초록색은 g, 파란색은 b.
        // 윗 면은 흰색, 아랫 면은 노란색, 앞 면은 빨간색, 뒷 면은 오렌지색, 왼쪽 면은 초록색, 오른쪽 면은 파란색이다.
        // U: 윗 면, D: 아랫 면, F: 앞 면, B: 뒷 면, L: 왼쪽 면, R: 오른쪽 면이다.
        for(int i = 0; i<3; i++){
            for(int j= 0; j<3; j++){
                up[i].add("w");
                down[i].add("y");
                front[i].add("r");
                back[i].add("o");
                left[i].add("g");
                right[i].add("b");
            }
        }
    }

    static void cube(int turnC, ArrayList<String[]> turns) throws IOException {
        initCube();
        for(String[] turn : turns){
            int turnDir = turn[1].equals("+") ? 1 : -1; // 1 : 시계방향, -1 반시계방향
            if(turn[0].equals("U")){
                turnUp(turnDir);
            }else if(turn[0].equals("D")){
                turnDown(turnDir);
            }else if(turn[0].equals("F")){
                turnFront(turnDir);
            }else if(turn[0].equals("B")){
                turnBack(turnDir);
            }else if(turn[0].equals("L")){
                turnLeft(turnDir);
            }else if(turn[0].equals("R")){
                turnRight(turnDir);
            }
            /*System.out.println("---" + "(" + turn[0] + turn[1] + ")---");
            System.out.println("up");
            for(int i = 0; i<3; i++){
                System.out.println(up[i]);
            }
            System.out.println("down");
            for(int i = 0; i<3; i++){
                System.out.println(down[i]);
            }
            System.out.println("front");
            for(int i = 0; i<3; i++){
                System.out.println(front[i]);
            }
            System.out.println("back");
            for(int i = 0; i<3; i++){
                System.out.println(back[i]);
            }
            System.out.println("left");
            for(int i = 0; i<3; i++){
                System.out.println(left[i]);
            }
            System.out.println("right");
            for(int i = 0; i<3; i++){
                System.out.println(right[i]);
            }

            System.out.println("---");
*/
        }
        // up
        for(int i = 0; i<3; i++){
            for(String r : up[i]){
                bw.write(r);
            }
            bw.write("\n");
        }


    }

    static void turnRight(int turnDir){
        if(turnDir == -1){ // 반시계
            right[2].addFirst(right[1].pollFirst());
            right[2].addFirst(right[0].pollFirst());
            right[1].addFirst(right[0].pollFirst());
            right[0].addLast(right[1].pollLast());
            right[0].addLast(right[2].pollLast());
            right[1].addLast(right[2].pollLast());

            Deque<String> frontLast = new LinkedList<>();
            Deque<String> backFirst = new LinkedList<>();
            Deque<String> upLast = new LinkedList<>();
            Deque<String> downLast = new LinkedList<>();

            for(int i = 0; i<3; i++){
                frontLast.add(front[i].pollLast());
                backFirst.add(back[i].pollFirst());
                upLast.addLast(up[i].pollLast());
                downLast.addLast(down[i].pollLast());
            }

            for(int i = 0; i<3; i++){
                up[i].addLast(backFirst.pollLast());
                back[i].addFirst(downLast.pollFirst());
                down[i].addLast(frontLast.pollLast());
                front[i].addLast(upLast.pollFirst());
            }


        }else{ // 시계
            right[2].addLast(right[1].pollLast());
            right[2].addLast(right[0].pollLast());
            right[1].addLast(right[0].pollLast());
            right[0].addFirst(right[1].pollFirst());
            right[0].addFirst(right[2].pollFirst());
            right[1].addFirst(right[2].pollFirst());

            Deque<String> frontLast = new LinkedList<>();
            Deque<String> backFirst = new LinkedList<>();
            Deque<String> upLast = new LinkedList<>();
            Deque<String> downLast = new LinkedList<>();

            for(int i = 0; i<3; i++){
                frontLast.add(front[i].pollLast());
                backFirst.add(back[i].pollFirst());
                upLast.addLast(up[i].pollLast());
                downLast.addLast(down[i].pollLast());
            }

            for(int i = 0; i<3; i++){
                up[i].addLast(frontLast.pollFirst());
                back[i].addFirst(upLast.pollLast());
                down[i].addLast(backFirst.pollFirst());
                front[i].addLast(downLast.pollLast());
            }
        }
    }

    static void turnLeft(int turnDir){
        if(turnDir == -1){ // 반시계
            left[2].addFirst(left[1].pollFirst());
            left[2].addFirst(left[0].pollFirst());
            left[1].addFirst(left[0].pollFirst());
            left[0].addLast(left[1].pollLast());
            left[0].addLast(left[2].pollLast());
            left[1].addLast(left[2].pollLast());

            Deque<String> frontFirst = new LinkedList<>();
            Deque<String> backLast = new LinkedList<>();
            Deque<String> upFirst = new LinkedList<>();
            Deque<String> downFirst = new LinkedList<>();

            for(int i = 0; i<3; i++){
                frontFirst.add(front[i].pollFirst());
                backLast.add(back[i].pollLast());
                upFirst.addLast(up[i].pollFirst());
                downFirst.addLast(down[i].pollFirst());
            }

            for(int i = 0; i<3; i++){
                up[i].addFirst(frontFirst.pollFirst());
                back[i].addLast(upFirst.pollLast());
                down[i].addFirst(backLast.pollFirst());
                front[i].addFirst(downFirst.pollLast());
            }


        }else{ // 시계
            left[2].addLast(left[1].pollLast());
            left[2].addLast(left[0].pollLast());
            left[1].addLast(left[0].pollLast());
            left[0].addFirst(left[1].pollFirst());
            left[0].addFirst(left[2].pollFirst());
            left[1].addFirst(left[2].pollFirst());

            Deque<String> frontFirst = new LinkedList<>();
            Deque<String> backLast = new LinkedList<>();
            Deque<String> upFirst = new LinkedList<>();
            Deque<String> downFirst = new LinkedList<>();

            for(int i = 0; i<3; i++){
                frontFirst.add(front[i].pollFirst());
                backLast.add(back[i].pollLast());
                upFirst.addLast(up[i].pollFirst());
                downFirst.addLast(down[i].pollFirst());
            }

            for(int i = 0; i<3; i++){
                up[i].addFirst(backLast.pollLast());
                back[i].addLast(downFirst.pollFirst());
                down[i].addFirst(frontFirst.pollLast());
                front[i].addFirst(upFirst.pollFirst());
                //
            }
        }
    }

    static void turnBack(int turnDir){
        if(turnDir == -1){ // 반시계
            back[2].addFirst(back[1].pollFirst());
            back[2].addFirst(back[0].pollFirst());
            back[1].addFirst(back[0].pollFirst());
            back[0].addLast(back[1].pollLast());
            back[0].addLast(back[2].pollLast());
            back[1].addLast(back[2].pollLast());

            Deque<String> leftFirst = new LinkedList<>();
            Deque<String> rightLast = new LinkedList<>();

            for(int i = 0; i<3; i++){
                leftFirst.addLast(left[i].pollFirst());
                rightLast.addLast(right[i].pollLast());
            }

            for(int i = 0; i<3; i++){
                left[i].addFirst(down[0].pollFirst());
                right[i].addLast(up[0].pollFirst());
            }

            for(int i = 0; i<3; i++){
                up[0].addLast(leftFirst.pollLast());
                down[0].addLast(rightLast.pollLast());
            }

        }else{ // 시계
            back[2].addLast(back[1].pollLast());
            back[2].addLast(back[0].pollLast());
            back[1].addLast(back[0].pollLast());
            back[0].addFirst(back[1].pollFirst());
            back[0].addFirst(back[2].pollFirst());
            back[1].addFirst(back[2].pollFirst());

            Deque<String> leftFirst = new LinkedList<>();
            Deque<String> rightLast = new LinkedList<>();

            for(int i = 0; i<3; i++){
                leftFirst.addLast(left[i].pollFirst());
                rightLast.addLast(right[i].pollLast());
            }

            for(int i = 0; i<3; i++){
                left[i].addFirst(up[0].pollLast());
                right[i].addLast(down[0].pollLast());

            }

            up[0] = rightLast;
            down[0] = leftFirst;

        }
    }

    static void turnFront(int turnDir){
        if(turnDir == -1){ // 반시계
            front[2].addFirst(front[1].pollFirst());
            front[2].addFirst(front[0].pollFirst());
            front[1].addFirst(front[0].pollFirst());
            front[0].addLast(front[1].pollLast());
            front[0].addLast(front[2].pollLast());
            front[1].addLast(front[2].pollLast());

            Deque<String> leftLast = new LinkedList<>();
            Deque<String> rightFirst = new LinkedList<>();
            for(int i = 0; i< 3; i++){
                leftLast.add(left[i].pollLast());
                rightFirst.add(right[i].pollFirst());
            }
            for(int i = 0; i<3; i++){
                left[i].addLast(up[2].pollLast());
                right[i].addFirst(down[2].pollLast());
            }

            up[2] = rightFirst;
            down[2] = leftLast;

        }else{ // 시계
            front[2].addLast(front[1].pollLast());
            front[2].addLast(front[0].pollLast());
            front[1].addLast(front[0].pollLast());
            front[0].addFirst(front[1].pollFirst());
            front[0].addFirst(front[2].pollFirst());
            front[1].addFirst(front[2].pollFirst());

            Deque<String> leftLast = new LinkedList<>();
            Deque<String> rightFirst = new LinkedList<>();
            for(int i = 0; i<3; i++){
                leftLast.addLast(left[i].pollLast());
                rightFirst.addLast(right[i].pollFirst());
            }
            for(int i = 0; i<3; i++){
                left[i].addLast(down[2].pollFirst());
                right[i].addFirst(up[2].pollFirst());
            }

            for(int i = 0; i<3; i++){
                up[2].addLast(leftLast.pollLast());
                down[2].addLast(rightFirst.pollLast());
            }
        }
    }

    static void turnDown(int turnDir){
        if(turnDir == -1){// 밑면 반시계 = 윗면 시계
            down[2].addLast(down[1].pollLast());
            down[2].addLast(down[0].pollLast());
            down[1].addLast(down[0].pollLast());
            down[0].addFirst(down[1].pollFirst());
            down[0].addFirst(down[2].pollFirst());
            down[1].addFirst(down[2].pollFirst());

            Deque<String> front0 = front[2];
            front[2] = right[2];
            right[2] = back[2];
            back[2] = left[2];
            left[2] = front0;

        }else{// 밑면 시계 = 윗면 반시계
            down[2].addFirst(down[1].pollFirst());
            down[2].addFirst(down[0].pollFirst());
            down[1].addFirst(down[0].pollFirst());
            down[0].addLast(down[1].pollLast());
            down[0].addLast(down[2].pollLast());
            down[1].addLast(down[2].pollLast());

            Deque<String> front0 = front[2];
            front[2] = left[2];
            left[2] = back[2];
            back[2] = right[2];
            right[2] = front0;

        }
    }
    static void turnUp(int turnDir){
        if(turnDir == -1){ // 반시계
            up[2].addFirst(up[1].pollFirst());
            up[2].addFirst(up[0].pollFirst());
            up[1].addFirst(up[0].pollFirst());
            up[0].addLast(up[1].pollLast());
            up[0].addLast(up[2].pollLast());
            up[1].addLast(up[2].pollLast());

            Deque<String> front0 = front[0];
            front[0] = left[0];
            left[0] = back[0];
            back[0] = right[0];
            right[0] = front0;

        }else{ // 시계
            up[2].addLast(up[1].pollLast());
            up[2].addLast(up[0].pollLast());
            up[1].addLast(up[0].pollLast());
            up[0].addFirst(up[1].pollFirst());
            up[0].addFirst(up[2].pollFirst());
            up[1].addFirst(up[2].pollFirst());

            Deque<String> front0 = front[0];
            front[0] = right[0];
            right[0] = back[0];
            back[0] = left[0];
            left[0] = front0;
        }
    }


}
