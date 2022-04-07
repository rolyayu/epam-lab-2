package com.epam.components;

import java.util.Collection;
import java.util.List;

public class TextService {
    private final Text text;

    public TextService(Text text) {
        this.text = text;
    }

    public Text getText() {
        return text;
    }

    public void printSentencesByTargets(List<WordTarget> targets) {
        text.getSentences().forEach(sentence -> {
            printSentence(sentence);
            targets.forEach(target -> printTargetBySentence(target,sentence));
            System.out.println();
        });
    }

    public void printSentence(Sentence sentence) {
        System.out.println(sentence.getContent());
    }


    private void printTargetBySentence(WordTarget target, Sentence sentence) {
        target.setCounter((int) sentence.getWords().stream().
                filter(word -> word.matchesTarget(target)).
                count());
        System.out.println(target);
    }

    private void printTarget(WordTarget target) {
        target.setCounter((int)text.getSentences().stream().
                map(Sentence::getWords).
                flatMap(Collection::stream).
                filter(word -> word.matchesTarget(target)).
                count());
        System.out.println(target);
    }

    public void printTargets(List<WordTarget> targets) {
        targets.forEach(this::printTarget);
    }

    public String getContent() {
        StringBuilder text = new StringBuilder();
        this.text.getSentences().forEach(sentence -> text.append(sentence.getContent()).append("\n"));
        return text.toString();
    }
}
