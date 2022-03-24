package com.epam.controller;

import com.epam.comparators.WordTargetsComparator;
import com.epam.components.Text;
import com.epam.components.WordTarget;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Runner {

    public static final String WORD_PATTERN = "(\\+\\d{3}\\(\\d{2}\\)\\d{3}\\-\\d\\d\\-\\d\\d)?" +
            "(\\w*@\\w*\\.\\w{2,})?" +
            "([a-zA-Z']*)?";
    public static final String SENTENCE_PATTERN = "(((\\w*@\\w*\\.\\w{2,})?" +
            "(\\+\\d{3}\\(\\d{2}\\)\\d{3}\\-\\d\\d\\-\\d\\d)?" +
            "([a-zA-Z-'\"]+)?)" +
            "[:,]?\\s?)+[.!?]";

    private static final String TEXT_PATH = "files"+ File.separator +"text.txt";
    private static final String TARGETS_PATH = "files"+ File.separator +"targets.txt";

    private static Text text;
    private static List<WordTarget> targets;

    public static void main(String[] args) {
        init();
        printText();
        printSentencesByTargets();
        setTotalCounters();
        printTargets();
        sortTargets();
        printTargets();
    }

    private static void init() {
        StringBuilder textString = new StringBuilder();
        try (BufferedReader bf = new BufferedReader(new FileReader(TEXT_PATH))) {
            bf.lines().map(String::toLowerCase).forEach(textString::append);
        } catch (IOException e) {
            System.out.println("Text reading error in init block");
        }

        text = new Text(textString.toString());

        targets = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(TARGETS_PATH))) {
            targets = new ArrayList<>(bf.lines().map(String::toLowerCase).map(WordTarget::new).toList());
        } catch (IOException e) {
            System.out.println("Targets reading error in init block");
        }
    }

    private static void printSentencesByTargets() {
        text.printTargets(targets);
        System.out.println();
    }

    private static void sortTargets() {
        targets.sort(new WordTargetsComparator());
    }

    private static void setTotalCounters() {
        targets.forEach(target -> target.setCounter(text.totalContentCounter(target)));
    }

    private static void printText() {
        System.out.println(text.getContent());
        System.out.println();
    }

    private static void printTargets() {
        targets.forEach(target -> System.out.println(target.toString()));
        System.out.println();
    }
}
