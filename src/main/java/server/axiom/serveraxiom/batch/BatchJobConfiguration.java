package server.axiom.serveraxiom.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.Task;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class BatchJobConfiguration {

    @Bean
    public Job parquetJob(JobRepository jobRepository, Step parquetStep) {
        return new JobBuilder("parquetJob", jobRepository)
                .start(parquetStep)
                .build();
    }

    @Bean
    public Step parquetStep(JobRepository jobRepository, Tasklet parquetTasklet, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("parquetStep", jobRepository)
                .tasklet(parquetTasklet, platformTransactionManager).build();
    }

    @Bean
    public Tasklet parquetTasklet() {
        return (((contribution, chunkContext) -> {
            log.info(">>> This is step1");
            return RepeatStatus.FINISHED;
        }));
    }
}
