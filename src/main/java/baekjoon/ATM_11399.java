package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ATM_11399 {

    static int N;
    static int[] times;
    static int[] delayTimes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        times = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        delayTimes = new int[N];
        Arrays.sort(times);

        int answer = 0;
        for (int i = 0; i < times.length; i++) {
            for (int j = i + 1; j < times.length; j++) {
                delayTimes[j] += times[i];
            }
        }

        for (int i = 0; i < times.length; i++) {
            answer += times[i] + delayTimes[i];
        }

        System.out.println(answer);
    }
}
