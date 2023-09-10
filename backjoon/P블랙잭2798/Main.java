package backjoon.P블랙잭2798;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        ArrayList<Integer> inputs = new ArrayList<>();
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //String inputs[] = br.readLine().split(" ");
        for(int i = 0; i<N; i++){
            inputs.add(sc.nextInt());
        }
        //System.out.println(inputs);


        // //System.out.println("결과 : " +num(M, inputs, 0, 0));
        System.out.println(num(M, inputs, 0, 0));
    }

    public static int num(int M, ArrayList<Integer> cards, int score, int count){
        int totalScore = score;
        ArrayList<Integer> temp = (ArrayList<Integer>) cards.clone();
        System.out.println(cards);

        if (M < score || count > 3){
            return -1;
        }
        else if (count == 3 && M >= totalScore){
            System.out.println(totalScore);
            System.out.println();
            return totalScore;
        }
        else if(count == 3){
            return -1;
        }

        int i = 0, select, result = 0;
        while(cards.size() != 0){
            // 1. 선택한 카드를 뺼 준비
            temp = cards;

            // 2. 선택한 카드 값 +
            select = temp.get(0);
            // 3. 선택한 카드 카드 선택지에서 빼기
            temp.remove(0);

            if(score + select < M)
                result = num(M, (ArrayList<Integer>) temp.clone(), score + select, count + 1);
            else{
                continue;
            }

            if(totalScore < result){
                System.out.println(temp);
                totalScore = result;
            }
        }
        return totalScore;
    }
}
