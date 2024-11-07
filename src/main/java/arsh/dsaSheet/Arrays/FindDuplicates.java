package arsh.dsaSheet.Arrays;

import java.util.*;

/**
 * The FindDuplicates class provides multiple methods to find the first duplicate element
 * in an integer array. It explores different approaches including brute force, HashSet,
 * frequency array, HashMap, sorting, and Floyd's Tortoise and Hare (Cycle Detection) algorithm.
 */
public class FindDuplicates {

    public static void main(String[] args) {
        int[] nums = {1, 4,2,1,9, 2, 5, 5,9,8,3,1,5,3}; // Example input
        System.out.println("First Duplicate Number : " + findDuplicates(nums));
    }

    /**
     * Method 1: Brute Force using Nested Loops
     * Time Complexity: O(N^2), Space Complexity: O(1)
     * - Iterates over all pairs of elements to find the first duplicate.
     * - Inefficient for large arrays but does not use additional space.
     *
     * Limitations:
     * - Very slow for large arrays due to quadratic time complexity.
     *
     * Assumptions:
     * - There may be multiple duplicates; this method returns the first found duplicate.
     *
     * @param nums The input array.
     * @return The first duplicate element, or 0 if no duplicate exists.
     */
    private static int firstDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return nums[i];
                }
            }
        }
        return 0;
    }

    /**
     * Method 2: Using HashSet
     * Time Complexity: O(N), Space Complexity: O(N)
     * - Uses a HashSet to track elements seen so far.
     * - If an element is already in the set, it is the first duplicate.
     *
     * Limitations:
     * - Requires extra space proportional to the input size, so it’s not suitable for space-constrained scenarios.
     *
     * Assumptions:
     * - Assumes all numbers are positive and within bounds of the array size.
     *
     * @param nums The input array.
     * @return The first duplicate element, or 0 if no duplicate exists.
     */
    private static int firstDuplicateUsingHashSet(int[] nums) {
        Set<Integer> st = new HashSet<>();
        for (int num : nums) {
            if (!st.add(num)) {
                return num;
            }
        }
        return 0;
    }

    /**
     * Method 3: Using Frequency Array
     * Time Complexity: O(N), Space Complexity: O(N)
     * - Creates an auxiliary array to keep count of each element's occurrences.
     * - If an element's count exceeds 1, it is the first duplicate.
     *
     * Limitations:
     * - Uses extra space for the frequency array, so not optimal for very large arrays.
     * - Only works if array elements are within the range of the array size.
     *
     * Assumptions:
     * - Assumes elements are in the range [1, n] where n is the array size.
     *
     * @param nums The input array.
     * @return The first duplicate element, or 0 if no duplicate exists.
     */
    private static int findDuplicateUsingFreq(int[] nums) {
        int[] freq = new int[nums.length + 1];
        for (int num : nums) {
            freq[num]++;
            if (freq[num] > 1) {
                return num;
            }
        }
        return 0;
    }

    /**
     * Method 4: Using HashMap
     * Time Complexity: O(N), Space Complexity: O(N)
     * - Counts occurrences of each element using a HashMap.
     * - After counting, identifies the first duplicate by checking counts.
     *
     * Limitations:
     * - Extra space requirement for HashMap, so not ideal for large arrays.
     *
     * Assumptions:
     * - Assumes elements are positive and within an expected range.
     *
     * @param nums The input array.
     * @return The first duplicate element, or 0 if no duplicate exists.
     */
    private static int findDuplicateUsingMap(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // Check for duplicates
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                return entry.getKey();
            }
        }
        return 0;
    }

    /**
     * Method 5: Using Sorting
     * Time Complexity: O(N log N), Space Complexity: O(1) if in-place sorting is used
     * - Sorts the array, then checks adjacent elements for duplicates.
     * - Note: Modifies the input array, so it may not be suitable for all cases.
     *
     * Limitations:
     * - Sorting the array alters the original order, which may not be acceptable in some cases.
     *
     * Assumptions:
     * - Assumes the input array can be modified.
     *
     * @param nums The input array.
     * @return The first duplicate element, or 0 if no duplicate exists.
     */
    private static int findDuplicateUsingSorting(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) { // Adjusted to avoid ArrayIndexOutOfBoundsException
            if (nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }
        return 0;
    }

    /**
     * Method 6: Using Floyd's Tortoise and Hare (Cycle Detection)
     * Time Complexity: O(N), Space Complexity: O(1)
     * - Uses two pointers (slow and fast) to detect a cycle in the sequence,
     *   which indicates the presence of a duplicate.
     * - After meeting inside the cycle, resets one pointer and moves both
     *   one step at a time to locate the duplicate.
     *
     * Limitations:
     * - Only works when there is exactly one duplicate in the array.
     * - Requires that all numbers in the array are within the range [1, n].
     *
     * Assumptions:
     * - Assumes there is exactly one duplicate in the array.
     * - Assumes elements are in the range [1, n], where n is the array size minus one.
     *
     * @param nums The input array.
     * @return The duplicate element, or 0 if no duplicate exists.
     */
    private static int usingTwoPointers(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];

        // Phase 1: Finding the intersection point inside the cycle
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // Phase 2: Finding the entry point of the cycle
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    /**
     * Finds all unique duplicate elements in an array.
     *
     * This method sorts the array and then iterates through it to identify duplicates,
     * adding each duplicate element only once to the list of results.
     *
     * Time Complexity: O(N log N) due to the sorting operation.
     * Space Complexity: O(D), where D is the number of unique duplicates (for storing results).
     *
     * Limitations:
     * - The input array is modified by sorting, which may not be acceptable for all use cases.
     * - This method only works for arrays that contain integers in a reasonably constrained range.
     *
     * Assumptions:
     * - The method assumes the array can be sorted in-place.
     * - The array may contain multiple duplicates, but only unique duplicates are added to the result.
     *
     * @param nums The input array of integers.
     * @return A list of unique duplicate elements in the array.
     */
    private static List<Integer> allDuplicates(int[] nums) {
        List<Integer> duplicates = new ArrayList<>();
        Arrays.sort(nums); // Sorts the array to bring duplicates together
        for (int i = 1; i < nums.length; i++) {
            // Check if current element is the same as the previous and not already in the list
            if (nums[i] == nums[i - 1] && !duplicates.contains(nums[i])) {
                duplicates.add(nums[i]);
            }
        }
        return duplicates;
    }

    /**
     * Finds all duplicates in an array.
     *
     * This method uses an in-place approach by marking elements as "seen" by negating values at specific indices.
     * If an element is encountered at an index that is already negative, it is identified as a duplicate.
     *
     * Time Complexity: O(N), where N is the length of the array, as we traverse the array once.
     * Space Complexity: O(D), where D is the number of duplicates (for storing results).
     * Note: The method uses the array itself for marking purposes, so it operates in O(1) auxiliary space.
     *
     * Limitations:
     * - Modifies the input array by changing the sign of its elements.
     * - Assumes all numbers are positive and fall within the range 1 to N (where N is the array length).
     *
     * Assumptions:
     * - The input array contains values in the range 1 to N.
     * - The array is non-empty.
     * - The caller does not require the original array to remain unchanged.
     *
     * @param arr The input array containing integers.
     * @return A list of duplicate elements in the array.
     */
    public static List<Integer> findDuplicates(int[] arr) {
        List<Integer> duplicates = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            int index = Math.abs(arr[i]) - 1;  // Get the index based on the current element's value

            // Check if the element at the calculated index is already negative
            if (arr[index] < 0 && !duplicates.contains(Math.abs(arr[i]))) {
                // If so, it's a duplicate, so add the absolute value of arr[i]
                duplicates.add(Math.abs(arr[i]));
            } else {
                // Otherwise, mark it as seen by negating the value at this index
                arr[index] = -arr[index];
            }
        }

        return duplicates;
    }
}
