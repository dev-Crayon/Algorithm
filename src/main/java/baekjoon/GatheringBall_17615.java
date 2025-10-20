package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GatheringBall_17615 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] answerCandidate = new int[2];

        int N = Integer.parseInt(br.readLine());
        String input = br.readLine();
        if (!input.contains("R") || !input.contains("B")) {
            System.out.println(0);
            return;
        }

        int answer = Integer.MAX_VALUE;

        // 빨간 공 이동, R B 순서로 정렬
        int countIndex = 0;
        while (countIndex < N && input.charAt(countIndex) == 'R') {
            countIndex++;
        }
        int moveCount = 0;
        for (int i = countIndex; i <  N; i++) {
            if (input.charAt(i) == 'R') {
                moveCount++;
            }
        }
        answer = Math.min(answer, moveCount);

        // 빨간 공 이동, B R 순서로 정렬
        countIndex = N - 1;
        while (countIndex >= 0 && input.charAt(countIndex) == 'R') {
            countIndex--;
        }
        moveCount = 0;
        for (int i = 0; i <= countIndex; i++) {
            if (input.charAt(i) == 'R') {
                moveCount++;
            }
        }
        answer = Math.min(answer, moveCount);

        // 파란 공 이동
        countIndex = 0;
        while (countIndex < N && input.charAt(countIndex) == 'B') {
            countIndex++;
        }
        moveCount = 0;
        for (int i = countIndex; i <  N; i++) {
            if (input.charAt(i) == 'B') {
                moveCount++;
            }
        }
        answer = Math.min(answer, moveCount);

        countIndex = N - 1;
        while (countIndex >= 0 && input.charAt(countIndex) == 'B') {
            countIndex--;
        }
        moveCount = 0;
        for (int i = 0; i <= countIndex; i++) {
            if (input.charAt(i) == 'B') {
                moveCount++;
            }
        }
        answer = Math.min(answer, moveCount);

        System.out.println(answer);
    }
}
