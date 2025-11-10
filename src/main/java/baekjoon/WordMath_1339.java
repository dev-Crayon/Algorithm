package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class WordMath_1339 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Character, Integer> alphabetMap = new HashMap<>();

        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
            for (int j = 0; j < words[i].length(); j++) {
                char c = words[i].charAt(j);
                int weight = (int) Math.pow(10, words[i].length() - j - 1);
                alphabetMap.put(c, alphabetMap.getOrDefault(c, 0) + weight);
            }
        }

        List<Map.Entry<Character, Integer>> sortedList = new ArrayList<>(alphabetMap.entrySet());
        sortedList.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        Map<Character, Integer> charToNum = new HashMap<>();
        int number = 9;
        for (Map.Entry<Character, Integer> entry : sortedList) {
            charToNum.put(entry.getKey(), number--);
        }

        int answer = 0;
        for (String word : words) {
            int num = 0;
            for (char c : word.toCharArray()) {
                num = num * 10 + charToNum.get(c);
            }
            answer += num;
        }

        System.out.println(answer);
    }
}
