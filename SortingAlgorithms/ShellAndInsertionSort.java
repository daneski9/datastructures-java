package SortingAlgorithms;
public class ShellAndInsertionSort {
    //Shell sort uses gap values to determine the number of interleaved lists.
    //gap is the distance between elements in an interleaved list
    //ex (94, 23, 51, 1, 5}
    //gap value 2
    //first interleaved list: 94, 51, 5
    //second interleaved list: 23, 1
    //apply insertion sort to both smaller interleaved lists
    //rebuild array
    public static void main(String[] args) {
        int[] data = {29, 17, 3, 94, 46, 8, -4, 12,13, 63, 1, 42};
        insertionSort(data);
        for(int num : data){
            System.out.print(num + ", ");
        }
        
        
        
    }
    public static void ShellSort(int[] arr){
      /*  int k;
       // k = arr.length/2;
        for(int i = 0; i<arr.length; i++){ //n = 12
            int half = arr[k];
            if(arr[i] > half){ //compare i and the half way value. swap correctly if needed;
                arr[k] = arr[i];
                arr[i] = half;
            } */
            
     }
    
    public static void insertionSort(int[] arr){ //O^2
        
        for(int i=1; i<arr.length; i++){
            int j = i;
            while(j>0 && arr[j] < arr[j-1]){//comparing to left numbers.
                int temp = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = temp;
                j--;
            }
        }
    }
        
        
        
 
        
    
    
    
}
    
    

