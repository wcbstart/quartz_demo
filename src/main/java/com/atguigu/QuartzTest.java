package com.atguigu;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTest {

    public static void main(String[] args) {
        try {
            //1.定义一个jobDetail
            JobDetail jobDetail = JobBuilder.newJob(HelloQuartz.class)
                    .withIdentity("job1","group1")
                    .usingJobData("name","sdas")
                    .build();
            //2.定义一个Trigger
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1","group1")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever()).build();
            //3.创建scheduler
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.scheduleJob(jobDetail,trigger);
            scheduler.start();
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
