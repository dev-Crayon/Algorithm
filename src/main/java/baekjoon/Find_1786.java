package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Find_1786 {

    private static int[] makeTable(String text) {
        int n = text.length();
        int[] table = new int[n];

        int index = 0;
        for (int i = 1; i < n; i++) {
            while (index > 0 && text.charAt(i) != text.charAt(index)) {
                index = table[index - 1];
            }

            if (text.charAt(i) == text.charAt(index)) {
                index++;
                table[i] = index;
            }
        }

        return table;
    }

    private static void KMP(String document, String pattern) {
        StringBuilder sb = new StringBuilder();

        int[] table = makeTable(pattern);

        int documentLength = document.length();
        int patternLength = pattern.length();

        int index = 0;
        int count = 0;
        for (int i = 0; i < documentLength; i++) {
            while (index > 0 && document.charAt(i) != pattern.charAt(index)) {
                index = table[index - 1];
            }

            if (document.charAt(i) == pattern.charAt(index)) {
                if (index == patternLength - 1) {
                    sb.append((i - index + 1)).append(" ");
                    count++;
                    index = table[index];
                } else {
                    index++;
                }
            }
        }

        System.out.println(count);
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String document = br.readLine();
        String pattern = br.readLine();

        KMP(document, pattern);
    }
}
