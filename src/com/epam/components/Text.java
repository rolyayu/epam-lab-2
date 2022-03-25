package com.epam.components;

import com.epam.controller.Runner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Text {

    private final List<Sentence> sentences = new ArrayList<>();

    public Text(String text) {
        Pattern sentencePattern = Pattern.compile(Runner.SENTENCE_PATTERN);
        Matcher sentenceMatcher = sentencePattern.matcher(text);
        while (sentenceMatcher.find()) {
            Sentence currentWord = new Sentence(text.substring(sentenceMatcher.start(), sentenceMatcher.end()));
            sentences.add(currentWord);
        }
    }

    public void printSentencesByTargets(List<WordTarget> targets) {
        sentences.forEach(sentence -> {
            System.out.println("\n" + sentence.getContent());
            targets.forEach(target -> {
                target.setCounter((int) sentence.getWords().stream().
                        filter(word -> word.matchesTarget(target)).
                        count());
                System.out.print(target + "\s");
            });
            System.out.println();
        });
    }

    public int totalTargetCounter(WordTarget target) {
        return (int) sentences.stream().map(Sentence::getWords).flatMap(Collection::stream).
                filter(word -> word.matchesTarget(target)).count();
    }

    public String getContent() {
        StringBuilder text = new StringBuilder();
        sentences.forEach(sentence -> text.append(sentence.getContent()).append("\n"));
        return text.toString();
    }

}
