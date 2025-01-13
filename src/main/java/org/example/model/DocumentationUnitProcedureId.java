package org.example.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class DocumentationUnitProcedureId implements Serializable {
  private UUID documentationUnitId;

  private UUID procedureId;
  @Setter
  private int rank;

  public DocumentationUnitProcedureId(UUID documentationUnitId, UUID procedureId) {
    this.documentationUnitId = documentationUnitId;
    this.procedureId = procedureId;
  }
}
