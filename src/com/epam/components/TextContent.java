package com.epam.components;

public interface TextContent {
    String getContent();

    void addContent(TextContent content);

    boolean matchesTarget(WordTarget target);
}
