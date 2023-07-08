package com.start6.personalkit.model.vo;

import lombok.Data;

@Data
public class TranslationResultVo {
    /**
     * 来源
     */
    private String src;
    /**
     * 目标
     */
    private String dst;
}