package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NumberingTheComplexes_2667 {

    static int N;
    static int[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static boolean[][] visited;
    static int complexesCount = 0;

    static void dfs(int x, int y) {
        visited[x][y] = true;
        complexesCount++;

        // 상하좌우 움직일 수 있는 칸 확인
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if (0 <= nextX && nextX < N && 0 <= nextY && nextY < N && !visited[nextX][nextY] && map[nextX][nextY] == 1) {
                dfs(nextX, nextY);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        List<Integer> answerList = new ArrayList<>();

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        // 지도 정보 입력
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        // dfs 탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    dfs(i, j);
                    answerList.add(complexesCount);
                    complexesCount = 0;
                }
            }
        }

        Collections.sort(answerList);
        sb.append(answerList.size()).append("\n");
        for (int count : answerList) {
            sb.append(count).append("\n");
        }

        System.out.println(sb);
    }
}
