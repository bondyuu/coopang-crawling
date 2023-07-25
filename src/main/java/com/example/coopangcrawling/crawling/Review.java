package com.example.coopangcrawling.crawling;

import lombok.Getter;

@Getter
public class Review {
    private String content;

    private int score;

    private boolean isContain;

    public Review(String content, int score, boolean isContain) {
        this.content = content;
        this.score = score;
        this.isContain = isContain;
    }
}
