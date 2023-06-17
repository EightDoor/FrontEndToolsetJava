package com.start6.personalkit.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class PersonalkitConfig {
    @Value("${personalkit.baiduTranslationUrl}")
    private String baiduTranslationUrl;

    @Value("${compressPath}")
    private String compressPath;
}