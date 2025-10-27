package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class AC_5430 {

    private static int[] stringToIntArray(String s) {
        if (s.length() == 2) {
            return new int[] {};
        }

        s = s.substring(1, s.length() - 1);
        String[] splitS = s.split(",");
        int[] result = new int[splitS.length];

        for (int index = 0; index < result.length; index++) {
            result[index] = Integer.parseInt(splitS[index]);
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();

        // 테스트 반복
        for (int test = 1; test <= testCase; test++) {
            Deque<Integer> deque = new ArrayDeque<>();
            boolean isFront = true;
            boolean isError = false;
            String[] command = br.readLine().split("");
            int arrLength = Integer.parseInt(br.readLine());
            int[] arr = stringToIntArray(br.readLine());

            // 입력받은 배열 deque에 삽입
            for (int number : arr) {
                deque.addLast(number);
            }

            // 명령어 수행
            for (String c : command) {
                if (c.equals("R")) {
                    isFront = !isFront;
                    continue;
                }

                // 비어있는데 삭제연산이 요청될 때
                if (deque.isEmpty()) {
                    isError = true;
                } else {
                    if (isFront) {
                        deque.removeFirst();
                    } else {
                        deque.removeLast();
                    }
                }
            }

            if (isError) {
                answer.append("error\n");
            } else {
                answer.append("[");
                // 배열이 정순이라면
                if (isFront) {
                    while (!deque.isEmpty()) {
                        if (deque.size() == 1) {
                            answer.append(deque.pollFirst());
                            break;
                        }
                        answer.append(deque.pollFirst()).append(",");
                    }
                } else { // 배열이 역순이라면
                    while (!deque.isEmpty()) {
                        if (deque.size() == 1) {
                            answer.append(deque.pollLast());
                            break;
                        }
                        answer.append(deque.pollLast()).append(",");
                    }
                }

                answer.append("]\n");
            }
        }

        System.out.println(answer);
    }
}
