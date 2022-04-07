package com.epam.components;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sentence implements TextContent {

    public static final String WORD_PATTERN = "(\\+\\d{3}\\(\\d{2}\\)\\d{3}\\-\\d\\d\\-\\d\\d)?" +
            "(\\w*@\\w*\\.\\w{2,})?" +
            "([A-Z]?[-]?([a-zA-Z']*))";

    private List<TextContent> words;

    public Sentence(String sentence) {
        words = new ArrayList<>();
        initWords(sentence);
    }

    private void initWords(String sentence) {
        Pattern wordPattern = Pattern.compile(WORD_PATTERN);
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
