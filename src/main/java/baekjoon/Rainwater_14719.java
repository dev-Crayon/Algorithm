package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Rainwater_14719 {

    static int H, W;
    static int[][] blocks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int answer = 0;

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        blocks = new int[H][W];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            int height = Integer.parseInt(st.nextToken());
            for (int j = H - 1; j >= 0; j--) {
                if (height > 0) {
                    blocks[j][i] = 1;
                    height--;
                }
            }
        }

        // 빗물 계산
        for (int i = 0; i < H; i++) {
            int last = -1;
            for (int j = 0; j < W; j++) {
                if (blocks[i][j] == 1) {
                    if (last != -1) {
                        answer += j - last - 1;
                    }
                    last = j;
                }
            }
        }

        System.out.println(answer);
    }
}
