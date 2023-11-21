import java.util.*;

class Solution {
    
    Deque<Integer> firstCol, lastCol;
    Deque<Deque<Integer>> linkedRows;
    int maxRow, maxCol;
    public int[][] solution(int[][] rc, String[] operations) {
        firstCol = new LinkedList<>();
        lastCol = new LinkedList<>();
        
        maxRow = rc.length;
        maxCol = rc[0].length;
        
        linkedRows = new LinkedList<>();
        
        for(int i = 0; i<maxRow; i++){
            Deque<Integer> row = new LinkedList<>();
            
            for(int j = 0; j<maxCol; j++){
                if(j == 0)
                    firstCol.add(rc[i][j]);
                else if(j == maxCol -1)
                    lastCol.add(rc[i][j]);
                else{
                    row.add(rc[i][j]);
                }
            }
            linkedRows.add(row);
        }
        
        for(String op : operations){
            if(op.equals("ShiftRow"))
                shiftRow();
            else{
                if(linkedRows.size() == 0){
                    rotate2();
                }else{
                    rotate1();
                }
            }
        }
        
        //printM();
        
        int [][]answer = new int[maxRow][maxCol];
        for(int i = 0; i<maxRow; i++){
            answer[i][0] = firstCol.pollFirst();
            
            Deque<Integer> row = linkedRows.pollFirst();
            for(int j = 1; j<=maxCol-2; j++){
                answer[i][j] = row.pollFirst();
            }
            
            answer[i][maxCol-1] = lastCol.pollFirst();
        }
        
        return answer;
    }
    
    public void printM(){
        System.out.println(firstCol);
        for(Deque<Integer> i : linkedRows){
            System.out.println(i);
        }
        System.out.println(lastCol);
        System.out.println();
    }
    
    //https://tech.kakao.com/2022/07/13/2022-coding-test-summer-internship/
    public void shiftRow(){
        firstCol.addFirst(firstCol.pollLast());
        lastCol.addFirst(lastCol.pollLast());
        linkedRows.addFirst(linkedRows.pollLast());
    }
    
    public void rotate1(){
        linkedRows.getFirst().addFirst(firstCol.pollFirst());
        lastCol.addFirst(linkedRows.getFirst().pollLast());
        linkedRows.getLast().addLast(lastCol.pollLast());
        firstCol.addLast(linkedRows.getLast().pollFirst());
    }
    
    public void rotate2(){
        lastCol.addFirst(linkedRows.getFirst().pollLast());
        firstCol.addLast(linkedRows.getLast().pollFirst());
    }
}

/*class Solution {
    LinkedList <Deque<Integer>> matrixLinked;
    ArrayList <Deque<Integer>> matrixList;
    int maxRow, maxCol;
    int[][] answer;
    public ArrayList <Deque<Integer>> solution(int[][] rc, String[] operations) {
        matrixLinked = new LinkedList<>();
        for(int i = 0; i<rc.length; i++){
            Deque<Integer> temp = new LinkedList<>();
            for(int j = 0; j<rc[i].length; j++){
                temp.add(rc[i][j]);
            }
            matrixLinked.add(temp);
        }
        
        matrixList = new ArrayList<>(matrixLinked);
        //printM();
        maxRow = rc.length;
        maxCol = rc[0].length;
        
        for(String op : operations){
            if(op.equals("ShiftRow"))
                shiftRow();
            else
                rotate();
        }
        
        
        return matrixList;
    }
    
    public void printM(){
        for(Deque<Integer> i : matrixLinked){
            System.out.println(i);
        }
        System.out.println();
    }
    
    public void shiftRow(){
        matrixLinked.addFirst(matrixLinked.pollLast());
        matrixList = new ArrayList<>(matrixLinked);
    }
    
    public void rotate(){
        int leftTop = matrixLinked.getFirst().getFirst();
        int rightTop = matrixLinked.getFirst().getLast();
        int leftBot = matrixLinked.getLast().getFirst();
        int rightBot = matrixLinked.getLast().getLast();
        
        for(int i = 0; i<maxRow-1; i++){
            matrixList.get(i).addFirst(matrixList.get(i+1).pollFirst());
        }
        
        for(int i = maxRow-1; i> 0; i--){
            matrixList.get(i).addLast(matrixList.get(i-1).pollLast());
        }
        
    }
        
}*/