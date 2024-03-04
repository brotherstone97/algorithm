//hard nodeinfo 정렬은 성공했으나, 정렬된 배열을 이용해 이진트리를 만드는 건 실패
import java.util.*;

class Solution {
    private int idx;
    int[][] answer;
    
    public int[][] solution(int[][] nodeinfo) {
        answer = new int[2][nodeinfo.length];
        Node[] nodes = new Node[nodeinfo.length];
        
        for(int i=0; i<nodeinfo.length; i++){
            nodes[i] = new Node(i+1, null, null, nodeinfo[i][0], nodeinfo[i][1]);
        }
        
        Arrays.sort(nodes, (e1,e2)->{
            if(e1.y==e2.y){
                return e1.x-e2.x;
            }
            return e2.y-e1.y;
        }
        );
        
        Node root = nodes[0];
        for(int i=1; i<nodes.length; i++){
            insertNode(root, nodes[i]);
        }
        
        preOrder(root);
        idx=0;
        postOrder(root);
        
        return answer;
    }
    
    private void insertNode(Node parent, Node child){
        if(parent.x > child.x){
           if(parent.leftChild==null){
               parent.leftChild = child;
           }else{
               insertNode(parent.leftChild, child);
           } 
        } else{
            if(parent.rightChild==null){
                parent.rightChild=child;
            }else{
                insertNode(parent.rightChild, child);
            }
        }
    }
    
    private void preOrder(Node currentNode){
        answer[0][idx++]=currentNode.value;
        
        if(currentNode.leftChild!=null){
            preOrder(currentNode.leftChild);
        }
        if(currentNode.rightChild!=null){
            preOrder(currentNode.rightChild);
        }
    }    
    
    private void postOrder(Node currentNode){
        if(currentNode.leftChild!=null){
            postOrder(currentNode.leftChild);
        }
        if(currentNode.rightChild!=null){
            postOrder(currentNode.rightChild);
        }
        
        answer[1][idx++]=currentNode.value;
    }
    
    class Node{
        public int value;
        public Node leftChild;
        public Node rightChild;
        public int x;
        public int y;
        
        Node(int value, Node leftChild, Node rightChild, int x, int y){
            this.value=value;
            this.leftChild=leftChild;
            this.rightChild = rightChild;
            this.x=x;
            this.y=y;
        }
    }
}