import java.util.*;
class Solution {
    class Node implements Comparable<Node>{
        int num, count;
        Node(int n, int c){
            this.num = n;
            this.count = c;
        }
        
        public int compareTo(Node n){
            return n.count - this.count;
        }
    }
    
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        Map<Integer, Integer> howmany = new HashMap<>();
        for(int i = 0; i<tangerine.length; i++){
            howmany.put(
                tangerine[i],
                howmany.getOrDefault(tangerine[i], 0) + 1
            );
        }
        
        PriorityQueue<Node> nodes = new PriorityQueue<>();
        for(Integer key : howmany.keySet()){
            nodes.add(new Node(key, howmany.get(key)));
        }
        
        while(!nodes.isEmpty()){
            Node gyul = nodes.poll();
            answer++;
            if(gyul.count >= k){
                break;
            }else{
                k -= gyul.count;
            }
        }
        
        return answer;
    }
}