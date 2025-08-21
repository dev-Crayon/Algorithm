package baekjoon;

import java.io.*;
import java.util.*;

public class MakePassword_1759 {

    static int L, C;
    static String[] cand;     // 후보 문자들(정렬됨)
    static String[] seq;      // 현재까지 만든 암호
    static StringBuilder sb = new StringBuilder();

    static boolean isVowel(char c) {
        return c=='a' || c=='e' || c=='i' || c=='o' || c=='u';
    }

    static void dfs(int start, int depth, int vowelCnt, int consonantCnt) {
        if (depth == L) {
            if (vowelCnt >= 1 && consonantCnt >= 2) {
                for (int i = 0; i < L; i++) sb.append(seq[i]);
                sb.append('\n');
            }
            return;
        }

        for (int i = start; i < C; i++) {
            char ch = cand[i].charAt(0);
            seq[depth] = cand[i];
            if (isVowel(ch)) {
                dfs(i + 1, depth + 1, vowelCnt + 1, consonantCnt);   // ✅ i+1로 진행
            } else {
                dfs(i + 1, depth + 1, vowelCnt, consonantCnt + 1);   // ✅ i+1로 진행
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        cand = br.readLine().trim().split(" ");
        Arrays.sort(cand);                // 사전순 요구
        seq = new String[L];

        dfs(0, 0, 0, 0);
        System.out.print(sb.toString());
    }
}
