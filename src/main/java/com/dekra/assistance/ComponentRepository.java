package com.dekra.assistance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ComponentRepository extends JpaRepository<Component, String> {
    // Spring implementerà automaticamente i metodi save(), findAll(), findById(), ecc.
}