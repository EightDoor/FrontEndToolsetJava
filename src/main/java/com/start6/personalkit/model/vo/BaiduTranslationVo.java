package com.start6.personalkit.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class BaiduTranslationVo {
    private String from;
    private String to;
    private List<BaiduTransResult> trans_result;
    @Data
    public static class BaiduTransResult {
        private String dst;
        private String src;
    }
}