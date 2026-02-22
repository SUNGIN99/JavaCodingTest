import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static Building[] building;
    static int[][] see;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        building = new Building[N];
        see  = new int[N][3];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            building[i] = new Building(i, Integer.parseInt(st.nextToken()));
            see[i] = new int[3];
        }

        Stack<Building> stack = new Stack<>();
        for(int i = 0; i<N; i++){

            if(stack.isEmpty()){
                stack.push(building[i]);
                continue;
            }

            Building peek = stack.peek();
            if(peek.getHeight() < building[i].getHeight()){ // 더 높은 빌딩이 들어오면, 그전에 빌딩까지 1 씩 높여준다
                Building top = null;
                while(!stack.isEmpty()){

                    if(stack.peek().getHeight() < building[i].getHeight()){
                        top = stack.pop();
                    }else{
                        break;
                    }
                }

                if(!stack.isEmpty()){ // top이 나보다 크거나 같을 때,
                    Building a = stack.peek();
                    for(int j = a.index + 1; j<i; j++){
                        see[j][0] ++;
                        if(see[j][1] == 0){
                            see[j][1] = building[i].getIndex() + 1;
                            see[j][2] = building[i].getIndex() - j;
                        }else{
                            if(see[j][2] <= building[i].getIndex() - j){

                            }else {
                                see[j][1] = building[i].getIndex() + 1;
                                see[j][2] = building[i].getIndex() - j;
                            }
                        }
                    }

                    if(a.getHeight() == building[i].getHeight()){
                        stack.pop();
                    }


                    see[i][0] += stack.size();
                    if(!stack.isEmpty()){
                        see[i][1] = stack.peek().getIndex() + 1;
                        see[i][2] = building[i].getIndex() - stack.peek().getIndex();
                    }

                    stack.push(building[i]);

                }else{
                    for(int j = 0; j<i; j++){
                        see[j][0]++;
                        if(see[j][1] == 0){
                            see[j][1] = building[i].getIndex() + 1;
                            see[j][2] = building[i].getIndex() - j;
                        }else{
                            if(see[j][2] <= building[i].getIndex() - j){

                            }else {
                                see[j][1] = building[i].getIndex() + 1;
                                see[j][2] = building[i].getIndex() - j;
                            }
                        }
                    }

                    see[i][0] += stack.size();
                    stack.push(building[i]);
                }

            } else if (peek.getHeight() == building[i].getHeight()) {
                stack.pop();

                see[i][0] += stack.size();
                if(!stack.isEmpty()){
                    see[i][1] = stack.peek().getIndex() + 1;
                    see[i][2] = building[i].getIndex() - stack.peek().getIndex();
                }
                stack.push(building[i]);
            }else{
                see[i][0] += stack.size();

                if(!stack.isEmpty()){
                    see[i][1] = stack.peek().getIndex() + 1;
                    see[i][2] = building[i].getIndex() - stack.peek().getIndex();
                }

                stack.push(building[i]);
            }

            // 스택에 맨 위가 나보다 클 경우, 스택에 값을 넣는다.
            // 스택에 맨 위가 나보다 작을 경우,
            //     나보다 클 때 까지 스택에 있는 값들을 pop한다.
                    // 나보다 클 때 까지 모두 pop한뒤 스택이 남아 있으면, 그 앞부터 1씩 높이를 증가시킨다.
            //     나보다 클 때까지 스택에 있는 값들을 모두 Pop 했을 경우, 0부터 1씩 높이를 증가시킴

            // 스택에 맨위가 나랑 같을 경우
                // 빼고, 그냥 넣은후 넘어간다.

        }

        //System.out.println(Arrays.toString(see));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i<N; i++){
            if(see[i][0] != 0){
                bw.write(see[i][0] + " " + see[i][1] + "\n");
            }else{
                bw.write("0\n");
            }
        }

        bw.flush();
        bw.close();
    }

    static class Building {
        int index, height;

        Building(int i, int h){
            this.index = i;
            this.height = h;
        }

        int getIndex(){
            return this.index;
        }

        int getHeight(){
            return this.height;
        }
    }
}