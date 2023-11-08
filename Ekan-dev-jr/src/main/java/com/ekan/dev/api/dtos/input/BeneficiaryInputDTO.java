package com.ekan.dev.api.dtos.input;


import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

@Schema(description = "Modelo de entrada para a operação POST")
public record BeneficiaryInputDTO(

        @Schema(description = "Nome", example = "Leandro")
        @NotBlank(message = "O nome não pode ser nulo ou vazio")
        String name,

        @Schema(description = "Número de telefone", example = "11947165215")
        @NotBlank(message = "O telefone não pode ser nulo ou vazio")
        String telNumber,

        @Schema(description = "Data de nascimento", example = "2001-05-30")
        @NotNull(message = "Preecnha sua data de nascimento")
        Date birthDate,

        @SchemaProperty(name = "Documentos")
        @NotEmpty(message = "Preencha com pelo menos 1 documento")
        List<DocumentInputDTO> documentInputDTOS

) {
}
