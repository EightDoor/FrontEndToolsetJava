package com.start6.personalkit.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.start6.personalkit.config.PersonalkitConfig;
import com.start6.personalkit.entity.TranslationContentDTO;
import com.start6.personalkit.service.TranslationService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TranslationServiceImpl implements TranslationService {
    private final PersonalkitConfig personalkitConfig;

    public TranslationServiceImpl(PersonalkitConfig personalkitConfig) {
        this.personalkitConfig = personalkitConfig;
    }

    @Override
    public Map<String, Object> getTranslation(TranslationContentDTO translationContentDTO) {
        Map<String, Object> map = BeanUtil.beanToMap(translationContentDTO);
        String s = HttpUtil.get(personalkitConfig.getBaiduTranslationUrl(), map);
        return JSONUtil.parseObj(s);
    }
}