import java.util.*;
import java.io.*;

class Solution {
    static class Node{
        int enteredTime;
        int weight;

        public Node(int enteredTime, int weight) {
            this.enteredTime = enteredTime;
            this.weight = weight;
        }
    }
    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        Deque<Node> bridge = new LinkedList<>();

        int i = 0;
        int result = 0;
        int currentWeight = 0;

        while(i< truck_weights.length || !bridge.isEmpty()){
            //if(i >=  truck_weights.length && bridge.isEmpty())
             //   break;

            // 다리를 트럭이 완전히 건넘
            if(!bridge.isEmpty() && bridge_length == result - bridge.getFirst().enteredTime){
                currentWeight -= bridge.pollFirst().weight;
            }

            // 다리가 비어있을 때 트럭이 건너기 시작함
            if(bridge.isEmpty() && i < truck_weights.length){
                bridge.addLast(new Node(result, truck_weights[i]));
                currentWeight += truck_weights[i];
                i++;
            }else{ //다리가 비지 않았을 때
                // 트럭이 올라가도 다리가 버틸 수 있고, 다리가 꽉 차지 않았다면
                if(i < truck_weights.length && currentWeight + truck_weights[i] <= weight && bridge.size() < bridge_length){
                    bridge.addLast(new Node(result, truck_weights[i]));
                    currentWeight += truck_weights[i];
                    i++;
                }
            }
            result++;
            //System.out.println(result + " " + i + " " + bridge);
        }
        return result;
    }

    
}