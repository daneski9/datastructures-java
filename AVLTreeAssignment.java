/***************************************************************************************
* Name       : Dane Coleman
* Assignment : AVL Trees
* Due Date   : 12/07/21
* Description: Practicing AVL tree. insert, do rotations, re-balance, and use the breadth function to display level by level
****************************************************************************************/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node{
    private Node left;
    private Node right;
    private int height;
    private int data;
    private int balanceFactor;
   
    public Node(int data){
        this.data = data;
        this.height = 0; //when insert happens, set height to 0 for the node.
    }
    public void setHeight(int height){
        this.height = height;
    }
    public void setbalanceFactor(int balanceFactor){
        this.balanceFactor = balanceFactor;
    }
    public void setLeft(Node left){
        this.left = left;
    }
    public void setRight(Node right){
        this.right = right;
    }
    public void setRight(Node right, int data){
        this.right = right;
        this.data = data;
    }
    public void setData(int data){
        this.data = data;
    }
    public Node getLeft(){
        return left;
    }
    public Node getRight(){
        return right;
    }
    public int getData(){
        return data;
    }
    public int getHeight(){
        return height;
    }
    public int getBalanceFactor(){
        return balanceFactor;
    }
}
final class AVLTree{
    private Node root;
    public Node getRoot(){
        return root;
    }
    public AVLTree(String textFile) throws FileNotFoundException{
        Scanner scan = new Scanner(new File(textFile));
        Node nn = new Node(scan.nextInt());
        root = nn;
        while(scan.hasNextInt()){
           insert(root, scan.nextInt());
        }
        scan.close();
    }
    public Node insert(Node node, int key){
        if(node == null){
           return (new Node(key));
        }
        if(key < node.getData()){
            node.setLeft(insert(node.getLeft(), key));
        }
        else{
            node.setRight(insert(node.getRight(), key));
        }
        node = rebalance(node); 
        node.setbalanceFactor(getBalance(node)); //update new balance factor
        return node;
    }
    public Node rebalance(Node node){
        node.setHeight(max(node.getLeft(), node.getRight())+1);
        int balance = getBalance(node);
        if (balance > 1){ //when it's left leaning
            if(getBalance(node.getLeft()) > 0){//single rotation (Left heavy outside)
                return rightRotate(node);
            }
            else{       //double rotation (If left heavy inside: left rotation first, right rotation 2nd, return top node)
                node.setLeft(leftRotate(node.getLeft()));
                return rightRotate(node);
            }
        }
        else if (balance < -1){ //when it's right leaning
            if(getBalance(node.getRight()) < 0){ //single rotation (right heavy outside)
                return leftRotate(node);
            }
            else{ //double rotation (if right heavy inside: right rotation first, left rotation 2nd, return top node)
                node.setRight(rightRotate(node.getRight()));
                return leftRotate(node);
            }
        }
        return node;
    }
    public Node leftRotate(Node node){
        Node y = node.getRight();
        Node z = y.getLeft();
        y.setLeft(node);
        node.setRight(z);
        node.setHeight(max(node.getLeft(), node.getRight())+1);
        y.setHeight(max(y.getLeft(), y.getRight())+1);
        node.setbalanceFactor(getBalance(node));
        if(node == root){ //updating root
            root = y;
        }
        return y;
    }
    public Node rightRotate(Node node){
        Node y = node.getLeft();
        Node z = y.getRight();
        y.setRight(node);
        node.setLeft(z);
        node.setHeight(max(node.getLeft(),node.getRight())+1);
        y.setHeight(max(y.getLeft(),y.getRight())+1);
        node.setbalanceFactor(getBalance(node));
        if(node == root){ //updating root
            root = y;
        }
        return y;
    }
    public int getBalance(Node node){ //balanced 
        if(node.getLeft() == null && node.getRight() == null){
            return 0;
        }
        else if(node.getLeft() == null){
            return -1 - node.getRight().getHeight();
        }
        else if(node.getRight() == null){
            return node.getLeft().getHeight() + 1;
        }
        else
        return node.getLeft().getHeight() - node.getRight().getHeight();
    }
    public int max(Node Left, Node Right){
        if(Left == null && Right == null){
            return -1;
        }
        if(Left == null){
            return Right.getHeight();
        }
        else if(Right == null){
            return Left.getHeight();
        }
        else{
        if(Left.getHeight() > Right.getHeight()){
            return Left.getHeight();
        }
        else{
            return Right.getHeight();
        }
        }
    }
    public void displayInLevelFormat(Node root) throws IOException{ //using a single queue
        FileWriter fw = new FileWriter("output.txt");
        System.out.println("Displaying in level format. data(h, bf)");
        System.out.println("--------------------------------------------------------");
        int nodesInCurrentLevel = 0;
        int nodesInNextLevel = 0;
        Queue<Node> display = new LinkedList<>();
        display.add(root);
        nodesInCurrentLevel++;
        while(nodesInCurrentLevel!= 0){
            Node cur = display.peek();
            System.out.print(cur.getData() + "("+cur.getHeight()+","+cur.getBalanceFactor()+") ");
            fw.write((cur.getData() + "("+cur.getHeight()+","+cur.getBalanceFactor()+") "));
            if(cur.getLeft()!=null){
            display.add(cur.getLeft());
            nodesInNextLevel++;
            }
            if(cur.getRight()!=null){
            display.add(cur.getRight());
            nodesInNextLevel++;
            }
            display.remove();
            nodesInCurrentLevel--;
            
            if(nodesInCurrentLevel == 0){
                nodesInCurrentLevel = nodesInNextLevel;
                nodesInNextLevel = 0;
                System.out.println("");
                fw.write("\n");
            }
        }
        fw.close();
    }
}
public class AVLTreeAssignment{
    public static void main(String[] args) throws FileNotFoundException, IOException {
        AVLTree myTree = new AVLTree("input.txt");
        myTree.displayInLevelFormat(myTree.getRoot());
        
        
    }
    
    
}
