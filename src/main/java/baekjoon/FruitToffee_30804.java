package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FruitToffee_30804 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] fruits = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] count = new int[10];
        int left = 0;
        int kinds = 0;
        int maxLength = 0;

        for (int right = 0; right < N; right++) {
            if (count[fruits[right]] == 0) {
                kinds++;
            }
            count[fruits[right]]++;

            while (kinds > 2) {
                count[fruits[left]]--;
                if (count[fruits[left]] == 0) {
                    kinds--;
                }
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        System.out.println(maxLength);
    }
}
