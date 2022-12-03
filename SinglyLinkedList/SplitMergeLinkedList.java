package SinglyLinkedList;

/**
 * Dane Coleman
 * Assignment 01 SplitMerge
 * Description: Practicing LinkedLists. There are three classes: Node, LinkedList, and Driver. 
 *              The objective is to read from a file that contains an even number of names, 
 *              store those names into a linked list, then create three methods in the LinkedList
 *              that Split, Merge, and Traverse the LinkedList.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
class LinkedList {
    private Node head; //first node in the list
    
    void readFile(String fileName) throws FileNotFoundException{ //adds all names from file into LinkedList at the beginning
        Scanner input = new Scanner(new File(fileName));
        while(input.hasNext()){
            Node newName = new Node(input.next(),null);
            newName.setNext(head);
            head=newName;
        }
    }
    
    LinkedList split(){ //splits LinkedList. keeps the first-half, and returns new LinkedList that contains the second-half.
        LinkedList splitList = new LinkedList();
        Node cur = head;
        for(int i = 0; i<(getSize()/2)-1; i++){
           cur = cur.getNext();
        }
        splitList.head = cur.getNext();
        cur.setNext(null);
        return splitList;
    }
    
    void merge(LinkedList list){ //merges then splits list into a single list
        Node curr = head;
        int size = getSize();
        for(int i = 0; i<size-1; i++){
            curr = curr.getNext();
        }
        curr.setNext(list.head);
        list.head = null; //list2 is now merged onto list1 so I am making list2 empty. 
    }
    void addEnd(String name){ //adds node to the end of list
        if(head == null){
        head = new Node(name, null);
        }
        else{
            Node current = head;
            while(current.getNext()!=null){
                current = current.getNext();
            }
            current.setNext(new Node(name, null)); 
        }  
    }
    int getSize(){ //returns the current number of elements in the list
        int count = 0;
        if(head == null){
            return count;
        }
        else{
            for(Node curr=head; curr!=null; curr=curr.getNext()){
                count++;
            }
        }
        return count;    
    }
    public String traverse(){ //displays contents of the list
        if (head == null) {
               return "[]";
        } 
        else{
            String result = "[" + head.getName();
            Node current = head.getNext();
            while (current != null){
                   result += ", " + current.getName();
                   current = current.getNext();
            }
            result += "]";
            return result;
        }
    }
}

public class SplitMergeLinkedList {
    public static void main(String[] args) throws FileNotFoundException{
        LinkedList myList1 = new LinkedList();
        myList1.readFile("input.txt"); 
        System.out.printf("%-19s %s %n", "List 1:", myList1.traverse());
        LinkedList myList2 = myList1.split();                           //myList2 contains second-half of myList1
        System.out.println("List 1 after split: "  +myList1.traverse());
        System.out.printf("%-19s %s %n", "List 2:", myList2.traverse());
        myList1.merge(myList2);
        System.out.println("List 1 after merge: "  +myList1.traverse());
        System.out.println("List 2 after merge: "  +myList2.traverse());
    }
}
