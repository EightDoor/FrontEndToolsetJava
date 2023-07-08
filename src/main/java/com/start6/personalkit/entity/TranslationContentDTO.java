package com.start6.personalkit.entity;

import com.start6.personalkit.utils.TranslationLanguageEnum;
import lombok.Data;

@Data
public class TranslationContentDTO {
    String q;
    String from;
    String to;
}