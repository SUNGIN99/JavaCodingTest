import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        int[][] times = new int[records.length][2];
        String[] carNum = new String[records.length];
        String[] inout = new String[records.length];
        
        // times: 차량 출입 시간, carNum : 차 번호 문자열, inout: 출입 여부
        for(int i = 0; i<records.length; i++){
            String[] rSub = records[i].split(" ");
            String[] time = rSub[0].split(":");
            times[i] = new int[]{Integer.parseInt(time[0]), Integer.parseInt(time[1])};
            carNum[i] = rSub[1];
            inout[i] = rSub[2];
        }
        
        // 1) stack의 top이 -1이라면 out 까지 걸린 누적 주차 시간
        // 2) stack의 top이 양수이라면 in한 시간을 24시간에서 분 단위로 전환한 시간
        //   2-1) 양수라면 Out한 시간을 뺀 분 단위를 다시 음수인 누적 주차시간에 더해줌
        HashMap<String, Stack<Integer>> carPaid = new HashMap<>();
        
        for(int i = 0; i<records.length; i++){
            if(!carPaid.containsKey(carNum[i])){
                carPaid.put(carNum[i], new Stack<>());
            }
            
            Stack<Integer> carIoRecord = carPaid.get(carNum[i]);
            
            if(inout[i].equals("IN")){
                carIoRecord.push(times[i][0] * 60 + times[i][1]);
            }else{ // out
                int lastEntered = carIoRecord.pop();
                int diffMin = (times[i][0] * 60 + times[i][1]) - lastEntered;
                
                diffMin *= -1;
                if(!carIoRecord.isEmpty()){
                    diffMin += carIoRecord.pop();
                }
                carIoRecord.push(diffMin);
            }
        }
        
        // 차 번호가 작은 순으로 저장할 누적주차요금 우선순위큐
        PriorityQueue<CarParked> carParked = new PriorityQueue<>();
        
        int t2359 = 23 * 60 + 59;
        for(String numS : carPaid.keySet()){
            Stack<Integer> finalRecord = carPaid.get(numS);
            int parkedMinute = 0;
            if(finalRecord.peek() >= 0){ // 양수라면 IN했지만 23:59 전에 OUT안한 차
                parkedMinute += -1 * (t2359 - finalRecord.pop());
            }
            if(!finalRecord.isEmpty()){ // 최종 누적 주차 Minute
                parkedMinute += finalRecord.pop();
            }
            
            parkedMinute *= -1; // 누적 주차시간을 판별하기 위해 놔뒀던 음수를 양수로 전환
            int charge = fees[1];
            if (parkedMinute > fees[0]){ // 주차 요금 계산
                charge += Math.ceil((double) (parkedMinute - fees[0]) / fees[2]) * fees[3];
            }
            
            carParked.add(new CarParked(numS, Integer.parseInt(numS), charge));
        }
        
        int[] answer = new int[carPaid.size()];
        for(int i = 0; i<carPaid.size(); i++){
            answer[i] = carParked.poll().charge;
        }
        
        
        return answer;
    }
    
    public class CarParked implements Comparable<CarParked>{
        String carNum_s;
        int carNum_i;
        int charge;
        
        public CarParked(String carNum_s, int carNum_i, int charge){
            this.carNum_s = carNum_s;
            this.carNum_i = carNum_i;
            this.charge = charge;
        }
        
        public int compareTo(CarParked c){
            return this.carNum_i - c.carNum_i;
        }
    }
}