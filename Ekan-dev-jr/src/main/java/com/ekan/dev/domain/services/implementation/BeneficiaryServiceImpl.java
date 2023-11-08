package com.ekan.dev.domain.services.implementation;

import com.ekan.dev.api.dtos.input.BeneficiaryInputDTO;
import com.ekan.dev.api.dtos.output.BeneficiaryOutputDTO;
import com.ekan.dev.api.dtos.output.DocumentOutputDTO;
import com.ekan.dev.common.exceptions.ResourceNotFoundException;
import com.ekan.dev.domain.entitys.BeneficiaryEntity;
import com.ekan.dev.domain.entitys.DocumentEntity;
import com.ekan.dev.domain.repositorys.BeneficiaryRepository;
import com.ekan.dev.domain.services.BeneficiaryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BeneficiaryServiceImpl implements BeneficiaryService {

    private final BeneficiaryRepository beneficiaryRepository;

    @Override
    public BeneficiaryOutputDTO createBeneficiaryWithDocuments(BeneficiaryInputDTO beneficiaryInputDTO) {

        BeneficiaryEntity beneficiaryEntity = new BeneficiaryEntity(
                beneficiaryInputDTO.name(),
                beneficiaryInputDTO.telNumber(),
                beneficiaryInputDTO.birthDate(),
                LocalDateTime.now()
        );



        createCascadeDocumentForBeneficiary(beneficiaryInputDTO, beneficiaryEntity);

        return outputBeneficiaryAssembler(beneficiaryRepository.save(beneficiaryEntity));
    }


    @Override
    public Page<BeneficiaryOutputDTO> findAllBeneficiarys(Pageable pageRequest) {
        Page<BeneficiaryEntity> beneficiaryPage = beneficiaryRepository.findAll(pageRequest);

        return beneficiaryPage.map(this::outputBeneficiaryAssembler);
    }

    @Override
    public List<DocumentOutputDTO> findAllDocumentsForIdOfBeneficiary(UUID id) {
        BeneficiaryEntity beneficiaryEntity = beneficiaryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Beneficiário com id: " + id + " não encontrado!"));

        List<DocumentEntity> documentEntities = beneficiaryEntity.getDocumentEntity();

        return documentEntities.stream()
                .map(this::mapToDocumentOutputDTO)
                .toList();
    }

    @Override
    public BeneficiaryOutputDTO updateBeneficiary(UUID id, BeneficiaryInputDTO beneficiaryInputDTO) {

        BeneficiaryEntity beneficiaryUpdate = searchForIdEntity(id);

        beneficiaryUpdate.setName(beneficiaryInputDTO.name());
        beneficiaryUpdate.setTelNumber(beneficiaryInputDTO.telNumber());
        beneficiaryUpdate.setBirthDate(beneficiaryInputDTO.birthDate());
        beneficiaryUpdate.setUpdateDate(LocalDateTime.now());

        return outputBeneficiaryAssembler(beneficiaryRepository.save(beneficiaryUpdate));
    }

    @Override
    public void deleteABeneficiary(UUID id) {

        Optional<BeneficiaryEntity> idBeneficiary = beneficiaryRepository.findById(id);

        if (idBeneficiary.isEmpty())
            throw new ResourceNotFoundException("Beneficiário com id: " + id + " não encontrado!");

        beneficiaryRepository.deleteById(id);
    }

    @Override
    public void createCascadeDocumentForBeneficiary(BeneficiaryInputDTO beneficiaryInputDTO, BeneficiaryEntity beneficiaryEntity) {
        List<DocumentEntity> listDocuments = beneficiaryInputDTO.documentInputDTOS()
                .stream()
                .map(documentInputDTO -> new DocumentEntity(
                        documentInputDTO.typeDocument(),
                        documentInputDTO.description(),
                        LocalDateTime.now(),
                        beneficiaryEntity))
                .toList();

        beneficiaryEntity.setDocumentEntity(listDocuments);
    }


    @Override
    public BeneficiaryOutputDTO outputBeneficiaryAssembler(BeneficiaryEntity beneficiary) {
        return new BeneficiaryOutputDTO(
                beneficiary.getId(),
                beneficiary.getName(),
                beneficiary.getTelNumber(),
                beneficiary.getBirthDate(),
                beneficiary.getInclusionDate(),
                beneficiary.getUpdateDate(),
                beneficiary.getDocumentEntity().stream().map(
                        document ->
                                new DocumentOutputDTO(
                                        document.getId(),
                                        document.getTypeDocument(),
                                        document.getDescription(),
                                        document.getInclusionDate(),
                                        document.getUpdateDate())).toList());

    }

    @Override
    public DocumentOutputDTO mapToDocumentOutputDTO(DocumentEntity documentEntity) {
        return new DocumentOutputDTO(
                documentEntity.getId(),
                documentEntity.getTypeDocument(),
                documentEntity.getDescription(),
                documentEntity.getInclusionDate(),
                documentEntity.getUpdateDate()
        );
    }

    @Override
    public BeneficiaryEntity searchForIdEntity(UUID id) {
        return beneficiaryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Beneficiário com id: " + id + " não encontrado!")
        );
    }
}



