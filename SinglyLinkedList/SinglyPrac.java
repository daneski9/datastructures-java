
package SinglyLinkedList;
class Singly{
    Node head;
    Node tail;
    Singly(){}
    
    void append(String name){
        if(head == null){
            Node nn = new Node(name);
            head = nn;
            tail = nn;
        }
        else{
            Node nn = new Node(name);
            tail.setNext(nn);
            tail = nn;
        }
    }
    void insertAfter(String name, String nn){
        if(head == null){
            Node newN = new Node(nn);
            head = newN;
            tail = newN;
        }
        if(name.equals(tail.getName())){
           Node newN = new Node(nn);
           tail.setNext(newN);
           tail = newN;
        }
        else{
            Node cur = head;
            while(!cur.getName().equals(name)){
                cur = cur.getNext();
            }
            Node newN = new Node(nn);
            newN.setNext(cur.getNext());
            cur.setNext(newN);
            
        }
    }
    void removeAfter(String name, String nn){
        
        
        
        
        
        
        
    }
    
    String toStringg(){
        Node cur = head;
        String s = "";
        while(cur!=null){
            s+=cur.getName()+" ";
            cur = cur.getNext();
        }
        return s;
    }
    
    
    
}



public class SinglyPrac {
    public static void main(String[] args) {
        Singly myList = new Singly();
        myList.append("Dane");
        myList.append("Jake");
        myList.append("Bert");
        myList.insertAfter("Jake", "Gabby");
        myList.insertAfter("Bert", "Erik");
        System.out.println(myList.toStringg());
        
        
    }
            
            
}
