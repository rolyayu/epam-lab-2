package com.epam.controller;

import com.epam.comparators.WordTargetsComparator;
import com.epam.components.Text;
import com.epam.components.TextService;
import com.epam.components.WordTarget;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Runner {

    /*public static final String WORD_PATTERN = "(\\+\\d{3}\\(\\d{2}\\)\\d{3}\\-\\d\\d\\-\\d\\d)?" +
            "(\\w*@\\w*\\.\\w{2,})?" +
            "([A-Z]?([a-zA-Z']*))";
    public static final String SENTENCE_PATTERN = "(((\\w*@\\w*\\.\\w{2,})?" +
            "(\\+\\d{3}\\(\\d{2}\\)\\d{3}\\-\\d\\d\\-\\d\\d)?" +
            "([A-Z]?([a-zA-Z']*))?)" +
            "[:,]?\\s?)+[.!?]";

     */

    private static final String TEXT_PATH = "files" + File.separator + "text.txt";
    private static final String TARGETS_PATH = "files" + File.separator + "targets.txt";

    private static Text text;
    private static TextService service;
    private static List<WordTarget> targets;

    public static void main(String[] args) {
        init();
        printText();
        printSentencesByTargets();
        printTargets();
        sortTargets();
        printTargets();
    }

    private static void init() {
        StringBuilder textString = new StringBuilder();
        try (BufferedReader bf = new BufferedReader(new FileReader(TEXT_PATH))) {
            bf.lines().
                    forEach(textString::append);
        } catch (IOException e) {
            System.out.println("Text reading error in init block");
        }

        text = new Text(textString.toString());
        service = new TextService(text);

        targets = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(TARGETS_PATH))) {
            targets = new ArrayList<>(bf.lines().
                    map(WordTarget::new).
                    toList());
        } catch (IOException e) {
            System.out.println("Targets reading error in init block");
        }
    }

    private static void printSentencesByTargets() {
        service.printSentencesByTargets(targets);
        System.out.println();
    }

    private static void sortTargets() {
        targets.sort(new WordTargetsComparator());
    }


    private static void printText() {
        System.out.println(service.getContent());
        System.out.println();
    }

    private static void printTargets() {
        service.printTargets(targets);
        System.out.println();
    }
}
