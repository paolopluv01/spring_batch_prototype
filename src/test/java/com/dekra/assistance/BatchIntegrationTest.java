package com.dekra.assistance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.test.JobOperatorTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@SpringBatchTest
public class BatchIntegrationTest {

    @Autowired
    private JobOperatorTestUtils jobOperatorTestUtils;

    @Autowired
    private ComponentRepository repository;

    @BeforeEach
    public void cleanUp() {
        // Svuota il database prima di ogni test per garantire l'isolamento
        repository.deleteAll();
    }

    @Test
    public void testImportXmlJobExecution() throws Exception {
        // 1. ESECUZIONE (Act)
        // Lancia il job intero esattamente come farebbe Spring all'avvio
        var jobExecution = jobOperatorTestUtils.startJob();

        // 2. VERIFICA DELLO STATO (Assert)
        // Controlliamo che il framework abbia concluso il job senza eccezioni
        assertEquals(ExitStatus.COMPLETED.getExitCode(), jobExecution.getExitStatus().getExitCode());

        // 3. VERIFICA DEI DATI (Assert)
        // Nel nostro XML originale avevamo 3 componenti.
        // Il Processor scartava il componente di classe "C" (Sensore TPMS).
        // Ci aspettiamo quindi esattamente 2 record nel database.
        long componentiSalvati = repository.count();
        assertEquals(2, componentiSalvati, "Il database dovrebbe contenere esattamente 2 componenti filtrati.");
    }
}