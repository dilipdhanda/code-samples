package com.others.a2.Others_Done.Binary_others;

class BinarySearch_Basic {

    int binarySearch(int arr[], int x) // return index if present or -1 is not present
    {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int index = left + (right - left) / 2;
            if (arr[index] == x)
                return index;
            if (arr[index] < x)
                left = index + 1;
            else
                right = index - 1;
        }
        return -1;
    }

    public static void main(String args[])
    {
        BinarySearch_Basic ob = new BinarySearch_Basic();
        int arr[] = { 0,1,2,3,4,5,6 };
        int n = arr.length;
        int x = 2;
        int result = ob.binarySearch(arr, x);
        if (result == -1)
            System.out.println("Not present");
        else
            System.out.println("Present at idx " + result);
    }
}
