// Question1b
// b)You have two sorted arrays of investment returns, returns1 and returns2, and a target number k. You
// want to find the kth lowest combined return that can be achieved by selecting one investment from each
// array.

// Rules:
//     .The arrays are sorted in ascending order.
//     .You can access any element in the arrays.

// Goal:
// Determine the kth lowest combined return that can be achieved.
    
// Input:
//     .returns1: The first sorted array of investment returns.
//     .returns2: The second sorted array of investment returns.
//     .k: The target index of the lowest combined return.

// Output:
//     .The kth lowest combined return that can be achieved.

// Example 1:
// Input: returns1= [2,5], returns2= [3,4], k = 2
// Output: 8

// Explanation: The 2 smallest investments are are:
// - returns1 [0] * returns2 [0] = 2 * 3 = 6
// - returns1 [0] * returns2 [1] = 2 * 4 = 8
// The 2nd smallest investment is 8.

// Example 2:
// Input: returns1= [-4,-2,0,3], returns2= [2,4], k = 6
// Output: 0
// Explanation: The 6 smallest products are:
// - returns1 [0] * returns2 [1] = (-4) * 4 = -16
// - returns1 [0] * returns2 [0] = (-4) * 2 = -8
// - returns1 [1] * returns2 [1] = (-2) * 4 = -8
// - returns1 [1] * returns2 [0] = (-2) * 2 = -4
// - returns1 [2] * returns2 [0] = 0 * 2 = 0
// - returns1 [2] * returns2 [1] = 0 * 4 = 0
// The 6th smallest investment is 0.

import java.util.PriorityQueue;
public class Question1b {
    
    public static int kthSmallestInvestment(int[] returns1, int[] returns2, int k) {
        // Edge case: If k is larger than the total number of pairs
        if (k > returns1.length * returns2.length) {
            throw new IllegalArgumentException("k is larger than the total number of pairs");
        }

        // Min-heap to store pairs (product, i, j)
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Initialize the heap with all pairs (returns1[i], returns2[0])
        for (int i = 0; i < returns1.length; i++) {
            minHeap.offer(new int[]{returns1[i] * returns2[0], i, 0});
        }

        // Pop the smallest product k times
        int result = 0;
        for (int count = 0; count < k; count++) {
            int[] smallest = minHeap.poll();
            result = smallest[0]; // Store the kth smallest product
            int i = smallest[1]; // Index in returns1
            int j = smallest[2]; // Index in returns2

            // Push the next pair (returns1[i], returns2[j + 1]) into the heap
            if (j + 1 < returns2.length) {
                minHeap.offer(new int[]{returns1[i] * returns2[j + 1], i, j + 1});
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Example 1
        int[] returns1 = {2, 5};
        int[] returns2 = {3, 4};
        int k1 = 2;
        System.out.println("Example 1: " + kthSmallestInvestment(returns1, returns2, k1)); // Output: 8

        // Example 2
        int[] returns3 = {-4, -2, 0, 3};
        int[] returns4 = {2, 4};
        int k2 = 6;
        System.out.println("Example 2: " + kthSmallestInvestment(returns3, returns4, k2)); // Output: 0
    }
}

// Output
// 8
// 0