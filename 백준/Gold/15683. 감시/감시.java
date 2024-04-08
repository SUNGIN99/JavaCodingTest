import java.util.*;
import java.io.*;
public class Main {

    static class Cctv{
        int x, y;
        int type;
        Cctv(int x, int y, int type){
            this.x = x ;
            this.y = y;
            this.type = type;
        }
    }
    static int n, m;
    static int min = Integer.MAX_VALUE;
    static ArrayList<Cctv> cctv;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        cctv = new ArrayList<>();
        char[][] office = new char[n][m];
        int cctvCount = 0;
        for(int i = 0; i<n; i++){
            office[i] = new char[m];
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<m; j++){
                char num = st.nextToken().toCharArray()[0];
                office[i][j] = num;
                if(num == '0'){
                    cctvCount++;
                }
                if(num >= '1' && num <= '5'){
                    cctv.add(new Cctv(i, j, num - '0'));
                }
            }
        }
        
        if(cctv.isEmpty()){
            System.out.println(cctvCount);
            return;
        }
        

        Cctv first = cctv.get(0);
        if(first.type == 1){
            cctv1(first, office, 0);
        }else if(first.type == 2){
            cctv2(first, office, 0);
        }else if(first.type == 3){
            cctv3(first, office, 0);
        }else if(first.type == 4){
            cctv4(first, office, 0);
        }else if(first.type == 5){
            cctv5(first, office, 0);
        }

        System.out.println(min);


    }

    static void cctv1(Cctv currCctv, char[][] office, int cctvIndex){ // 한 방향
        int x = currCctv.x;
        int y = currCctv.y;
        for(int i = 0; i<4; i++){
            char[][] copiedOffice = new char[n][];
            for(int j = 0; j<n; j++){
                copiedOffice[j] = new char[m];
                System.arraycopy(office[j], 0, copiedOffice[j], 0, m);
            }

            canWatch(copiedOffice, x, y, i);
            if(cctvIndex + 1 >= cctv.size()){
                checkNotObserved(copiedOffice, cctvIndex);
            }else{
                Cctv nextCctv = cctv.get(cctvIndex+1);
                if(nextCctv.type == 1){
                    cctv1(nextCctv, copiedOffice, cctvIndex+1);
                }else if(nextCctv.type == 2){
                    cctv2(nextCctv, copiedOffice, cctvIndex+1);
                }else if(nextCctv.type == 3){
                    cctv3(nextCctv, copiedOffice, cctvIndex+1);
                }else if(nextCctv.type == 4){
                    cctv4(nextCctv, copiedOffice, cctvIndex+1);
                }else if(nextCctv.type == 5){
                    cctv5(nextCctv, copiedOffice, cctvIndex+1);
                }
            }
        }
    }

    static void cctv2(Cctv currCctv, char[][] office, int cctvIndex){ // 한 방향
        int x = currCctv.x;
        int y = currCctv.y;
        for(int i = 0; i<2; i++){
            char[][] copiedOffice = new char[n][];
            for(int j = 0; j<n; j++){
                copiedOffice[j] = new char[m];
                System.arraycopy(office[j], 0, copiedOffice[j], 0, m);
            }

            if(i == 0){ // 위 아래
                canWatch(copiedOffice, x, y, 0);
                canWatch(copiedOffice, x, y, 1);
            }else{ // 좌 우
                canWatch(copiedOffice, x, y, 2);
                canWatch(copiedOffice, x, y, 3);
            }

            if(cctvIndex + 1 >= cctv.size()){
                checkNotObserved(copiedOffice, cctvIndex);
            }else{
                Cctv nextCctv = cctv.get(cctvIndex+1);
                if(nextCctv.type == 1){
                    cctv1(nextCctv, copiedOffice, cctvIndex+1);
                }else if(nextCctv.type == 2){
                    cctv2(nextCctv, copiedOffice, cctvIndex+1);
                }else if(nextCctv.type == 3){
                    cctv3(nextCctv, copiedOffice, cctvIndex+1);
                }else if(nextCctv.type == 4){
                    cctv4(nextCctv, copiedOffice, cctvIndex+1);
                }else if(nextCctv.type == 5){
                    cctv5(nextCctv, copiedOffice, cctvIndex+1);
                }
            }
        }
    }

    static void cctv3(Cctv currCctv, char[][] office, int cctvIndex){ // 한 방향
        int x = currCctv.x;
        int y = currCctv.y;
        for(int i = 0; i<4; i++){
            char[][] copiedOffice = new char[n][];
            for(int j = 0; j<n; j++){
                copiedOffice[j] = new char[m];
                System.arraycopy(office[j], 0, copiedOffice[j], 0, m);
            }

            if(i == 0){ // 위, 왼쪽
                canWatch(copiedOffice, x, y, 0);
                canWatch(copiedOffice, x, y, 2);
            }else if(i == 1){ // 위 오른쪽
                canWatch(copiedOffice, x, y, 0);
                canWatch(copiedOffice, x, y, 3);
            }else if(i == 2){ // 아래, 왼쪽
                canWatch(copiedOffice, x, y, 1);
                canWatch(copiedOffice, x, y, 2);
            }else if(i == 3){ // 아래, 오른쪽
                canWatch(copiedOffice, x, y, 1);
                canWatch(copiedOffice, x, y, 3);
            }

            if(cctvIndex + 1 >= cctv.size()){
                checkNotObserved(copiedOffice, cctvIndex);
            }else{
                Cctv nextCctv = cctv.get(cctvIndex+1);
                if(nextCctv.type == 1){
                    cctv1(nextCctv, copiedOffice, cctvIndex+1);
                }else if(nextCctv.type == 2){
                    cctv2(nextCctv, copiedOffice, cctvIndex+1);
                }else if(nextCctv.type == 3){
                    cctv3(nextCctv, copiedOffice, cctvIndex+1);
                }else if(nextCctv.type == 4){
                    cctv4(nextCctv, copiedOffice, cctvIndex+1);
                }else if(nextCctv.type == 5){
                    cctv5(nextCctv, copiedOffice, cctvIndex+1);
                }
            }
        }
    }

    static void cctv4(Cctv currCctv, char[][] office, int cctvIndex){ // 한 방향
        int x = currCctv.x;
        int y = currCctv.y;
        for(int i = 0; i<4; i++){
            char[][] copiedOffice = new char[n][];
            for(int j = 0; j<n; j++){
                copiedOffice[j] = new char[m];
                System.arraycopy(office[j], 0, copiedOffice[j], 0, m);
            }

            if(i == 0){ // 위, 왼쪽, 오
                canWatch(copiedOffice, x, y, 0);
                canWatch(copiedOffice, x, y, 2);
                canWatch(copiedOffice, x, y, 3);
            }else if(i == 1){ // 왼, 위, 아래
                canWatch(copiedOffice, x, y, 0);
                canWatch(copiedOffice, x, y, 1);
                canWatch(copiedOffice, x, y, 2);
            }else if(i == 2){ // 아래, 오, 왼
                canWatch(copiedOffice, x, y, 1);
                canWatch(copiedOffice, x, y, 2);
                canWatch(copiedOffice, x, y, 3);
            }else if(i == 3){ // 오, 위, 아래
                canWatch(copiedOffice, x, y, 0);
                canWatch(copiedOffice, x, y, 1);
                canWatch(copiedOffice, x, y, 3);
            }

            if(cctvIndex + 1 >= cctv.size()){
                checkNotObserved(copiedOffice, cctvIndex);
            }else{
                Cctv nextCctv = cctv.get(cctvIndex+1);
                if(nextCctv.type == 1){
                    cctv1(nextCctv, copiedOffice, cctvIndex+1);
                }else if(nextCctv.type == 2){
                    cctv2(nextCctv, copiedOffice, cctvIndex+1);
                }else if(nextCctv.type == 3){
                    cctv3(nextCctv, copiedOffice, cctvIndex+1);
                }else if(nextCctv.type == 4){
                    cctv4(nextCctv, copiedOffice, cctvIndex+1);
                }else if(nextCctv.type == 5){
                    cctv5(nextCctv, copiedOffice, cctvIndex+1);
                }
            }
        }
    }

    static void cctv5(Cctv currCctv, char[][] office, int cctvIndex){ // 한 방향
        int x = currCctv.x;
        int y = currCctv.y;
        for(int i = 0; i<1; i++){
            char[][] copiedOffice = new char[n][];
            for(int j = 0; j<n; j++){
                copiedOffice[j] = new char[m];
                System.arraycopy(office[j], 0, copiedOffice[j], 0, m);
            }

            canWatch(copiedOffice, x, y, 0);
            canWatch(copiedOffice, x, y, 1);
            canWatch(copiedOffice, x, y, 2);
            canWatch(copiedOffice, x, y, 3);


            if(cctvIndex + 1 >= cctv.size()){
                checkNotObserved(copiedOffice, cctvIndex);
            }else{
                Cctv nextCctv = cctv.get(cctvIndex+1);
                if(nextCctv.type == 1){
                    cctv1(nextCctv, copiedOffice, cctvIndex+1);
                }else if(nextCctv.type == 2){
                    cctv2(nextCctv, copiedOffice, cctvIndex+1);
                }else if(nextCctv.type == 3){
                    cctv3(nextCctv, copiedOffice, cctvIndex+1);
                }else if(nextCctv.type == 4){
                    cctv4(nextCctv, copiedOffice, cctvIndex+1);
                }else if(nextCctv.type == 5){
                    cctv5(nextCctv, copiedOffice, cctvIndex+1);
                }
            }
        }
    }

    static void checkNotObserved(char[][] office, int index){
        int notObserved = 0;
        //System.out.println(index);
        for(int i = 0; i<n; i++){
            //System.out.println(Arrays.toString(office[i]));
            for(int j = 0; j<m; j++){
                if(office[i][j] == '0'){
                    notObserved++;
                }
            }
        }
        //System.out.println("---");


        min = Math.min(min, notObserved);
    }



    static void canWatch(char[][] office, int x, int y, int dir){

        if(dir == 0){ // 위 방향
            while(x - 1 >= 0 && office[x-1][y] != '6'){
                if(office[x-1][y] == '0'){
                    office[x-1][y] = '#';
                }
                x--;
            }
        }else if(dir == 1){ // 아래 방향
            while(x + 1 < n && office[x+1][y] != '6'){
                if(office[x+1][y] == '0'){
                    office[x+1][y] = '#';
                }
                x++;
            }
        }else if(dir == 2){ // 왼쪽
            while(y - 1 >= 0 && office[x][y-1] != '6'){
                if(office[x][y-1] == '0'){
                    office[x][y-1] = '#';
                }
                y--;
            }
        }else{ // dir == 3  오른쪽
            while(y + 1 < m && office[x][y+1] != '6'){
                if(office[x][y+1] == '0'){
                    office[x][y+1] = '#';
                }
                y++;
            }
        }
    }

}
