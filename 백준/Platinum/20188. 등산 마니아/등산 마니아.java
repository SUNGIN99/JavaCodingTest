import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static boolean[] visited;
    static ArrayList<Integer>[] vertex;
    static int LOG = 19;
    static int[][] ancestor; // ancestor[k][v] = v의 2^k번째 조상

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        vertex = new ArrayList[N + 1];
        ancestor = new int[LOG + 1][N + 1];

        for (int i = 0; i <= N; i++) {
            vertex[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            vertex[a].add(b);
            vertex[b].add(a);
        }

        nodes = new HashMap<>();

        Node root = new Node(0, 1);
        root.level = 0;

        nodes.put(0, new Node(-1, 0));
        nodes.put(1, root);

        makeGraph(1);

        // Binary Lifting 테이블 채우기
        fillAncestorTable();

        //printGraph(root);

        //int result = lca(2, 7);
        //System.out.println(result);

        int sum = 0;
        for(int i = 1; i < N; i++){
            for(int j = i+1; j<=N; j++){
                int subParent = lca(i, j);
                if(subParent == 1){
                    sum += nodes.get(i).level + nodes.get(j).level;
                }else{
                    sum += nodes.get(i).level + (nodes.get(j).level - nodes.get(subParent).level);
                }
            }
        }

        System.out.println(sum);

    }

    static HashMap<Integer, Node> nodes;

    static void makeGraph(int num) {
        ArrayList<Integer> list = vertex[num];

        if (visited[num]) {
            return;
        }

        visited[num] = true;
        Node parent = nodes.get(num);

        for (Integer i : list) {
            if (visited[i]) {
                continue;
            }

            Node child = parent.add(i);
            child.level = parent.level + 1;

            ancestor[0][i] = num; // 직접 부모 저장

            nodes.put(i, child);

            makeGraph(i);
        }

    }

    static void fillAncestorTable() {
        for (int k = 1; k <= LOG; k++) {
            for (int v = 1; v <= N; v++) {
                ancestor[k][v] = ancestor[k - 1][ancestor[k - 1][v]];
            }
        }
    }

    static int lca(int a, int b) {
        Node nodeA = nodes.get(a);
        Node nodeB = nodes.get(b);

        // a가 더 깊도록
        if (nodeA.level < nodeB.level) {
            int t = a; a = b; b = t;
        }

        // 1단계: 레벨 맞추기
        int diff = nodes.get(a).level - nodes.get(b).level;
        for (int k = 0; k < LOG; k++) {
            if (((diff >> k) & 1) == 1) {
                a = ancestor[k][a];
                // 부모의 부모를 계속 따라감
            }
        }

        if (a == b) return a;

        // 2단계: 동시에 올라가기
        for (int k = LOG - 1; k >= 0; k--) {
            if (ancestor[k][a] != ancestor[k][b]) {
                a = ancestor[k][a];
                b = ancestor[k][b];
            }
        }

        return ancestor[0][a];
    }

    static class Node {
        int num;
        int level;
        Node parent, left, right;

        public Node(int p, int num) {
            this.num = num;
            this.parent = nodes.get(p);
        }

        Node add(int num) {
            if (this.left == null) {
                return addLeft(num);
            } else {
                return addRight(num);
            }
        }

        Node addLeft(int num) {
            this.left = new Node(this.num, num);
            return left;
        }

        Node addRight(int num) {
            this.right = new Node(this.num, num);
            return right;
        }
    }

    static void printGraph(Node node) {
        if (node == null) {
            return;
        }

        if (node.left != null) {
            System.out.println(node.left.num + ", " + node.left.level + ", " + node.left.parent.num);
        }

        if (node.right != null) {
            System.out.println(node.right.num + ", " + node.right.level + ", " + node.right.parent.num);
        }

        printGraph(node.left);
        printGraph(node.right);
    }
}