package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ClassroomAssignment_11000 {

    private static int N;
    private static int minimumClassroom = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[][] classTimes = new int[N][2];
        for (int i = 0; i < N; i++) {
            classTimes[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        Arrays.sort(classTimes, (o1, o2) -> {
            if (o1[0] == o2[0]) { // 시작 시간이 같으면 종료 시간으로 비교
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0]; // 시작 시간으로 비교
        });

        PriorityQueue<Integer> classroomEndTimes = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int currentStartTime = classTimes[i][0];
            int currentEndTime = classTimes[i][1];

            if (!classroomEndTimes.isEmpty() && currentStartTime >= classroomEndTimes.peek()) {
                classroomEndTimes.poll(); // 가장 빨리 비는 강의실을 재활용
            }

            classroomEndTimes.add(currentEndTime);
        }

        sb.append(classroomEndTimes.size());
        System.out.println(sb);
        br.close();
    }
}
