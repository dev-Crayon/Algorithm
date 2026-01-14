package baekjoon;

import java.io.*;
import java.util.*;

public class PointCompression_18870 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] original = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            original[i] = Integer.parseInt(st.nextToken());
        }

        int[] sorted = original.clone();
        Arrays.sort(sorted);

        Map<Integer, Integer> rankMap = new HashMap<>();
        int rank = 0;
        for (int value : sorted) {
            if (!rankMap.containsKey(value)) {
                rankMap.put(value, rank++);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(rankMap.get(original[i]));
            if (i < N - 1) sb.append(' ');
        }
        System.out.print(sb);
    }
}