package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tree_1068 {

    static int N;
    static int removeNodeValue;
    static int answer = 0;

    private static class Node {

        int value;
        List<Node> childNodes;

        public Node(int value) {
            this.value = value;
            childNodes = new ArrayList<>();
        }

        public boolean isLeafNode() {
            return childNodes.isEmpty();
        }

        public void addChildNode(Node node) {
            childNodes.add(node);
        }
    }

    private static void dfs(Node node) {
        if (node.value == removeNodeValue) return;

        int validChildren = 0;
        for (Node child : node.childNodes) {
            if (child.value == removeNodeValue) continue;
            validChildren++;
            dfs(child);
        }

        if (validChildren == 0) {
            answer++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] nodeParents = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        List<Node> nodeList = new ArrayList<>();
        int rootNodeValue = -1;

        // 모든 노드 생성
        for (int index = 0; index < N; index++) {
            nodeList.add(new Node(index));
        }

        // 노드 연결
        for (int index = 0; index < nodeParents.length; index++) {
            // 루트노드 등록
            if (nodeParents[index] == -1) {
                rootNodeValue = index;
                continue;
            }

            int parentNodeValue = nodeParents[index];
            Node parentNode = nodeList.get(parentNodeValue);
            Node childNode = nodeList.get(index);
            parentNode.addChildNode(childNode);
        }

        // 지울 노드
        removeNodeValue = Integer.parseInt(br.readLine());

        // 그래프 탐색
        dfs(nodeList.get(rootNodeValue));

        System.out.println(answer);
    }
}
