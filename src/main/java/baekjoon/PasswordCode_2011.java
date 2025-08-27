package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PasswordCode_2011 {

    private static final int MOD = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = s.length();

        if (n == 0 || s.charAt(0) == '0') {
            System.out.println(0);
            return;
        }

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            char c = s.charAt(i - 1);     // 현재 문자
            char p = s.charAt(i - 2);     // 이전 문자

            // 1) 한 자리 해석 (현재 문자가 '0'이 아닐 때만)
            if (c != '0') {
                dp[i] = (dp[i] + dp[i - 1]) % MOD;
            } else {
                // 현재가 '0'이면 반드시 앞이 '1' 또는 '2'여야 함
                if (p != '1' && p != '2') {
                    System.out.println(0);
                    return;
                }
            }

            // 2) 두 자리 해석 (10~26)
            int two = (p - '0') * 10 + (c - '0');
            if (two >= 10 && two <= 26) {
                dp[i] = (dp[i] + dp[i - 2]) % MOD;
            }
        }

        System.out.println(dp[n] % MOD);
    }
}
