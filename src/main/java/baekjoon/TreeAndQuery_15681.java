package baekjoon;

import java.io.*;
import java.util.*;

public class TreeAndQuery_15681 {

    static int N, R, Q;
    static List<List<Integer>> linkedNode;
    static int[] parent;
    static int[] size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        linkedNode = new ArrayList<>();
        for (int i = 1; i <= N; i++) linkedNode.add(new ArrayList<>(2));
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            linkedNode.get(u - 1).add(v);
            linkedNode.get(v - 1).add(u);
        }

        parent = new int[N + 1];
        size = new int[N + 1];

        int[] order = new int[N];
        int idx = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        parent[R] = 0;
        stack.push(R);
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            order[idx++] = cur;
            for (int nxt : linkedNode.get(cur - 1)) {
                if (nxt == parent[cur]) continue;
                parent[nxt] = cur;
                stack.push(nxt);
            }
        }

        for (int i = N - 1; i >= 0; i--) {
            int u = order[i];
            int subtotal = 1; // 자신 포함
            for (int v : linkedNode.get(u - 1)) {
                if (v == parent[u]) continue;
                subtotal += size[v];
            }
            size[u] = subtotal;
        }

        for (int i = 0; i < Q; i++) {
            int u = Integer.parseInt(br.readLine());
            sb.append(size[u]).append('\n');
        }

        System.out.print(sb);
    }
}
