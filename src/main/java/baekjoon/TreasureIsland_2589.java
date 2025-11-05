package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TreasureIsland_2589 {

    static char[][] island;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int answer = 0;

    static void bfs(int x, int y) {
        boolean[][] visited = new boolean[island.length][island[0].length];
        Queue<int[]> queue = new LinkedList<>();
        visited[x][y] = true;
        int[] start = new int[] {x, y, 0};
        queue.add(start);

        while (!queue.isEmpty()) {
            int[] currentPosition = queue.poll();
            int currentX = currentPosition[0];
            int currentY = currentPosition[1];
            int currentTime = currentPosition[2];
            answer = Math.max(answer, currentTime);

            for (int i = 0; i < 4; i++) {
                int nextX = currentX + dx[i];
                int nextY = currentY + dy[i];

                if (0 <= nextX && nextX < island.length && 0 <= nextY && nextY < island[0].length && !visited[nextX][nextY] && island[nextX][nextY] == 'L') {
                    int[] nextPosition = new int[] {nextX, nextY, currentTime + 1};
                    visited[nextX][nextY] = true;
                    queue.add(nextPosition);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        island = new char[input[0]][input[1]];

        for (int i = 0; i < island.length; i++) {
            island[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < island.length; i++) {
            for (int j = 0; j < island[i].length; j++) {
                if (island[i][j] == 'L') {
                    bfs(i, j);
                }
            }
        }

        System.out.println(answer);
    }
}
