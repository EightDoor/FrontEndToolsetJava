package com.start6.personalkit.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.start6.personalkit.config.PersonalkitConfig;
import com.start6.personalkit.entity.TranslationContentDTO;
import com.start6.personalkit.model.vo.BaiduTranslationVo;
import com.start6.personalkit.model.vo.TranslationResultVo;
import com.start6.personalkit.service.TranslationService;
import com.start6.personalkit.utils.TranslationLanguageEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TranslationServiceImpl implements TranslationService {
    private final PersonalkitConfig personalkitConfig;

    public TranslationServiceImpl(PersonalkitConfig personalkitConfig) {
        this.personalkitConfig = personalkitConfig;
    }

    @Override
    public List<TranslationResultVo> getTranslation(TranslationContentDTO translationContentDTO) {
        // 加密
        String salt = String.valueOf(System.currentTimeMillis());
        String joinStr = personalkitConfig.getAppId() + translationContentDTO.getQ() + salt + personalkitConfig.getAppKey();
        log.info("sign组合参数: {}", joinStr);
        String sign = DigestUtils.md5DigestAsHex(joinStr.getBytes());
        Map<String, Object> map = BeanUtil.beanToMap(translationContentDTO);
        map.put("appid", personalkitConfig.getAppId());
        map.put("salt", salt);
        map.put("sign", sign);
        log.info("最终传递参数: {}", map);
        String s = HttpUtil.get(personalkitConfig.getBaiduTranslationUrl(), map);
        BaiduTranslationVo translationVo = JSONUtil.toBean(s, BaiduTranslationVo.class);
        List<TranslationResultVo> result = new ArrayList<>();
        for (BaiduTranslationVo.BaiduTransResult item: translationVo.getTrans_result()) {
            TranslationResultVo resultVo = new TranslationResultVo();
            resultVo.setDst(item.getDst());
            resultVo.setSrc(item.getSrc());
            result.add(resultVo);
        }
        return result;
    }
}