import java.util.*;
import java.io.*;
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int[][] road = new int[n][n];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                road[i][j] = Integer.parseInt(st.nextToken());
            }
            //System.out.println(Arrays.toString(road[i]));
        }

        int result = 0;
        int valid;
        for(int i = 0; i<n; i++){
            valid = 1;
            int j = 0;
            while(j < n-1){
                if(road[i][j] == road[i][j+1]){
                    valid++;
                    j++;
                }else{
                    if(Math.abs(road[i][j] - road[i][j+1]) > 1){
                        break;
                    }else{
                        if(road[i][j] > road[i][j+1]){
                            int stair = road[i][j+1];
                            int k = 1;
                            boolean check = false;
                            while(k <= l){
                                if(j + k >= n){
                                    check = true;
                                    break;
                                }
                                if(road[i][j+k] != stair){
                                    check = true;
                                    break;
                                }
                                k++;
                            }
                            if(check){
                                valid = 0;
                                break;
                            }else{
                                j += k-1;
                                valid = 0;
                            }
                        }else{ // road[i][j] < road[i][j+1]
                            if(valid >= l){
                                j++;
                                valid = 1;
                            }else{
                                valid = 1;
                                break;
                            }
                        }
                    }
                }
            }
            if(j>=n-1){
                //System.out.println("a: " + i);
                result++;
            }
        }

        //System.out.println(result);

        for(int i = 0; i<n; i++){
            valid = 1;
            int j = 0;
            while(j < n-1){
                if(road[j][i] == road[j+1][i]){
                    valid++;
                    j++;
                }else{
                    if(Math.abs(road[j][i] - road[j+1][i]) > 1) {
                        break;
                    }else{
                        if(road[j][i] > road[j+1][i]){
                            int stair = road[j+1][i];
                            int k = 1;
                            boolean check = false;
                            while(k <= l){
                                if(j + k >= n){
                                    check = true;
                                    break;
                                }
                                if(road[j+k][i] != stair){
                                    check = true;
                                    break;
                                }
                                k++;
                            }
                            if(check){
                                valid = 0;
                                break;
                            }else{
                                j += k-1;
                                valid = 0;
                            }
                        }else{
                            if(valid >= l){
                                j++;
                                valid = 1;
                            }else{
                                valid = 1;
                                break;
                            }
                        }
                    }

                }
            }
            if(j>=n-1){
                //System.out.println("b: " + i);
                result++;
            }
        }
        System.out.println(result);
    }

}
