package com.ekan.dev.api.controllers;


import com.ekan.dev.api.dtos.input.BeneficiaryInputDTO;
import com.ekan.dev.api.dtos.output.BeneficiaryOutputDTO;
import com.ekan.dev.api.dtos.output.DocumentOutputDTO;
import com.ekan.dev.domain.services.implementation.BeneficiaryServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ekan/api/beneficiary")
@AllArgsConstructor
@Tag(name = "Benefiary")
public class BeneficiaryController {

    private final BeneficiaryServiceImpl beneficiaryService;

    @Operation(
            summary = "Criar uma novo beneficiário",
            description = "Endpoint para criação de um novo beneficiário com documentos",
            operationId = "createBeneficiary",
            responses = {
                    @ApiResponse(responseCode = "201", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BeneficiaryOutputDTO.class)))
            }
    )
    @PostMapping
    public ResponseEntity<BeneficiaryOutputDTO> create(@RequestBody @Valid BeneficiaryInputDTO beneficiaryInputDTO) {
        BeneficiaryOutputDTO buildingOutputDTO = beneficiaryService.createBeneficiaryWithDocuments(beneficiaryInputDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(buildingOutputDTO);
    }

    @Operation(
            summary = "Listar todos beneficiários",
            description = "Endpoint para listar todos beneficiários",
            operationId = "listBeneficiary",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BeneficiaryOutputDTO.class)))
            }
    )
    @GetMapping
    public ResponseEntity<Page<BeneficiaryOutputDTO>> listAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {

        Pageable pageRequest = PageRequest.of(page, size);

        Page<BeneficiaryOutputDTO> beneficiaryOutputDTOPage = beneficiaryService.findAllBeneficiarys(pageRequest);

        return ResponseEntity.status(HttpStatus.OK).body(beneficiaryOutputDTOPage);
    }

    @Operation(
            summary = "Listar documentos",
            description = "Endpoint para listar todos documentos pelo id do beneficiário",
            operationId = "listDocumentsBeneficary",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BeneficiaryOutputDTO.class)))
            }
    )
    @GetMapping("{beneficiaryId}/documents")
    public ResponseEntity<List<DocumentOutputDTO>> getDocumentsForBeneficiary(@PathVariable UUID beneficiaryId) {
        List<DocumentOutputDTO> documents = beneficiaryService.findAllDocumentsForIdOfBeneficiary(beneficiaryId);
        return ResponseEntity.status(HttpStatus.OK).body(documents);
    }

    @Operation(
            summary = "Atualizar beneficiário",
            description = "Endpoint para atualizar um beneficiário",
            operationId = "updateBeneficiary",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BeneficiaryOutputDTO.class)))
            }
    )
    @PutMapping("{beneficiaryId}")
    public ResponseEntity<BeneficiaryOutputDTO> update(@PathVariable UUID beneficiaryId, @RequestBody @Valid BeneficiaryInputDTO beneficiaryInputDTO){
        BeneficiaryOutputDTO buildingOutputDTO = beneficiaryService.updateBeneficiary(beneficiaryId, beneficiaryInputDTO);

        return ResponseEntity.status(HttpStatus.OK).body(buildingOutputDTO);
    }

    @Operation(
            summary = "Excluir uma Beneficiário",
            description = "Endpoint para excluir um beneficiário pelo seu id",
            operationId = "deleteBeneficiário",
            responses = {
                    @ApiResponse(responseCode = "204", content = @Content(mediaType = "application/json", schema = @Schema()))
            }
    )
    @DeleteMapping("{beneficiaryId}")
    public ResponseEntity<Void> delete(@PathVariable UUID beneficiaryId) {
        beneficiaryService.deleteABeneficiary(beneficiaryId);

        return ResponseEntity.noContent().build();
    }

}
