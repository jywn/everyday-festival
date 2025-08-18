package com.festival.everyday.core.common;

public class Tokenizer {
    public static String[] getTokens(String keyword) {
        return keyword.trim().split("\\s+");
    }
}
