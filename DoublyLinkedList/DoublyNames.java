
package DoublyLinkedList;

/* Name       : Dane Coleman
                Practicing Doubly LinkedLists. Node, Doubly, and Driver classes.
 * ************ Write a program that reads a list of students(first name) from a file.
 * ************ If names are unsorted, they must be placed in the Linkedlist in sorted order.
 * ************ We're using files, methods for doubly such as: insert, delete, and traverseAscending/Descending.*/
import java.io.*;
import java.util.Scanner;
final class Doubly{
    private Node head;
    private Node tail;
    FileWriter fw; //writing into file
    Doubly(String inputFile, String outputFile) throws FileNotFoundException, IOException{
        fw = new FileWriter(outputFile);
        Scanner scan = new Scanner(new File(inputFile));
        String name;
        while(scan.hasNext()){
            name = scan.next();
            if(name.equals("delete"))
               delete(scan.next());
            else
               insert(name); //you sort as you insert into your linked list.  
        }
    }    
    void insert(String insertName){ //insert name in correct spot for the ascending list
            if(head==null){ //empty list
               Node nn = new Node(insertName, null, null);
               head = nn;
               tail = nn;
            }
            else if(insertName.compareTo(head.getName()) < 0){ //if  smaller than head
               Node nn = new Node(insertName, null, head); //nn.prev = null, nn.next = head;
               head.setPrev(nn);
               head = nn;
            }
            else if(insertName.compareTo(tail.getName()) > 0){ //if larger than tail
               Node nn = new Node(insertName, tail, null); //nn.prev = tail, nn.next = null
               tail.setNext(nn);
              //nn.setPrev(tail); unneeded, set in the constructor. 
               tail = nn;
            }
            else{ //somewhere in the middle
               Node cur = head;
               while(insertName.compareTo(cur.getName()) >= 0){ 
                   cur = cur.getNext();
               }
               Node nn = new Node(insertName, cur.getPrev(), cur);
               cur.getPrev().setNext(nn);
               cur.setPrev(nn);
            } 
    }
    void delete(String deleteName){ //You can assume that everything is in lowercase
        Node curr = head;
        if(head!=null){ //skip delete if null
            while(!curr.getName().equals(deleteName)){ //finding node I want to delete
            curr = curr.getNext();
            }
            if(curr.getPrev()!=null){ 
                curr.getPrev().setNext(curr.getNext()); //a node other than head is being deleted
            }
            else{
                head = curr.getNext(); //the node being deleted is the head
            }
            if(curr.getNext()!=null){ // a node other than tail is being deleted
                curr.getNext().setPrev(curr.getPrev());
            }
            else{ //the node being deleted is the tail
                tail = curr.getPrev();
            }
        }
    }
    String traverseAscending() throws IOException{ //returns list in ascending order, and adds to file
        if (head == null) {
               return "[]";
        } 
        else{
            String result = "[" + head.getName();
            fw.write(head.getName()+"\r\n");
            Node current = head.getNext(); //starting at head
            while (current != null){
                   result += ", " + current.getName();
                   fw.write(current.getName()+"\r\n");
                   current = current.getNext();
            }
            fw.write("==========\r\n");
            result += "]";
            return result;
        }
    }
    String traverseDescending() throws IOException{ //returns list in descending order, and adds to file
        Node curNode = tail; //starting at tail
        if(tail==null){
            return "[]";
        }
        String result = "[";
        while(curNode.getPrev()!=null){
            result += curNode.getName() + ", ";
            fw.write(curNode.getName()+"\r\n");
            curNode = curNode.getPrev();
        }
        fw.write(head.getName()+"\r\n");
        return result+head.getName()+"]";
    }
}

public class DoublyNames { //driver
    public static void main(String[] args) throws FileNotFoundException, IOException{
        Doubly myList = new Doubly("input.txt", "output.txt");
        System.out.println("Doubly Llist Ascending: "+myList.traverseAscending());
        System.out.println("Doubly Llist Descending: "+myList.traverseDescending());
        myList.fw.close(); //closing stream for filewriter
    }
}
