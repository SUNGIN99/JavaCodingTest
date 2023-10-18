import java.util.*;

class Solution {
    HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
    int answer = 0;
    public int solution(String numbers) {
        
        getNums(numbers, "");
        
        return answer;
    }
    
    // 모든 숫자 브르투 포스
    public void getNums(String numbers, String prime){
        for(int i = 0; i<numbers.length(); i++){
            // 현재 만들 수 있는 숫자 = 이전에 만든 숫자 + 현재 index 숫자
            Integer numMade = Integer.parseInt(prime + numbers.substring(i,i+1));
            
            // 현재 숫자를 제외한 숫자 목록 설정
            String nextPrime = numbers.substring(0,i);
            
            // 이미 탐색한 숫자인지 확인
            if(!hash.containsKey(numMade)){
                hash.put(numMade, 0);
                System.out.println(numMade);
                
                if(isPrime(numMade)){// 소수 판별
                    answer++;
                }                
            }
            // 숫자 목록 나를 제외하고 추가 (만일 현재 숫자 뒤쪽이 문자열의 끝이 아닐때)
            if(i+1 < numbers.length()){
                nextPrime = nextPrime + numbers.substring(i+1, numbers.length());
                // nextPrime = 0~i-1  + i+1 ~ len-1 (i만 제외)
            }
            
            if(!nextPrime.equals(""))
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