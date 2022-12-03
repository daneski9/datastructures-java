//singly stacks and queues prac
package StacksAndQueues;
class N{  
    private int age; 
    private N next; 
    N(){} 
    N(int age){ 
        this.age = age; 
    } 
    N(int age, N next){ 
        this.age = age; 
        this.next = next; 
    } 
    int getAge(){ 
        return age; 
    } 
    N getNext(){ 
        return next; 
    } 
    void setNext(N next){  
        this.next=next; 
    } 
} 

class daneStack{
    N head;
    daneStack(){}
    void push(int age){
        if(head == null){
            N nn = new N(age);
            head = nn;
        }
        else{
            N nn = new N(age);
            nn.setNext(head);
            head = nn;
        }
    }
    int pop(){
        int popNum = 0;
        if(head!=null){
            popNum = head.getAge();
            head = head.getNext();
        }
        return popNum;
    }
    public String toString(){
        N cur = head;
        String s = "";
        while(cur!=null){
            s+= cur.getAge()+" ";
            cur = cur.getNext();
        }
        
        
        return s;
        
    }
}
class daneQueue{
    N head;
    N tail;
    daneQueue(){};
    void enqueue(int nn){
        if(head == null){
            N newq = new N(nn);
            head = newq;
            tail = newq;
        }
        else{
            N newq = new N(nn);
            tail.setNext(newq);
            tail = newq;
        } 
    }
    int dequeue(){
        int valueHead = 0;
        if(head!=null){
            valueHead = head.getAge();
            head = head.getNext();
        }
        return valueHead;
    }
    public String toString(){
        N cur = head;
        String s = "";
        while(cur!=null){
            s+= cur.getAge()+" ";
            cur = cur.getNext();
        }
        return s;
    }
}

public class StackAndQBasic {
    public static void main(String[] args) {
    daneStack myStack = new daneStack();
    myStack.push(6);
    myStack.push(13);
    myStack.push(16);
    System.out.println(myStack.pop());
    System.out.println(myStack);
    
    
    daneQueue myQueue = new daneQueue();
    myQueue.enqueue(6);
    myQueue.enqueue(42);
    myQueue.enqueue(2);
    myQueue.dequeue();
    System.out.println(myQueue);
    
    }
}
