// You have two points in a 2D plane, represented by the arrays x_coords and y_coords. The goal is to find
// the lexicographically pair i.e. (i, j) of points (one from each array) that are closest to each other.
// Goal:
// Determine the lexicographically pair of points with the smallest distance and smallest distance calculated
// using
// | x_coords [i] - x_coords [j]| + | y_coords [i] - y_coords [j]|
// Note that
// |x| denotes the absolute value of x.
// A pair of indices (i1, j1) is lexicographically smaller than (i2, j2) if i1 < i2 or i1 == i2 and j1 < j2.
// Input:
// x_coords: The array of x-coordinates of the points.
// y_coords: The array of y-coordinates of the points.
// Output:
// The indices of the closest pair of points.
// Input: x_coords = [1, 2, 3, 2, 4], y_coords = [2, 3, 1, 2, 3]
// Output: [0, 3]
// Explanation: Consider index 0 and index 3. The value of | x_coords [i]- x_coords [j]| + | y_coords [i]-
// y_coords [j]| is 1, which is the smallest value we can achieve.
// Time Complexity: O(n^2) 

// Space Complexity: O(1) 
 

public class Question2b {
   
        public static int[] toFindClosestPair(int[] x_coords, int[] y_coords) {
            // Initialize variables to store the result
            int[] result = new int[2]; // To store the indices (i, j) of the closest pair
            int minDistance = Integer.MAX_VALUE; // Initialize with the maximum possible value
    
            // Iterate over all pairs of indices (i, j)
            for (int i = 0; i < x_coords.length; i++) {
                for (int j = 0; j < x_coords.length; j++) {
                    // Skip if i == j (same point)
                    if (i == j) continue;
    
                    // Calculate the Manhattan distance
                    int distance = Math.abs(x_coords[i] - x_coords[j]) + Math.abs(y_coords[i] - y_coords[j]);
    
                    // Check if this distance is smaller than the current minimum
                    if (distance < minDistance) {
                        // Update the minimum distance and the result indices
                        minDistance = distance;
                        result[0] = i;
                        result[1] = j;
                    } else if (distance == minDistance) {
                        // If the distance is the same, choose the lexicographically smaller pair
                        if (i < result[0] || (i == result[0] && j < result[1])) {
                            result[0] = i;
                            result[1] = j;
                        }
                    }
                }
            }
    
            // Return the result
            return result;
        }
    
        public static void main(String[] args) {
            // Input arrays
            int[] x_coords = {1, 2, 3, 2, 4};
            int[] y_coords = {2, 3, 1, 2, 3};
    
            // Find the closest pair
            int[] closestPair = toFindClosestPair(x_coords, y_coords);
    
            // Print the result
            System.out.println("Closest Pair: [" + closestPair[0] + ", " + closestPair[1] + "]");
        }
    }

// Output
// Closest Pair: [0, 3]
