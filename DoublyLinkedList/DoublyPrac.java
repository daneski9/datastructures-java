package DoublyLinkedList;
class LListDoubly{
    private Node head;
    private Node tail;
    LListDoubly(){}
    
    /*
    ListRemove(list, curNode) {
   sucNode = curNode⇢next
   predNode = curNode⇢prev

   if (sucNode is not null) {
      sucNode⇢prev = predNode       //if pred is null, then sucnode just points to null. it works.
   }

   if (predNode is not null) {      
      predNode⇢next = sucNode
   }

   if (curNode == list⇢head) { // Removed head
      list⇢head = sucNode
   }

   if (curNode == list⇢tail) { // Removed tail
      list⇢tail = predNode
   }
}*/
    
    
    
    
    
    
    
    public String toString(){
        Node cur = head;
        String s = "";
        while(cur!=null){
            s+= cur.getName()+" ";
            cur = cur.getNext();
        }
        return s;
    }
}



public class DoublyPrac {
    LListDoubly mylist = new LListDoubly();
    
    
    
}
