package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "procedure")
public class ProcedureDTO {
  @Id @GeneratedValue private UUID id;

  @Include
  @Column(name = "name")
  String label;

  @Column(name = "created_at", updatable = false)
//  @CreationTimestamp
  Instant createdAt;

  @ManyToMany
  @JoinTable(
      name = "documentation_unit_procedure",
      inverseJoinColumns = @JoinColumn(name = "documentation_unit_id"),
      joinColumns = @JoinColumn(name = "procedure_id"))
  List<DocumentationUnitDTO> documentationUnits;
}
