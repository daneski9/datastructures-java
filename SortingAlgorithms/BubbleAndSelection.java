package SortingAlgorithms;
import java.util.Arrays;
public class BubbleAndSelection {
    public static void main(String[] args) {
        int[] data = {29, 17, 3, 94, 46, 8, -4, 12};
        int[] data2 = {5,22,7,2,9,8,1};
        BubbleSort(data);
        
        
}
    public static void SelectionSort(int[] data){
        int minIndex, numToSwap;
        for(int i = 0; i<data.length-1; i++){
            minIndex = i;
            numToSwap = data[i]; //keeping track of this initial num so we can swap it to list[minIndex]
            for (int j = i+1; j<data.length; j++){
                if(data[j] < data[minIndex]){ 
                    minIndex = j; 
                }
            }
                data[i] = data[minIndex];
                data[minIndex] = numToSwap; //swapping list[minIndex] with this greater number 
            
            System.out.println(Arrays.toString(data));
        }   
    }
    public static void BubbleSort(int[] data){ 
       int temp;
        for(int i = 1; i<data.length - 1; i++){                 //{3,42,62,2,6}
            for (int j = 0; j<data.length - i - 1; j++){
                if(data[j] > data[j+1]){ //change comparison operator for descending
                    temp = data[j];
                    data[j] = data[j+1];
                    data[j+1] = temp;
                }
            }               
        }
        
        System.out.println(Arrays.toString(data));
    }
   public static void bubbleSort(int[] data){
    boolean swap=true;
    while (swap){
      swap=false;
       for (int j=0;j<data.length-1;j++){
        if (data[j]>data[j+1]){
          swap=true;
          //swap the elements
          int bigBubble =data[j];
          data[j]=data[j+1];
          data[j+1]=bigBubble;
          System.out.println(Arrays.toString(data));
        }
      }
    }
  }
    public static void BinarySearchMethod(){
        int[] array1 = {-5, -1, 0, 3, 9, 14, 19, 24, 33, 41, 56, 62, 70, 88, 99};
        int key = -1;
        Arrays.sort(array1);
        recursSearch(array1,0,array1.length-1,key);
    }
    public static void recursSearch(int[] arr, int L, int h, int k){
        int mid = (L+h)/2;
        if(arr[mid]==k){
            System.out.println(mid);
        }
        else if(k < arr[mid]){
           recursSearch(arr, L, mid-1,k );
        }
        else
           recursSearch(arr, mid+1, h, k);
        
    }
    
    
}

