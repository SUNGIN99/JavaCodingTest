import java.io.*;
import java.util.*;

public class Main {


    static class Bus implements Comparable<Bus>{
        int target;
        long pay;
        String path;
        Bus(int t, long pay, String path){
            this.target = t;
            this.pay = pay;
            this.path = path;
        }

        public int compareTo(Bus b){
            if(this.pay - b.pay < 0){
                return -1;
            }else if(this.pay - b.pay == 0){
                return 0;
            }else{
                return 1;
            }
        }
    }
    static int n, m;
    // 똑같은 경로에 서로 다른 비용의 간선이 2개 주어질 수도 있기 때문에 PriorityQueue 적용
    static HashMap<Integer,PriorityQueue<Bus>> busInfo;

    public static void main(String[] args) throws Exception {

        // 버스 출발 도시 번호, 도착 도시 번호, 버스 비용
        // 최소 버스비용과 경로
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        busInfo = new HashMap<>();

        int from, to, pay;
        for(int j = 0; j<m; j++){
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            pay = Integer.parseInt(st.nextToken());

            PriorityQueue<Bus> nextBuses = busInfo.getOrDefault(from, new PriorityQueue<Bus>());

            nextBuses.add(new Bus(to, pay, null));
            busInfo.put(from, nextBuses);
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start, end);
        System.out.println(minPay);
        String[] splitPath = minPath.split(",");
        System.out.println(splitPath.length);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(String v : splitPath){
            bw.write(v + " ");
        }
        bw.flush();
        bw.close();

    }

    static long minPay = Long.MAX_VALUE;
    static String minPath = "";
    static long[] minPathValues;
    static void dijkstra(int start, int end){
        minPathValues = new long[n+1];
        for(int i = 1; i<=n; i++){
            minPathValues[i] = Long.MAX_VALUE;
        }

        PriorityQueue<Bus> queue = new PriorityQueue<>();
        queue.add(new Bus(start, 0, start+","));
        minPathValues[start] = 0;

        while(!queue.isEmpty()){
            Bus curBus = queue.poll();

            int to = curBus.target;
            long pay = curBus.pay;
            String path = curBus.path;
            PriorityQueue<Bus> nextBuses = busInfo.get(to);
            if(nextBuses == null){
                continue;
            }
            for(Bus nextBus : busInfo.get(to)){
                long nextPay = pay + nextBus.pay;
                if(nextPay >= minPay){
                    continue;
                }

                if(nextBus.target == end){
                    if(nextPay < minPay){
                        minPay = nextPay;
                        minPath = path + nextBus.target;
                    }
                }else{
                    //https://www.acmicpc.net/board/view/115847
                    //https://www.acmicpc.net/board/view/130728
                    if(minPathValues[nextBus.target] > nextPay && nextPay <minPay){
                        minPathValues[nextBus.target] = nextPay;
                        queue.add(new Bus(nextBus.target, nextPay, path + nextBus.target + ","));
                    }
                }
            }

            //System.out.println(Arrays.toString(minPathValues));
        }
    }

}
