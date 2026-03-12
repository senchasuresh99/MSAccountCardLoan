package com.java;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Result {

    /*
     * Complete the 'solve' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts LONG_INTEGER_ARRAY d as parameter.
     */

    public static long solve(List<Long> d) {
    // Write your code here
Map<Long, Integer> freq = new HashMap<>();
        List<Long> filtered = new ArrayList<>();

        for (Long num : d) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
            if (freq.get(num) <= 2) {
                filtered.add(num);
            }
        }

        // Step 2: Sort the filtered list
        Collections.sort(filtered);

        // Step 3: Use a set to store unique triplets
        Set<String> triplets = new HashSet<>();
        int n = filtered.size();

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    long a = filtered.get(i);
                    long b = filtered.get(j);
                    long c = filtered.get(k);
                    if (a < b && b < c) {
                        triplets.add(a + "," + b + "," + c);
                    }
                }
            }
        }

        return triplets.size();

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int dCount = Integer.parseInt(bufferedReader.readLine().trim());

        String[] dTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Long> d = new ArrayList<>();

        for (int i = 0; i < dCount; i++) {
            long dItem = Long.parseLong(dTemp[i]);
            d.add(dItem);
        }

        long result = Result.solve(d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
