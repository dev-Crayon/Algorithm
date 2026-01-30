package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Laboratory_14502 {

    static int N;
    static int M;
    static int[][] lab;
    static List<List<Integer>> candidateList = new ArrayList<>();
    static List<List<List<Integer>>> combinationList = new ArrayList<>();
    static List<int[]> virusList = new ArrayList<>();
    static int maxSafeArea = 0;

    // 상하좌우 이동
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static void makeCandidate() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (lab[i][j] == 0) {  // 빈 칸만 후보로 추가
                    List<Integer> tempList = new ArrayList<>();
                    tempList.add(i);
                    tempList.add(j);
                    candidateList.add(tempList);
                } else if (lab[i][j] == 2) {  // 바이러스 위치 저장
                    virusList.add(new int[]{i, j});
                }
            }
        }
    }

    private static void makeCombinationList(int r, boolean[] isUsed, int currentPosition) {
        if (r == 0) {
            List<List<Integer>> tempList = new ArrayList<>();
            for (int i = 0; i < isUsed.length; i++) {
                if (isUsed[i]) {
                    tempList.add(candidateList.get(i));
                }
            }
            combinationList.add(tempList);
        } else {
            for (int i = currentPosition; i < isUsed.length; i++) {
                isUsed[i] = true;
                makeCombinationList(r - 1, isUsed, i + 1);
                isUsed[i] = false;
            }
        }
    }

    // 바이러스 확산 시뮬레이션 (BFS)
    private static void spreadVirus(int[][] tempLab) {
        Queue<int[]> queue = new LinkedList<>();

        // 모든 바이러스 위치를 큐에 추가
        for (int[] virus : virusList) {
            queue.offer(new int[]{virus[0], virus[1]});
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            // 상하좌우로 확산
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위 체크 및 빈 칸인지 확인
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && tempLab[nx][ny] == 0) {
                    tempLab[nx][ny] = 2;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }

    // 안전 영역 개수 세기
    private static int countSafeArea(int[][] tempLab) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tempLab[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    // 배열 복사
    private static int[][] copyLab() {
        int[][] temp = new int[N][M];
        for (int i = 0; i < N; i++) {
            temp[i] = lab[i].clone();
        }
        return temp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lab = new int[N][M];

        for (int i = 0; i < N; i++) {
            lab[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        // 1. 후보 좌표 생성 (빈 칸만)
        makeCandidate();

        // 2. 3개를 선택하는 조합 생성
        int candidateSize = candidateList.size();
        makeCombinationList(3, new boolean[candidateSize], 0);

        // 3. 모든 조합에 대해 시뮬레이션
        for (List<List<Integer>> combination : combinationList) {
            // 연구소 복사
            int[][] tempLab = copyLab();

            // 벽 3개 세우기
            for (List<Integer> wall : combination) {
                int x = wall.get(0);
                int y = wall.get(1);
                tempLab[x][y] = 1;
            }

            // 바이러스 확산
            spreadVirus(tempLab);

            // 안전 영역 개수 세기
            int safeArea = countSafeArea(tempLab);

            // 최댓값 갱신
            maxSafeArea = Math.max(maxSafeArea, safeArea);
        }

        System.out.println(maxSafeArea);
    }
}