package com.ekan.dev.domain.entitys;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_document")
public class DocumentEntity {

    @Id
    @EqualsAndHashCode.Include
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NonNull
    private String typeDocument;

    @NonNull
    private String description;

    @NonNull
    private LocalDateTime inclusionDate;

    private LocalDateTime updateDate;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beneficiaryEntity", referencedColumnName = "id", nullable = false)
    private BeneficiaryEntity beneficiaryEntity;

}
