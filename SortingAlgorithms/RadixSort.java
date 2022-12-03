package SortingAlgorithms;
/* Name       : Dane Coleman
 * Assignment : Radix sort
 * Due Date   : 10/29/21
 * Description: Sorting an array of numbers using Radix Sort. Has four classes: Node, Queue, Radix, Driver.
***********************************************************************************************************/
import java.util.Arrays;
class Node{
    private Node next;
    private int data;
    Node(int data){
    this.data = data;
    }
   
    public Node getNext(){
        return next;
    }
    public void setNext(Node next){
        this.next = next;
    }
    public int getData(){
        return data;
    }
}
class Queue{ 
    private Node head;
    private Node tail;
    Queue(){}
    public void enqueue(Node nn){ //storing num in the bucket
        if(head==null){
            head = nn;
            tail = nn;
        }
        else{
            tail.setNext(nn);
            tail = nn;
        }
    }
    public int dequeue(){ //dequeuing from the bucket
        int headData = -1;
        if(head==null){
            
        }
        else{
          headData = head.getData();
          head = head.getNext(); 
        }
        return headData;
    }
    public int size(){ //used for iterating over the buckets when placing back onto the array
        int count = 0;
        Node cur = head;
        if(cur==null){
            return count;
        }
        else{
            while(cur!=null){
                count++;
                cur = cur.getNext();
            }
        }
        return count;
    }
}
class Radix{
    public static int[] radixSortAscend(int nums[]){
        Queue[] bucket = new Queue[10]; //an array of queue's used to temporary store the digits (0-9)
        for(int m =0; m<10; m++){
          bucket[m] = new Queue(); //initializing
        }
        
        int pow10 = 1; //1, 10, 100, ...
        for(int i = 0; i<maxDigitLenInArr(nums); i++){ 
            for(int j = 0; j < nums.length; j++){ //assigning each LSD to correct buckets
                int bucketIndex = Math.abs(nums[j]/pow10) % 10;
                Node nn = new Node(nums[j]);
                bucket[bucketIndex].enqueue(nn); //add to bucket
            }
            //dequeueing onto nums array.
            int newIndex = 0;
            for(int k = 0; k<10; k++){//going through each bucket (0-9) and dequeuing everything from it onto the nums array
                if(bucket[k].size()==0){ }
                    else{
                        int buckSize = bucket[k].size(); //dequeuing, so I have to declare this so it doesn't change.
                        for(int x = 0; x<buckSize; x++){
                            nums[newIndex] = bucket[k].dequeue();
                            newIndex++;
                        }
                    }
            }
            pow10 = pow10*10; 
        }
        return nums;
    }
    
    public static int[] radixSortDescend(int nums[]){
        Queue[] bucket = new Queue[10]; 
        for(int m =0; m<10; m++){
          bucket[m] = new Queue(); 
        }
        
        //putting into buckets
        int pow10 = 1; 
        for(int i = 0; i<maxDigitLenInArr(nums); i++){ 
            for(int j = 0; j < nums.length; j++){ 
                int bucketIndex = Math.abs(nums[j]/pow10) % 10;
                Node nn = new Node(nums[j]);
                bucket[bucketIndex].enqueue(nn); 
            }
        //dequeuing buckets back onto nums array 
            int newIndex = 0;
            for(int k = 9; k >=0; k--){ //0-9 buckets
                if(bucket[k].size()==0){ }
                    else{
                        int buckSize = bucket[k].size(); 
                        for(int x = 0; x<buckSize; x++){ //loops over each buckets specific size in the buckets array. 
                            nums[newIndex] = bucket[k].dequeue();
                            newIndex++;
                        }
                    }
            }
            pow10 = pow10*10; 
        }
        return nums;
        
    }
    
    public static int maxDigitLenInArr(int[] nums){ //returns max length, in number of digits, out of all elements in the array
        int maxDigit = 0;
        for(int i=0; i<nums.length; i++){
            int digitCount = getLengthOfDigit(nums[i]);
                if(digitCount > maxDigit){
                    maxDigit = digitCount;
                }   
        }
        return maxDigit;
    }
    public static int getLengthOfDigit(int value){ //returns length, in number of digits, of value
        if (value == 0){
            return 1;
        }
        int digits = 0;
        while(value!=0){
            digits+=1;
            value = value/10;
        }
        return digits;
    }
    public static int[] resetArr(int[] arr){
        int[] nums = {1,7,6,786,49,49,2,4,123,3,11,1};
        return arr = nums.clone();
    }
}
public class RadixSort {
    public static void main(String[] args) {
        int[] nums = {1,7,6,786,49,49,2,4,123,3,11,1};
        System.out.println("Array                        :"+Arrays.toString(nums));
        Radix.radixSortAscend(nums);
        System.out.println("After Radix sort (ascending) :"+Arrays.toString(nums));
        System.out.println("\nArray reset                  :"+Arrays.toString(Radix.resetArr(nums)));
        Radix.radixSortDescend(nums);
        System.out.println("After Radix sort (descending):"+Arrays.toString(nums));
    } 
}

