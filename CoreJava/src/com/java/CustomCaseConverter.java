package com.java;
public class CustomCaseConverter {
    public static void main(String[] args) {
        String name = "SWarNa";
        StringBuilder transformed = new StringBuilder();

        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);

            // Apply casing rules based on index
            if (i == 0 || i==1) {
                transformed.append(Character.toLowerCase(ch)); // First letter lowercase
            } else if (i == 2 || i == 3) {
                transformed.append(Character.toUpperCase(ch)); // Next two letters uppercase
            } else if (i == 4) {
                transformed.append(Character.toLowerCase(ch)); // Fourth letter lowercase
            } else if (i == 5) {
                transformed.append(Character.toUpperCase(ch)); // Last two letters uppercase
            }
        }

        System.out.println("Output: " + transformed.toString()); // Output: swARnA
    }
}