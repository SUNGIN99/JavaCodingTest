import java.util.*;
class Solution {
    int[][] answer = new int[2][];
    public class Node implements Comparable<Node>{
        int value;
        int x, y;
        Node leftChild;
        Node rightChild;
        
        @Override
        public int compareTo(Node n){
            if (n.y > this.y){
                return 1;
            }else if (n.y < this. y){
                return -1;
            }
            return 0;
        }
        
        public Node(int value, int x, int y){
            this.value = value;
            this.x = x;
            this.y = y;
            this.leftChild = null;
            this.rightChild = null;
        }
    }
    public int prefix(Node root, int index){
        //System.out.print(root.value + " ");
        answer[0][index++] = root.value;
        if(root.leftChild != null){
            index = prefix(root.leftChild, index);
        }
        if(root.rightChild != null)
            index = prefix(root.rightChild, index);
        
        return index;
    }
    
     public int postfix(Node root, int index){
        int increase = index;
        if(root.leftChild != null){
            increase = postfix(root.leftChild, increase);
            answer[1][increase] = root.value;
        }
        if(root.rightChild != null){
            increase = postfix(root.rightChild, increase);
            answer[1][increase] = root.value;
        }
        answer[1][increase++] = root.value;
        return increase;
        //System.out.print(root.value + " ");
    }
    
    public void add(Node root, Node child){
        if(root.x > child.x){
            if(root.leftChild == null)
                root.leftChild = child;
            else{
                add(root.leftChild, child);
            }
        }else{
            if(root.rightChild == null){
                root.rightChild = child;
            }else{
                add(root.rightChild, child);
            }
        }
    }
    
    public int[][] solution(int[][] nodeinfo) {
        PriorityQueue <Node> nodes = new PriorityQueue<>();
        for(int i = 0; i<nodeinfo.length; i++){
            nodes.offer(new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]));
        }
        answer[0] = new int[nodeinfo.length];
        answer[1] = new int[nodeinfo.length];
        
        Node root = nodes.poll();
        while(nodes.size() != 0){
            Node child = nodes.poll();
            add(root, child);
        }
        
        prefix(root, 0);
        System.out.println();
    
        postfix(root, 0);
        
        return answer;
    }
}