package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class MakePassword_1759 {

    private static int L;
    private static int C;
    private static Set<String> answer = new HashSet<>();

    // 모든 조합 구하기
    private static void comb(List<String> alphabetList, boolean[] selected, int r, int start) {
        // 다 뽑았다면
        if (r == 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < selected.length; i++) {
                if (selected[i]) sb.append(alphabetList.get(i));
            }
            if (isCorrectPassword(sb.toString())) {
                answer.add(sb.toString());
                return;
            }
        }

        // 아직 뽑아야 한다면
        for (int i = start; i < alphabetList.size(); i++) {
            selected[i] = true;
            comb(alphabetList, selected, r - 1, i + 1);
            selected[i] = false;
        }
    }

    // 최소 한 개의 모음, 최소 두 개의 자음으로 구성되었는지 확인
    private static boolean isCorrectPassword(String password) {
        int consonantCount = 0;
        int vowelCount = 0;
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');

        for (char ch : password.toCharArray()) {
            if (vowels.contains(ch)) vowelCount++;
            else consonantCount++;
        }

        return vowelCount >= 1 && consonantCount >= 2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        List<String> alphabetList = Arrays.stream(br.readLine().split(" ")).sorted().collect(Collectors.toList());
        comb(alphabetList, new boolean[C], L, 0);
        List<String> sortedAnswer = new ArrayList<>(new ArrayList<>(answer));
        Collections.sort(sortedAnswer);

        for (String s : sortedAnswer) {
            System.out.println(s);
        }
    }
}
