
---

## 📄 `_docs/api.md`

```markdown
---
title: Documentazione API
layout: doc
nav_order: 4
---

# Documentazione API REST

## Endpoint Job

### Avviare un Job

**POST** `/api/jobs/{jobName}/start`

Avvia un job specifico.

**Parametri:**
- `jobName` (path): Nome del job da eseguire
- `parameters` (query, optional): Parametri JSON per il job

**Esempio di richiesta:**

```bash
curl -X POST "http://localhost:8080/api/jobs/csvProcessingJob/start" \
  -H "Content-Type: application/json"