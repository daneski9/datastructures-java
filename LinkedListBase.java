


/*Practicing linked lists in csc 130 (all files are in this one java file)*/
class Nodee {
    private int data;
    private Nodee next;
    Nodee(){ }
    Nodee(int data, Nodee next){
        this.data=data;
        this.next=next;
    }
    
    public void setData(int data){
        this.data=data;
    }
    public void setNext(Nodee next){
        this.next=next;
    }
    public int getData(){
        return data;
    }
    public Nodee getNext(){
        return next;
    }
}

class Llist{ //creating the linked list. we then add nodes to the list using the above Node class. 
    Nodee head;//holds address of front of linked list.
    void addBeg(int value){ //no public void because this is one java file?
        Nodee nn = new Nodee(value,null);
        nn.setNext(head);
        head=nn; //[3,2,1] most recent added node is the front
        
        /*
        
        */  
    }
    void addEnd(int value){
        if(head == null){
            head = new Nodee(value, null);
        }
        else{
            Nodee current = head;
            while(current.getNext()!=null){
                current = current.getNext();
            }
            current.setNext(new Nodee(value, null)); 
        }   
    }
    void delete(int value){
        if(head!=null){
            if(head.getData()==value)
                head=head.getNext();
            else{
                Nodee curr = head;
                while(curr.getNext().getData()!=value && curr.getNext()!=null){ //!=null is for if we dont find it
                    curr = curr.getNext();
                }
                if(curr.getNext()!=null){
                    curr.setNext(curr.getNext().getNext());
                }
            }
        }
        
        
    }
    
    public String toString(){
        if(head == null){
            return "[]";
        }
        else{
            String result = "["+ head.getData();
            Nodee current = head.getNext();
            while(current!=null){
                result+=","+current.getData();
                current = current.getNext();
            }
            return result + "]";
        }
    } 
    
    
}

public class LinkedListBase{
    public static void main(String[] args) {
        Llist listOne = new Llist();
        listOne.addBeg(1);
        listOne.addBeg(2);
        listOne.addBeg(3);
        listOne.delete(2);
        System.out.println(listOne.toString());
        Llist listTwo = new Llist();
        listTwo.addBeg(50);
        listTwo.addBeg(51);
        listTwo.addBeg(52);
        
        
    }
    
    
    
    
}