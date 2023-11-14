
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static String[] grades;
    static class Student implements Comparable<Student>{
        int seq;
        double score;
        public Student(int seq, double score){
            this.seq = seq;
            this.score = score;
        }

        @Override
        public int compareTo(Student s1){
            return (int) ((this.score - s1.score) * 100) * -1;
        }
    }
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        ArrayList<Double> totalScores = new ArrayList<>();
        grades = new String[]{"A+", "A0", "A-", "B+","B0", "B-" , "C+", "C0", "C-", "D0"};
        int tc = Integer.parseInt(st.nextToken());
        for(int i = 0; i<tc; i++){
            System.out.println("#"+(i+1) + " " + solution());
        }

    }
    public static String solution() throws IOException{
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        PriorityQueue<Student> totalScores = new PriorityQueue<>();
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int mid = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int term = Integer.parseInt(st.nextToken());
            double score = Math.round(getScore(mid, end, term) * 100)/100.0;
            Student s = new Student(i+1, score);
            totalScores.add(s);
        }

        int gradeRange = n/10;
        int seq= 1;
        int gradeIndex = 0;
        while(!totalScores.isEmpty()){
            Student s = totalScores.poll();
            if(s.seq == k){
                //System.out.println(gradeIndex + ", " + k + ", " +grades[gradeIndex]);
                //break;
                return grades[gradeIndex];
            }
            if(seq ++ % gradeRange == 0){
                gradeIndex++;
            }
        }

        return "";
    }
    public static double getScore(int midExam, int endExam, int term){
        double midScore = (double) midExam * 0.35;
        double endScore = (double) endExam * 0.45;
        double termScore = (double) term * 0.20;

        return midScore + endScore + termScore;
    }
}