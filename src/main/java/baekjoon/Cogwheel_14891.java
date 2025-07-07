package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Cogwheel_14891 {

    private static class Cogwheel {
        private int[] magnet = new int[8];

        public Cogwheel(int[] m) {
            System.arraycopy(m, 0, magnet, 0, 8);
        }

        public void turnLeft() {
            int temp = magnet[0];
            for (int i = 0; i < 7; i++) {
                magnet[i] = magnet[i + 1];
            }
            magnet[7] = temp;
        }

        public void turnRight() {
            int temp = magnet[7];
            for (int i = 7; i > 0; i--) {
                magnet[i] = magnet[i - 1];
            }
            magnet[0] = temp;
        }

        public int getLeft() {
            return magnet[6];
        }

        public int getRight() {
            return magnet[2];
        }

        public int getTop() {
            return magnet[0];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Cogwheel> cogwheelList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int[] input = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            cogwheelList.add(new Cogwheel(input));
        }

        int turnCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < turnCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cogwheelNumber = Integer.parseInt(st.nextToken()) - 1; // 0-based index
            int direction = Integer.parseInt(st.nextToken());

            boolean[] visited = new boolean[4];
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{cogwheelNumber, direction});
            visited[cogwheelNumber] = true;

            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                int idx = current[0];
                int dir = current[1];

                // 왼쪽 톱니 확인
                if (idx - 1 >= 0 && !visited[idx - 1]) {
                    if (cogwheelList.get(idx - 1).getRight() != cogwheelList.get(idx).getLeft()) {
                        queue.add(new int[]{idx - 1, -dir});
                        visited[idx - 1] = true;
                    }
                }

                // 오른쪽 톱니 확인
                if (idx + 1 < 4 && !visited[idx + 1]) {
                    if (cogwheelList.get(idx).getRight() != cogwheelList.get(idx + 1).getLeft()) {
                        queue.add(new int[]{idx + 1, -dir});
                        visited[idx + 1] = true;
                    }
                }

                // 회전
                if (dir == 1) cogwheelList.get(idx).turnRight();
                else cogwheelList.get(idx).turnLeft();
            }
        }

        // 점수 계산
        int score = 0;
        for (int i = 0; i < 4; i++) {
            if (cogwheelList.get(i).getTop() == 1) {
                score += (1 << i); // 2^i
            }
        }

        System.out.println(score);
    }
}
