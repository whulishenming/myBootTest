package com.lsm.boot.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScheduledJob implements Job {

    private SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("HH:mm:ss");
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Thread current = Thread.currentThread();

        System.out.println("job1: The time is now " + dateFormat().format(new Date()) + "----currentThread:" + current);
    }
}
