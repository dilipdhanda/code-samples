package a_1_b;
import java.util.Arrays;
public class MaximumProduct_On {

    public static int maximumProduct_On(int[] arr) {  // O(n)
        // find two max and two min, then multiple each to find max - O(n)
        // Sort and then multiply first two and last two is similar but take longer - nLog(n)
        int length = arr.length;
        if (length < 2) {
            System.out.println("No maximum exists, returning sentinel value");
            return Integer.MIN_VALUE;
        }
        int max1 = arr[0];
        int max2 = Integer.MIN_VALUE;
        int min1 = arr[0];
        int min2 = Integer.MAX_VALUE;
        for (int i = 1; i < length; i++) {
            if (arr[i] < min1) {
                min2 = min1;
                min1 = arr[i];
            } else if (arr[i] < min2) {
                min2 = arr[i];
            } else if (arr[i] > max1) {
                max2 = max1;
                max1 = arr[i];
            } else if (arr[i] > max2) {
                max2 = arr[i];
            }
        }

        int maxProduct = max1 * max2;
        int minProduct = min1 * min2;
        if (maxProduct > minProduct) {
            return maxProduct;
        } else {
            return minProduct;
        }
    }

    public static void main(String[] args) {
        // O(nLog(n)) - first sort, then find min and max straight - sort itself is nLogn
        int[] t1 = {5, 3, 2, 5, 7, 0, 1};
        System.out.println(maximumProduct_OnLogn(t1));
        System.out.println(maximumProduct_On(t1));
//        int[] t2 = {-2, -1, -3, 4, 8, 0};
//        int[] t3 = {-20, -10, 3, 9, -8};
//        System.out.println();
//        System.out.println(maximumProduct_OnLogn(t2));
//        System.out.println(maximumProduct_OnLogn(t3));
//        System.out.println();
//        System.out.println(maximumProduct_On(t2));
//        System.out.println(maximumProduct_On(t3));
    }

    public static int maximumProduct_OnLogn(int[] arr) { // O(nLog(n))
        // Sort and then multiply first two and last two - nLog(n)
        int length = arr.length;
        if (length < 2) {
            System.out.println("No maximum exists, returning sentinel value");
            return Integer.MIN_VALUE;
        }
        Arrays.sort(arr); // sort primitive array in place with average O(n*log(n)
        int maxProduct = arr[length - 2] * arr[length - 1];
        int minProduct = arr[0] * arr[1];
        if (maxProduct > minProduct) {
            return maxProduct;
        } else {
            return minProduct;
        }
    }
}
