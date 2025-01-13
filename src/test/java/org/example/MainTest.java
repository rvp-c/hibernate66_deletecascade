package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.UUID;
import org.example.model.DocumentationUnitDTO;
import org.example.model.DocumentationUnitProcedureDTO;
import org.example.model.DocumentationUnitProcedureId;
import org.example.model.ProcedureDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MainTest {
  private EntityManagerFactory entityManagerFactory;

  @BeforeEach
  void init() {
    entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );
  }

  @AfterEach
  void destroy() {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();

    List resultList = entityManager.createQuery("FROM DocumentationUnitDTO").getResultList();

    for (Object o : resultList) {
      entityManager.remove(o);
    }
    entityManager.getTransaction().commit();

    entityManagerFactory.close();
  }

  // Entities are auto-discovered, so just add them anywhere on class-path
  // Add your tests, using standard JUnit.
  @Test
  void testCreateAndDeleteWithOneEntityManager() throws Exception {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();

    DocumentationUnitDTO documentationUnit = new DocumentationUnitDTO();

    entityManager.persist(documentationUnit);

    ProcedureDTO procedure = new ProcedureDTO();

    entityManager.persist(procedure);

    DocumentationUnitProcedureDTO documentationUnitProcedure = new DocumentationUnitProcedureDTO();
    DocumentationUnitProcedureId primaryKey = new DocumentationUnitProcedureId(documentationUnit.getId(), procedure.getId());
    primaryKey.setRank(1);
    documentationUnitProcedure.setPrimaryKey(primaryKey);
    documentationUnitProcedure.setDocumentationUnit(documentationUnit);
    documentationUnitProcedure.setProcedure(procedure);

    documentationUnit.getProcedures().add(documentationUnitProcedure);

    entityManager.merge(documentationUnit);

    entityManager.getTransaction().commit();

    entityManager.getTransaction().begin();

    entityManager.remove(documentationUnit);

    entityManager.getTransaction().commit();

    entityManager.close();
  }

  @Test
  void testCreateAndDeleteWithDifferentEntityManager() throws Exception {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();

    DocumentationUnitDTO documentationUnit = new DocumentationUnitDTO();

    entityManager.persist(documentationUnit);

    ProcedureDTO procedure = new ProcedureDTO();

    entityManager.persist(procedure);

    DocumentationUnitProcedureDTO documentationUnitProcedure = new DocumentationUnitProcedureDTO();
    DocumentationUnitProcedureId primaryKey = new DocumentationUnitProcedureId(documentationUnit.getId(), procedure.getId());
    primaryKey.setRank(1);
    documentationUnitProcedure.setPrimaryKey(primaryKey);
    documentationUnitProcedure.setDocumentationUnit(documentationUnit);
    documentationUnitProcedure.setProcedure(procedure);

    documentationUnit.getProcedures().add(documentationUnitProcedure);

    entityManager.merge(documentationUnit);

    UUID docmentationUnitId = documentationUnit.getId();

    entityManager.getTransaction().commit();

    entityManager.close();

    entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();

    documentationUnit = entityManager.find(DocumentationUnitDTO.class, docmentationUnitId);
    entityManager.remove(documentationUnit);

    entityManager.getTransaction().commit();

    entityManager.close();
  }
  @Test
  void testOnlyCreateDeleteInTearDown() throws Exception {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();

    DocumentationUnitDTO documentationUnit = new DocumentationUnitDTO();

    entityManager.persist(documentationUnit);

    ProcedureDTO procedure = new ProcedureDTO();

    entityManager.persist(procedure);

    DocumentationUnitProcedureDTO documentationUnitProcedure = new DocumentationUnitProcedureDTO();
    DocumentationUnitProcedureId primaryKey = new DocumentationUnitProcedureId(documentationUnit.getId(), procedure.getId());
    primaryKey.setRank(1);
    documentationUnitProcedure.setPrimaryKey(primaryKey);
    documentationUnitProcedure.setDocumentationUnit(documentationUnit);
    documentationUnitProcedure.setProcedure(procedure);

    documentationUnit.getProcedures().add(documentationUnitProcedure);

    entityManager.merge(documentationUnit);

    entityManager.getTransaction().commit();

    entityManager.close();
  }
}