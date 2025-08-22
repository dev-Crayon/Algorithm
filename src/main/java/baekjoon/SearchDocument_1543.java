package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SearchDocument_1543 {

    static String document;
    static String find;
    static int answer = 0;

    private static void findAndRemove() {
        while (document.contains(find)) {
            int index = document.indexOf(find);
            document = document.substring(index + find.length());
            answer++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        document = br.readLine();
        find = br.readLine();

        findAndRemove();
        System.out.println(answer);
    }
}
