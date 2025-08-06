package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MazeSearch_2178 {

    static int[][] maze;
    static int N, M;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int answer = Integer.MAX_VALUE;

    static void bfs(int x, int y, int depth) {
        Queue<List<Integer>> queue = new LinkedList<>();
        List<Integer> start = new ArrayList<>();
        start.add(x);
        start.add(y);
        start.add(depth);
        visited[x][y] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            List<Integer> currentPosition = queue.poll();
            int currentX = currentPosition.get(0);
            int currentY = currentPosition.get(1);
            int currentDepth = currentPosition.get(2);

            // 마지막 칸에 도착했는지 확인
            if (currentX == N - 1 && currentY == M - 1) {
                answer = Math.min(answer, currentDepth);
            }

            // 갈 수 있는 다음 칸 확인
            for (int i = 0; i < 4; i++) {
                int nextX = currentX + dx[i];
                int nextY = currentY + dy[i];

                // 미로 내부에 있고, 갈 수 있는 칸인데 방문한 적이 없다면
                if (0 <= nextX && nextX < N && 0 <= nextY && nextY < M && maze[nextX][nextY] == 1 && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    List<Integer> nextNode = new ArrayList<>();
                    nextNode.add(nextX);
                    nextNode.add(nextY);
                    nextNode.add(currentDepth + 1);
                    queue.add(nextNode);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maze = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            maze[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        bfs(0, 0, 1);

        System.out.println(answer);
    }
}
