import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        
        int[] a = new int[str.length()];
        
        for(int i = 0; i<a.length; i++){
            a[i] = Integer.parseInt(str.substring(i,i+1));
        }
        
        for(int i = 0; i<a.length; i++){
            for(int j = i+1; j < a.length; j++){
                if(a[i] < a[j]){
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }

        Arrays.stream(a).forEach(System.out::print);
    }
}