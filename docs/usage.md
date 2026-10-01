
---

## 📄 `_docs/usage.md`

```markdown
---
title: Guida d'uso
layout: doc
nav_order: 2
---

# Guida d'uso

## Concetti base di Spring Batch

Spring Batch è un framework per l'elaborazione batch di dati. I componenti principali sono:

- **Job**: Un'unità di lavoro composta da uno o più step
- **Step**: Un'unità di elaborazione che legge, processa e scrive i dati
- **ItemReader**: Legge i dati da una sorgente
- **ItemProcessor**: Elabora i singoli item
- **ItemWriter**: Scrive i risultati

## Anatomia di un Job

```java
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    
    @Bean
    public Job myJob(JobBuilder jobBuilder, Step step1) {
        return jobBuilder
            .get("myJob")
            .start(step1)
            .build();
    }
    
    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory
            .get("step1")
            .<String, String>chunk(10)
            .reader(itemReader())
            .processor(itemProcessor())
            .writer(itemWriter())
            .build();
    }
}