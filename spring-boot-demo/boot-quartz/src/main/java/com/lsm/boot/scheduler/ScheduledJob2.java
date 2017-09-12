package com.lsm.boot.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScheduledJob2 implements Job {

    private SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("yyyyMMdd HH:mm:ss");
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Thread current = Thread.currentThread();

        System.out.println("job2: The time is now " + dateFormat().format(new Date()) + "----currentThread:" + current);
    }
}
