package impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ChickenDelivery {

    private static int N;
    private static int M;
    private static int[][] map;
    private static List<List<Integer>> chickenStore;
    private static List<List<Integer>> house;
    private static List<List<List<Integer>>> combinations = new ArrayList<>();

    private static void comb(int start, List<List<Integer>> selected) {
        if (selected.size() == M) {
            combinations.add(new ArrayList<>(selected));
            return;
        }

        for (int i = start; i < chickenStore.size(); i++) {
            selected.add(chickenStore.get(i));
            comb(i + 1, selected);
            selected.remove(selected.size() - 1);
        }
    }

    public int solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        M = input[1];
        map = new int[N][N];
        chickenStore = new ArrayList<>();
        house = new ArrayList<>();

        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < map[i].length; j++) {
                // 집 위치정보 기록
                if (map[i][j] == 1) {
                    house.add(List.of(i, j));
                }

                // 치킨집 위치정보 기록
                if (map[i][j] == 2) {
                    chickenStore.add(List.of(i, j));
                }
            }
        }

        // M개의 치킨집을 뽑는 조합 구하기
        comb(0, new ArrayList<>());

        // 현재 조합에 대해 도시의 치킨 거리 계산
        for (List<List<Integer>> selectedChickenStore : combinations) {
            int totalDistance = 0;

            // 각 집에 대해 가장 가까운 치킨집 거리 구하기
            for (List<Integer> h : house) {
                int hX = h.get(0);
                int hY = h.get(1);
                int minDistance = Integer.MAX_VALUE;

                for (List<Integer> c : selectedChickenStore) {
                    int cX = c.get(0);
                    int cY = c.get(1);
                    int distance = Math.abs(hX - cX) + Math.abs(hY - cY);
                    minDistance = Math.min(minDistance, distance);
                }

                totalDistance += minDistance;
            }

            answer = Math.min(answer, totalDistance);
        }

        return answer;
    }
}
