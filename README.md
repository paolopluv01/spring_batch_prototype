# Spring Batch: Automotive Inventory ETL

Applicazione didattica Spring Boot che dimostra un flusso ETL con Spring Batch, JAXB e Spring Data JPA. Il job legge un catalogo XML di componenti automobilistici, applica una regola di filtro e salva i componenti validi in un database H2.

## Funzionalita principali

- Lettura a chunk tramite `StaxEventItemReader`, senza caricare l'intero XML in memoria.
- Deserializzazione dei fragment XML in oggetti `Component` tramite JAXB.
- Scarto dei componenti con `qualityClass` uguale a `C` nel processor.
- Persistenza dei componenti accettati tramite `RepositoryItemWriter` e `ComponentRepository`.
- Verifica dei dati salvati su H2 tramite test di integrazione.

## Gestione del namespace XML

Il file `src/main/resources/componenti.xml` utilizza il namespace predefinito:

```xml
<inventory xmlns="http://www.greyshield.com/schema/inventory">
```

Per consentire il corretto unmarshalling JAXB:

- `Component` dichiara `@XmlRootElement` con nome `component` e namespace `http://www.greyshield.com/schema/inventory`.
- Gli elementi XML mappati (`name`, `qualityClass`, `manufacturer`) riportano lo stesso namespace nelle annotazioni `@XmlElement`.
- Il reader seleziona esplicitamente i fragment tramite `addFragmentRootElements("{http://www.greyshield.com/schema/inventory}component")`.

In questo modo il reader riconosce solo gli elementi `component` appartenenti al namespace previsto, evitando che elementi con lo stesso nome ma namespace diverso vengano interpretati come componenti del catalogo.

## Struttura del job

Il job `importXmlJob` contiene uno step con tre fasi:

1. **Reader**: legge `componenti.xml` dal classpath e converte ogni fragment `component` in un oggetto `Component`.
2. **Processor**: filtra i componenti di classe `C` restituendo `null`; gli altri elementi proseguono nel flusso.
3. **Writer**: salva i componenti filtrati nel database H2.

## Test di integrazione

`BatchIntegrationTest` avvia il contesto Spring e l'intero job tramite `JobOperatorTestUtils`.

Il test:

- svuota il repository prima dell'esecuzione per garantire l'isolamento;
- verifica che il job termini con `ExitStatus.COMPLETED`;
- verifica che vengano salvati esattamente 2 componenti su 3 presenti nell'XML;
- conferma indirettamente che il componente `SENS-044`, di classe `C`, sia stato filtrato e che la lettura dei fragment XML con namespace sia avvenuta correttamente.

Esecuzione del test:

```bash
./mvnw test
```

## Avvio dell'applicazione

Prerequisiti: JDK 21 e Maven, oppure il Maven Wrapper incluso nel repository.

```bash
./mvnw clean spring-boot:run
```
