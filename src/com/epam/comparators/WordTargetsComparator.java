package com.epam.comparators;

import com.epam.components.WordTarget;

import java.util.Comparator;

public class WordTargetsComparator implements Comparator<WordTarget> {

    @Override
    public int compare(WordTarget firstTarget, WordTarget secondTarget) {
        return secondTarget.getCounter() - firstTarget.getCounter();
    }
}
