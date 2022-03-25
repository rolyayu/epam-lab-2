package com.epam.components;

public class WordTarget {

    private String value;
    private int counter;

    public WordTarget(String value) {
        this.value = value;
    }

    public int getCounter() {
        return counter;
    }

    public String getValue() {
        return value;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public String toString() {
        return "WordTarget{" +
                "value='" + value + '\'' +
                ", counter=" + counter +
                '}';
    }
}