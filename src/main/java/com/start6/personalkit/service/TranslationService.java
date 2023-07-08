package com.start6.personalkit.service;

import com.start6.personalkit.entity.TranslationContentDTO;
import com.start6.personalkit.model.vo.TranslationResultVo;

import java.util.List;
import java.util.Map;

public interface TranslationService {
    public List<TranslationResultVo> getTranslation(TranslationContentDTO translationContentDTO);
}