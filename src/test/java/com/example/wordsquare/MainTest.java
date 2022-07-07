package com.example.wordsquare;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.example.wordsquare.Main.isWordSquareFit;
import static com.example.wordsquare.Main.INPUT_STR;
import static com.example.wordsquare.Main.wordSquare;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {

    @Test
    // Checks that a correct fit works
    void isWordSquareFitTest() {
        INPUT_STR = "aaaabbbbccccdddd";
        wordSquare = new ArrayList<>();
        wordSquare.add("abcd");
        wordSquare.add("abcd");
        assertTrue(isWordSquareFit("bb"));
    }
}
