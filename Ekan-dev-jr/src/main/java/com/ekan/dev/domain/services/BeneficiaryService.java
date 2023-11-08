package com.ekan.dev.domain.services;

import com.ekan.dev.api.dtos.input.BeneficiaryInputDTO;
import com.ekan.dev.api.dtos.output.BeneficiaryOutputDTO;
import com.ekan.dev.api.dtos.output.DocumentOutputDTO;
import com.ekan.dev.domain.entitys.BeneficiaryEntity;
import com.ekan.dev.domain.entitys.DocumentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface BeneficiaryService {

    BeneficiaryOutputDTO createBeneficiaryWithDocuments(BeneficiaryInputDTO beneficiaryInputDTO);

    Page<BeneficiaryOutputDTO> findAllBeneficiarys(Pageable pageable);

    List<DocumentOutputDTO> findAllDocumentsForIdOfBeneficiary(UUID id);

    BeneficiaryOutputDTO updateBeneficiary(UUID id, BeneficiaryInputDTO beneficiaryInputDTO);

    void deleteABeneficiary(UUID id);

    DocumentOutputDTO mapToDocumentOutputDTO(DocumentEntity documentEntity);

    BeneficiaryEntity searchForIdEntity(UUID id);

    BeneficiaryOutputDTO outputBeneficiaryAssembler(BeneficiaryEntity beneficiary);

    void createCascadeDocumentForBeneficiary(BeneficiaryInputDTO beneficiaryInputDTO, BeneficiaryEntity beneficiaryEntity);
}
