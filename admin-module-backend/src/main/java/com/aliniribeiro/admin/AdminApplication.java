package com.aliniribeiro.admin;

import com.aliniribeiro.admin.api.controller.businessintelligenceintegration.BusinessIntelligenceIntegration;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.quartz.JobBuilder.newJob;

@SpringBootApplication
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
        callQuartzScheduller();
    }

    @Bean
    public CommandLineRunner commandLineRunner(BusinessIntelligenceIntegration BiIn, PasswordEncoder passwordEncoder) {
        return args -> {
            callQuartzScheduller();
        };
    }

    /**
     * Agenda um novo JOB á cada 24 horas para enviar informações para a API de BI.
     */
    private static void callQuartzScheduller() {
        System.out.println("Iniciando Scheduller ");
        SchedulerFactory sf = new StdSchedulerFactory();
        try {

            JobDetail job = newJob(com.aliniribeiro.admin.api.common.scheduler.Scheduler.class)
                    .withIdentity("job1", "group1")
                    .build();

            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("dummyTriggerName", "group1")
                    .withSchedule(
                            SimpleScheduleBuilder.simpleSchedule()
                                    .withIntervalInSeconds(86400).repeatForever())
                    .build();

            Scheduler sched = sf.getScheduler();
            sched.start();
            sched.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}