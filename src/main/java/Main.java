import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    // Word square
    public static List<String> wordSquare = new ArrayList<>();

    // Stores the set of all valid words
    public static Set<String> words = new HashSet<>();

    /* Program entry point */
    public static void main(String[] args) {
        loadWords();
        searchAllPermutations("eeeeddoonnnsssrv");
        System.out.println(wordSquare);
    }

    /* Find all 4 digit permutations from the given string */
    private static void searchAllPermutations(String chars) {
        for (int i = 0; i < chars.length(); i++) {
            for (int j = 0; j < chars.length(); j++) {
                if (j != i) {
                    for (int k = 0; k < chars.length(); k++) {
                        if (k != j) {
                            for (int l = 0; l < chars.length(); l++) {
                                if (l != k) {
                                    char[] pick = {chars.charAt(i), chars.charAt(j), chars.charAt(k), chars.charAt(l)};
                                    permute( "", new String(pick));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /* Boilerplate code for finding all permutations of a given substring */
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
        } else if (wordSquare.size() == 1) {
            if (str.charAt(0) == wordSquare.get(0).charAt(1)) {
                wordSquare.add(str);
            }
        } else if (wordSquare.size() == 2) {
            if (str.charAt(0) == wordSquare.get(0).charAt(2) && str.charAt(1) == wordSquare.get(1).charAt(2)) {
                wordSquare.add(str);
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

    /* Prints the valid word square then exits program */
    private static void printAndReturn() {
        System.out.println("A valid word square is;");
        for (String s : wordSquare) System.out.print(s + '\n');
        System.exit(1);
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
