package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RGBDistance_1149 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N + 1][3];

        // 집 색칠 비용 입력
        for (int i = 0; i < N; i++) {
            int[] colorCost = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            dp[i][0] = colorCost[0];
            dp[i][1] = colorCost[1];
            dp[i][2] = colorCost[2];
        }

        for (int i = 1; i <= N; i++) {
            dp[i][0] += Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] += Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] += Math.min(dp[i - 1][0], dp[i - 1][1]);
        }

        System.out.println(Math.min(Math.min(dp[N][0], dp[N][1]), dp[N][2]));
    }
}
