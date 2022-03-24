package com.epam.comparators;

import com.epam.components.WordTarget;

import java.util.Comparator;

public class WordTargetsComparator implements Comparator<WordTarget> {

    @Override
    public int compare(WordTarget o1, WordTarget o2) {
        return o2.getCounter()-o1.getCounter();
    }
}
