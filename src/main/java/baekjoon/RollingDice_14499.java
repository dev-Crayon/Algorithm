package baekjoon;

import java.io.*;
import java.util.*;

public class RollingDice_14499 {
    static int N, M, x, y, K;
    static int[][] map;
    static int[] dice = new int[6]; // 0: top, 1: north, 2: bottom, 3: south, 4: west, 5: east

    // 동 서 북 남
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            int dir = Integer.parseInt(st.nextToken()) - 1;
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // 범위 밖이면 무시
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

            roll(dir);
            x = nx;
            y = ny;

            if (map[x][y] == 0) {
                map[x][y] = dice[2];
            } else {
                dice[2] = map[x][y];
                map[x][y] = 0;
            }

            sb.append(dice[0]).append("\n"); // 윗면 출력
        }

        System.out.print(sb);
    }

    // 주사위 회전
    static void roll(int dir) {
        int temp;
        switch (dir) {
            case 0: // 동쪽
                temp = dice[0];
                dice[0] = dice[4];
                dice[4] = dice[2];
                dice[2] = dice[5];
                dice[5] = temp;
                break;
            case 1: // 서쪽
                temp = dice[0];
                dice[0] = dice[5];
                dice[5] = dice[2];
                dice[2] = dice[4];
                dice[4] = temp;
                break;
            case 2: // 북쪽
                temp = dice[0];
                dice[0] = dice[3];
                dice[3] = dice[2];
                dice[2] = dice[1];
                dice[1] = temp;
                break;
            case 3: // 남쪽
                temp = dice[0];
                dice[0] = dice[1];
                dice[1] = dice[2];
                dice[2] = dice[3];
                dice[3] = temp;
                break;
        }
    }
}