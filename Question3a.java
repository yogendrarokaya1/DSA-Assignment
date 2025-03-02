// You have a network of n devices. Each device can have its own communication module installed at a
// cost of modules [i - 1]. Alternatively, devices can communicate with each other using direct connections.
// The cost of connecting two devices is given by the array connections where each connections[j] =
// [device1j, device2j, costj] represents the cost to connect devices device1j and device2j. Connections are
// bidirectional, and there could be multiple valid connections between the same two devices with different
// costs.
// Goal:
// Determine the minimum total cost to connect all devices in the network.
// Input:
// n: The number of devices.
// modules: An array of costs to install communication modules on each device.
// connections: An array of connections, where each connection is represented as a triplet [device1j,
// device2j, costj].
// Output:
// The minimum total cost to connect all devices.
// Example:
// Input: n = 3, modules = [1, 2, 2], connections = [[1, 2, 1], [2, 3, 1]] Output: 3
// Explanation:
// The best strategy is to install a communication module on the first device with cost 1 and connect the
// other devices to it with cost 2, resulting in a total cost of 3.
// Time Complexity: 
// O(ElogE), where E is the number of edges (sorting dominates).
// Space Complexity: O(V+E), where V is the number of devices and E is the number of edges.

import java.util.*;

class Question3a {
    
    // Helper class for Union-Find (Disjoint Set)
    static class UnionFind {
        int[] parent, rank;
        
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        
        // Find the root of a node
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }
        
        // Union operation with rank optimization
        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            
            if (rootX == rootY) return false; // Already in the same set
            
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            return true;
        }
    }

    // Function to find the minimum total cost to connect all devices
    public static int minTotalCost(int n, int[] modules, int[][] connections) {
        List<int[]> edges = new ArrayList<>();
        
        // Add all given connections
        for (int[] conn : connections) {
            edges.add(new int[]{conn[0] - 1, conn[1] - 1, conn[2]}); // Convert to 0-based index
        }
        
        // Add virtual edges from a dummy node (index `n`) to each device
        for (int i = 0; i < n; i++) {
            edges.add(new int[]{n, i, modules[i]});
        }
        
        // Sort edges by cost (Greedy approach)
        edges.sort(Comparator.comparingInt(a -> a[2]));
        
        UnionFind uf = new UnionFind(n + 1); // Extra node for the dummy hub
        int totalCost = 0, edgesUsed = 0;
        
        // Apply Kruskal's algorithm
        for (int[] edge : edges) {
            if (uf.union(edge[0], edge[1])) { // If adding the edge doesn't form a cycle
                totalCost += edge[2];
                edgesUsed++;
                if (edgesUsed == n) break; // MST complete when n edges are used
            }
        }
        
        return totalCost;
    }

    // Driver code to test the function
    public static void main(String[] args) {
        int n = 3;
        int[] modules = {1, 2, 2};
        int[][] connections = {{1, 2, 1}, {2, 3, 1}};
        
        System.out.println(minTotalCost(n, modules, connections)); // Expected Output: 3
    }
}
