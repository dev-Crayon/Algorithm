package baekjoon;

import java.io.*;
import java.util.*;

public class Tomato_7569 {
    static int M, N, H;
    static int[][][] box;
    static int[][] directions = {
            {1, 0, 0}, {-1, 0, 0}, // 위, 아래
            {0, 1, 0}, {0, -1, 0}, // 앞, 뒤
            {0, 0, 1}, {0, 0, -1}  // 오른쪽, 왼쪽
    };

    static class Point {
        int z, y, x;

        Point(int z, int y, int x) {
            this.z = z;
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 가로
        N = Integer.parseInt(st.nextToken()); // 세로
        H = Integer.parseInt(st.nextToken()); // 높이

        box = new int[H][N][M];
        Queue<Point> queue = new LinkedList<>();

        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    box[h][n][m] = Integer.parseInt(st.nextToken());
                    if (box[h][n][m] == 1) {
                        queue.add(new Point(h, n, m)); // 익은 토마토
                    }
                }
            }
        }

        int result = bfs(queue);
        System.out.println(result);
    }

    static int bfs(Queue<Point> queue) {
        int day = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Point p = queue.poll();

                for (int[] dir : directions) {
                    int nz = p.z + dir[0];
                    int ny = p.y + dir[1];
                    int nx = p.x + dir[2];

                    if (nz >= 0 && nz < H && ny >= 0 && ny < N && nx >= 0 && nx < M && box[nz][ny][nx] == 0) {
                        box[nz][ny][nx] = 1; // 익은 토마토로 변경
                        queue.add(new Point(nz, ny, nx));
                    }
                }
            }

            if (!queue.isEmpty()) {
                day++; // 하루 경과
            }
        }

        // 익지 않은 토마토가 남아 있는지 검사
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (box[h][n][m] == 0) {
                        return -1;
                    }
                }
            }
        }

        return day;
    }
}
