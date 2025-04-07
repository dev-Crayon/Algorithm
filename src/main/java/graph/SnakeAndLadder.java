package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SnakeAndLadder {

    static int N, M;
    static int[] map = new int[101];      // 사다리, 뱀 이동 정보
    static boolean[] visited = new boolean[101];

    public int solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  // 사다리 수
        M = Integer.parseInt(st.nextToken());  // 뱀 수

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            map[from] = to;  // 사다리 또는 뱀 정보
        }

        return bfs(1);
    }

    private int bfs(int start) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start, 0});
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int position = curr[0];
            int count = curr[1];

            if (position == 100) {
                return count;
            }

            for (int i = 1; i <= 6; i++) {
                int next = position + i;
                if (next > 100) continue;

                // 사다리 또는 뱀 처리
                if (map[next] != 0) {
                    next = map[next];
                }

                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(new int[]{next, count + 1});
                }
            }
        }

        return -1;  // 도달 못하는 경우
    }
}
