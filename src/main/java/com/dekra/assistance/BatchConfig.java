package com.dekra.assistance;


import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.batch.infrastructure.item.data.RepositoryItemWriter;
import org.springframework.batch.infrastructure.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.batch.infrastructure.item.xml.StaxEventItemReader;
import org.springframework.batch.infrastructure.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.boot.ApplicationRunner;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    // 1. IL READER: Legge il file XML un fragment <component> alla volta
    @Bean
    public StaxEventItemReader<Component> reader() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(Component.class);

        return new StaxEventItemReaderBuilder<Component>()
                .name("componentXmlReader")
                .resource(new ClassPathResource("componenti.xml")) // Assicurati che il file sia in src/main/resources
                .addFragmentRootElements("{http://www.greyshield.com/schema/inventory}component")
                .unmarshaller(marshaller)
                .build();
    }

    // 2. IL PROCESSOR: Logica di business (es. convertiamo il nome in maiuscolo o filtriamo: ignoriamo i componenti di classe C restituendo null)
    @Bean
    public ItemProcessor<Component, Component> processor() {
        return item -> {
            System.out.println("Elaborazione componente: " + item.getId());
            // Logica di esempio: ignoriamo i componenti di classe C restituendo null
            if ("C".equals(item.getQualityClass())) {
                return null;
            }
            return item;
        };
    }
        // 3. IL WRITER: Cosa fare con i dati elaborati
    @Bean
    public RepositoryItemWriter<Component> writer(ComponentRepository repository) {
        return new RepositoryItemWriterBuilder<Component>() // Utilizza il builder per creare un RepositoryItemWriter
                .repository(repository)
                .methodName("save") // Il metodo del repository da invocare
                .build();
    }
    
    // 4. CONFIGURAZIONE STEP E JOB
    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager, ComponentRepository repository) {
        return new StepBuilder("elaborazioneComponentiStep", jobRepository)
                .<Component, Component>chunk(10) // Elabora blocchi di 10 elementi
                .reader(reader())
                .processor(processor())
                .writer(writer(repository))// Passa il repository corretto qui
                .transactionManager(transactionManager)// Assicurati di avere un PlatformTransactionManager configurato
                .build();
    }
    // 5. CONFIGURAZIONE JOB
    @Bean
    public Job importXmlJob(JobRepository jobRepository, Step step1) {
        return new JobBuilder("importXmlJob", jobRepository)
                .start(step1)
                .build();
    }
    // 6. VERIFICA POST-ESECUZIONE: Stampa a terminale
    @Bean
    public ApplicationRunner controllaDatabase(ComponentRepository repository) {
        return args -> {
            System.out.println("\n=========================================");
            System.out.println("VERIFICA SALVATAGGIO IN H2 (FINE BATCH):");
            System.out.println("=========================================");
            
            repository.findAll().forEach(componente -> {
                System.out.println("Presente in DB -> ID: " + componente.getId() + 
                                   " | Nome: " + componente.getName());
            });
            
            System.out.println("=========================================\n");
        };
    }
    
}
