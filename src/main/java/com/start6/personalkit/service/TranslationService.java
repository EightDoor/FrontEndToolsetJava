package com.start6.personalkit.service;

import com.start6.personalkit.entity.TranslationContentDTO;

import java.util.Map;

public interface TranslationService {
    public Map<String, Object> getTranslation(TranslationContentDTO translationContentDTO);
}