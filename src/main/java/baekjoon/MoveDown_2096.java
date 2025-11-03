package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MoveDown_2096 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][3];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[][] dp = new int[N + 1][3];
        dp[1] = map[0];
        for (int i = 2; i <= N; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]) + map[i - 1][0];
            dp[i][1] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2])) + map[i - 1][1];
            dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]) + map[i - 1][2];
        }

        int maxValue = dp[N][0];
        for (int value : dp[N]) {
            if (maxValue < value) {
                maxValue = value;
            }
        }

        dp = new int[N + 1][3];
        dp[1] = map[0];
        for (int i = 2; i <= N; i++) {
            dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]) + map[i - 1][0];
            dp[i][1] = Math.min(dp[i - 1][0], Math.min(dp[i - 1][1], dp[i - 1][2])) + map[i - 1][1];
            dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][2]) + map[i - 1][2];
        }

        int minValue = dp[N][0];
        for (int value : dp[N]) {
            if (minValue > value) {
                minValue = value;
            }
        }

        System.out.println(maxValue + " " + minValue);
    }
}
