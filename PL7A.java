import java.util.Scanner;

class Tree{
    public static class Node {
        int data;
        Node left, right;
        int parent;

        public Node(int item,int p) {
            data = item;
            left = right = null;
            parent=p;
        }
    }
    Node root;
    Tree()
    {
        root=null;
    }
    public static Node BST(int n,int p,Scanner scan)
    {
    if(n<=0)
        return null;
        Node lef=BST(n/2,0,scan);
        int data=scan.nextInt();
        Node ne=new Node(data,p);
        if(lef!=null)
            lef.parent=ne.data;
        ne.left=lef;
        ne.right=BST(n-n/2-1,ne.data,scan);
        return ne;
    }
    public void inorder()
    {
        inorderr(root);
    }
    void inorderr(Node root)
    {
        if(root!=null)
        {
            inorderr(root.left);
            System.out.println(root.parent);
            inorderr(root.right);
        }
    }
}

public class Main {


    public static void main(String[] args) {
        // write your code here
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Tree t=new Tree();
        t.root=t.BST(n,0,scan);
        t.inorder();
    }
}