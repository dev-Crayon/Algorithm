package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class SangbumBuilding_6593 {

    static int L, R, C;
    static String[][][] building;
    static boolean[][][] visited;
    static int[] start;
    static int[] end;
    static boolean isPossible = false;
    static int minute = 0;
    static int[] dl = {1, -1, 0, 0, 0, 0};
    static int[] dr = {0, 0, 1, -1, 0, 0};
    static int[] dc = {0, 0, 0, 0, 1, -1};

    static void bfs(int sl, int sr, int sc) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        visited[sl][sr][sc] = true;
        q.add(new int[]{sl, sr, sc, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int l = cur[0], r = cur[1], c = cur[2], d = cur[3];

            if (building[l][r][c].equals("E")) {
                isPossible = true;
                minute = d;
                return;
            }

            for (int k = 0; k < 6; k++) {
                int nl = l + dl[k], nr = r + dr[k], nc = c + dc[k];
                if (0 <= nl && nl < L && 0 <= nr && nr < R && 0 <= nc && nc < C) {
                    if (!visited[nl][nr][nc] && !building[nl][nr][nc].equals("#")) {
                        visited[nl][nr][nc] = true;
                        q.add(new int[]{nl, nr, nc, d + 1});
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (L == 0 && R == 0 && C == 0) break;

            building = new String[L][R][C];
            visited = new boolean[L][R][C];
            start = new int[3];
            end = new int[3];

            for (int i = L - 1; i >= 0; i--) {
                for (int j = 0; j < R; j++) {
                    String[] line = br.readLine().split("");
                    for (int k = 0; k < C; k++) {
                        building[i][j][k] = line[k];
                        if (line[k].equals("S")) {
                            start[0] = i; start[1] = j; start[2] = k;
                        }
                        if (line[k].equals("E")) {
                            end[0] = i; end[1] = j; end[2] = k;
                        }
                    }
                }
                br.readLine();
            }

            bfs(start[0], start[1], start[2]);

            if (isPossible) {
                answer.append("Escaped in ").append(minute).append(" minute(s).");
            } else {
                answer.append("Trapped!");
            }
            answer.append("\n");

            isPossible = false;
            minute = 0;
        }

        System.out.println(answer);
    }
}
