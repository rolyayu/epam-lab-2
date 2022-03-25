package com.epam.components;

public class Word implements TextContent {
    private final String word;

    public Word(String word) {
        this.word = word;
    }

    @Override
    public boolean matchesTarget(WordTarget target) {
        return word.equalsIgnoreCase(target.getValue());
    }

    @Override
    public String getContent() {
        return this.word;
    }

    @Override
    public void addContent(TextContent content) {
        //doing nothing
    }
}
