package com.start6.personalkit.controller;

import com.start6.personalkit.utils.CronRunUtil;
import com.start6.personalkit.utils.DateTimUtil;
import com.start6.personalkit.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/cron")
public class CronValidateController {
    @GetMapping
    public Result validateCron(@RequestParam("cron") String cron, @RequestParam("count") Integer count) throws ParseException {
        Date[] dates = CronRunUtil.getCronScheduleDate(cron, count);
        if (dates != null) {
            for (Date date : dates) {
                log.info("最后5次执行时间：" + DateTimUtil.formatDate(date));
            }
        }
        return Result.success(dates);
    }
}