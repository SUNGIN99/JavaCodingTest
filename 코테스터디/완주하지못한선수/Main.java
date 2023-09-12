package 코테스터디.완주하지못한선수;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    // 참가자 수 = 완주자 수 + 1
    // 단 한명 뺴고 모두 완주 가능.

    // participant 길이 1이상 100,000 이하
    // completion 길이 1이상 99,999 이하

    // 시간복잡도 : HashMap O(1) ->
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> whoCompleted = new HashMap<>();

        // O(n) : 최악 10000
        for (int i = 0; i<completion.length; i++){
            whoCompleted.put(completion[i], 1);
        }

        // O(n)
        String result= "";
        for (int i = 0; i<participant.length; i++){
            if (whoCompleted.containsKey(participant[i])) {
                whoCompleted.remove(participant[i]);
            }else{
                return participant[i];
            }
        }

        return whoCompleted.keySet().toString();
    }

    // 시간 복잡도
    public String solution2(String[] participant, String[] completion) {

        Arrays.sort(participant);
        Arrays.sort(completion);
        System.out.println(Arrays.toString(participant));
        System.out.println(Arrays.toString(completion));
        int i = 0, j = 0;
        String result = "";
        while(i< participant.length && j< completion.length){
            if (participant[i].equals(completion[j])){
                i++; j++;
            }
            else {
                return participant[i];
            }
        }
        return participant[i];
    }

    /*
    solution 1
    * 정확성  테스트
테스트 1 〉	통과 (0.04ms, 77MB)
테스트 2 〉	통과 (0.02ms, 66.7MB)
테스트 3 〉	통과 (0.25ms, 79.3MB)
테스트 4 〉	실패 (0.39ms, 77MB)
테스트 5 〉	실패 (0.29ms, 77.4MB)
테스트 6 〉	통과 (0.03ms, 73.8MB)
테스트 7 〉	통과 (0.03ms, 75.8MB)
효율성  테스트
테스트 1 〉	실패 (24.82ms, 81.8MB)
테스트 2 〉	실패 (64.39ms, 89MB)
테스트 3 〉	실패 (36.66ms, 97.4MB)
테스트 4 〉	실패 (30.32ms, 95.8MB)
테스트 5 〉	실패 (31.02ms, 97.6MB)
* */
    /*

    solution 2
    * 채점을 시작합니다.
정확성  테스트
테스트 1 〉	통과 (0.21ms, 74.5MB)
테스트 2 〉	통과 (0.35ms, 84.4MB)
테스트 3 〉	통과 (1.61ms, 73.8MB)
테스트 4 〉	통과 (2.66ms, 72.9MB)
테스트 5 〉	통과 (3.88ms, 74.9MB)
테스트 6 〉	통과 (0.18ms, 80.2MB)
테스트 7 〉	통과 (0.19ms, 75.5MB)
효율성  테스트
테스트 1 〉	통과 (106.84ms, 96.4MB)
테스트 2 〉	통과 (261.11ms, 87.9MB)
테스트 3 〉	통과 (260.72ms, 94.4MB)
테스트 4 〉	통과 (368.67ms, 110MB)
테스트 5 〉	통과 (314.41ms, 96.9MB)*/
}
