package baekjoon;

import java.io.*;
import java.util.Arrays;

public class RollingDice_14499 {

    private static int N;
    private static int M;
    private static int K;
    private static int startX;
    private static int startY;

    private static int[][] map;
    private static int[] dice;
    private static int diceTop = 1;

    private static void moveDice(int direction, int goalX, int goalY) {
        boolean isZero = map[goalX][goalY] == 0;

        switch (direction) {
            // 동쪽
            case 1:
                if (diceTop == 1 || diceTop == 2 || diceTop == 5 || diceTop == 6) {
                    if (isZero) map[goalX][goalY] = dice[2];
                    else dice[2] = map[goalX][goalY];
                    diceTop = 4;
                    break;
                }

                if (diceTop == 4) {
                    if (isZero) map[goalX][goalY] = dice[0];
                    else dice[0] = map[goalX][goalY];
                    diceTop = 6;
                    break;
                }

                if (diceTop == 3) {
                    if (isZero) map[goalX][goalY] = dice[5];
                    else dice[5] = map[goalX][goalY];
                    diceTop = 1;
                    break;
                }
            // 서쪽
            case 2:
                if (diceTop == 1 || diceTop == 2 || diceTop == 5 || diceTop == 6) {
                    if (isZero) map[goalX][goalY] = dice[3];
                    else dice[3] = map[goalX][goalY];
                    diceTop = 3;
                    break;
                }

                if (diceTop == 4) {
                    if (isZero) map[goalX][goalY] = dice[5];
                    else dice[5] = map[goalX][goalY];
                    diceTop = 1;
                    break;
                }

                if (diceTop == 3) {
                    if (isZero) map[goalX][goalY] = dice[0];
                    else dice[0] = map[goalX][goalY];
                    diceTop = 6;
                    break;
                }
            // 북쪽
            case 3:
                if (diceTop == 1 || diceTop == 3 || diceTop == 4) {
                    if (isZero) map[goalX][goalY] = dice[1];
                    else dice[1] = map[goalX][goalY];
                    diceTop = 5;
                    break;
                }

                if (diceTop == 2) {
                    if (isZero) map[goalX][goalY] = dice[5];
                    else dice[5] = map[goalX][goalY];
                    diceTop = 1;
                    break;
                }

                if (diceTop == 5) {
                    if (isZero) map[goalX][goalY] = dice[0];
                    else dice[0] = map[goalX][goalY];
                    diceTop = 6;
                    break;
                }

                if (diceTop == 6) {
                    if (isZero) map[goalX][goalY] = dice[4];
                    else dice[4] = map[goalX][goalY];
                    diceTop = 2;
                    break;
                }
            // 남쪽
            case 4:
                if (diceTop == 1 || diceTop == 3 || diceTop == 4) {
                    if (isZero) map[goalX][goalY] = dice[4];
                    else dice[4] = map[goalX][goalY];
                    diceTop = 2;
                    break;
                }

                if (diceTop == 2) {
                    if (isZero) map[goalX][goalY] = dice[0];
                    else dice[0] = map[goalX][goalY];
                    diceTop = 6;
                    break;
                }

                if (diceTop == 5) {
                    if (isZero) map[goalX][goalY] = dice[5];
                    else dice[5] = map[goalX][goalY];
                    diceTop = 1;
                    break;
                }

                if (diceTop == 6) {
                    if (isZero) map[goalX][goalY] = dice[1];
                    else dice[1] = map[goalX][goalY];
                    diceTop = 5;
                    break;
                }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        M = input[1];
        startX = input[2];
        startY = input[3];
        K = input[4];
        map = new int[N][M];
        dice = new int[6];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // 명령 실행
        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int command = 0; command < input.length; command++) {
            int direction = input[command];
            int nextX = startX;
            int nextY = startY;
            if (direction == 1) {
                nextY++;
            }

            if (direction == 2) {
                nextY--;
            }

            if (direction == 3) {
                nextX--;
            }

            if (direction == 4) {
                nextX++;
            }

            System.out.println("X: " + startX + ", Y: " + startY);

            // 지도를 벗어난 경우 명령 무시
            if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) continue;
            startX = nextX;
            startY = nextY;

            moveDice(direction, startX, startY);
            System.out.println(Arrays.toString(dice));
            System.out.println(diceTop);
            System.out.println(dice[diceTop - 1]);
            System.out.println("---------------------");
        }
    }
}
