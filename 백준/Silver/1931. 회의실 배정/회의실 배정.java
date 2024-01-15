import java.util.*;
import java.io.*;

public class Main {
    static class Meeting implements Comparable<Meeting>{
        int hour;
        int start;
        int end;

        @Override
        public int compareTo(Meeting m1){
            if(this.start < m1.start){
                return -1;
            }else if(this.start > m1.start){
                return 1;
            }else{
                return this.end - m1.end;
            }
        }

        Meeting (int start, int end){
            this.start = start;
            this.end = end;
            this.hour = end - start;
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 배달할 설탕의 무게 (kg)
        int n = Integer.parseInt(st.nextToken());
        int start, end;

        Meeting[] meetings = new Meeting[n];

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            meetings[i] = new Meeting(start, end);
        }

        Arrays.sort(meetings);


        Deque<Meeting> meetingList = new LinkedList<>();
        for(int i = 0; i<n; i++){
            //System.out.println(meetings[i].start + ", " + meetings[i].end + ", " + meetings[i].hour);

            if(meetingList.isEmpty()){
                meetingList.add(meetings[i]);
            }

            else{
                Meeting currMeeting = meetingList.getLast();

                if (meetings[i].start < currMeeting.end) {
                    if(meetings[i].end < currMeeting.end){
                        meetingList.removeLast();
                        meetingList.addLast(meetings[i]);
                    }
                }else{
                    meetingList.addLast(meetings[i]);
                }
            }
        }

        System.out.println(meetingList.size());
    }




}