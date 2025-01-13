package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "documentation_unit_procedure")
public class DocumentationUnitProcedureDTO {

  @EmbeddedId @Builder.Default
  private DocumentationUnitProcedureId primaryKey = new DocumentationUnitProcedureId();

  @ManyToOne
  @MapsId("documentationUnitId")
  @JoinColumn(name = "documentation_unit_id")
  private DocumentationUnitDTO documentationUnit;

  @ManyToOne
  @MapsId("procedureId")
  @JoinColumn(name = "procedure_id")
  private ProcedureDTO procedure;

  @Column(insertable = false, updatable = false)
  private int rank;

  @Transient
  public int getRank() {
    return primaryKey.getRank();
  }

  @Transient
  public void setRank(int rank) {
    primaryKey.setRank(rank);
  }
}