package com.aliniribeiro.admin.api.common.scheduler;

import com.aliniribeiro.admin.api.common.exceptions.ExceptionType;
import com.aliniribeiro.admin.api.common.util.Spring;
import com.aliniribeiro.admin.api.controller.businessintelligenceintegration.BusinessIntelligenceIntegration;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class Scheduler implements Job {

    @Autowired
    private BusinessIntelligenceIntegration biIntegration;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            Spring.bean(BusinessIntelligenceIntegration.class).sendBIData();
        } catch (Exception e) {
            System.out.println("ALERT: >>>" + ExceptionType.BI_ERROR.getDescription());
        }
    }
}
