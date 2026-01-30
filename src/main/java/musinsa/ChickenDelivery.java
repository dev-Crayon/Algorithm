import java.io.*;
import java.util.*;

public class ChickenDelivery {
    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
        int distanceTo(Point p) {
            return Math.abs(r - p.r) + Math.abs(c - p.c);
        }
    }
    
    static int N, M;
    static List<Point> houses = new ArrayList<>();
    static List<Point> chickens = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;
    
    static void combination(int start, int count, List<Point> selected) {
        if (count == M) {
            int cityDist = 0;
            for (Point house : houses) {
                int minDist = Integer.MAX_VALUE;
                for (Point chicken : selected) {
                    minDist = Math.min(minDist, house.distanceTo(chicken));
                }
                cityDist += minDist;
            }
            answer = Math.min(answer, cityDist);
            return;
        }
        
        for (int i = start; i < chickens.size(); i++) {
            selected.add(chickens.get(i));
            combination(i + 1, count + 1, selected);
            selected.remove(selected.size() - 1);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int val = Integer.parseInt(st.nextToken());
                if (val == 1) houses.add(new Point(i, j));
                else if (val == 2) chickens.add(new Point(i, j));
            }
        }
        
        combination(0, 0, new ArrayList<>());
        System.out.println(answer);
    }
}

