package SortingAlgorithms;
/* Name       : Dane Coleman
 * Assignment : SortSearch
 * Due Date   : 10/26/21
 * Description: Sorting a two dimensional Array using Bubble sort, Selection sort, Shell sort, and Insertion sort. Also practicing Binary search. */
import java.util.Arrays;
import java.util.Scanner;
public class SortSearchAssignment {
    public static void main(String[] args) {
        int[][] nums = {{5,3,2,16},
                        {9,8,10,17},
                        {4,7,11,18},
                        {2,5,9,12},
                        {7,9,4,10}};
        Scanner scan = new Scanner(System.in);
        System.out.println("Original Array           :"+Arrays.deepToString(nums) +"\n");
        System.out.println("Bubble sort    1st column:"+Arrays.deepToString(bubbleSort(nums)) + " ascending");
        System.out.println("Selection sort 2nd column:"+Arrays.deepToString(selectionSort(nums))+" descending");
        System.out.println("Shell sort     3rd column:"+Arrays.deepToString(shellSort(nums))+" ascending");
        System.out.println("Insertion sort 5th row   :"+Arrays.deepToString(insertionSort(nums))+" ascending");
        System.out.println("What number are you searching for in the 5th row?: ");
        int key = scan.nextInt();
        System.out.println(BinarySearch(nums, key, 0, nums[4].length-1));
        scan.close();
        
   
    }
    public static int[][] bubbleSort(int[][] nums){ //sort first column in ascending order
        
        boolean Sorted = false;
        for(int i = 1; i<nums.length-1 && !Sorted; i++){
            Sorted = true;
            for(int j = 0; j<nums.length-i; j++){
                if(nums[j][0] > nums[j+1][0]){
                    int temp = nums[j][0];
                    nums[j][0] = nums[j+1][0];
                    nums[j+1][0] = temp;
                    Sorted = false;
                }
            }
        }
        return nums;
    }
    public static int[][] selectionSort(int[][] nums){ //sort second column in descending order.
        resetArray(nums); //copy contents of the original array into nums
        for(int i = 0; i < nums.length-1; i++){
            int maxIndex = i;
            for(int j = i+1; j < nums.length; j++){  //{3,5,1,7,9}
                if(nums[j][1] > nums[maxIndex][1]){
                    maxIndex = j;
                }
            }
            int temp = nums[i][1];
            nums[i][1] = nums[maxIndex][1];
            nums[maxIndex][1] = temp;
        }
        return nums;
    }
    public static int[][] shellSort(int[][] nums){ //sort 3rd column in ascending order
        resetArray(nums);
        for(int gap = nums.length/2; gap>=1; gap=gap/2){ 
                for(int i=gap; i<nums.length; i++){ 
                    int j = i;
                    while(j >= gap && nums[j-gap][2]>nums[j][2]){
                        int temp = nums[j-gap][2];
                        nums[j-gap][2] = nums[j][2];
                        nums[j][2] = temp;
                        
                        j = j-gap;
                    }
                }
        }
        return nums;
    }
    public static int[][] insertionSort(int[][] nums){ //sort 5th row in ascending order
        resetArray(nums);
        for(int i = 1; i<nums.length-1; i++){
            int j = i;
            while(j > 0 && nums[4][j]<nums[4][j-1]){
                int temp = nums[4][j];
                nums[4][j] = nums[4][j-1];
                nums[4][j-1] = temp;
                j--;
            }        
        }
        return nums;
    }
    public static String BinarySearch(int[][] arr, int key, int l, int h){ //using recursion. search for Key in 5th row (already sorted from previous insertion sort)
        int mid = l + (h-l)/2;
        if(h < l){
            return "Not Found";
        }
        else if(arr[4][mid] == key){ //base case
            String s = "Located at: [4]"+"["+mid+"]\n";
            s+= "Its column:\n";
            for (int[] num : arr) {
                s += num[mid] + "\n";
            }
            return s;    
        }
        else if(arr[4][mid] < key){
            return BinarySearch(arr, key, mid+1, h);
        }
        else
            return BinarySearch(arr, key, l, mid-1);
    }
    
    public static void resetArray(int[][] nums){ //resets working array to its original array.
        int[][] originalArray = {{5,3,2,16},
                                 {9,8,10,17},
                                 {4,7,11,18},
                                 {2,5,9,12},
                                 {7,9,4,10}};
        for(int i = 0; i < originalArray.length; i++){
            nums[i] = Arrays.copyOf(originalArray[i], originalArray[i].length); 
        }
    }

}