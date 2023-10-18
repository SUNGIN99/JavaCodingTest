import java.util.*;

class Solution {
    HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
    int answer = 0;
    public int solution(String numbers) {
        
        getNums(numbers, "");
        
        return answer;
    }
    
    public void getNums(String numbers, String prime){
        for(int i = 0; i<numbers.length(); i++){
            Integer numMade = Integer.parseInt(prime + numbers.substring(i,i+1));
            String nextPrime = numbers.substring(0,i);
            
            if(!hash.containsKey(numMade)){
                hash.put(numMade, 0);
                System.out.println(numMade);
                
                if(isPrime(numMade)){
                    answer++;
                }                
            }
            if(i+1 < numbers.length()){
                    nextPrime = nextPrime + numbers.substring(i+1, numbers.length());
            }
            
            getNums(nextPrime, String.valueOf(numMade));
        }
    }
    
    public boolean isPrime(int num){
        if(num <= 1){
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }
}