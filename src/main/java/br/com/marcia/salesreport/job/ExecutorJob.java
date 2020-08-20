package br.com.marcia.salesreport.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ExecutorJob {

    @Autowired
    private Job job;

    @Autowired
    private JobLauncher jobLauncher;

    public JobExecution execute() throws Exception {

        log.debug("Executando o JobLauncher");

        long nowMillis = System.currentTimeMillis();

        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(nowMillis))
                .toJobParameters();

        return jobLauncher.run(job, params);
    }

}