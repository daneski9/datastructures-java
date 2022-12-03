package SinglyLinkedList;
public class Node { //singly node (no prev pointer)
    private String name; //name stored in this node
    private Node next;  //link to next node in the list
    Node(){ } //default constructor
    Node(String name){
        this.name = name;
    }
    Node(String name, Node next){ //constructs a node with given name and given link
        this.name=name;
        this.next=next;
    }
    public void setName(String name){ //mutator method to change name in node
        this.name=name;
    }
    public void setNext(Node next){ //mutator method to change the pointer in node
        this.next=next;
    }
    public String getName(){ //accessor method to retreive name
        return name;
    }
    public Node getNext(){ //accessor method to retreive the link to next node
        return next;
    }
}

