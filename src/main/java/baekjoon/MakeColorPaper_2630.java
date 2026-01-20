/*
시간 제한: 1초
메모리 제한: 128MB
 */

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MakeColorPaper_2630 {

    static int N;
    static int[][] colorPaper;
    static int whitePaper = 0;
    static int bluePaper = 0;

    private static void splitRecursion(int x, int y, int size) {
        // 내 영역이 모두 같은 색인지 확인
        boolean oneColor = true;
        int firstColor = colorPaper[x][y];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (colorPaper[i][j] != firstColor) {
                    oneColor = false;
                    break;
                }
            }

            if (!oneColor) {
                break;
            }
        }

        if (oneColor) {
            if (firstColor == 0) {
                whitePaper++;
            } else {
                bluePaper++;
            }
        } else { // 영역에 다른 색이 섞여있다면 네 영역으로 분리
            splitRecursion(x, y, size / 2);
            splitRecursion(x + (size / 2), y, size / 2);
            splitRecursion(x, y + (size / 2), size / 2);
            splitRecursion(x + (size / 2), y + (size / 2), size / 2);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        colorPaper = new int[N][N];
        for (int i = 0; i < N; i++) {
            colorPaper[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        splitRecursion(0, 0, N);
        sb.append(whitePaper).append("\n");
        sb.append(bluePaper);
        System.out.println(sb);
    }
}
