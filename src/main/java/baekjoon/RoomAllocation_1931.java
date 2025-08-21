package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class RoomAllocation_1931 {

    static int N;
    static int[][] times;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        times = new int[N][2];
        for (int i = 0; i < N; i++) {
            times[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        Arrays.sort(times, Comparator
                .comparingInt((int[] t) -> t[1])
                .thenComparingInt(t -> t[0])
        );
        int currentTime = 0;
        for (int[] time : times) {
            if (time[0] >= currentTime) {
                answer++;
                currentTime = time[1];
            }
        }

        System.out.println(answer);
    }
}
