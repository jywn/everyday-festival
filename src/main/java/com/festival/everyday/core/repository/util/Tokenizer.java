package com.festival.everyday.core.repository.util;

public class Tokenizer {
    public static String[] getTokens(String keyword) {
        return keyword.trim().split("\\s+");
    }
}
