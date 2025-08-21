package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SumOfSubsequence_1182 {

    static int N, S;
    static int[] numbers;
    static int[] sequence;
    static boolean[] isUsed;
    static int answer = 0;

    static void dfs(int start, int depth, int maxDepth) {
        // 다 뽑았다면 수열의 합 확인
        if (depth == maxDepth) {
            int tempSum = 0;
            for (int j : sequence) {
                tempSum += j;
            }

            if (tempSum == S) answer++;
            return;
        }

        for (int i = start; i < N; i++) {
            if (isUsed[i]) continue;
            isUsed[i] = true;
            sequence[depth] = numbers[i];
            dfs(i + 1, depth + 1, maxDepth);
            isUsed[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        isUsed = new boolean[N];

        for (int i =1; i <= N; i++) {
            // 뽑은 수를 저장할 배열
            sequence = new int[i];
            dfs(0, 0, i);
        }

        System.out.println(answer);
    }
}
