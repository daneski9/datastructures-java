/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StacksAndQueues;
/**************
 * Dane Coleman
 * Assignment 02 Postfix Evaluate
 * Description:  Grab an expression from the user and evaluate it. The expression can contain +-/*( ).
 **************  The expression will not contain any spaces. All the numbers will be single digit (between 0 and 9).
 **************  We are practicing stacks, queues, and LinkedLists.
 **************/
import java.util.Scanner;
class Node<T>{
    private T data; 
    private Node next;  //link to next node in the list
    Node(){ } 
    Node(T data){ //constructs a node with given name and given link
        this.data=data;
    }
    void setNext(Node next){ 
        this.next=next;
    }
    Node getNext(){
        return next;
    }
    T getData(){
      return data;
    }
    
}
class Stack<T>{ //stack class for pushing and popping the operators
    private Node<T> head;
    private Node<T> tail;
    Stack(){
        head=null;
    }
    Node getHead(){
        return head;
    }
    
    void push(T c){
        Node<T> nn = new Node(c);
        ListPrepend(nn);
    }
    void ListPrepend(Node nn){ //adding to the beginning (head)
        if (head == null){
            head = nn;
            tail = nn;
        }
        else{
            nn.setNext(head);
            head = nn;
        } 
    }
    T pop(){ //removing head and returning it's value. the new head is head.next
        T data = head.getData();
        ListRemoveAfter(null);
        return data;
    }
    void ListRemoveAfter(Node nn){
        Node sucNode;
        if(nn == null && head!=null){
            sucNode = head.getNext();
            head = sucNode;
        
            if(sucNode==null) //removed last item
               tail = null;
        }
        else if(nn.getNext()!=null){
            sucNode = nn.getNext().getNext();
            nn.setNext(sucNode);
            
            if(sucNode!=null){ //removed tail
              tail = nn;
            }
        }
    }
    int size(){ //returning size of stack
        int size = 1;
        if(head==null){
            return 0;
        }
        else{
            Node cur = head;
            while(cur.getNext()!=null){
                size+=1;
                cur = cur.getNext();
            } 
        }
        return size;
    }
    boolean isEmpty(){
        return head==null;
    }
}
class Queue<T>{   
    private Node<T> head;
    private Node<T> tail;
    Queue(){ }
    Node getTail(){
        return tail;
    } 
    void enqueue(T c){ //display value; add value to end of queue
        Node<T> nn = new Node(c);
        nn.setNext(null);
        ListAppend(nn);  
    }
    void ListAppend(Node nn){ //adds to end of queue
        if(head==null){
           head = nn;
           tail = nn;
        }
        else{
            tail.setNext(nn);
            tail = nn;
        }
    }
    String ToString(){
          if (head == null) {
               return "";
        } 
        else{
            String result = ""+head.getData();
            Node current = head.getNext();
            while (current != null){
                   result += current.getData();
                   current = current.getNext();
            }
            return result;
        }
    }

}
class Postfix{
    // For the conversion from infix to postfix you need one stack and one queue
    // The stack will be used to store the operators
    // The queue will be used to store what would usually be displayed on the screen which is the postfix expression
    // Both the stack and the queue will be storing chars. 
    // example: passing in: 9 + 4*(3-2)
    static String convertToPostfix(String str){
        Queue display = new Queue();
        Stack<Character> ops = new Stack();
        for(int i = 0; i<str.length(); i++){
            char nextValue = str.charAt(i);
            if(Character.isDigit(nextValue)){
                display.enqueue(nextValue);
            }
            else if(ops.size()==0){
                ops.push(nextValue);
            }
            else{
                if(checkP(nextValue)>checkP((char) ops.getHead().getData()) || nextValue=='('){
                    ops.push(nextValue);
                }
                else if(checkP(nextValue) <= checkP((char) ops.getHead().getData()) && nextValue !=')'){
                    while(ops.size()>0 && checkP(nextValue) <= checkP((char) ops.getHead().getData()) ){ //while loop in case there are multiple operators in the stack larger than what I'm trying to push 
                        display.enqueue(ops.pop());
                    }
                    ops.push(nextValue);
                } 
            } 
                if(nextValue ==')'){ //pop values in stack until (
                    char v = ops.pop();
                    while(v!='('){
                        display.enqueue(v);
                        v = ops.pop();
                    }
                }
                if ((i+1)==str.length()){ //if end of stack, pop remaining.
                    while(!ops.isEmpty()){
                    display.enqueue(ops.pop());
                    }
                } 
            } 
         return display.ToString();
    }
    static int checkP(char C){//check precedence.It takes a character as an argument which will be an operator. The operators will be assigned a number based on their precedence.
        int order = 0;
        switch(C){
            case '=' : order = 1; break;
            case '(' : order = 2; break;
            case '+' : order = 3; break;
            case '-' : order = 3; break;
            case '*' : order = 4; break;
            case '/' : order = 4; break;
            case '^' : order = 5; break;
        }
        return order;
    }
    //For the evaluation, another stack will be used. This stack will be storing integers. 
    //As the evaluation is being done, it is possible for the numbers to go beyond a single 
    //digit and so char datatype would no longer make any sense
    static int evaluatePostfix(String strPostFix){
        Stack<Integer> newStack = new Stack();
        for(int i = 0; i<strPostFix.length(); i++){   
            char nextValue = strPostFix.charAt(i);
            if(Character.isDigit(nextValue)){
                newStack.push(Integer.parseInt(Character.toString(nextValue)));
            }
            else{
                char op = nextValue;
                int first = newStack.pop(); //right
                int second = newStack.pop(); //left
                switch(op){
                    case '+': newStack.push(second + first);break;
                    case '-': newStack.push(second - first);break; 
                    case '*': newStack.push(second * first);break;
                    case '/': newStack.push(second / first);break;
                    case '^': newStack.push(second ^ first);break;
                } 
            }
        }
       return newStack.pop();
    }   
        
}

public class PostFixEvalutation {
    public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter an expression: ");
            String expres = scan.nextLine();
            String str = Postfix.convertToPostfix(expres);   
            System.out.println("Postfix expression: "+str);
            System.out.println("Answer: "+Postfix.evaluatePostfix(str));
            
        }
}

