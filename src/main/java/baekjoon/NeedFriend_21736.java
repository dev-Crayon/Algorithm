package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NeedFriend_21736 {

    static String[][] university;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int answer = 0;

    static void dfs(int x, int y) {

        if (university[x][y].equals("P")) {
            answer++;
        }

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (0 <= nextX && nextX < university.length && 0 <= nextY && nextY < university[0].length && !visited[nextX][nextY] && !university[nextX][nextY].equals("X")) {
                visited[nextX][nextY] = true;
                dfs(nextX, nextY);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        university = new String[N][M];
        visited = new boolean[N][M];
        int startX = 0;
        int startY = 0;
        for (int i = 0; i < N; i++) {
            university[i] = br.readLine().split("");
            for (int j = 0; j < university[i].length; j++) {
                if (university[i][j].equals("I")) {
                    startX = i;
                    startY = j;
                }
            }
        }

        visited[startX][startY] = true;
        dfs(startX, startY);

        if (answer == 0) {
            System.out.println("TT");
        } else {
            System.out.println(answer);
        }
    }
}
