package com.ekan.dev.domain.services.implementation;

import com.ekan.dev.api.dtos.input.BeneficiaryInputDTO;
import com.ekan.dev.api.dtos.input.DocumentInputDTO;
import com.ekan.dev.api.dtos.output.BeneficiaryOutputDTO;
import com.ekan.dev.domain.entitys.BeneficiaryEntity;
import com.ekan.dev.domain.entitys.DocumentEntity;
import com.ekan.dev.domain.repositorys.BeneficiaryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;


class BeneficiaryServiceImplTest {

    @Mock
    private BeneficiaryRepository beneficiaryRepository;

    @Mock
    private BeneficiaryServiceImpl beneficiaryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        beneficiaryService = new BeneficiaryServiceImpl(beneficiaryRepository);
    }



    @Test
    @DisplayName("Deve criar um beneficiário e seus documentos com sucesso")
    void createBeneficiaryWithDocumentsCase1() {

        DocumentInputDTO documentInputDTO = new DocumentInputDTO("RG", "00000004");

        BeneficiaryInputDTO beneficiaryInput = new BeneficiaryInputDTO("Leandro", "11947165215", new Date(),
                List.of(documentInputDTO));

        BeneficiaryEntity beneficiaryEntity = new BeneficiaryEntity(
                beneficiaryInput.name(),
                beneficiaryInput.telNumber(),
                beneficiaryInput.birthDate(),
                LocalDateTime.now()
        );

        List<DocumentEntity> list = new ArrayList<>();
        DocumentEntity documentEntity = new DocumentEntity(
                "CPF",
                "1234",
                LocalDateTime.now(),
                beneficiaryEntity
        );

        list.add(documentEntity);

        beneficiaryEntity.setDocumentEntity(list);

        Mockito.when(beneficiaryRepository.save(any(BeneficiaryEntity.class))).thenReturn(beneficiaryEntity);

        BeneficiaryOutputDTO result = beneficiaryService.createBeneficiaryWithDocuments(beneficiaryInput);

        verify(beneficiaryRepository, Mockito.times(1)).save(any(BeneficiaryEntity.class));
    }
}