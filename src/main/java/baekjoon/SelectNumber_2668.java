package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class SelectNumber_2668 {
    static int N;
    static int[] next;          // next[i] = f(i)
    static boolean[] visited;   // 방문 여부
    static boolean[] finished;  // 해당 노드 DFS 처리 완료 여부
    static List<Integer> answer = new ArrayList<>();

    static void dfs(int u) {
        visited[u] = true;
        int v = next[u];

        if (!visited[v]) {
            dfs(v);
        } else if (!finished[v]) {
            int x = v;
            answer.add(x);
            while (x != u) {
                x = next[x];
                answer.add(x);
            }
        }

        finished[u] = true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine().trim());
        next = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            next[i] = Integer.parseInt(br.readLine().trim());
        }

        visited = new boolean[N + 1];
        finished = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) dfs(i);
        }

        Collections.sort(answer);
        StringBuilder sb = new StringBuilder();
        sb.append(answer.size()).append('\n');
        for (int x : answer) sb.append(x).append('\n');
        System.out.print(sb);
    }
}
