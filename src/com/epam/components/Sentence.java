package com.epam.components;

import com.epam.controller.Runner;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sentence implements TextContent {

    private List<TextContent> words = new ArrayList<>();

    public Sentence(String sentence) {
        Pattern wordPattern = Pattern.compile(Runner.WORD_PATTERN);
        Matcher wordMatcher = wordPattern.matcher(sentence);
        while (wordMatcher.find()) {
            Word currentWord = new Word(sentence.substring(wordMatcher.start(), wordMatcher.end()));
            addContent(currentWord);
        }
    }

    public List<TextContent> getWords() {
        return words;
    }

    @Override
    public String getContent() {
        StringBuilder sentence = new StringBuilder();
        words.forEach(word -> sentence.append(word.getContent()).append("\s"));
        return sentence.toString();
    }

    @Override
    public void addContent(TextContent content) {
        this.words.add(content);
    }

    @Override
    public boolean matchesTarget(WordTarget target) {
        return getContent().equalsIgnoreCase(target.getValue());
    }

}
