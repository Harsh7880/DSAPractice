package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int [] nums = {1,3,5,1,2};
        System.out.println(findDuplicate(nums));
    }




    private static int findDuplicate(int[] nums) {
        int duplicateNumber = 0;
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Check for duplicates
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                duplicateNumber = entry.getKey();
            }
        }
        return duplicateNumber;
    }
}