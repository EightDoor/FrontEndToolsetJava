package com.start6.personalkit.utils;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class CronRunUtil {
    /**
     * 获取n次运行时间
     *
     * @param cron
     * @param count
     * @return
     */
    public static Date[] getCronScheduleDate(String cron, Integer count) {
        if (!CronExpression.isValidExpression(cron)) {
            log.error("定时任务Cron格式不正确, Cron: " + cron);
            return null;
        }
        if (count == null || count < 1) {
            count = 1;
        }
        Date[] dates = new Date[count];
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("Caclulate Date").withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
        Date time0 = trigger.getStartTime();
        Date time1 = trigger.getFireTimeAfter(time0);
        dates[0] = time1;
        if (dates.length > 1) {
            Date timeTemp = time1;
            for (int i = 1; i < dates.length; i++) {
                timeTemp = trigger.getFireTimeAfter(timeTemp);
                if (timeTemp != null) {
                    dates[i] = timeTemp;
                } else {
                    break;
                }
            }
        }
        return dates;
    }

    /**
     * @param cronExpression cron表达式
     * @param numTimes       下几次运行时间
     * @return
     */
    public static List<String> getNextExecTime(String cronExpression, Integer numTimes) {
        List<String> list = new ArrayList<>();
        CronTriggerImpl cronTriggerImpl = new CronTriggerImpl();
        try {
            cronTriggerImpl.setCronExpression(cronExpression);
        } catch (ParseException e) {
            e.printStackTrace();
            log.error("Error parsing cron expression: ", e);
        }
        // 这个是重点，一行代码搞定
        List<Date> dates = TriggerUtils.computeFireTimes(cronTriggerImpl, null, numTimes);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Date date : dates) {
            list.add(dateFormat.format(date));
        }
        return list;
    }
}