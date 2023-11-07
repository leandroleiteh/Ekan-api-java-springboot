package com.ekan.dev.domain.entitys;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_beneficiary")
public class BeneficiaryEntity {

    @Id
    @EqualsAndHashCode.Include
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NonNull
    private String name;

    @NonNull
    private String telNumber;

    @NonNull
    private Date birthDate;

    @NonNull
    private LocalDateTime inclusionDate;

    private LocalDateTime updateDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "beneficiaryEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DocumentEntity> documentEntity;




}
