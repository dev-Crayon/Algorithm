package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CutLANWire_1654 {

    static int K, N;
    static int[] LANWireLength;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        LANWireLength = new int[K];

        long maxLen = 0;
        for (int i = 0; i < K; i++) {
            LANWireLength[i] = Integer.parseInt(br.readLine());
            if (LANWireLength[i] > maxLen) maxLen = LANWireLength[i];
        }

        long left = 1;
        long right = maxLen;
        long answer = 0;

        while (left <= right) {
            long mid = (left + right) / 2;
            long count = 0;

            for (int i = 0; i < K; i++) {
                count += (LANWireLength[i] / mid);
            }

            if (count >= N) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }
}
