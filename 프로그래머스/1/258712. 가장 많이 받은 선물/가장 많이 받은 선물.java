import java.util.*;
class Solution {
    public class Friend {
        int[] send;
        int[] recv;
        int giftScore;
        public Friend(int n){
            send = new int[n];
            recv = new int[n];
        }
    }


    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        HashMap<String, Integer> index = new HashMap<>();
        HashMap<Integer, String> names = new HashMap<>();
        Friend[] giftRecord = new Friend[friends.length];
        for(int i = 0; i<friends.length; i++){
            index.put(friends[i], i);
            names.put(i,friends[i]);
            giftRecord[i] = new Friend(friends.length);
        }


        for(String g : gifts){
            String[] fromto = g.split(" "); //from = fromto[0], to = fromto[1];

            int fromIndex = index.get(fromto[0]);
            int toIndex = index.get(fromto[1]);

            giftRecord[fromIndex].giftScore++;
            giftRecord[toIndex].giftScore--;

            giftRecord[fromIndex].send[toIndex]++;
            giftRecord[toIndex].recv[fromIndex]++;
        }

        /*for(Friend f : giftRecord){
            System.out.println(Arrays.toString(f.send));
            System.out.println(Arrays.toString(f.recv));
            System.out.println(f.giftScore);
            System.out.println();
        }*/

        int[] willRecv = new int[giftRecord.length];
        for(int i = 0; i< giftRecord.length; i++){
            int[] send = giftRecord[i].send;
            int[] recv = giftRecord[i].recv;

            for(int j = 0; j<giftRecord.length; j++){
                if(i == j)
                    continue;

                if(send[j] > recv[j]){
                    willRecv[i]++;
                }
                else if (send[j] == recv[j] || (send[j] == 0 && recv[j] == 0)){
                    if(giftRecord[i].giftScore > giftRecord[j].giftScore){
                        willRecv[i]++;
                    }
                }

                answer = Math.max(answer, willRecv[i]);
            }
        }

        System.out.println(Arrays.toString(willRecv));


        return answer;
    }
}