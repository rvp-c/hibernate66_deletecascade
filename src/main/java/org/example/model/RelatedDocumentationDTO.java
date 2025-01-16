package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = "related_documentation")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class RelatedDocumentationDTO {

  @Id @GeneratedValue private UUID id;

  @Column(name = "file_number")
  private String fileNumber;

  @Column(name = "dtype", updatable = false, insertable = false)
  private String type;

  @Column @NotNull private Integer rank;

  @Column(name = "document_number")
  private String documentNumber;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "document_number",
      referencedColumnName = "document_number",
      insertable = false,
      updatable = false)
  private DocumentationUnitDTO referencedDocumentationUnit;
}
