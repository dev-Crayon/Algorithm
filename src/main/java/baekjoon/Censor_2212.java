package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Censor_2212 {

    static int N;
    static int K;
    static int[] censors;
    static int[] diff;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        // 집중국이 센서를 하나씩 맡을 수 있으면
        if (K >= N) {
            System.out.println(0);
            return;
        }

        censors = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(censors);

        diff = new int[N - 1];
        // 다음 센서와의 거리차이
        for (int i = 0; i < diff.length; i++) {
            diff[i] = censors[i + 1] - censors[i];
        }
        Arrays.sort(diff);

        int answer = 0;
        for (int i = 0; i < N - K; i++) {
            answer += diff[i];
        }

        System.out.println(answer);
    }
}
