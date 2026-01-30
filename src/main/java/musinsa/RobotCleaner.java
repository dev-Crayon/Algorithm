import java.io.*;
import java.util.*;

public class RobotCleaner {
    static int N, M;
    static int[][] room;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Robot {
        int x, y, direction;
        boolean isStop;

        public Robot(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        public void clean() {
            room[x][y] = 2;
        }

        public void turnLeft() {
            direction = (direction + 3) % 4;
        }

        public boolean hasCleanableAround() {
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (isValid(nx, ny) && room[nx][ny] == 0) {
                    return true;
                }
            }
            return false;
        }

        public boolean canMoveForward() {
            int nx = x + dx[direction];
            int ny = y + dy[direction];
            return isValid(nx, ny) && room[nx][ny] == 0;
        }

        public void moveForward() {
            x += dx[direction];
            y += dy[direction];
        }

        public void moveBack() {
            int backDir = (direction + 2) % 4;
            int bx = x + dx[backDir];
            int by = y + dy[backDir];
            if (isValid(bx, by) && room[bx][by] != 1) {
                x = bx;
                y = by;
            } else {
                isStop = true;
            }
        }

        private boolean isValid(int x, int y) {
            return x >= 0 && x < N && y >= 0 && y < M;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        room = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        Robot robot = new Robot(x, y, d);

        while (!robot.isStop) {
            if (room[robot.x][robot.y] == 0) {
                robot.clean();
                count++;
            }

            if (robot.hasCleanableAround()) {
                robot.turnLeft();
                if (robot.canMoveForward()) {
                    robot.moveForward();
                }
            } else {
                robot.moveBack();
            }
        }
        System.out.println(count);
    }
}
