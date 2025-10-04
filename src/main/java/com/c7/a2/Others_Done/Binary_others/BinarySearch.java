package com.c7.a2.Others_Done.Binary_others;

public class BinarySearch {
    public static void main(String args[])
    {
        int arr1[] = {5, 6, 7, 8, 9, 10, 1, 2, 3}; // search val 3
        int n = arr1.length;
        int val = 3;
        System.out.println("Index of the element is : " + pivotedBinarySearch(arr1, n, val));
    }

    static int findPivot(int arr[], int low, int high) // 0 , 8
    {
        if (high < low)  return -1;
        if (high == low) return low;
        int mid = (low + high)/2;    // 4
        if (mid < high && arr[mid] > arr[mid + 1]) return mid; // if mid > next
        if (mid > low && arr[mid] < arr[mid - 1]) return (mid-1); // if mid < next
        if (arr[low] >= arr[mid]) return findPivot(arr, low, mid-1);
        return findPivot(arr, mid + 1, high);
    }

    static int pivotedBinarySearch(int arr[], int n, int val)
    {
        int pivot = findPivot(arr, 0, n-1); // no val needed
        if (pivot == -1) return binarySearch(arr, 0, n-1, val); // no pivot, do regular binary search
        if (arr[pivot] == val) return pivot; // found pivot
        if (arr[0] <= val) return binarySearch(arr, 0,       pivot-1, val);
        else               return binarySearch(arr, pivot+1, n-1,     val);
    }

    static int binarySearch(int arr[], int low, int high, int val)
    {
        if (high < low) return -1;
        int mid = (low + high)/2;
        if (val == arr[mid]) return mid;
        if (val > arr[mid]) return binarySearch(arr, (mid + 1), high,     val);
        else                return binarySearch(arr, low,       (mid -1), val);
    }
}
