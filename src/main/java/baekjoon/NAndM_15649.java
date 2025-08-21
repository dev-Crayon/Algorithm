package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NAndM_15649 {

    static int N, M;
    static boolean[] isUsed;
    static int[] sequence;
    static StringBuilder sb = new StringBuilder();

    private static void dfs(int depth) {
        // 다 뽑았으면
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(sequence[i]).append(" ");
            }
            sb.setLength(sb.length() - 1);
            sb.append("\n");
            return;
        }

        for (int number = 1; number <= N; number++) {
            if (isUsed[number]) continue;
            isUsed[number] = true;
            sequence[depth] = number;
            dfs(depth + 1);
            isUsed[number] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        isUsed = new boolean[N + 1];
        sequence = new int[M];

        dfs(0);

        System.out.println(sb.toString());
    }
}
