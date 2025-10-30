package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MovePipe_17070 {

    private static int N;
    private static int[][] house;
    private static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        house = new int[N][N];
        dp = new int[N][N][3];

        // 집 정보 입력
        for (int i = 0; i < N; i++) {
            house[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // dp 초기 정보
        dp[0][1][0] = 1;

        // dp 배열 완성
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                // 현재 칸이 벽이면 무시
                if (house[r][c] == 1) {
                    continue;
                }

                // 가로 방향
                // 이전 위치가 (r, c - 1)이고 가로 또는 대각선 방향이었던 경우
                if (c > 0) {
                    dp[r][c][0] += dp[r][c - 1][0]; // 가로 → 가로
                    dp[r][c][0] += dp[r][c - 1][2]; // 대각선 → 가로
                }

                // 세로 방향 (↓)
                // 이전 위치가 (r-1, c)이고 세로 또는 대각선 방향이었던 경우
                if (r > 0) {
                    dp[r][c][1] += dp[r - 1][c][1]; // 세로 → 세로
                    dp[r][c][1] += dp[r - 1][c][2]; // 대각선 → 세로
                }

                // 대각선 방향 (↘)
                // 이전 위치가 (r-1, c-1)이고, 3칸 모두 비어있어야 함
                if (r > 0 && c > 0 &&
                        house[r][c] == 0 &&
                        house[r - 1][c] == 0 &&
                        house[r][c - 1] == 0) {
                    dp[r][c][2] += dp[r - 1][c - 1][0]; // 가로 → 대각선
                    dp[r][c][2] += dp[r - 1][c - 1][1]; // 세로 → 대각선
                    dp[r][c][2] += dp[r - 1][c - 1][2]; // 대각선 → 대각선
                }
            }
        }

        // (N-1, N-1) 위치에 도달하는 모든 방향의 경로 수 합산
        int result = dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2];
        System.out.println(result);
    }
}
