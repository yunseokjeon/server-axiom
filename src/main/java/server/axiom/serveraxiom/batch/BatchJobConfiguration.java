package server.axiom.serveraxiom.batch;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.*;


@Slf4j
@RequiredArgsConstructor
@Configuration
public class BatchJobConfiguration {

    @Value("${cloud.aws.s3.file-name}")
    private String fileName;

    @Value("${cloud.aws.s3.bucket-name}")
    private String bucketName;

    private final AmazonS3 s3Client;

    public String readFile(String bucketName, String s3Path) throws IOException {
        S3Object s3Object = s3Client.getObject(bucketName, s3Path);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        S3ObjectInputStream ois = null;
        BufferedReader br = null;

        try{
            ois = s3Object.getObjectContent();
            br = new BufferedReader (new InputStreamReader(ois, "UTF-8"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", 0);
                log.info("data = " + data[0]);
            }

            return IOUtils.toString(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(ois != null){
                ois.close();
            }
            if(br != null){
                br.close();
            }
            IOUtils.closeQuietly(inputStream, null);
        }
    }

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
            readFile(bucketName, fileName);
            return RepeatStatus.FINISHED;
        }));
    }
}
