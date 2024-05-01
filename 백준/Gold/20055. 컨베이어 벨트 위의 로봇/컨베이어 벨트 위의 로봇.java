import java.util.*;
import java.io.*;

public class Main{

    static class Belt{
        int durability;
        boolean robot, zeroCounted;
        Belt left, right;
        int seq;

        Belt(int d, int s){
            this.seq = s;
            this.durability = d;
            this.robot = false;
            this.zeroCounted = false;
        }

        void beltConnect(Belt b){
            this.right = b;
            b.left = this;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int durability;

        Belt topHead = new Belt(-1, 0);
        Belt currBelt = topHead;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            durability = Integer.parseInt(st.nextToken());
            Belt newBelt = new Belt(durability, i+1);

            currBelt.beltConnect(newBelt);
            currBelt = newBelt;
        }

        Belt topTail = new Belt(-1, 0);
        topTail.right = currBelt;

        Belt botTail = new Belt(-1, 0);
        currBelt = botTail;
        for(int i = 0; i<n; i++){
            durability = Integer.parseInt(st.nextToken());
            Belt newBelt = new Belt(durability, n + i + 1);

            currBelt.beltConnect(newBelt);
            currBelt = newBelt;
        }
        topTail.right.beltConnect(botTail.right);

        Belt botHead = new Belt(-1, 0);
        botHead.right = currBelt;
        currBelt.beltConnect(topHead.right);


        int zeroBelt = 0;
        int count = 0;
        while(zeroBelt < k){
            // 1. 한칸 회전
            // 올리는 칸
            count++;
            /*System.out.println("초기 : " + count + ", " + zeroBelt);
            printTopNode(topHead.right, n);
            printBotNode(botHead.right, n);
            System.out.println();*/


            Belt upBelt = botHead.right;
            botHead.right = upBelt.left;
            upBelt.beltConnect(topHead.right);
            //botHead.right.beltConnect(upBelt);
            topHead.right = upBelt;

            // 내리는 칸
            Belt downBelt = topTail.right;
            topTail.right = downBelt.left;
            botTail.right = downBelt;

            if(topTail.right.robot){
                topTail.right.robot = false;
            }

            /*System.out.println("회전");
            printTopNode(topHead.right, n);
            printBotNode(botHead.right, n);
            System.out.println();*/


            // 2. 올라간 로봇부터 다음 칸으로 올라갈 수 있는지 확인하기.
            currBelt = topTail.right;
            int index = 0;
            while(index < n -1){
                if(!currBelt.robot && currBelt.left.robot && !currBelt.zeroCounted){
                    currBelt.robot = true;
                    currBelt.left.robot = false;
                    currBelt.durability --;
                    if(currBelt.durability == 0){
                        currBelt.zeroCounted = true;
                        zeroBelt++;
                        if(zeroBelt >= k){
                            break;
                        }
                    }
                }
                currBelt = currBelt.left;
                index++;
            }

            /*System.out.println("로봇이동");
            printTopNode(topHead.right, n);
            printBotNode(botHead.right, n);
            System.out.println();*/

            if(topTail.right.robot){
                topTail.right.robot = false;
            }

            /*System.out.println("내리기 칸");
            printTopNode(topHead.right, n);
            printBotNode(botHead.right, n);
            System.out.println();*/

            if(!topHead.right.zeroCounted){
                topHead.right.robot = true;
                topHead.right.durability --;
                if(topHead.right.durability == 0){
                    topHead.right.zeroCounted = true;
                    zeroBelt++;
                    if(zeroBelt >= k){
                        break;
                    }
                }
            }

           /* System.out.println("올리기 칸");
            printTopNode(topHead.right, n);
            printBotNode(botHead.right, n);
            System.out.println();*/



            //System.out.println("---");

        }

        System.out.println(count);
    }

    static void printTopNode(Belt start, int n){
        for(int i = 0; i<n; i++){
            if(start.robot){
                System.out.print("(" + start.durability + ", " + "(" + start.seq + ")) ");
            }else{
                System.out.print("(" + start.durability + ", " + start.seq + ") ");
            }
            start = start.right;
        }
        System.out.println();

    }

    static void printBotNode(Belt start, int n){
        for(int i = 0; i<n; i++){
            if(start.robot){
                System.out.print("(" + start.durability + ", " + "(" + start.seq + ")) ");
            }else{
                System.out.print("(" + start.durability + ", " + start.seq + ") ");
            }
            start = start.left;
        }
        System.out.println();
    }





}