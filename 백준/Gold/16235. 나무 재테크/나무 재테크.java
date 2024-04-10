import java.util.*;
import java.io.*;

public class Main{

    static class Tree implements Comparable<Tree>{
        int age;

        Tree(int age){
            this.age = age;
        }

        public int compareTo(Tree t){
            return this.age - t.age;
        }

        public String toString(){
            return age + " " ;
        }
    }
    static class Land{
        int row, col;
        int energy;
        ArrayList<Tree> trees;

        Land(int row, int col){
            this.row = row;
            this.col = col;
            energy = 5;
            trees = new ArrayList<>();
        }
    }

    static Land[][] land;
    static int n, m, k;
    static int[][] s2d2;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        land = new Land[n][];
        s2d2 = new int[n][];
        for(int i = 0; i<n; i++){
            land[i] = new Land[n];
            s2d2[i] = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                land[i][j] = new Land(i, j);
                s2d2[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int x, y, z;
        for(int i = 0; i< m; i++){
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            z = Integer.parseInt(st.nextToken());
            land[x-1][y-1].trees.add(new Tree(z));
        }

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(!land[i][j].trees.isEmpty()){
                    Collections.sort(land[i][j].trees);
                }
            }
        }

        // 나무 확인
        /*for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                System.out.println("[" + i + ", " + j +"] : " + land[i][j].trees);
            }
        }*/

        int year = 0;
        while(year < k){
            springNSummer();
            fall();
            winter();
            year ++;
        }

        int aliveTree = 0;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                aliveTree += land[i][j].trees.size();
            }
        }

        System.out.println(aliveTree);
        /*for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                System.out.println("[" + i + ", " + j +"] : " + land[i][j].trees);
            }
        }*/
    }

    static void springNSummer(){
        // 봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다. 각각의 나무는 나무가 있는 1×1 크기의 칸에 있는 양분만 먹을 수 있다.
        // 하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다.
        // 만약, 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.

        // 여름에는 봄에 죽은 나무가 양분으로 변하게 된다.
        // 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다. 소수점 아래는 버린다.

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                Land currLand = land[i][j];
                ArrayList<Tree> tree = currLand.trees;
                int leftEnergy = currLand.energy;

                if(tree.isEmpty()){
                    continue;
                }

                int treeIndex = 0;
                while(treeIndex < tree.size()){
                    // 봄: 나무는 어린 나무부터 자신의 나이만큼 양분을 먹는다.
                    Tree treeAge = tree.get(treeIndex);
                    if(treeAge.age <= leftEnergy){
                        leftEnergy -= treeAge.age++;
                    }else{
                        break;
                    }
                    treeIndex++;
                }

                int diedIndex = treeIndex;
                while(treeIndex < tree.size()){
                    // 여름: 양분이 부족해서 못먹는 나무들은 죽어서 양분이 된다;
                    Tree treeAge = tree.get(treeIndex);
                    leftEnergy += (treeAge.age / 2);
                    treeIndex++;
                }

                currLand.energy = leftEnergy;
                ArrayList<Tree> newTrees = new ArrayList<>();
                for(int t = 0; t<diedIndex; t++){
                    newTrees.add(tree.get(t));
                }
                Collections.sort(newTrees);
                currLand.trees = newTrees;
            }
        }
    }

    static void fall(){
        // 가을에는 나무가 번식한다. 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
        // 어떤 칸 (r, c)와 인접한 칸은 (r-1, c-1), (r-1, c), (r-1, c+1), (r, c-1), (r, c+1), (r+1, c-1), (r+1, c), (r+1, c+1) 이다.
        // 상도의 땅을 벗어나는 칸에는 나무가 생기지 않는다.
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                Land currLand = land[i][j];
                ArrayList<Tree> tree = currLand.trees;
                for(Tree curT : tree){
                    if(curT.age % 5 == 0){
                        if(i-1 >= 0){
                            land[i-1][j].trees.add(0, new Tree(1));
                            if(j - 1 >= 0){
                                land[i-1][j-1].trees.add(0, new Tree(1));
                            }
                            if(j + 1 < n){
                                land[i-1][j+1].trees.add(0, new Tree(1));
                            }
                        }
                        if(i+1 < n){
                            land[i+1][j].trees.add(0, new Tree(1));
                            if(j - 1 >= 0){
                                land[i+1][j-1].trees.add(0, new Tree(1));
                            }
                            if(j + 1 < n){
                                land[i+1][j+1].trees.add(0, new Tree(1));
                            }
                        }
                        if(j-1 >= 0){
                            land[i][j-1].trees.add(0, new Tree(1));
                        }
                        if(j+1 < n){
                            land[i][j+1].trees.add(0, new Tree(1));
                        }
                    }
                }
            }
        }
    }

    static void winter(){
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                land[i][j].energy += s2d2[i][j];
            }
        }
    }

}