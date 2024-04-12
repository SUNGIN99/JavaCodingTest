import java.util.*;
import java.io.*;

public class Main{

    static class Circle{
        int radius;
        int[] numbers;
        Circle(int r, int m){
            this.radius = r;
            this.numbers = new int[m];
        }

        public String toString(){
            return "["+radius + " : " + Arrays.toString(numbers)+"]";
        }

        void turnClockSide(int k){
            int m = numbers.length;
            int index = m;
            for(int i = 0; i<k; i++){
                index--;
            }
            int[] shifted = new int[m];
            System.arraycopy(numbers, 0, shifted, k, index);

            int j = 0;
            for(int i = index; i<m; i++){
                shifted[j++] = numbers[i];
            }

            numbers = shifted;
        }

        void turnClockOpSide(int k){
            int n = numbers.length;
            int index = 0;
            for(int i = 0; i<k; i++){
                index++;
            }

            int[] shifted = new int[m];
            System.arraycopy(numbers, index, shifted, 0, m - index);
            int j = m - index;
            for(int i = 0; i<index; i++){
                shifted[j++] = numbers[i];
            }

            numbers = shifted;
        }
    }

    static int n, m, t;
    static Circle[] circle;
    static int[][] turnInfo;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        circle = new Circle[n+1];
        for(int i = 1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            circle[i] = new Circle(i, m);
            for(int j = 0; j<m; j++){
                circle[i].numbers[j] = Integer.parseInt(st.nextToken());
            }
        }

        turnInfo = new int[t][];
        for(int i = 0; i<t; i++){
            st = new StringTokenizer(br.readLine());
            turnInfo[i] = new int[3];
            for(int j = 0; j<3; j++){
                turnInfo[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int[] turns : turnInfo){
            int x = turns[0];
            int d = turns[1];
            int k = turns[2];

            for(int i = 1; i<=n; i++){
                if (i % x == 0){
                    if(d == 0){
                        circle[i].turnClockSide(k);
                    }else{
                        circle[i].turnClockOpSide(k);
                    }
                }
            }

            numberDelete();
            /*for(int i = 1; i<=n; i++){
                System.out.println(circle[i]);
            }
            System.out.println("---");*/
        }

        int result = 0;
        for(int i = 1; i<=n; i++){
            for(int j = 0; j<m; j++){
                result += circle[i].numbers[j];
            }
        }

        System.out.println(result);
    }

    static void numberDelete(){
        boolean[][] connected = new boolean[n+1][m];
        double avg = 0;
        int count = 0;
        int left, right;
        boolean check = true;
        // 1) 원 내부에서 인접한 수 중 같은 번호 찾기
        for(int i = 1; i<=n; i++){
            connected[i] = new boolean[m];
            Circle curC = circle[i];
            int[] nums = curC.numbers;

            for(int j = 0; j<=m-1; j++){
                if(nums[j] == 0){
                    continue;
                }else{
                    avg += nums[j];
                    count++;
                }

                if(j == m-1){
                    if(nums[j] == nums[0]){
                        connected[i][j] = connected[i][0] = true;
                        check = false;
                    }
                }else{
                    if(nums[j] == nums[j+1]){
                        connected[i][j] = connected[i][j+1] = true;
                        check = false;
                    }
                }
            }
        }

        avg = avg / (double)count;

        // 다른 원에 접한 같은 수 찾기
        for(int i = 0; i<m; i++){
            for(int j = 1; j<n; j++){
                if(circle[j].numbers[i] != 0 && circle[j].numbers[i] == circle[j+1].numbers[i]){
                    connected[j][i] = connected[j+1][i] = true;
                    check = false;
                }
            }
        }

        if(check){
            for(int i = 1; i<=n; i++){
                Circle curC = circle[i];
                int[] nums = curC.numbers;
                for(int j = 0; j<m ;j++){
                    if(nums[j] == 0){
                        continue;
                    }
                    if((double)nums[j] > avg){
                        nums[j]--;
                    }else if((double)nums[j] < avg){
                        nums[j]++;
                    }
                }
            }
        }else{
            for(int i = 1; i<=n; i++){
                for(int j = 0; j<m; j++){
                    if(connected[i][j]){
                        circle[i].numbers[j] = 0;
                    }
                }
            }
        }


    }



}