package com.example.demo.enums;

public enum Priority {
    LOW("L"), MEDIUM("M"), HIGH("H");

    private String code;

    private Priority(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
