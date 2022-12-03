package DoublyLinkedList;
public class Node { //doubly nodes
    private String name;
    private Node next;
    private Node prev;
    Node(){}
    Node(String name){
        this.name = name;
    }
    Node(String name, Node prev, Node next){
        this.name = name;
        this.prev = prev;
        this.next = next;
    }
    String getName(){
        return name;
    }
    Node getNext(){
        return next;
    }
    Node getPrev(){
        return prev;
    }
    void setNext(Node next){ 
        this.next=next;
    }
    void setPrev(Node prev){
        this.prev = prev;
    }
}
