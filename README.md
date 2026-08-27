Spring Batch: Automotive Inventory ETL
This repository contains a didactic Spring Boot application designed to demonstrate the core concepts of Spring Batch and Spring Data JPA. It implements a robust ETL (Extract, Transform, Load) pipeline that processes a moderately complex XML catalog of automotive spare parts and persists the filtered data into a relational database.

🎯 Project Overview
The application simulates a nightly batch job for an automotive post-sales department. It reads an XML export of spare parts (containing metrics like wear rates, quality classes, and pricing), applies specific business rules to filter the inventory, and saves the valid components into an in-memory database.

Key Learning Objectives
Chunk-based Processing: Understanding how Spring Batch reads, processes, and writes data in transactional chunks rather than loading entire files into memory.

XML Unmarshalling: Mapping XML fragments to Java POJOs using StaxEventItemReader and JAXB annotations.

Database Persistence: Utilizing RepositoryItemWriter to seamlessly save processed items via Spring Data JPA.

In-Memory Testing: Configuring and querying an H2 database using the built-in web console.

Architecture: The Batch Job
The core of the application is a single Spring Batch Job consisting of one Step, which is divided into three distinct phases:

Reader (StaxEventItemReader): Reads the inventario.xml file located in the classpath. Instead of loading the entire file, it streams the XML and binds each <component> fragment to a Componente Java entity.

Processor (ItemProcessor): Applies business logic to each item. In this scenario, it filters out components with a specific quality class (e.g., ignoring class "C" parts) by returning null, preventing them from reaching the writer.

Writer (RepositoryItemWriter): Takes the chunk of processed Componente entities and persists them to the H2 database utilizing the ComponenteRepository (Spring Data JPA).

🚀 Getting Started
Prerequisites
JDK 21 installed on your machine.

Maven installed (or use the provided Maven wrapper).

git clone https://github.com/yourusername/your-repo-name.git
cd your-repo-name

Build and run the application using Maven:

./mvnw clean spring-boot:run

Running the Application
Clone the repository:
