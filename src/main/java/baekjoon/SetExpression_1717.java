package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SetExpression_1717 {

    static int n, m;
    static int[] parents;
    static int[] size;

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) {
            return;
        }

        // 사이즈가 더 작은쪽이 이동
        if (size[a] < size[b]) {
            parents[a] = b;
            size[b] += size[a];
        } else {
            parents[b] = a;
            size[a] += size[b];
        }
    }

    static int find(int a) {
        while (parents[a] != a) {
            parents[a] = parents[parents[a]];
            a = parents[a];
        }

        return a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parents = new int[n + 1];
        size = new int[n + 1];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
            size[i] = 1;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int operation = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 유니온 연산
            if (operation == 0) {
                union(a, b);
            }

            // 파인드 연산
            if (operation == 1) {
                int aParent = find(a);
                int bParent = find(b);
                if (aParent == bParent) {
                    sb.append("YES\n");
                } else {
                    sb.append("NO\n");
                }
            }
        }

        System.out.println(sb);
        br.close();
    }
}