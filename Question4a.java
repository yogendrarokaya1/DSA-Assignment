// Write a solution to find the top 3 trending hashtags in February 2024. Every tweet may
// contain several hashtags.
// Return the result table ordered by count of hashtag, hashtag in descending order.
// The result format is in the following example.
// Explanation:
// #HappyDay: Appeared in tweet IDs 13, 14, and 17, with a total count of 3 mentions.
// #TechLife: Appeared in tweet IDs 16 and 18, with a total count of 2 mentions.
// #WorkLife: Appeared in tweet ID 15, with a total count of 1 mention.
// Note: Output table is sorted in descending order by hashtag_count and hashtag respectively.
// Time: O(n×m+klogk)

import java.util.*;

public class Question4a {
    public static void main(String[] args) {
        // Sample input data: user_id, tweet_id, tweet, tweet_date
        // Create a list to store tweet data as maps.
        List<Map<String, String>> tweets = new ArrayList<>();

        // Add sample tweets to the list using the createTweet helper method.
        tweets.add(createTweet("135", "13", "Enjoying a great start to the day. #HappyDay #MorningVibes", "2024-02-01"));
        tweets.add(createTweet("136", "14", "Another #HappyDay with good vibes! #FeelGood", "2024-02-03"));
        tweets.add(createTweet("137", "15", "Productivity peaks! #WorkLife #ProductiveDay", "2024-02-04"));
        tweets.add(createTweet("138", "16", "Exploring new tech frontiers. #TechLife #Innovation", "2024-02-04"));
        tweets.add(createTweet("139", "17", "Gratitude for today's moments. #HappyDay #Thankful", "2024-02-05"));
        tweets.add(createTweet("140", "18", "Innovation drives us. #TechLife #FutureTech", "2024-02-07"));
        tweets.add(createTweet("141", "19", "Connecting with nature's serenity. #Nature #Peaceful", "2024-02-09"));

        // Count hashtag mentions
        // Create a map to store hashtags and their counts.
        Map<String, Integer> hashtagCounts = new HashMap<>();

        // Iterate through each tweet in the list.
        for (Map<String, String> tweet : tweets) {
            // Get the tweet text from the current tweet.
            String tweetText = tweet.get("tweet");

            // Split the tweet text into individual words.
            String[] words = tweetText.split(" ");

            // Iterate through each word in the tweet.
            for (String word : words) {
                // Check if the word starts with a hashtag (#).
                if (word.startsWith("#")) {
                    // Normalize the hashtag to lowercase to avoid case sensitivity issues.
                    String hashtag = word.toLowerCase();

                    // Update the count for the hashtag in the map.
                    hashtagCounts.put(hashtag, hashtagCounts.getOrDefault(hashtag, 0) + 1);
                }
            }
        }

        // Sort by count descending, then by hashtag name
        // Convert the map entries to a list for sorting.
        List<Map.Entry<String, Integer>> sortedHashtags = new ArrayList<>(hashtagCounts.entrySet());

        // Sort the list of hashtags by count in descending order.
        // If counts are equal, sort alphabetically by hashtag name.
        sortedHashtags.sort((a, b) -> {
            int countCompare = b.getValue().compareTo(a.getValue()); // Compare counts in descending order.
            if (countCompare != 0) return countCompare;
            return a.getKey().compareTo(b.getKey()); // If counts are equal, compare hashtag names.
        });

        // Output the top 3 hashtags in the redesigned table format
        // Print the table header.
        System.out.println("+-------------+---------+");
        System.out.println("|   HASHTAG   |  COUNT  |");
        System.out.println("+-------------+---------+");

        // Iterate through the top 3 hashtags (or fewer if there are less than 3).
        for (int i = 0; i < Math.min(3, sortedHashtags.size()); i++) {
            // Get the current hashtag and its count.
            Map.Entry<String, Integer> entry = sortedHashtags.get(i);

            // Print the hashtag and its count in a formatted table row.
            System.out.printf("| %-11s | %-7d |%n", entry.getKey(), entry.getValue());
        }

        // Print the table footer.
        System.out.println("+-------------+---------+");
    }

    // Helper method to create a tweet map
    // This method creates a map representing a single tweet with user_id, tweet_id, tweet, and tweet_date.
    private static Map<String, String> createTweet(String userId, String tweetId, String tweet, String tweetDate) {
        Map<String, String> tweetMap = new HashMap<>(); // Create a new map.
        tweetMap.put("user_id", userId); // Add user_id to the map.
        tweetMap.put("tweet_id", tweetId); // Add tweet_id to the map.
        tweetMap.put("tweet", tweet); // Add tweet text to the map.
        tweetMap.put("tweet_date", tweetDate); // Add tweet date to the map.
        return tweetMap; // Return the created map.
    }
}