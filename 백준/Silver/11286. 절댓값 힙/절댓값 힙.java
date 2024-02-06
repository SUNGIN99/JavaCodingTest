import java.util.*;
import java.io.*;

public class Main {

    static class Num implements Comparable<Num>{
        int origin;
        int abs;

        public int compareTo(Num n){
            if(this.abs > n.abs){
                return 1;
            }else if(this.abs < n.abs){
                return -1;
            } else{
                return this.origin - n.origin;
            }
        }

        Num(int o, int a){
            this.origin = o;
            this.abs = a;
        }
    }

    public static void main(String[] args) {

        Scanner sc =new Scanner(System.in);
        int n = sc.nextInt();

        PriorityQueue<Num> heap = new PriorityQueue<>();
        for(int i = 0; i<n; i++){
            int num = sc.nextInt();
            if(num == 0){
                if(heap.size() == 0){
                    System.out.println(0);
                }else{
                    System.out.println(heap.poll().origin);
                }
            }else{
                heap.add(new Num(num, Math.abs(num)));
            }
        }

    }

}

