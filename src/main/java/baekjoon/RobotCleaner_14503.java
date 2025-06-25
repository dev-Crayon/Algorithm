package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/* 로봇 청소기 방향 정의
0 -> 북쪽
1 -> 동쪽
2 -> 남쪽
3 -> 서쪽
 */

public class RobotCleaner_14503 {

    private static int N;
    private static int M;
    private static int robotX;
    private static int robotY;
    private static int robotDirection;
    private static int[][] room;

    private static class Robot {

        private int x;
        private int y;
        private int direction;
        private boolean isFinished;
        private int cleaningCount;

        public Robot(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.isFinished = false;
            cleaningCount = 0;
        }

        public void move(int nextX, int nextY) {
            this.x = nextX;
            this.y = nextY;
        }

        public void clean() {
            if (room[this.x][this.y] == 0) {
                cleaningCount++;
                room[this.x][this.y] = -1;
            }
        }

        public void changeDirection() {
            switch (this.direction) {
                case 0:
                    this.direction = 3;
                    break;
                case 1:
                    this.direction = 0;
                    break;
                case 2:
                    this.direction = 1;
                    break;
                case 3:
                    this.direction = 2;
                    break;
            }
        }

        public int[] getNextPosition() {
            int nextX = this.x;
            int nextY = this.y;
            switch (this.direction) {
                case 0:
                    nextX--;
                    break;
                case 1:
                    nextY++;
                    break;
                case 2:
                    nextX++;
                    break;
                case 3:
                    nextY--;
            }

            return new int[]{nextX, nextY};
        }

        public boolean checkAroundDirty() {
            int[] dx = {-1, 1, 0, 0};
            int[] dy = {0, 0, -1, 1};

            for (int i = 0; i < 4; i++) {
                int nextX = this.x + dx[i];
                int nextY = this.y + dy[i];
                if (!checkPossiblePosition(nextX, nextY)) {
                    continue;
                }

                if (room[nextX][nextY] == 0) {
                    return true;
                }
            }

            return false;
        }

        public void tryReverse() {
            int nextX = this.x;
            int nextY = this.y;
            switch (this.direction) {
                case 0:
                    nextX++;
                    break;
                case 1:
                    nextY--;
                    break;
                case 2:
                    nextX--;
                    break;
                case 3:
                    nextY++;
            }

            if (checkPossiblePosition(nextX, nextY) && room[nextX][nextY] != 1) {
                move(nextX, nextY);
            } else {
                this.isFinished = true;
            }
        }

        public int getCleaningCount() {
            return this.cleaningCount;
        }
    }

    private static boolean checkPossiblePosition(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 방 크기 입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        room = new int[N][M];

        // 로봇 정보 입력
        st = new StringTokenizer(br.readLine());
        robotX = Integer.parseInt(st.nextToken());
        robotY = Integer.parseInt(st.nextToken());
        robotDirection = Integer.parseInt(st.nextToken());
        Robot robot = new Robot(robotX, robotY, robotDirection);

        // 방 정보 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (!robot.isFinished) {
            robot.clean();

            if (robot.checkAroundDirty()) {
                robot.changeDirection();
                int[] nextPosition = robot.getNextPosition();
                if (checkPossiblePosition(nextPosition[0], nextPosition[1]) && room[nextPosition[0]][nextPosition[1]] == 0) {
                    robot.move(nextPosition[0], nextPosition[1]);
                }
            } else {
                robot.tryReverse();
            }

        }

        bw.write(Integer.toString(robot.getCleaningCount()));
        bw.flush();
        br.close();
        bw.close();
    }
}
