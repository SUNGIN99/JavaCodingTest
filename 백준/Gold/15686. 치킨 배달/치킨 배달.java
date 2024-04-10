import java.util.*;
import java.io.*;

public class Main{

    static class Location{
        int row, col;
        Location(int r, int c){
            this.row = r;
            this.col = c;
        }
        public String toString(){
            return "["+row + ", " + col +"] ";
        }
    }
    static int n, m;
    static ArrayList<Location> chicken;
    static ArrayList<Location> houses;
    static int minCityChickenD = Integer.MAX_VALUE;

    static ArrayList<ArrayList<Integer>> eachChickenDistance;
    public static void main(String[] args) throws Exception{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        chicken = new ArrayList<>();
        houses = new ArrayList<>();

        eachChickenDistance = new ArrayList<>();
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                int info = Integer.parseInt(st.nextToken());
                if(info == 1){ // house
                    houses.add(new Location(i,j));
                }else if (info == 2){ // info
                    chicken.add(new Location(i, j));
                }
            }
        }

        for(Location chick : chicken){
            int chickenX = chick.row;
            int chickenY = chick.col;
            ArrayList<Integer> distance = new ArrayList<>();
            int oneSurvive = 0;
            for(Location house : houses){
                int houseX = house.row;
                int houseY = house.col;
                int dist = getDist(chickenX, chickenY, houseX, houseY);
                distance.add(dist);
                oneSurvive += dist;
            }

            minCityChickenD = Math.min(minCityChickenD, oneSurvive);
            eachChickenDistance.add(distance);
            //System.out.println(distance);
        }

        //System.out.println(minCityChickenD);

        for(int i = 0; i<chicken.size(); i++){
            selectChicken(i, 1, eachChickenDistance.get(i));
        }

        System.out.println(minCityChickenD);
    }

    static void selectChicken(int start, int count, ArrayList<Integer> prevDistance){
        if(count + 1 > m){
            return;
        }

        for(int i = start+1; i<chicken.size(); i++){
            ArrayList<Integer> newDistance = new ArrayList<>();

            int minDistance = 0;
            ArrayList<Integer> currDistance = eachChickenDistance.get(i);
            //System.out.println(currDistance);
            for(int j = 0; j<currDistance.size(); j++){
                newDistance.add(
                        Math.min(prevDistance.get(j), currDistance.get(j)));
                minDistance += newDistance.get(j);
            }

            if(minDistance < minCityChickenD){
                //System.out.println(start + ", " + i + ": " + newDistance);
                minCityChickenD  = minDistance;
            }
            selectChicken(i, count+1, newDistance);


        }
    }


    static int getDist(int x1, int y1, int x2, int y2){
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }



}