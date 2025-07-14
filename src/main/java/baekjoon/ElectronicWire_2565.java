package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class ElectronicWire_2565 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] wires = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            wires[i][0] = Integer.parseInt(st.nextToken()); // A
            wires[i][1] = Integer.parseInt(st.nextToken()); // B
        }

        // 1. A 오름차순 정렬
        Arrays.sort(wires, Comparator.comparingInt(a -> a[0]));

        // 2. B 값만 LIS
        List<Integer> lis = new ArrayList<>();
        for (int[] w : wires) {
            int b = w[1];
            int idx = Collections.binarySearch(lis, b);
            if (idx < 0) idx = -idx - 1;      // 삽입 위치

            if (idx == lis.size()) lis.add(b);
            else lis.set(idx, b);
        }

        int answer = N - lis.size();  // 제거할 최소 전깃줄
        System.out.println(answer);
    }
}
