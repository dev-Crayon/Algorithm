package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Meteors_14658 {

    static int saveMeteorCount(int[][] meteors, int x, int y, int L, int N, int M) {
        int count = 0;

        int maxX = Math.min(x + L, N);
        int maxY = Math.min(y + L, M);

        for (int[] meteor : meteors) {
            int meteorX = meteor[0];
            int meteorY = meteor[1];

            if (meteorX >= x && meteorX <= maxX && meteorY >= y && meteorY <= maxY) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] meteors = new int[K][2];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            meteors[i][0] = Integer.parseInt(st.nextToken());
            meteors[i][1] = Integer.parseInt(st.nextToken());
        }

        int maxSaved = 0;
        for (int i = 0; i < K; i++) {
            int minX = meteors[i][0];
            for (int j = 0; j < K; j++) {
                int minY = meteors[j][1];
                int count = saveMeteorCount(meteors, minX, minY, L, N, M);
                maxSaved = Math.max(maxSaved, count);
            }
        }

        System.out.println(K - maxSaved);
    }
}
