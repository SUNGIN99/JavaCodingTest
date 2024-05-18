import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        Set<Integer> sensor = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            sensor.add(Integer.parseInt(st.nextToken()));
        }

        ArrayList<Integer> sensorArr = new ArrayList<>();
        sensorArr.addAll(sensor);
        Collections.sort(sensorArr);
        Integer[] gaps = new Integer[sensorArr.size()-1];
        for(int i = 0; i<sensorArr.size()-1; i++){
            gaps[i] = sensorArr.get(i+1) - sensorArr.get(i);
        }

        Arrays.sort(gaps, Collections.reverseOrder());
        //System.out.println(Arrays.toString(gaps));
        int result = 0;
        for(int i = m-1; i< gaps.length; i++){
            result += gaps[i];
        }

        System.out.println(result);
    }


}