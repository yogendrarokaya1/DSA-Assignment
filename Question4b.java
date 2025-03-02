import java.util.*;

public class Question4b {
    public static void main(String[] args) {
        // Input 1
        int[] packages1 = {1, 0, 0, 0, 0, 1};
        int[][] roads1 = {{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 5}};

        // Input 2
        int[] packages2 = {0, 0, 0, 1, 1, 0, 0, 1};
        int[][] roads2 = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {5, 6}, {5, 7}};

        // Find the minimum number of roads to traverse for Input 1
        int minRoads1 = findMinRoads(packages1, roads1);
        System.out.println("Minimum number of roads to traverse for Input 1: " + minRoads1);

        // Find the minimum number of roads to traverse for Input 2
        int minRoads2 = findMinRoads(packages2, roads2);
        System.out.println("Minimum number of roads to traverse for Input 2: " + minRoads2);
    }

    // Method to find the minimum number of roads to traverse
    public static int findMinRoads(int[] packages, int[][] roads) {
        int n = packages.length; // Number of locations
        List<List<Integer>> graph = buildGraph(n, roads); // Build the graph

        int minRoads = Integer.MAX_VALUE;

        // Try starting from each location
        for (int start = 0; start < n; start++) {
            boolean[] visited = new boolean[n]; // Track visited locations
            int roadsTraversed = 0;

            // Perform BFS to collect packages within distance 2
            roadsTraversed += bfs(start, graph, packages, visited);

            // Backtrack to the starting location
            roadsTraversed += backtrack(start, graph, visited);

            // Update the minimum number of roads
            minRoads = Math.min(minRoads, roadsTraversed);
        }

        return minRoads;
    }

    // Method to build the graph from roads
    private static List<List<Integer>> buildGraph(int n, int[][] roads) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Add edges to the graph
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        return graph;
    }

    // Method to perform BFS and collect packages within distance 2
    private static int bfs(int start, List<List<Integer>> graph, int[] packages, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        int roadsTraversed = 0;

        // Perform BFS up to distance 2
        for (int level = 0; level < 2; level++) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();

                // Collect packages at the current location
                if (packages[current] == 1) {
                    packages[current] = 0; // Mark package as collected
                }

                // Explore neighbors
                for (int neighbor : graph.get(current)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.add(neighbor);
                        roadsTraversed++; // Count roads traversed
                    }
                }
            }
        }

        return roadsTraversed;
    }

    // Method to backtrack to the starting location
    private static int backtrack(int start, List<List<Integer>> graph, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        int roadsTraversed = 0;

        // Perform BFS to backtrack
        while (!queue.isEmpty()) {
            int current = queue.poll();

            // If we reach the starting location, stop
            if (current == start) {
                break;
            }

            // Explore neighbors
            for (int neighbor : graph.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                    roadsTraversed++; // Count roads traversed
                }
            }
        }

        return roadsTraversed;
    }
}

// Output
// Minimum number of roads to traverse for Input 1: 2
// Minimum number of roads to traverse for Input 2: 3