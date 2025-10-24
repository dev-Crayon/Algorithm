package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class DrinkingAndWalk_9205 {

    static int N;
    static int[] startPoint;
    static int[][] marketPoints;
    static int[] endPoint;

    private static boolean canReach() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N];

        queue.offer(startPoint);
        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            // 목적지 도착 가능 여부
            if (Math.abs(current[0] - endPoint[0]) + Math.abs(current[1] - endPoint[1]) <= 1000) {
                return true;
            }

            for (int i = 0; i < N; i++) {
                if (!visited[i] && Math.abs(current[0] - marketPoints[i][0]) + Math.abs(current[1] - marketPoints[i][1]) <= 1000) {
                    visited[i] = true;
                    queue.offer(marketPoints[i]);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            N = Integer.parseInt(br.readLine());
            startPoint = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            marketPoints = new int[N][2];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                marketPoints[i] = new int[] {x, y};
            }

            endPoint = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            if (canReach()) {
                sb.append("happy\n");
            } else {
                sb.append("sad\n");
            }
        }

        System.out.println(sb);
    }
}
