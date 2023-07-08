package com.start6.personalkit.utils;

public enum TranslationLanguageEnum {
    zh("zh"),
    en("en");

    private String value;
    private
    TranslationLanguageEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}