// Question 2a
// You have a team of n employees, and each employee is assigned a performance rating given in the
// integer array ratings. You want to assign rewards to these employees based on the following rules:
// Every employee must receive at least one reward.
// Employees with a higher rating must receive more rewards than their adjacent colleagues.
// Goal:
// Determine the minimum number of rewards you need to distribute to the employees.
// Input:
// ratings: The array of employee performance ratings.
// Output:
// The minimum number of rewards needed to distribute.
// Example 1:
// Input: ratings = [1, 0, 2]
// Output: 5
// Explanation: You can allocate to the first, second and third employee with 2, 1, 2 rewards respectively.
// Example 2:
// Input: ratings = [1, 2, 2]
// Output: 4
// Explanation: You can allocate to the first, second and third employee with 1, 2, 1 rewards respectively.
// The third employee gets 1 rewards because it satisfies the above two conditions.

// Time Complexity: 
// O(n)
// Space Complexity: 
// O(n)


public class Question2a {

        public static int minRewards(int[] ratings) {
            // Get the number of employees
            int n = ratings.length;
    
            // Create an array to store the rewards for each employee
            int[] rewards = new int[n];
    
            // Initialize all employees with at least 1 reward
            for (int i = 0; i < n; i++) {
                rewards[i] = 1; // Every employee must receive at least 1 reward
            }
    
            // Left-to-Right Pass:
            // Traverse the ratings array from left to right
            for (int i = 1; i < n; i++) {
                // If the current employee has a higher rating than the previous one
                if (ratings[i] > ratings[i - 1]) {
                    // Assign them one more reward than the previous employee
                    rewards[i] = rewards[i - 1] + 1;
                }
            }
    
            // Right-to-Left Pass:
            // Traverse the ratings array from right to left
            for (int i = n - 2; i >= 0; i--) {
                // If the current employee has a higher rating than the next one
                if (ratings[i] > ratings[i + 1]) {
                    // Ensure they have more rewards than the next employee
                    rewards[i] = Math.max(rewards[i], rewards[i + 1] + 1);
                }
            }
    
            // Calculate the total number of rewards required
            int totalRewards = 0;
            for (int reward : rewards) {
                totalRewards += reward; // Sum all rewards in the rewards array
            }
    
            // Return the total number of rewards
            return totalRewards;
        }
    
        public static void main(String[] args) {
            // Example 1
            int[] ratings1 = {1, 0, 2}; // Input ratings for Example 1
            System.out.println("Example 1: " + minRewards(ratings1)); // Output: 5
    
            // Example 2
            int[] ratings2 = {1, 2, 2}; // Input ratings for Example 2
            System.out.println("Example 2: " + minRewards(ratings2)); // Output: 4
        }
    }
    
// Outputs
// Example 1: 5
// Example 2: 4
