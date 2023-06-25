package com.start6.personalkit.controller;

import com.start6.personalkit.entity.TranslationContentDTO;
import com.start6.personalkit.service.TranslationService;
import com.start6.personalkit.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/translate")
public class TranslationController {
    private final TranslationService translationService;

    public TranslationController(TranslationService translationService) {
        this.translationService = translationService;
    }

    @PostMapping("/translationContent")
    public Result translationContent(@RequestBody TranslationContentDTO translationContentDTO) {
        return Result.success(translationService.getTranslation(translationContentDTO));
    }
}