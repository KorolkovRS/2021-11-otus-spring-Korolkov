package ru.korolkovrs.spring26.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.lang.NonNull;
import ru.korolkovrs.spring26.domain.jpa.AuthorJpa;
import ru.korolkovrs.spring26.domain.mongo.AuthorMongo;
import ru.korolkovrs.spring26.repository.AuthorJpaRepository;

import java.util.List;
import java.util.Map;


@Configuration
@RequiredArgsConstructor
@Slf4j
public class JobConfig {
    private final static int CHUNK_SIZE = 2;

    private final StepBuilderFactory stepBuilderFactory;

    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job importAuthorJob(Step transformAuthorStep) {
        return jobBuilderFactory.get("importAuthorJob")
                .start(transformAuthorStep)
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        log.info("Начало работы с Author");
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        log.info("Конец работы с Author");
                    }
                })
                .build();
    }

    @Bean
    public Step transformAuthorStep(ItemReader<AuthorJpa> itemReader,
                                    ItemProcessor<AuthorJpa, AuthorMongo> itemProcessor,
                                    ItemWriter<AuthorMongo> itemWriter) {
        return stepBuilderFactory.get("convertAuthorStep")
                .<AuthorJpa, AuthorMongo>chunk(CHUNK_SIZE)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .listener(new ItemReadListener<>() {
                    @Override
                    public void beforeRead() {
                        log.info("Начало чтения AuthorJpa");
                    }

                    @Override
                    public void afterRead(AuthorJpa authorJpa) {
                        log.info("Конец чтения AuthorJpa");
                    }

                    @Override
                    public void onReadError(Exception e) {
                        log.info("Ошибка чтения AuthorJpa");
                    }
                })
                .listener(new ItemProcessListener<AuthorJpa, AuthorMongo>() {
                    @Override
                    public void beforeProcess(AuthorJpa o) {
                        log.info("Начало обработки AuthorJpa");
                    }

                    @Override
                    public void afterProcess(AuthorJpa o, AuthorMongo o2) {
                        log.info("Конец обработки AuthorJpa");
                    }

                    @Override
                    public void onProcessError(AuthorJpa authorJpa, Exception e) {
                        log.info("Конец обработки AuthorJpa");
                    }
                })
                .listener(new ItemWriteListener<AuthorMongo>() {
                    @Override
                    public void beforeWrite(List<? extends AuthorMongo> list) {
                        log.info("Начало записи AuthorMongo");
                    }

                    @Override
                    public void afterWrite(List<? extends AuthorMongo> list) {
                        log.info("Конец записи AuthorMongo");
                    }

                    @Override
                    public void onWriteError(Exception e, List<? extends AuthorMongo> list) {
                        log.info("Ошибка записи AuthorMongo");
                    }
                })
                .build();
    }

    @Bean
    public ItemReader<AuthorJpa> authorJpaItemReader(AuthorJpaRepository authorRepository) {
        return new RepositoryItemReaderBuilder<AuthorJpa>()
                .name("authorItemReader")
                .repository(authorRepository)
                .methodName("findAll")
                .sorts(Map.of("id", Sort.Direction.ASC))
                .build();
    }

    @Bean
    public ItemProcessor<AuthorJpa, AuthorMongo> authorItemProcessor() {
        return authorJpa -> {
            log.info(authorJpa.toString());
            return new AuthorMongo(authorJpa);
        };
    }

    @Bean
    public ItemWriter<AuthorMongo> authorItemWriter(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<AuthorMongo>()
                .template(mongoTemplate)
                .collection("author")
                .build();
    }
}
