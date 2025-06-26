package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class SetExpression_1717 {

    private static int N;
    private static int M;
    private static int[] parent;
    private static int[] size;

    private static boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return false;

        if (size[x] < size[y]) {
            parent[x] = y;
            size[y] += size[x];
        } else {
            parent[y] = x;
            size[x] += size[y];
        }

        return true;
    }

    private static int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }

        return x;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        size = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int operation = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (operation == 0) {
                union(a, b);
            } else {
                int aParent = find(a);
                int bParent = find(b);
                if (aParent == bParent) {
                    sb.append("YES\n");
                } else {
                    sb.append("NO\n");
                }
            }
        }

        System.out.println(sb);
        br.close();
    }
}
