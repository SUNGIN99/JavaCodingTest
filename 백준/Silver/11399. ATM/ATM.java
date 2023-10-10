import java.util.*;

public class Main {
  // 삽입 정렬
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] a = new int[n];

        for(int i = 0; i< n; i++){
            a[i] = sc.nextInt();
        }

        for(int i= 1; i<n; i++){
            int selectN = a[i];
            int insertPoint = i;
            //System.out.println("i: "+ i + ", selectN: " + selectN);

            // 값을 삽입할 지점 찾기 (나보다 작은 수 다음)
            for(int j= i-1; j>= 0; j--){
                if(a[j] < a[i]){
                    insertPoint = j + 1;
                    //System.out.println("insert-> i: " + i + ", insertPoint= " + insertPoint);
                    break;
                }
                if (j == 0){
                    insertPoint = 0;
                    //System.out.println("j0-> i: " + i + ", insertPoint= " + insertPoint);
                }
            }
            
            // 한칸 씩 당기기
            for(int j = i; j> insertPoint; j--){
                a[j] = a[j-1];
            }
            
            // 해당 위치에 값 넣기
            a[insertPoint] = selectN;
            //System.out.println(Arrays.toString(a));
            //System.out.println();
        }
        
        // 구간 합 구하기
        int[] s = new int[n];
        s[0] = a[0];
        for(int i = 1; i<n; i++){
            s[i] = s[i-1] + a[i];
        }
        // 돈을 인출하는데 걸리는시간
        int sum = 0;
        for(int i =0; i<n; i++){
            sum += s[i];
        }

        System.out.println(sum);
    }
}
