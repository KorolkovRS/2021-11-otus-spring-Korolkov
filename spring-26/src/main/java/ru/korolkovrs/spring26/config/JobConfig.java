package ru.korolkovrs.spring26.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.database.HibernateCursorItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.korolkovrs.spring26.domain.jpa.AuthorJpa;
import ru.korolkovrs.spring26.domain.mongo.AuthorMongo;
import ru.korolkovrs.spring26.repository.AuthorRepository;

import java.util.function.Function;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class JobConfig {
    private final static int CHUNK_SIZE = 10;

    private final StepBuilderFactory stepBuilderFactory;

    private final AuthorRepository authorRepository;

    @Bean
    public Step transformAuthorStep(ItemReader<AuthorJpa> itemReader,
                                    ItemProcessor<AuthorJpa, AuthorMongo> itemProcessor,
                                    ItemWriter<AuthorMongo> itemWriter) {
        return stepBuilderFactory.get("convertAuthorStep")
                .<AuthorJpa, AuthorMongo>chunk(CHUNK_SIZE)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }

    @Bean
    public ItemReader<AuthorJpa> authorJpaItemReader() {
        return new RepositoryItemReaderBuilder<AuthorJpa>()
                .repository(authorRepository)
                .methodName("findAll")
                .build();
    }

    @Bean
    public ItemProcessor<AuthorJpa, AuthorMongo> authorItemProcessor() {
        return AuthorMongo::new;
    }

    @Bean
    public ItemWriter<AuthorMongo> authorItemWriter(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<AuthorMongo>()
                .template(mongoTemplate)
                .collection("author")
                .build();
    }

}
