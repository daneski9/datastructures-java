/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SortingAlgorithms;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        
        //Merge sort is a sorting algorithm that divides a list into two halves, recursively sorts each half, 
        //and then merges the sorted halves to produce a sorted list. The recursive partitioning continues 
        //until a list of 1 element is reached, as a list of 1 element is already sorted.
        int[] list = {3,6,19,4,8,11};
        MergeSort(list, 0, list.length-1);
        System.out.println(Arrays.toString(list));
    }
    /*
    The index variable i is the index of first element in the list
    the index variable k is the index of the last element. 
    The index variable j is used to divide the list into two halves.
    */
    public static void Merge(int[] arr, int low, int mid, int high){ //merging array back
        int mergedSize = high - low + 1;  
        int mergePos = 0;
        int leftPos = 0;
        int rightPos = 0;
        int[] mergedList = new int[mergedSize];
        
        leftPos = low;
        rightPos = mid+1;
        
        while(leftPos <= mid && rightPos <= high){ 
            if(arr[leftPos] <= arr[rightPos]){
                mergedList[mergePos] = arr[leftPos];
                leftPos++;
            }
            else{
                mergedList[mergePos] = arr[rightPos];
                rightPos++;
            }
            mergePos++;;
        }
        while(leftPos<=mid){  // If left partition not empty, add remaining elements
            mergedList[mergePos] = arr[leftPos];
            leftPos++;
            mergePos++; 
        }
        while(rightPos<=high){  // If right partition not empty, add remaining elements
            mergedList[mergePos] = arr[rightPos];
            rightPos++;
            mergePos++;    
        }
        // Copy merge number back to numbers
        for(mergePos = 0; mergePos < mergedList.length; mergePos++){
            arr[low + mergePos] = mergedList[mergePos];        
        }
    }
    public static void MergeSort(int[] numbers, int low, int high){
        int mid = 0;
        if(low<high){ //divides until one element. once one element reached, merge back up. (recursion does it automatically but yeah)
            mid = (low + high) / 2;  //midpoint in partition.
            MergeSort(numbers, low, mid); //passing mid(j) as the last point(k)
            MergeSort(numbers, mid+1, high); //passing mid+1 as the low point(i). in recursion, it performs this after the above MergeSort() is finished.
            
            Merge(numbers, low, mid, high);
        }
    }
    
    
    
    
}
