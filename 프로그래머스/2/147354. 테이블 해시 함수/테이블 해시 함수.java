import java.util.*;

class Solution {
    
    class Tuple implements Comparable<Tuple>{
        int[] data;
        int col;
        
        Tuple(int[] d, int c){
            this.data = d;
            this.col = c-1;
        }
        
        public String toString(){
            return Arrays.toString(data);
        }
        
        public int compareTo(Tuple t){
            if(t.data[col] == this.data[col]){
                return -1 * (this.data[0] - t.data[0]);
            }else{
                return this.data[col] - t.data[col];
            }
        }
    }
    
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        
        PriorityQueue<Tuple> hash = new PriorityQueue<>();
        for(int i = 0; i<data.length; i++){
            hash.add(new Tuple(data[i], col));
        }
        
        int i = 1;
        ArrayList<Integer> hashValue = new ArrayList<>();
        while(!hash.isEmpty() && i <= row_end){
            int[] sortedData = hash.poll().data;
            if(i>=row_begin && i<=row_end){
               hashValue.add(getHashValue(sortedData, i));
            }
            i++;
        }
        
        //System.out.println(hashValue);
        
        // getXor
        int xor = hashValue.get(0);
        for(i = 1; i<hashValue.size(); i++){
            xor = xor ^ hashValue.get(i);
        }
        
        return answer = xor;
    }
    
    int getHashValue(int[] sortedData, int index){
        int result = 0 ;
        for(Integer d : sortedData){
            result += (d % index);
        }
        return result;
    }
}