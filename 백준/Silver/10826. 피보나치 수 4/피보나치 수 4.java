import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        BigInteger[] num = new BigInteger[10001];

        num[0] = BigInteger.valueOf(0);
        num[1] = BigInteger.valueOf(1);

        for(int i = 2; i<=n; i++){
            num[i] = num[i-1].add(num[i-2]);
        }

        System.out.println(num[n]);
    }

}