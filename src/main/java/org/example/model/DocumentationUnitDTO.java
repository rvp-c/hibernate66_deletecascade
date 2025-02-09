package org.example.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Include;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "documentation_unit")
@SuppressWarnings(
    "java:S6539") // This class depends on many classes, because it's the key part and merging
// everything.
public class DocumentationUnitDTO {

  @Id @GeneratedValue @Include private UUID id;

  @Column(nullable = false, unique = true, updatable = false, name = "document_number")
  @NotBlank
  @Include
  private String documentNumber;

  @OneToMany(
      mappedBy = "documentationUnit",
      orphanRemoval = true,
      cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @OrderBy("rank")
  @Builder.Default
  private List<DocumentationUnitProcedureDTO> procedures = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  @JoinColumn(name = "documentation_unit_id", nullable = false)
  @Builder.Default
  @OrderBy("rank")
  private List<EnsuingDecisionDTO> ensuingDecisions = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  @JoinColumn(name = "documentation_unit_id", nullable = false)
  @Builder.Default
  @OrderBy("rank")
  private List<PendingDecisionDTO> pendingDecisions = new ArrayList<>();
}
