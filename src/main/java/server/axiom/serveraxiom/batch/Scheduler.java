package server.axiom.serveraxiom.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class Scheduler {

    private final Job parquetJob;
    private final JobLauncher jobLauncher;

    @Scheduled(cron = "* * * * * *", zone = "Asia/Seoul")
    public void scheduledRun() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters params = new JobParametersBuilder()
                .addString("JobId", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(parquetJob, params);
    }
}
