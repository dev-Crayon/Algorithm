package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CutTree_2805 {

    static int N, M;
    static int[] treeLengths;

    private static long getCutTreeLength(int[] treeLengthList, long cutLength) {
        long sum = 0;
        for (int j : treeLengthList) {
            if (j > cutLength) {
                sum += j - cutLength;
            }
        }

        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        treeLengths = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int maxTreeLength = treeLengths[0];
        for (int i = 1; i < treeLengths.length; i++) {
            if (maxTreeLength < treeLengths[i]) {
                maxTreeLength = treeLengths[i];
            }
        }

        long cutLength = 0;
        long left = 0;
        long right = maxTreeLength;
        long answer = 0;
        while (left <= right) {
            long mid = (left + right) / 2;

            long lengthSum = getCutTreeLength(treeLengths, mid);
            if (lengthSum >= M) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }
}
