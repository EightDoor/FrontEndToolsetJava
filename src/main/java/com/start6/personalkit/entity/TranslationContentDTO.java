package com.start6.personalkit.entity;

import lombok.Data;

@Data
public class TranslationContentDTO {
    String q;
    String appid;
    String salt;
    String sign;
    String from;
    String to;
}