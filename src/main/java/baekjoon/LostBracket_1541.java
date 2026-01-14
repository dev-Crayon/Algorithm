package baekjoon;

import java.io.*;

public class LostBracket_1541 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String expression = br.readLine();

        String[] minusGroups = expression.split("-");

        int result = 0;

        String[] firstGroup = minusGroups[0].split("\\+");
        for (String num : firstGroup) {
            result += Integer.parseInt(num);
        }

        for (int i = 1; i < minusGroups.length; i++) {
            String[] plusGroup = minusGroups[i].split("\\+");
            for (String num : plusGroup) {
                result -= Integer.parseInt(num);
            }
        }

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}