package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.UUID;
import org.example.model.DocumentationUnitDTO;
import org.example.model.DocumentationUnitProcedureDTO;
import org.example.model.DocumentationUnitProcedureId;
import org.example.model.EnsuingDecisionDTO;
import org.example.model.PendingDecisionDTO;
import org.example.model.ProcedureDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InheritanceTest {
  private EntityManagerFactory entityManagerFactory;

  @BeforeEach
  void init() {
    entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );
  }

  @Test
  void test() throws Exception {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();

    EnsuingDecisionDTO ensuingDecision1 = EnsuingDecisionDTO.builder().fileNumber("abc").note("note1").build();
    EnsuingDecisionDTO ensuingDecision2 = EnsuingDecisionDTO.builder().fileNumber("def").note("note2").build();

    DocumentationUnitDTO documentationUnit = DocumentationUnitDTO.builder().documentNumber("xyz").build();
    documentationUnit.getEnsuingDecisions().add(ensuingDecision1);
    documentationUnit.getEnsuingDecisions().add(ensuingDecision2);

    entityManager.persist(documentationUnit);

    entityManager.getTransaction().commit();

    entityManager.getTransaction().begin();

    documentationUnit = entityManager.find(DocumentationUnitDTO.class, documentationUnit.getId());
    documentationUnit.getEnsuingDecisions().clear();
    documentationUnit.getEnsuingDecisions().add(EnsuingDecisionDTO.builder().id(ensuingDecision1.getId()).fileNumber("abc").note("note_new").build());
    documentationUnit.getPendingDecisions().add(PendingDecisionDTO.builder().id(ensuingDecision2.getId()).fileNumber("def").note("note2").build());

    entityManager.merge(documentationUnit);

    entityManager.getTransaction().commit();

    entityManager.close();
  }
}