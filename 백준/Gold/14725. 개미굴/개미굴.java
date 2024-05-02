import java.util.*;
import java.io.*;

public class Main{

    static class Trie{
        String food;
        int level;
        HashMap<String, Trie> tunnel;

        Trie(String f, int l){
            this.food = f;
            this.tunnel = new HashMap<>();
            this.level = l;
        }

        public String toString(){
            return tunnel.toString();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        HashMap<String, Trie> root = new HashMap<>();
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            String firstFloor = st.nextToken();
            Trie curTunnel = root.getOrDefault(firstFloor, new Trie(firstFloor, 0));
            root.put(firstFloor, curTunnel);
            HashMap<String, Trie> temp = curTunnel.tunnel;
            for(int j = 0; j<count - 1; j++){
                String foodName = st.nextToken();
                Trie subTunnel = temp.getOrDefault(foodName, new Trie(foodName, j + 1));
                temp.put(foodName, subTunnel);
                temp = subTunnel.tunnel;
            }
        }

        dfs(root);

    }
    static void dfs(HashMap<String, Trie> level){
        List<String> a = new ArrayList<>(level.keySet());
        Collections.sort(a);
        for(String food : a){
            Trie t = level.get(food);
            System.out.println("--".repeat(t.level) + t.food);
            dfs(t.tunnel);
        }

    }


}