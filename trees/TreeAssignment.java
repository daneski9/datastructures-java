package trees;
/***************************************************************************************
* Description: Practicing Binary Search trees. Inserting, and Deleting. if number after delete isn't in file, insert it. otherwise, delete it.
****************************************************************************************/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
class Node{
    private Node left;
    private Node right;
    private int data;
   
    Node(int data){
        this.data = data;
    }
    public void setLeft(Node left){
        this.left = left;
    }
    public void setRight(Node right){
        this.right = right;
    }
    public void setRight(Node right, int data){ //overload for Recursive case
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
}
final class BinarySearchTree{
    private Node root;
    private String printInOrder = "";
    private String printPostOrder = "";
    private String printPreOrder = "";
    private FileWriter fw;
    private String levels = "";

    BinarySearchTree(String inputFile) throws FileNotFoundException{ //populate in constructor
        Scanner scan = new Scanner(new File(inputFile));
        String data;
        String initRoot = scan.next();
        if(initRoot.equals("delete")){ //first declaring root as first num in file
            root = new Node(Integer.parseInt(scan.next())); //if delete is the first line in the txt. ex; skips the word delete and sets root to 5 -> "delete 5"
        }
        else{
            root = new Node(Integer.parseInt(initRoot)); 
        }
        while(scan.hasNext()){
            data = scan.next();
            if(!data.equals("delete")){
               insertNumRecur(root, Integer.parseInt(data));  //recursive insert 
            }
            else{
                int dataDel   = Integer.parseInt(scan.next());  //"delete 5" - skips delete and takes in 5
                boolean found = false;
                found = treeSearch(root, dataDel);            //search Node. if exists ->delete it. else ->insert it
                if(found){
                    delete(root, dataDel); 
                }
                else{
                    insertNumRecur(root, dataDel);            //recursive insert 
                } 
            } 
        }
        scan.close();
    }
    
    public Node getRoot(){
        return root;
    }
    public void insertNum(int num){ //nonrecursive
        Node leaf = root;
        boolean done = false;
        if(root == null){
            Node nn = new Node(num);
            root = nn;
        }
        else{
            while(!done){
                if(num < leaf.getData()){
                    if(leaf.getLeft()!=null){
                        leaf = leaf.getLeft();
                    }
                    else{
                        leaf.setLeft(new Node(num));
                        done = true;
                    }
                }
                else{
                    if(leaf.getRight()!=null){
                        leaf = leaf.getRight();
                    }
                    else{
                        leaf.setRight(new Node(num));
                        done = true;
                    }
                }
            }
        }
    }
    public Node insertNumRecur(Node leaf, int num){ //recursive insert
        if(leaf == null){
            leaf = new Node(num);
        }
        else if(num > leaf.getData()){
            leaf.setRight(insertNumRecur(leaf.getRight(), num)); //leaf.right = 
        }
        else{
            leaf.setLeft(insertNumRecur(leaf.getLeft(), num));   //left.left = 
        }
        return leaf;
        
    }
    public boolean treeSearch(Node leaf, int key){
        if(leaf!=null){
            if(leaf.getData() == key){
                return true;
            }
            else if(key  < leaf.getData()){
                return treeSearch(leaf.getLeft(), key);
                }
                else{
                return treeSearch(leaf.getRight(), key);
                }
            }
        return false;
        }
    public Node delete(Node leaf, int key){ //account for no children, one child, and two children
        if(leaf == null){ //if not found
           return null;
        }
        //search for node
        if(key < leaf.getData()){ //move to left
            leaf.setLeft(delete(leaf.getLeft(), key)); 
        }                                              
        else if(key > leaf.getData()){ //move to right             
            leaf.setRight(delete(leaf.getRight(), key));        
        }
        //if found (num == leaf.getData())
        else{
        //one or none 
            if(leaf.getLeft() == null){
                return leaf.getRight();
            }
            else if(leaf.getRight() == null){
                return leaf.getLeft();
            }
        //two children
            leaf.setData(min(leaf.getRight())); // go right once, then all the way left
            leaf.setRight(delete(leaf.getRight(), leaf.getData())); //brand new method to delete the old position and set right to the in-order successor 
        }
       return leaf; 
    }
        
    public int min(Node leaf){
        int min = leaf.getData();
        while(leaf.getLeft()!=null){ 
            min = leaf.getLeft().getData();
            leaf = leaf.getLeft();
        }
        return min;
    }
    
    public String BSTprintInorder(Node leaf){ //smallest to largest
        if(leaf == null){
            return "";
        }
        else{
            BSTprintInorder(leaf.getLeft());        //recur on left
            printInOrder += leaf.getData() + " ";   //add data
            BSTprintInorder(leaf.getRight());       //recur on right
        }
        return printInOrder;
    }
    public String BSTprintPostOrder(Node leaf){
        if(leaf == null){
            return "";
        }
        else{
            BSTprintPostOrder(leaf.getLeft());      //recur on left
            BSTprintPostOrder(leaf.getRight());     //recur on right
            printPostOrder += leaf.getData() + " "; //add data
        }
        return printPostOrder;
    }
    public String BSTprintPreOrder(Node leaf){
        if(leaf == null){
            return "";
        }
        else{
            printPreOrder+=leaf.getData() + " ";         //add data
            BSTprintPreOrder(leaf.getLeft());       //recur on left
            BSTprintPreOrder(leaf.getRight());      //recur on right
        }
        return printPreOrder;
    }
    public void displayTreeLevels(Node root) throws IOException{//breadth search format
        fw = new FileWriter("output.txt");
        int rootHeight = height(root);
        for(int i = 1; i<=rootHeight; i++){
            printLevel(root, i);
            fw.write(levels+"\n");
            levels="";
            System.out.println();
        }
        fw.close();
    }
    public void printLevel(Node leaf, int level) throws IOException{
        if(leaf == null){
            return;
        }
        if(level == 1){
            System.out.print(leaf.getData() + " "); //displaying in driver
            levels+=leaf.getData() + " "; //for writing in file
            
        }
        else if(level > 1){
            printLevel(leaf.getLeft(), level-1);
            printLevel(leaf.getRight(), level-1);
        }
    }
    public int height(Node leaf){
        if(leaf == null){
            return 0;
        }
        else{
            int leftDepth = height(leaf.getLeft());
            int rightDepth = height(leaf.getRight());
            
            if(leftDepth > rightDepth){
                return (leftDepth + 1);
            }
            else{
                return (rightDepth + 1);
            }
        }
        
        
        
    }
            
}

public class TreeAssignment { //driver
    public static void main(String[] args) throws FileNotFoundException, IOException {
        BinarySearchTree myBST = new BinarySearchTree("input.txt");
        System.out.println("Example: (tests no child, 1 child, and 2 child deletion)");
        fileInfo();
        System.out.println("In-order   : "+myBST.BSTprintInorder(myBST.getRoot()));
        System.out.println("Post-order : "+myBST.BSTprintPostOrder(myBST.getRoot()));
        System.out.println("Pre-order  : "+myBST.BSTprintPreOrder(myBST.getRoot()));
        System.out.println("Breadth search format: ");
        myBST.displayTreeLevels(myBST.getRoot());
        
    }
    public static void fileInfo() throws FileNotFoundException{
        Scanner scan = new Scanner(new File("input.txt"));
        System.out.print("File info  : ");
        while(scan.hasNext()){
            System.out.print(scan.nextLine() + " ");
        }
        System.out.println();
    }
}
