package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class FindPassword_17219 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = input[0];
        int M = input[1];

        HashMap<String, String> passwordMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String[] password = br.readLine().split(" ");
            passwordMap.put(password[0], password[1]);
        }

        for (int i = 0; i < M; i++) {
            String url = br.readLine();
            bw.write(passwordMap.get(url));
            bw.newLine();
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
