package com.epam.components;

import com.epam.controller.Runner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Text{

    private final List<Sentence> sentences = new ArrayList<>();

    public Text(String text) {
        Pattern sentencePattern = Pattern.compile(Runner.SENTENCE_PATTERN);
        Matcher sentenceMatcher = sentencePattern.matcher(text);
        while (sentenceMatcher.find()) {
            Sentence currentWord = new Sentence(text.substring(sentenceMatcher.start(), sentenceMatcher.end()));
            sentences.add(currentWord);
        }
    }

    public void printTargets(List<WordTarget> targets) {
        for(Sentence sentence:sentences) {
            System.out.println(sentence.getContent());
            Iterator<WordTarget> wordTargetIterator = targets.iterator();
            while(wordTargetIterator.hasNext()) {
                WordTarget currentTarget = wordTargetIterator.next();
                int counter = 0;
                for (TextContent word : sentence.getWords()) {
                    if (word.getContent().equalsIgnoreCase(currentTarget.getValue())) {
                        counter++;
                    }
                }
                currentTarget.setCounter(counter);
                System.out.print(currentTarget+"\s");
            }
            System.out.println();
        }
    }

    public int totalContentCounter(WordTarget target) {
        int counter = 0;
        for(Sentence sentence:sentences) {
            for(TextContent word:sentence.getWords()) {
                if (word.getContent().equalsIgnoreCase(target.getValue())) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public String getContent() {
        StringBuilder text = new StringBuilder();
        sentences.forEach(sentence -> text.append(sentence.getContent()).append("\n"));
        return text.toString();
    }

}
