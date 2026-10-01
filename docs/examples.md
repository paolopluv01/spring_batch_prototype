
---

## 📄 `_docs/examples.md`

```markdown
---
title: Esempi
layout: doc
nav_order: 3
---

# Esempi pratici

## Esempio 1: Lettura e scrittura di file CSV

Un job che legge dati da un file CSV, li elabora e scrive i risultati in un altro file.

```java
@Configuration
public class CsvJobConfiguration {
    
    @Bean
    public FlatFileItemReader<Person> csvReader() {
        return new FlatFileItemReaderBuilder<Person>()
            .name("csvReader")
            .resource(new ClassPathResource("input/persons.csv"))
            .delimited()
            .names("firstName", "lastName", "age")
            .targetType(Person.class)
            .build();
    }
    
    @Bean
    public ItemProcessor<Person, Person> csvProcessor() {
        return person -> {
            person.setAge(person.getAge() + 1);
            return person;
        };
    }
    
    @Bean
    public FlatFileItemWriter<Person> csvWriter() {
        return new FlatFileItemWriterBuilder<Person>()
            .name("csvWriter")
            .resource(new FileSystemResource("output/persons.csv"))
            .delimited()
            .names("firstName", "lastName", "age")
            .build();
    }
}
## Esempio 2: Lettura e scrittura su H2 ## 
Un job che legge dati da un database e li processa.
@Bean
public JdbcPagingItemReader<Person> databaseReader(DataSource dataSource) {
    return new JdbcPagingItemReaderBuilder<Person>()
        .name("databaseReader")
        .dataSource(dataSource)
        .selectClause("SELECT id, firstName, lastName, age")
        .fromClause("FROM persons")
        .pageSize(1000)
        .rowMapper(new PersonRowMapper())
        .build();
}