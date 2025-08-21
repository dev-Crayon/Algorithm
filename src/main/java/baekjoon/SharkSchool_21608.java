package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SharkSchool_21608 {

    static int N;
    static int[][] classroom;
    static Map<Integer, List<Integer>> favoriteStudent = new HashMap<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static List<List<Integer>> firstCondition(int student) {
        List<List<Integer>> result = new ArrayList<>();
        int favoriteCount = 0;

        // 비어있는 칸 확인
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (classroom[x][y] == 0) {
                    int tempFavoriteCount = 0;

                    // 인접한 칸 확인
                    for (int direction = 0; direction < 4; direction++) {
                        int nearX = x + dx[direction];
                        int nearY = y + dy[direction];

                        // 교실을 벗어나지 않았는지 확인
                        if (0 > nearX || nearX >= N || 0 > nearY || nearY >= N) {
                            continue;
                        }

                        // 해당 자리에 학생이 존재하는지 확인
                        if (classroom[nearX][nearY] == 0) {
                            continue;
                        }

                        List<Integer> favorites = favoriteStudent.get(student);
                        // 좋아하는 친구인지 확인
                        if (favorites.contains(classroom[nearX][nearY])) {
                            tempFavoriteCount++;
                        }
                    }

                    // 좋아하는 학생이 가장 많은 칸인지 확인
                    if (tempFavoriteCount > favoriteCount) {
                        favoriteCount = tempFavoriteCount;
                        result = new ArrayList<>();
                        result.add(List.of(x, y));
                    } else if (tempFavoriteCount == favoriteCount) {
                        result.add(List.of(x, y));
                    }
                }
            }
        }

        return result;
    }

    private static List<List<Integer>> secondCondition(List<List<Integer>> candidatePositions) {
        List<List<Integer>> result = new ArrayList<>();
        int emptySpaceCount = 0;

        // 후보칸 확인
        for (List<Integer> candidate : candidatePositions) {
            int x = candidate.get(0);
            int y = candidate.get(1);
            int tempEmptySpaceCount = 0;

            for (int direction = 0; direction < 4; direction++) {
                int nearX = x + dx[direction];
                int nearY = y + dy[direction];

                // 교실을 벗어나지 않았는지 확인
                if (0 > nearX || nearX >= N || 0 > nearY || nearY >= N) {
                    continue;
                }

                // 해당 자리에 학생이 존재하는지 확인
                if (classroom[nearX][nearY] == 0) {
                    tempEmptySpaceCount++;
                }
            }

            if (tempEmptySpaceCount > emptySpaceCount) {
                emptySpaceCount = tempEmptySpaceCount;
                result = new ArrayList<>();
                result.add(List.of(x, y));
            } else if (tempEmptySpaceCount == emptySpaceCount) {
                result.add(List.of(x, y));
            }
        }

        return result;
    }

    private static List<Integer> thirdCondition(List<List<Integer>> candidatePositions) {
        // 후보 자리중에서 x, y의 값이 최소인 자리 찾기
        candidatePositions.sort(Comparator
                .comparing((List<Integer> list) -> list.get(0))
                .thenComparing(list -> list.get(1))
        );

        return candidatePositions.get(0);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        classroom = new int[N][N];
        List<Integer> studentOrder = new ArrayList<>();
        int answer = 0;

        for (int i = 1; i <= N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            List<Integer> favoriteList = new ArrayList<>();
            int student = Integer.parseInt(st.nextToken());
            studentOrder.add(student);
            while (st.hasMoreTokens()) {
                favoriteList.add(Integer.parseInt(st.nextToken()));
            }

            favoriteStudent.put(student, favoriteList);
        }

        for (int s : studentOrder) {
            // 1번 조건의 일치하는 자리가 하나라면
            List<List<Integer>> firstPositions = firstCondition(s);
            if (firstPositions.size() == 1) {
                List<Integer> position = firstPositions.get(0);
                classroom[position.get(0)][position.get(1)] = s;
            } else {
                List<List<Integer>> secondPositions = secondCondition(firstPositions);
                if (secondPositions.size() == 1) {
                    List<Integer> position = secondPositions.get(0);
                    classroom[position.get(0)][position.get(1)] = s;
                } else {
                    List<Integer> position = thirdCondition(secondPositions);
                    classroom[position.get(0)][position.get(1)] = s;
                }
            }
        }

        // 만족도 구하기
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                List<Integer> favorites = favoriteStudent.get(classroom[x][y]);
                int count = 0;
                for (int direction = 0; direction < 4; direction++) {
                    int nearX = x + dx[direction];
                    int nearY = y + dy[direction];

                    if (0 <= nearX && nearX < N && 0 <= nearY && nearY < N && favorites.contains(classroom[nearX][nearY])) {
                        count++;
                    }
                }

                if (count != 0) {
                    answer += (int) Math.pow(10, count - 1);
                }
            }
        }

        System.out.println(answer);
    }
}
