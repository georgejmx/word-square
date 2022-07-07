package com.example.wordsquare;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    static final int MAX_SHUFFLES = 1000;

    static String INPUT_STR = "eeeeddoonnnsssrv";

    // Word square
    public static ArrayList<String> wordSquare = new ArrayList<>();

    // Potential matching characters
    public static int[] selectedChars;

    // Used characters
    public static ArrayList<Set<Integer>> visited = new ArrayList<>(
        Arrays.asList(new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>())
    );

    // Stores the set of all valid words
    public static Set<String> words = new HashSet<>();

    // Stores whether we need to break after a successful entry
    public static boolean toBreak = false;

    /* Program entry point */
    public static void main(String[] args) {
        loadWords();

        // If don't find word square then shuffle all. Ideally this would be a last resort
        int shufflesCount = 0;
        while (shufflesCount < MAX_SHUFFLES) {
            searchAllPermutations(INPUT_STR);

            // Shuffle input string and clear wordSquare before trying again
            INPUT_STR = shuffle(INPUT_STR);
            wordSquare = new ArrayList<>();
            visited = new ArrayList<>(Arrays.asList(
                    new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>()));
            shufflesCount++;
        }
        System.out.println(wordSquare);
        System.out.println(visited);
    }

    /* Find all 4 digit permutations from the given string. Uses the cartesian product with N nested for loop.
     * This could either be replaced by an off the shelf Cartesian product function of could make a manual one to
     * avoid the bad nested loops */
    private static void searchAllPermutations(String chars) {
        for (int i = 0; i < chars.length(); i++) {
            if (!visited.get(0).contains(i)) {
                for (int j = 0; j < chars.length(); j++) {
                    if (j != i && !visited.get(1).contains(j)) {
                        for (int k = 0; k < chars.length(); k++) {
                            if (k != j && !visited.get(2).contains(k)) {
                                for (int l = 0; l < chars.length(); l++) {
                                    if (l != k && !visited.get(3).contains(l)) {
                                        char[] pick = {
                                            chars.charAt(i), chars.charAt(j), chars.charAt(k), chars.charAt(l)};
                                        selectedChars = new int[]{i, j, k, l};
                                        permute("", new String(pick));
                                        if (toBreak) break;
                                    }
                                }
                            }
                            if (toBreak) break;
                        }
                    }
                    if (toBreak) {
                        toBreak = false;
                        break;
                    }
                }
            }
        }
    }

    /* Finding all permutations of a given substring that is of length the word square */
    private static void permute(String prefix, String str) {
        int n = str.length();
        if (n == 0) {
            validateWordAndWs(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                permute(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
            }
        }
    }

    /* Helper function that, for each discovered permutation, validates this is a word and can fit in word square */
    private static void validateWordAndWs(String str) {
        // If this string is not a word or if already in word square, early return
        if (!words.contains(str)) return;
        if (wordSquare.contains(str)) return;

        // Checking existing words
        if (wordSquare.size() == 0) {
            wordSquare.add(str);
            updateVisited();
        } else if (wordSquare.size() == 1) {
            if (str.charAt(0) == wordSquare.get(0).charAt(1)) {
                wordSquare.add(str);
                updateVisited();
            }
        } else if (wordSquare.size() == 2) {
            if (str.charAt(0) == wordSquare.get(0).charAt(2) && str.charAt(1) == wordSquare.get(1).charAt(2)) {
                wordSquare.add(str);
                updateVisited();
            }
        } else {
            // We are one word away from a complete square
            if (str.charAt(0) == wordSquare.get(0).charAt(3) && str.charAt(1) == wordSquare.get(1).charAt(3) &&
                    str.charAt(2) == wordSquare.get(2).charAt(3)) {
                wordSquare.add(str);
                // So we have a complete word square
                printAndReturn();
            }
        }
    }

    /* Updates visited characters */
    private static void updateVisited() {
        visited.get(0).add(selectedChars[0]);
        visited.get(1).add(selectedChars[1]);
        visited.get(2).add(selectedChars[2]);
        visited.get(3).add(selectedChars[3]);
        toBreak = true;
    }

    /* Prints the valid word square then exits program */
    private static void printAndReturn() {
        System.out.println("A valid word square is;");
        for (String s : wordSquare) System.out.print(s + '\n');
        System.exit(0);
    }

    /* Shuffle string and start process again if not found word square */
    public static String shuffle(String input) {
        List<Character> characters = new ArrayList<>();
        for (char c:input.toCharArray()) characters.add(c);
        StringBuilder output = new StringBuilder(input.length());
        while (characters.size() != 0) {
            int randPicker = (int) (Math.random()*characters.size());
            output.append(characters.remove(randPicker));
        }
        return output.toString();
    }

    /* Reading words into set */
    private static void loadWords() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("words.txt"));
            String line = reader.readLine();
            while (line != null) {
                words.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}