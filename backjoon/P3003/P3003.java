package backjoon.P3003;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class P3003 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>(
                Stream.of(1, 1, 2, 2, 2, 8)
                        .collect(Collectors.toList())
        );

        for (int i = 0; i<6; i++){
            a.add(sc.nextInt());
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i<6; i++){
            result.add(b.get(i) - a.get(i));
        }

        result.forEach(v -> System.out.print(v+ " ") );

        //System.out.println(result);
    }
}
