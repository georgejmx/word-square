# WordSquare

Attempts to solve the Word Square problem. Focuses on when *N=4*

## What is done so far

- Read all the words from file into a set
- At a granular level uses a recursive permutation algorithm to search through all permutations of strings of length 4
  - Checks if this would fit to form a valid word square after a candidate permutation has been found
- To get batches of length 4, used the Cartesian product to generate substrings from the input string
- Introduced an optimisation to keep track of visited characters, then break for loops in cartesian product function
to avoid visiting again

### Possible Optimisation that just breaks things

Adding the function;

```
/* Determining if the proposed word will fit in the square */
public static boolean isWordSquareFit(String word) {
    // Concatenating the proposed word with word square
    String charsSoFar = word;
    String reducedInput = INPUT_STR;
    for (String sWord : wordSquare) charsSoFar = charsSoFar.concat(sWord);

    // Removing all characters from input string that we have.
    int i = 0;
    while (i < charsSoFar.length()) {
        String current = String.valueOf(charsSoFar.charAt(i));
        if (!reducedInput.contains(current)) return false;
        reducedInput = reducedInput.replaceFirst(current, "");
        i++;
    }
    return true;
}
```

add a call to it somewhere in *validateWordAndWs* ideally just before the candidate word is added to the square;
`if (!isWordSquareFit(str)) return;`

This eliminates the problem I had by crossover character words added to the square however slows things down
considerably. This function has actually been tested using the file *MainTest.java*

## Possible fixes

- Restart the search process once a word has been added to the word square. Also remove such used characters from the
input string as part of this
- Generalise for any size N could easily be done once switched to using a prebuilt Cartesian product function
- Bring in that *isWordSquareFit* earlier on to avoid processing unnecessary permutations

## Build and Run

In Javaesque fashion, bang open a Jetbrains IDE