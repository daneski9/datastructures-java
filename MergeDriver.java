/***************************************************************************************
* Name       : Dane Coleman
* Assignment : Merge sort
* Due Date   : 11/3/21
* Description: Implement the merge sort algorithm using a linked list instead of arrays.
* ************ I will be using three classes: Node, linkedlist, and Driver.
****************************************************************************************/
import java.util.Arrays;
class Nodeee{
    private Nodeee next;
    private int data;
    Nodeee(int data){
        this.data = data;
    }
    public void setNext(Nodeee next){
        this.next = next;
    }
    public int getData(){
        return data;
    }
    public Nodeee getNext(){
        return next;
    }
   
}
class linkedlist{
    private Nodeee head;
    private Nodeee tail;
    linkedlist(int[] arr){ //populate linkedlist in constructor
        if(arr.length==0){
            head = null;
            tail = null;
        }
        else{
        for(int i=0; i<arr.length; i++){
            if(head == null){
                Nodeee nn = new Nodeee(arr[i]);
                head = nn;
                tail = nn;
            }
            else{
                Nodeee nn = new Nodeee(arr[i]);
                tail.setNext(nn);
                tail = nn;
            }
        }
        }
    }
    linkedlist(){}
    public Nodeee getHead(){
        return head;
    }
    public void setHead(Nodeee head){
        this.head = head;
    }
    public Nodeee getTail(){
        return tail;
    }
    public void setTail(Nodeee tail){
        this.tail = tail;
    }
    public int getData(int num){ //gets data at the specified index of the linkedlist. ex: {3,5,6,1,5} if num = 2, return 6. Using this for comparisons in the Merge method
        Nodeee cur = head; 
        if(num==0){
            return head.getData();
        }
        else{
            int count = 0;
            while(count < num){
                cur = cur.getNext();
                count++;
            }
        }
        return cur.getData();
    }
    public int size(){ //linkedlist:{4,3,5,1} would return 4.
        int count = 0;
        if(head==null){
            return count;
        }
        else if(head.getNext()==null){
            return 1;
        }
        else{
            Nodeee cur = head;
            while(cur!=null){
                count++;
                cur = cur.getNext();
            }
        }
        return count;
    }
    public linkedlist split(int length){ //splits the original list, and then returns second half of the list. also reassigns the head/tail nodes of both lists
        Nodeee cur = head;
        linkedlist secondList = new linkedlist(); //second list
        if(length==2){ //{3,2}
            secondList.setHead(cur.getNext());; //{2}
            secondList.setTail(cur.getNext());  //{2}
            cur.setNext(null); //{3|null}
            tail = head; //{3}
        }
        else{
            int count = 0;
            if(length%2==0)
                count = 1;
            while(count<(length/2)-1){
                cur = cur.getNext();
                count++;
            }
            secondList.setHead(cur.getNext().getNext()); //set head of second list to mid+1 -> [3,5,1,4] --> [1,4]. 1 = head
            secondList.setTail(getTail());               //set tail of second list to tail of first list  -> [3,5,1,4] --> [1,4]. 4 = tail   
            tail = cur.getNext();                        //tail of first list is mid -> [3,5,1,4]  5 = tail
            cur.getNext().setNext(null);                 //splits list [3,5] [1,4]
        }
        return secondList;
    }
    public void add(int data){ //adding Node to end of linkedlist
        Nodeee nn = new Nodeee(data);
        if(head == null){
            head = nn;
            tail = nn;
        }
        else{
            tail.setNext(nn);
            tail = nn;
        }
    }
    public String toString(){
        if(head == null){
            return "[]";
        }
        Nodeee cur = head;
        String s = "[";
        if(cur.getNext()==null){
            s+=cur.getData()+"]";
        }
        else{
            while(cur.getNext()!=null){
                s+=cur.getData() + ", ";
                cur=cur.getNext();
            }
            s+=cur.getData()+"]";
        }
        return s;
    }  
}

public class MergeDriver {
    public static void main(String[] args) {
        int[] nums = {64,1,5,429,12,43,2,9,1,6};    // populate your list from an explicitly defined array in your program.
        System.out.println("Four examples:\n");
        System.out.println("Unsorted list         : "+Arrays.toString(nums)+" (array)");
        linkedlist myList = new linkedlist(nums);
        linkedlist sortedList = mergeSort(myList);
        System.out.println("Sorted with Merge sort: "+sortedList.toString() +" (now a linked list)");
        int[] n = {6,3}; //example 2
        System.out.println("\nUnsorted list         : "+Arrays.toString(n)+" (array)");
        linkedlist my = new linkedlist(n);
        linkedlist sorted = mergeSort(my);
        System.out.println("Sorted with Merge sort: "+sorted.toString() +" (now a linked list)");
        int[] s = {5}; //example 3
        System.out.println("\nUnsorted list         : "+Arrays.toString(s)+" (array)");
        linkedlist m = new linkedlist(s);
        linkedlist sort = mergeSort(m);
        System.out.println("Sorted with Merge sort: "+sort.toString() +" (now a linked list)");
        int[] a = {}; //example 4
        System.out.println("\nUnsorted list         : "+Arrays.toString(a)+" (array)");
        linkedlist x = new linkedlist(a);
        linkedlist sor = mergeSort(x);
        System.out.println("Sorted with Merge sort: "+sor.toString() +" (now a linked list)");
        
    }
    public static linkedlist mergeSort(linkedlist nums){
        linkedlist c;
        if(nums.getHead() == null) //handles trying to sort an empty list
            return nums;
        else if(nums.getHead().getNext() == null){ //base case
            return nums;
        }
        else{
        int length = nums.size();
        linkedlist a = nums;
        linkedlist b = a.split(length);
        a = mergeSort(a); 
        b = mergeSort(b);
        c = merge(a, b);
        }
        return c;
    } 
    public static linkedlist merge(linkedlist one, linkedlist two){
       linkedlist sorted = new linkedlist();
       int oneSize = one.size();                  //ex: [3,4,5,6]  [1,7,9] oneSize = 4, twoSize = 3
       int twoSize = two.size();
       int i = 0,j = 0;
       
       while(i < oneSize && j < twoSize){         //comparing both lists with each other, and incrementing after adding. 
            if(one.getData(i) <= two.getData(j)){ // [3,4,5,6] , [1,7,9] (both already sorted from previous recursions; so we must compare and merge the two by adding to a new linkedlist)
               sorted.add(one.getData(i));
               i++; 
            }
            else{                           
               sorted.add(two.getData(j)); 
               j++;                        
            }
        }
        while(i<oneSize){ //add remaining numbers on left 
               sorted.add(one.getData(i)); 
               i++;
        }
        while(j<twoSize){ //add remaining numbers on right
               sorted.add(two.getData(j)); 
               j++;
        }
        return sorted;
    }
}
