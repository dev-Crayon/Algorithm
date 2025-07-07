package baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class LeastPrice_1916 {

    private static int N;
    private static int M;
    private static int origin;
    private static int destination;
    private static ArrayList<ArrayList<Node>> list;
    private static int[] dist;
    private static boolean[] visited;

    private static class Node implements Comparable<Node> {
        int end;
        int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }

    private static int dijkstra(int o, int d) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(o, 0));
        dist[o] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int cur = current.end;

            // 아직 방문하지 않았다면
            if (!visited[cur]) {
                visited[cur] = true;

                for (Node node : list.get(cur)) {
                    if (!visited[node.end] && dist[node.end] > dist[cur] + node.weight) {
                        dist[node.end] = dist[cur] + node.weight;
                        pq.add(new Node(node.end, dist[node.end]));
                    }
                }
            }
        }

        return dist[d];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        StringTokenizer st;

        list = new ArrayList<>();
        dist = new int[N + 1];
        visited = new boolean[N + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int finish = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            list.get(start).add(new Node(finish, value));
        }

        st = new StringTokenizer(br.readLine());
        origin = Integer.parseInt(st.nextToken());
        destination = Integer.parseInt(st.nextToken());

        bw.write(dijkstra(origin, destination) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
