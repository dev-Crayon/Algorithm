package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ABCDE_13023 {

    static int N;
    static int M;
    static Map<Integer, List<Integer>> relation = new HashMap<>();
    static boolean[] visited;
    static int answer = 0;

    static void dfs(int person, int depth) {

        if (depth == 4) {
            answer = 1;
            return;
        }

        visited[person] = true;
        for (int friend : relation.get(person)) {
            if (!visited[friend]) {
                dfs(friend, depth + 1);
            }
        }
        visited[person] = false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        M = input[1];

        for (int i = 0; i < N; i++) {
            relation.put(i, new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int one = input[0];
            int two = input[1];
            relation.get(one).add(two);
            relation.get(two).add(one);
        }

        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            dfs(i, 0);
            if (answer == 1) break;
        }

        System.out.println(answer);
    }
}
