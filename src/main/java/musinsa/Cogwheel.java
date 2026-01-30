import java.io.*;
import java.util.*;

public class Cogwheel {

    static List<Cog> cogList = new ArrayList<>();

    public static class Cog {
        int[] status;
        int index;

        public Cog(int[] input, int index) {
            this.status = input;
            this.index = index;
        }

        public int[] getStatus() {
            return this.status;
        }

        public void turnRight() {
            int temp = status[7];
            for (int i = 7; i >= 0; i--) {
                if (i == 0) {
                    status[i] = temp;
                    break;
                }
                status[i] = status[i - 1];
            }
        }

        public void turnLeft() {
            int temp = status[0];
            for (int i = 0; i < 8; i++) {
                if (i == 7) {
                    status[i] = temp;
                    break;
                }
                status[i] = status[i + 1];
            }
        }

        public int getLeft() {
            return status[6];
        }

        public int getRight() {
            return status[2];
        }

        public int getNorth() {
            return status[0];
        }
    }

    private static void recursion(int currentIndex, int previousIndex, int direction) {
        Cog currentCog = cogList.get(currentIndex);
        Cog leftCog = null;
        Cog rightCog = null;
        int leftPole = currentCog.getLeft();
        int rightPole = currentCog.getRight();

        if (direction == 1) {
            currentCog.turnRight();
        } else {
            currentCog.turnLeft();
        }

        if (currentIndex != 0) {
            leftCog = cogList.get(currentIndex - 1);
            if (currentIndex - 1 == previousIndex) {
                leftCog = null;
            }
        }
        if (currentIndex != 3) {
            rightCog = cogList.get(currentIndex + 1);
            if (currentIndex + 1 == previousIndex) {
                rightCog = null;
            }
        }

        int nextDirection = direction == 1 ? -1 : 1;
        if (leftCog != null) {
            if (leftPole != leftCog.getRight()) {
                recursion(currentIndex - 1, currentIndex, nextDirection);
            }
        }
        if (rightCog != null) {
            if (rightPole != rightCog.getLeft()) {
                recursion(currentIndex + 1, currentIndex, nextDirection);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 4; i++) {
            cogList.add(new Cog(Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray(), i));
        }

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int cogIndex = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken());

            recursion(cogIndex, -1, direction);
        }

        int answer = 0;
        for (int i = 0; i < 4; i++) {
            if (cogList.get(i).getNorth() == 1) {
                answer += (int) Math.pow(2, i);
            }
        }

        System.out.println(answer);
    }
}
