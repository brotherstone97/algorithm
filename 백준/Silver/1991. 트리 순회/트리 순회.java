import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        Tree tree = new Tree();
        Node rootNode;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String data = st.nextToken();
            String left = st.nextToken();
            String right = st.nextToken();

            tree.createNode(data, left, right);
            Node node = new Node(data);
        }
        tree.preOrder(tree.root);
        System.out.println();
        tree.inOrder(tree.root);
        System.out.println();
        tree.postOrder(tree.root);
    }

    static class Node {
        String data;
        Node left;
        Node right;

        Node(String data) {
            this.data = data;
        }
    }

    static class Tree {
        Node root = new Node(".");

        void createNode(String mid, String left, String right) {
            if (root.data.equals(".")) {
                if (!mid.equals(".")) {
                    root = new Node(mid);
                }
                if (!left.equals(".")) {
                    root.left = new Node(left);
                }
                if (!right.equals(".")) {
                    root.right = new Node(right);
                }
            } else {
                findNode(root, mid, left, right);
            }
        }

        void findNode(Node node, String mid, String left, String right) {
            if (node == null) {
                return;
            }
            if (mid.equals(node.data)) {
                if (!left.equals(".")) {
                    node.left = new Node(left);
                }
                if (!right.equals(".")) {
                    node.right = new Node(right);
                }
            }
            findNode(node.left, mid, left, right);
            findNode(node.right, mid, left, right);
        }

        void preOrder(Node node) {
            System.out.print(node.data);
            if (node.left != null) {
                preOrder(node.left);
            }
            if (node.right != null) {
                preOrder(node.right);
            }
        }

        void inOrder(Node node) {
            if (node.left != null) {
                inOrder(node.left);
            }
            System.out.print(node.data);
            if (node.right != null) {
                inOrder(node.right);
            }
        }

        void postOrder(Node node) {
            if (node.left != null) {
                postOrder(node.left);
            }
            if (node.right != null) {
                postOrder(node.right);
            }
            System.out.print(node.data);
        }
    }


}