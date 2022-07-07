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
    void isWordSquareFitTestTrue() {
        INPUT_STR = "aaaabbbbccccdddd";
        wordSquare = new ArrayList<>();
        wordSquare.add("abcd");
        wordSquare.add("abcd");
        assertTrue(isWordSquareFit("bb"));
        assertTrue(isWordSquareFit("abcdabcd"));
    }

    @Test
    // Checks that an incorrect fit fails
    void isWordSquareFitTestFalse() {
        INPUT_STR = "aaaabbbbccccdddd";
        wordSquare = new ArrayList<>();
        wordSquare.add("abcd");
        wordSquare.add("abcd");
        assertTrue(!isWordSquareFit("bbb"));
        assertTrue(!isWordSquareFit("abcdabcdabcd"));
    }

    @Test
    // Checks that an empty wordsquare works
    void checkEmptyWordSquare() {
        INPUT_STR = "eeeeddoonnnsssrv";
        wordSquare = new ArrayList<>();
        assertTrue(isWordSquareFit("eden"));
        assertTrue(isWordSquareFit("need"));
    }

    @Test
    // Checks that a single filled wordsquare works
    void checkSingleWordSquare() {
        INPUT_STR = "eeeeddoonnnsssrv";
        wordSquare = new ArrayList<>();
        wordSquare.add("eden");
        assertTrue(isWordSquareFit("soon"));
    }
}
